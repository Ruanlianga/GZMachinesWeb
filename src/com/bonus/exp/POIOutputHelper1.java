package com.bonus.exp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import com.bonus.ma.beans.MachineBean;

@SuppressWarnings("deprecation")
public class POIOutputHelper1 {
	public static HSSFWorkbook excel(List<Map<String, Object>> result, List<String> list,String filename,String machineName,String typeName) {
		// 获取工作簿对象
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFCellStyle tittleStyle = createTittleStyle(workbook);
		HSSFCellStyle headerStyle = createHeaderStyle(workbook);
		HSSFCellStyle contentStyle = createCellStyle(workbook);
		String sheetName = filename;
		
		sheet.setDefaultColumnWidth(20);
		HSSFRow row;
		workbook.setSheetName(0, sheetName);
		HSSFCell cell;
		if (result != null && result.size() > 0) {
			int nColumn = list.size();
			row = sheet.createRow((short) 0);
			row.setHeightInPoints(30);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (nColumn - 1)));
			cell = row.createCell(0);
			cell.setCellStyle(tittleStyle);
			cell.setCellValue(machineName+typeName);
			int ii = 1;
			row = sheet.createRow((short) ii);
			row.setHeightInPoints(20);
			for (int j = 0; j < nColumn; j++) {
				cell = row.createCell((short) j);
				if (list.get(j) != null) {
					cell.setCellStyle(headerStyle);
					cell.setCellValue(list.get(j));
				} else {
					cell.setCellStyle(headerStyle);
					cell.setCellValue("");
				}
			}
			ii++;
			for (int i = 0; i < result.size(); i++) {
				Map<String, Object> resulttrow = result.get(i);
				List<Object> rowdata = map2List(resulttrow);
				row = sheet.createRow((short) ii);
				row.setHeightInPoints(15);
				for (int j = 0; j < nColumn; j++) {
					cell = row.createCell((short) j);
					if (rowdata.get(j) != null) {
						cell.setCellStyle(contentStyle);
						cell.setCellValue(rowdata.get(j).toString());
					} else {
						cell.setCellStyle(contentStyle);
						cell.setCellValue("");
					}
				}
				ii++;
			}
		} else {
			int nColumn = list.size();
			row = sheet.createRow((short) 0);
			row.setHeightInPoints(30);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (short) (nColumn - 1)));
			cell = row.createCell(0);
			cell.setCellStyle(tittleStyle);
			cell.setCellValue(machineName);
			
			int ii = 1;
			row = sheet.createRow((short) ii);
			row.setHeightInPoints(20);
			for (int j = 0; j < nColumn; j++) {
				cell = row.createCell((short) j);
				if (list.get(j) != null) {
					cell.setCellStyle(headerStyle);
					cell.setCellValue(list.get(j));
				} else {
					cell.setCellStyle(contentStyle);
					cell.setCellValue("");
				}
			}

		}
		
		return workbook;
	}
	public static List<Object> map2List(Map<String, Object> map) {
		// 将Map Key 转化为List
		List<Object> mapValuesList = new ArrayList<Object>(map.values());
		return mapValuesList;
	}
	
	public static HSSFCellStyle createTittleStyle(HSSFWorkbook workbook){
		HSSFCellStyle tittleStyle = workbook.createCellStyle();
		tittleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		tittleStyle.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 16);// 设置字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//设置加粗
		tittleStyle.setWrapText(true);
		tittleStyle.setFont(font);
		tittleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充单元格
		tittleStyle.setFillForegroundColor(HSSFColor.WHITE.index);//填充颜色
		tittleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		tittleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		tittleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		tittleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		return tittleStyle;
	}
	public static HSSFCellStyle createHeaderStyle(HSSFWorkbook workbook){
		HSSFCellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		headerStyle.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short)12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		font.setColor(HSSFColor.GREEN.index);    //绿字
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerStyle.setFont(font);
		return headerStyle;
	}
	
	public static  HSSFCellStyle createCellStyle(HSSFWorkbook workbook){
		HSSFCellStyle contentStyle = workbook.createCellStyle();
		contentStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		contentStyle.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
		contentStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		contentStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short)10);
		font.setColor(HSSFColor.BLACK.index);   //黑字
		contentStyle.setFont(font);
		return contentStyle;
	}

}
