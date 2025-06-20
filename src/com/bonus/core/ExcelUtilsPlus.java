package com.bonus.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


/**
 * 导出表格工具类
 * @author lvjilong
 *
 */
public class ExcelUtilsPlus {  
	
	 private static Logger logger=Logger.getLogger(ExcelUtils.class);
    //excel默认宽度；  
    private static int width = 256*14;  
    //默认字体  
    private static String excelfont = "微软雅黑";  
      
    /** 
     *  
     * @param excelName  导出的EXCEL名字 
     * @param sheetName  导出的SHEET名字  当前sheet数目只为1 
     * @param headers      导出的表格的表头 
     * @param ds_titles      导出的数据 map.get(key) 对应的 key 
     * @param ds_format    导出数据的样式 
     *                          1:String left;  
     *                          2:String center    
     *                          3:String right 
     *                          4 int  right 
     *                          5:float ###,###.## right  
     *                          6:number: #.00% 百分比 right 
     * @param widths      表格的列宽度  默认为 256*14 
     * @param data        数据集  List<Map> 
     * @param response 
     * @throws IOException 
     */  
    @SuppressWarnings("deprecation")
	public static void export(String excelName, String sheetName,String[] headers,String[] ds_titles,int[] ds_format,int[] widths, List<Map<String,Object>> data ,HttpServletRequest request, HttpServletResponse response) throws IOException {  
        HttpSession session = request.getSession();    
        session.setAttribute("state", null);    
        if(widths==null){  
              widths = new int[ds_titles.length];  
              for(int i=0;i<ds_titles.length;i++){  
                  widths[i]=width;  
              }  
          }  
          if(ds_format==null){  
              ds_format = new int[ds_titles.length];  
              for(int i=0;i<ds_titles.length;i++){  
                  ds_format[i]=1;  
              }  
          }  
           //设置文件名  
            String fileName = "";  
            if(StringHelper.isNotEmpty(excelName)){  
                fileName = excelName;  
            }  
            //创建一个工作薄  
            @SuppressWarnings("resource")
			HSSFWorkbook wb = new HSSFWorkbook();  
            //创建一个sheet  
            HSSFSheet  sheet = wb.createSheet(StringHelper.isNotEmpty(sheetName)?sheetName:"excel");  
            //创建表头，如果没有跳过  
            int headerrow = 0;  
            if(headers!=null){  
                HSSFRow  row = sheet.createRow(headerrow);  
                //表头样式  
                HSSFCellStyle style = wb.createCellStyle();    
                HSSFFont font = wb.createFont();  
                font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
                font.setFontName(excelfont);  
                font.setFontHeightInPoints((short) 11);  
                style.setFont(font);  
                style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
                style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
                style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
                style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
                style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
                 for (int i = 0; i < headers.length; i++) {    
                    sheet.setColumnWidth((short)i,(short)widths[i]);   
                    HSSFCell cell = row.createCell(i);    
                    cell.setCellValue(headers[i]);    
                    cell.setCellStyle(style);    
                }    
                headerrow++;  
            }  
            //表格主体  解析list  
            if(data != null){  
                List<HSSFCellStyle> styleList = new ArrayList<HSSFCellStyle>();  
                  
                for (int i = 0; i <ds_titles.length; i++) {  //列数  
                    HSSFCellStyle style = wb.createCellStyle();    
                    HSSFFont font = wb.createFont();  
                    font.setFontName(excelfont);  
                    font.setFontHeightInPoints((short) 10);  
                    style.setFont(font);  
                    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
                    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
                    style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
                    style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
                    if(ds_format[i]==1){  
                        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);    
                    }else if(ds_format[i]==2){  
                        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
                    }else if(ds_format[i]==3){  
                        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);   
                         //int类型  
                    }else if(ds_format[i]==4){  
                        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);   
                         //int类型  
                        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));     
                    }else if(ds_format[i]==5){  
                        //float类型  
                        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);   
                        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));     
                    }else if(ds_format[i]==6){  
                        //百分比类型  
                        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);   
                        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));    
                    }  
                    styleList.add(style);  
                }  
                for (int i = 0; i < data.size() ; i++) {  //行数  
                    HSSFRow  row = sheet.createRow(headerrow);  
                    Map<?, ?> map = data.get(i);  
                    for (int j = 0; j <ds_titles.length; j++) {  //列数  
                         HSSFCell cell = row.createCell(j);    
                         Object o = map.get(ds_titles[j]);  
                         if(o==null||"".equals(o)){  
                             cell.setCellValue("");  
                         }else if(ds_format[j]==4){  
                             //int  
                             cell.setCellValue((Long.valueOf((map.get(ds_titles[j]))+"")).longValue());   
                         }else if(ds_format[j]==5|| ds_format[j]==6){  
                             //float  
                             cell.setCellValue((Double.valueOf((map.get(ds_titles[j]))+"")).doubleValue());   
                         }else {  
                             cell.setCellValue(map.get(ds_titles[j])+"");   
                         }  
                           
                         cell.setCellStyle((HSSFCellStyle)styleList.get(j));    
                    }  
                    headerrow++;  
                }  
            }  
             
            fileName=fileName+".xls";  
            String filename = "";  
            try{  
               filename =encodeChineseDownloadFileName(request,fileName);  
            }catch(Exception e){  
                e.printStackTrace();  
            }  
            response.setHeader("Content-disposition", filename);  
            response.setContentType("application/vnd.ms-excel");    
            response.setHeader("Content-disposition", "attachment;filename="+filename);    
            response.setHeader("Pragma", "No-cache");  
            OutputStream ouputStream = response.getOutputStream();    
            wb.write(ouputStream);    
            ouputStream.flush();    
            ouputStream.close();  
              
    }  
      
    /**  
             * 对文件流输出下载的中文文件名进行编码 屏蔽各种浏览器版本的差异性  
     * @throws UnsupportedEncodingException   
     */    
    public static String encodeChineseDownloadFileName(    
            HttpServletRequest request, String pFileName) throws Exception {    
         String filename = null;      
            String agent = request.getHeader("USER-AGENT");  
            logger.warn("encodeChineseDownloadFileName="+agent);
            if (null != agent){      
            	  if(StringHelper.contains(agent, "MSIE") || StringHelper.contains(agent, "like Gecko")){//IE浏览器  
            		  logger.warn("encodeChineseDownloadFileName=IE");
            		  filename = URLEncoder.encode(pFileName,"UTF-8");  
                } else if(StringHelper.contains(agent, "Mozilla")){//google,火狐浏览器  
                	filename = new String(pFileName.getBytes(), "ISO8859-1");  
                } else {//其他     
                    filename = URLEncoder.encode(pFileName, "UTF-8");      
                }      
            } else {      
                filename = pFileName;      
            }      
            return filename;     
    }
    
    public static void createExcel(String[] strArray,String path) {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("登记表");
        sheet.setDefaultColumnWidth(20);// 默认列宽
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        
        HSSFDataFormat format = wb.createDataFormat();
        style.setDataFormat(format.getFormat("@"));
        
        // 创建一个居中格式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 添加excel title
        
        HSSFCell cell = null;
        for (int i = 0; i < strArray.length+1; i++) {
        	if(i==0) {
        		 cell = row.createCell(i);
                 cell.setCellValue("序号");
                 cell.setCellStyle(style);
        	}else {
                cell = row.createCell(i);
                cell.setCellValue(strArray[i-1]);
                cell.setCellStyle(style);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        	}
        }
        
        for(int i = 1; i < 200 ; i++){
        	row = sheet.createRow((int) i);
        	 for (int j = 0; j < strArray.length+1; j++) {
                 cell = row.createCell(j);
                 cell.setCellStyle(style);
                 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
             }
        }
        
        FileOutputStream fout;
		try {
			fout = new FileOutputStream(path);
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    public static List<List<String>> extractExcelFileInfo(String filePath) {
		List<List<String>> info =new ArrayList<List<String>>();
		List<String> list = null;
    	InputStream is;
		try {
			is = new FileInputStream(filePath);
			Workbook wk = WorkbookFactory.create(is);
			
			Sheet st = wk.getSheetAt(0);
			
			int rows = st.getPhysicalNumberOfRows();
			
			int coloumNum=st.getRow(1).getPhysicalNumberOfCells();
			
			Row row = null;
			
			Cell cell = null;
			
			boolean flag = false;
			for(int i = 1;i<rows;i++) {
				list = new ArrayList<String>();
				
				row = st.getRow(i);
				
				if(row != null) {
					for(int j = 1;j<coloumNum;j++) {
						 
						 String cellValue = "";
						 
						 cell = row.getCell(j);
					
						 cellValue = getCellValueByCell(cell);
						 
						 if(cell != null) {
							 if("".equals(cell.toString())){
								 flag = true;
								 break;
							 }
							 cell.setCellValue(cellValue);
							 list.add(cell.toString());
						 }else {
							 list.add(null);
						 }
					}
				}
				
//				  if(flag){ break; }
				 
				info.add(list);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
    }
    
  //获取单元格各类型值，返回字符串类型
    public static String getCellValueByCell(Cell cell) {
        //判断是否为null或空串
        if (cell==null || cell.toString().trim().equals("")) {
            return "";
        }
        String cellValue = "";
        int cellType=cell.getCellType();
        switch (cellType) {
        case Cell.CELL_TYPE_NUMERIC: // 数字
            short format = cell.getCellStyle().getDataFormat();
            if (DateUtil.isCellDateFormatted(cell)) {
                SimpleDateFormat sdf = null;  
                if (format == 20 || format == 32) {  
                    sdf = new SimpleDateFormat("HH:mm");  
                } else if (format == 14 || format == 31 || format == 57 || format == 58) {  
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)  
                    sdf = new SimpleDateFormat("yyyy-MM-dd");  
                    double value = cell.getNumericCellValue();  
                    Date date = org.apache.poi.ss.usermodel.DateUtil  
                            .getJavaDate(value);  
                    cellValue = sdf.format(date);  
                }else {// 日期  
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                }  
                try {
                    cellValue = sdf.format(cell.getDateCellValue());// 日期
                } catch (Exception e) {
                    try {
                        throw new Exception("exception on get date data !".concat(e.toString()));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }finally{
                    sdf = null;
                }
            }  else {
                BigDecimal bd = new BigDecimal(cell.getNumericCellValue()); 
                cellValue = bd.toPlainString();// 数值 这种用BigDecimal包装再获取plainString，可以防止获取到科学计数值
            }
            break;
        case Cell.CELL_TYPE_STRING: // 字符串
            cellValue = cell.getStringCellValue();
            break;
        case Cell.CELL_TYPE_BOOLEAN: // Boolean
            cellValue = cell.getBooleanCellValue()+"";;
            break;
        case Cell.CELL_TYPE_FORMULA: // 公式
            cellValue = cell.getCellFormula();
            break;
        case Cell.CELL_TYPE_BLANK: // 空值
            cellValue = "";
            break;
        case Cell.CELL_TYPE_ERROR: // 故障
            cellValue = "ERROR VALUE";
            break;
        default:
            cellValue = "UNKNOW VALUE";
            break;
        }
        return cellValue;
    }
}  