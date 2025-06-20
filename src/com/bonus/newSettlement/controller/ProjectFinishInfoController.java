package com.bonus.newSettlement.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.FileToZip;
import com.bonus.core.StringHelper;
import com.bonus.newSettlement.beans.MaTypeProjectStorageBean;
import com.bonus.newSettlement.beans.ProjectFinishInfoBean;
import com.bonus.newSettlement.beans.ProjectSettlementInfoBean;
import com.bonus.newSettlement.service.MaTypeProjectStorageService;
import com.bonus.newSettlement.service.ProjectFinishInfoService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;
import com.bonus.sys.dao.FileUploadInfoDao;
import com.bonus.sys.service.FileUploadService;
import com.bonus.sys.service.UserService;

@Controller
@RequestMapping("/backstage/projectFinish/")
public class ProjectFinishInfoController extends BaseController<ProjectFinishInfoBean> {

	@Autowired
	FileUploadInfoDao dao;

	@Autowired
	FileUploadService fservice;

	@Autowired
	ProjectFinishInfoService service;

	@Autowired
	MaTypeProjectStorageService psService;

	@Autowired
	UserService userService;
	
	@RequestMapping("list")
	public String settlementFinishList(Model model) {

		return "/newSettlement/projectFinishManagement";
	}

	@RequestMapping("uploadFileUrl")
	public String uploadFile(Model model) {
		return "/newSettlement/newSettlementFile";
	}
	
	

	/**
	 * @Author js
	 * @Date 2020-06-11
	 * @function 工程结算列表
	 * @param o
	 * @return
	 */
	@RequestMapping("findByPage")
	public String getSettlementList(@RequestBody Page<ProjectFinishInfoBean> page, ProjectFinishInfoBean o,
			Model model) {
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o = page.getObj();
		    o.setOrgId(companyId);
			page = service.findByPage(o, page);
			model.addAttribute("page", page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/newSettlement/projectFinishList";
	}

	/**
	 * @Author js
	 * @Date 2020-06-11
	 * @function 跳转新增结算页面
	 * @param o
	 * @return
	 */
	@RequestMapping("addPage")
	public String addPage(Model model) {

		return "/newSettlement/addProjectFinish";
	}
	/* 
	 * 工程完结文件上传
	 */
	@RequestMapping("uploadProFile")
	public String imgSllt(Model model, HttpServletRequest req) {
		return "/newSettlement/uploadProFile";
	}

	
	/**
	 * @Author js
	 * @Date 2021-10-07
	 * @function 工程完结列表
	 * @param o
	 * @return
	 */
	@RequestMapping("findUnSltMaTypeList")
	public String findUnSltMaTypeList(@RequestBody MaTypeProjectStorageBean o, Model model) {
		try {
			List<MaTypeProjectStorageBean> list = psService.findUnFinishMaTypeList(o);
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/newSettlement/unFinishMaTypeList";
	}
	
	/**
	 * 创建工程完结
	 * 
	 * @param page
	 * @param o
	 * @return
	 * @date 2021-10-07
	 * @author js
	 */
	@RequestMapping(value = "saveFinishInfo", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes insertSlt(@RequestBody ProjectFinishInfoBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			int a = service.saveFinishInfo(o);
			if (a > 0) {
				ar.setSucceedMsg("工程完结添加成功!");
			}

		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg("新增失败!");
		}
		return ar;
	}

	
	/**
	 * @Author js
	 * @Date 2020-06-12
	 * @function 查看结算列表
	 * @param o
	 * @return
	 */
	@RequestMapping("view")
	public String viewSettlementInfo(ProjectFinishInfoBean o, Model model) {
		try {
			ProjectFinishInfoBean bean = service.findSettlementInfoById(o);
		
			model.addAttribute("bean", bean);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/newSettlement/viewFinishInfo";
	}
	
	/**
	 * 上传工程完结文件
	 * @param file
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "uploadImgSllt")
	@ResponseBody
    public Map<String,Object> upload(MultipartFile file,ProjectFinishInfoBean o,HttpServletRequest request){
        String prefix="";
        String dateStr="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
            	UserBean user = UserShiroHelper.getRealCurrentUser();
			    String uploadUser = user.getId().toString();
//            	String ids = request.getParameter("id");
            	String code = request.getParameter("code");
//			    Integer id =Integer.valueOf(ids).intValue();
//				o.setId(id);
				o.setCode(code);
				o.setUploadUser(uploadUser);
				o.setUploadTime(DateTimeHelper.getNowTime());	 
					
                String tempName = file.getOriginalFilename();
                if(StringHelper.isEmpty(tempName)) {
                	return null;
                }
                String suffix = tempName.substring(tempName.lastIndexOf("."));
                if(".jpg".equals(suffix) || ".png".equals(suffix) ||".jpeg".equals(suffix)) {
                	suffix = ".png";
                }else if(".docx".equals(suffix) || ".doc".equals(suffix)) {
                	suffix = ".docx";
                }else if(".xlsx".equals(suffix) || ".xls".equals(suffix)) {
                	suffix = ".xlsx";
                }else if(".pdf".equals(suffix)) {
                	suffix = ".pdf";
                }
                String fileName = DateTimeHelper.getNowDate().replace("-","")+"_"+generateShortUuid() + "_proFinish" + suffix;
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                //String filepath = "D:\\mycode\\machine\\images\\" + dateStr+"\\"+fileName;
//                String filePath = request.getSession().getServletContext().getRealPath("/ProjectFinishInfoFile")+generateShortUuid();
                String mkdirsName = "ProjectFinishInfoFile"; // 机具管理-机具类型管理
         		String filePath = "/data/gzimt/" + mkdirsName + "/"; // linux 系统路径
                 String os = System.getProperty("os.name");
                 if (os.toLowerCase().startsWith("win")) {
                	 filePath = "e://GZMachinesWeb/" + mkdirsName + "/";
                 }

                 File files = new File(filePath);
                 if(!files.exists()){
                     files.mkdirs();
                 }
                 
                 
//                 File files = new File(filePath);
//                 if(!files.getParentFile().exists()){
//                     files.getParentFile().mkdirs();
//                 }
                file.transferTo(files);
                //保存文件名文件路径
                o.setFileName(fileName);
                o.setFileUrl(filePath);
                o.setFileOldName(tempName);
                List<ProjectFinishInfoBean> list = new ArrayList<ProjectFinishInfoBean>();
                service.insertFile(o);		
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("fileName",fileName);
                map.put("data",map2);
                map2.put("src","/proFinishFile/"+ dateStr+"/"+fileName);
                return map;
            }

        }catch (Exception e){
        	logger.error(e.toString(),e);
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;
    }
	
//	@RequestMapping(value = "downProFile")
//	public void ShowImg(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id, @RequestParam("code") String code)
//			throws IOException {
//		 String sourceFilePath = "/data/gz_real_name/dzhtZip/";//文件地址
//	        String zipFilePath = "/data/gz_real_name/";
//	        String osName = System.getProperty("os.name");
//	        if (osName.toLowerCase().startsWith("win")) {
//	            sourceFilePath = "C:\\dzhtZip";
//	            zipFilePath = "C:\\";
//	        }
//	        FileToZip.delAllFile(sourceFilePath);//删除目录下面文件
//	        //判断文件包是否存在
//	        File file = new File(sourceFilePath);
//	        if(!file.exists()) {
//	            file.mkdirs();
//	        }
//	        List<ProjectFinishInfoBean> list=service.getProFInishFiels(id);
//	        String zipName=code+"工程完结";
//
//	        String photoName=sourceFilePath+"/"+code+".png";//图片文件名称
//	        
//
//	        if (osName.toLowerCase().startsWith("win")) {
//	            photoName=sourceFilePath+"//"+code+".png";//图片文件名称
//	        }
//
//	        String ipUrl="http://112.31.106.186:1917/gzrmw/";
//	        boolean photoExp= httpDownload(ipUrl+list.get(0).getFileUrl(), photoName);
//	        boolean flag = FileToZip.fileToZip(sourceFilePath, zipFilePath, zipName);
//	        if(flag){
//	            System.out.println("文件打包成功!");
//	            try {
//	                zipName+=".zip";
//	                String url = sourceFilePath+zipName;
//	                if (osName.toLowerCase().startsWith("win")) {
//	                    url=sourceFilePath+"\\"+zipName;
//	                }
//	                System.err.print(url);
//	                //downloadPicture(url,zipFilePath);   下载的方法
//	                InputStream docStream = new FileInputStream(new File(url));
//	                ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
//	                byte[] buff = new byte[10000]; //buff用于存放循环读取的临时数据
//	                int rc = 0;
//	                while ((rc = docStream.read(buff, 0, 10000)) > 0) {
//	                    swapStream.write(buff, 0, rc);
//	                }
//	                //将输入流转换为字符数组输出流
//	                byte[] docByte = swapStream.toByteArray();
//	                //设置响应头
//	                res.setContentType("application/x-msdownload; charset=utf-8");
//	                res.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(zipName));
//	                OutputStream os = res.getOutputStream();
//	                os.write(docByte);
//	                os.flush();
//	                os.close();
//	            }catch(Exception e) {
//	                System.err.print(e);
//	            }
//	        }
//
//	}
	
	@RequestMapping(value = "downloadFile")
	@ResponseBody
	public void plistDownLoad(HttpServletRequest request, HttpServletResponse response, ProjectFinishInfoBean bean) throws Exception {
    List<ProjectFinishInfoBean> list =service.getProFinishFiles(bean);
    if (list.size() != 0) {
    	String sourceFilePath = "/data/gz_imw/proFinishFile/";//文件地址
        String zipFilePath = "/data/gz_imw/";
        
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().startsWith("win")) {
            sourceFilePath = "D:\\proFinishFile\\";
            zipFilePath = "D:\\";
        }
        FileToZip.delAllFile(sourceFilePath);//删除目录下面文件
        //判断文件包是否存在
        File file = new File(sourceFilePath);
        if(!file.exists()) {
            file.mkdirs();
        }
        
        String zipName="工程完结";
        for (int i = 0;i<list.size();i++) {
            String path = list.get(i).getFileUrl();
        /* +list.get(i).getFileName() */
            String oldName = list.get(i).getFileOldName();
            String fileName = list.get(i).getFileName();
            path = path +"/"+fileName;
            fileName = sourceFilePath + oldName;
              // 该方法在下面定义All
            boolean download = fileDownload(path, fileName);
         }
        
        boolean flag = FileToZip.fileToZip(sourceFilePath, zipFilePath, zipName);
        if(flag){
        	System.out.println("文件打包成功!");
            try {
                zipName+=".zip";
                String path = sourceFilePath+zipName;
                if (osName.toLowerCase().startsWith("win")) {
                	path=sourceFilePath+"\\"+zipName;
                }
                //downloadPicture(url,zipFilePath);   下载的方法
                InputStream docStream = new FileInputStream(new File(path));
                ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
                byte[] buff = new byte[10000]; //buff用于存放循环读取的临时数据
                int rc = 0;
                while ((rc = docStream.read(buff, 0, 10000)) > 0) {
                    swapStream.write(buff, 0, rc);
                }
                //将输入流转换为字符数组输出流
                byte[] docByte = swapStream.toByteArray();
                //设置响应头
                response.setContentType("application/x-msdownload; charset=utf-8");
                response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(zipName));
                OutputStream os = response.getOutputStream();
                os.write(docByte);
                os.flush();
                os.close();
            }catch(Exception e) {
                System.err.print(e);
            }
        }
        }
}
	
	 public static boolean fileDownload(String url, String saveFile) {
	        try {
	        	FileInputStream fileInputStream = new FileInputStream(url);
	        	//新文件输出流
	        	FileOutputStream fileOutputStream = new FileOutputStream (saveFile);
	        	byte[] buffer= new byte[1024];
	        	int len;
	        	//将文件流信息读取文件缓存区，如果读取结果不为-1就代表文件没有读取完毕，反之已经读取完毕
	        	while ((len=fileInputStream.read(buffer))!=-1) {
		        	fileOutputStream.write(buffer, 0, len);
		        	fileOutputStream.flush();
	        	}
	        	fileInputStream.close();
	        	fileOutputStream.close();
	            return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	
public static void fileAllToZip(String filePath,ZipOutputStream zipOut,String oldName) throws IOException {
    // 需要压缩的文件
    File file = new File(filePath);
    // 获取文件名称,如果有特殊命名需求,可以将参数列表拓展,传fileName
    String fileName = file.getName();
    FileInputStream fileInput = new FileInputStream(filePath);
    // 缓冲
    byte[] bufferArea = new byte[1024 * 10];
    BufferedInputStream bufferStream = new BufferedInputStream(fileInput, 1024 * 10);
    // 将当前文件作为一个zip实体写入压缩流,fileName代表压缩文件中的文件名称
    zipOut.putNextEntry(new ZipEntry(oldName));
    int length = 0;
    // 最常规IO操作,不必紧张
    while ((length = bufferStream.read(bufferArea, 0, 1024 * 10)) != -1) {
        zipOut.write(bufferArea, 0, length);
    }
    //关闭流
    fileInput.close();
    // 需要注意的是缓冲流必须要关闭流,否则输出无效
    bufferStream.close();
    // 压缩流不必关闭,使用完后再关
}


	 public static String generateShortUuid() {
	        StringBuffer shortBuffer = new StringBuffer();
	        String uuid = UUID.randomUUID().toString().replace("-", "");
	        for (int i = 0; i < 8; i++) {
	            String str = uuid.substring(i * 4, i * 4 + 4);
	            int x = Integer.parseInt(str, 16);
	            shortBuffer.append(chars[x % 0x3E]);
	        }
	        return shortBuffer.toString();
	    }
	    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
	            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
	            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
	            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
	            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
	            "W", "X", "Y", "Z"};
}
