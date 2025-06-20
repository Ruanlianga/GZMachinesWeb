package com.bonus.scrap.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.core.BackstageApplication;
import com.bonus.core.FileToZip;
import com.bonus.scrap.beans.ScrapAuditBean;
import com.bonus.scrap.service.ScrapAuditService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.Page;
import com.sun.star.text.FilenameDisplayFormat;
import com.sun.webkit.network.URLs;

@Controller
@RequestMapping("/backstage/scrapAudit/")
public class ScrapAuditController extends BaseController<ScrapAuditBean> {

	@Autowired
	private ScrapAuditService service;

	
	@RequestMapping("list")
	public String index(Model model) {
		return "/scrap/scrapManagement";
	}
	
	
	  /**
     * @Author 
     * @Date 
     * @function 获得机具列表
     * @param o 
     * @return 
     */
    @RequestMapping("findByPage")
    public String findByPage(@RequestBody Page<ScrapAuditBean> page,ScrapAuditBean o,Model model) {
//    	List<MachineBean> list = new ArrayList<MachineBean>();
    	try {
    		o = page.getObj();
    		page = service.findByPage(o,page);
    		model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return "/scrap/scrapList";
    }
    
    /**
     * @Author js 
     * @Date 2022-08-25
     * @function 审批报废界面
     * @param o
     * @return
     */
    @RequestMapping("approvalApply")
  	public String approvalApply(ScrapAuditBean o,Model model) {
  		try {
  			
  			String opt = o.getOpt();
  			o = service.findScrapApplyById(o);
  			o.setOpt(opt);
  			model.addAttribute("apply", o);
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		return "/scrap/approvalScrapApplyPage";
  	}
    /**
     * @Author js 
     * @Date 2022-09-01
     * @function 审批报废界面
     * @param o
     * @return
     */
    @RequestMapping("approvalView")
    public String approvalView(ScrapAuditBean o,Model model) {
    	try {
    		
    		String opt = o.getOpt();
    		o = service.findScrapApplyById(o);
    		o.setOpt(opt);
    		model.addAttribute("apply", o);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return "/scrap/approvalViewApplyPage";
    }
    
    /**
     * @Author js 
     * @Date 2022-08-25
     * @function 审批报废通过
     * @param o
     * @return
     */
	@RequestMapping(value = "approvalScrapApply", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes approvalScrapApply(@RequestBody ScrapAuditBean o,Model model) {
		AjaxRes ar = getAjaxRes();
		try {
			
			ar = service.saveScrapResult(o);
			
    	}catch(Exception e) {
    		ar.setFailMsg("审核保存失败!");
    		e.printStackTrace();
    	}
    	return ar;
	}
  
		
	/**
	 * @Author js 
	 * @Date 2022-08-25
	 * @function 审批报废驳回
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "rejectScrapApply", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes rejectScrapApply(@RequestBody ScrapAuditBean o,Model model) {
		AjaxRes ar = getAjaxRes();
		try {
			
			ar = service.rejectScrapApply(o);
			
		}catch(Exception e) {
			ar.setFailMsg("审核保存失败!");
			e.printStackTrace();
		}
		return ar;
	}
	
	 /**
     * @Author js 
     * @Date 2022-09-01
     * @function 附件下载界面
     * @param o
     * @return
     */
    @RequestMapping("scrapFile")
    public String scrapFile(ScrapAuditBean o,Model model) {
    	try {
    		
    		String opt = o.getOpt();
    		o = service.findScrapFileById(o);
    		o.setOpt(opt);
    		model.addAttribute("apply", o);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return "/scrap/fileDownload";
    }
    
    /**
     *zip 下载
     * @return
     */
    @RequestMapping(value = "/exportZip", method = RequestMethod.GET)
    @ResponseBody
    public void exportZip(ScrapAuditBean bean,HttpServletRequest request,HttpServletResponse res) {
        String sourceFilePath = "/data/gz_imw/dzhtZip/";//文件地址
        String zipFilePath = "/data/gz_imw/";
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().startsWith("win")) {
            sourceFilePath = "D:\\dzhtZip\\";
            zipFilePath = "D:\\";
        }
        
        
        
        FileToZip.delAllFile(sourceFilePath);//删除目录下面文件
        //判断文件包是否存在
        File file = new File(sourceFilePath);
        if(!file.exists()) {
            file.mkdirs();
        }
       
       
        String ipUrl= BackstageApplication.getFileurlprefix();
        String zipName="报废附件";
        String[] urls = bean.getUrls();
        String[] names = bean.getFileNames();
        for(int i=0;i<urls.length;i++){
    		 String fileName = sourceFilePath + names[i];
    		 boolean download = fileDownload(ipUrl+urls[i], fileName);
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
                res.setContentType("application/x-msdownload; charset=utf-8");
                res.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(zipName));
                OutputStream os = res.getOutputStream();
                os.write(docByte);
                os.flush();
                os.close();
            }catch(Exception e) {
                System.err.print(e);
            }
        }
    }
    
  //视频/图片  http下载
    public static boolean httpDownload(String httpUrl, String saveFile) {
        // 1.下载网络文件
        int byteRead;
        URL url;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return false;
        }
        try {
            //2.获取链接
            URLConnection conn = url.openConnection();
            //3.输入流
            InputStream inStream = conn.getInputStream();
            //3.写入文件
            FileOutputStream fs = new FileOutputStream(saveFile);
            byte[] buffer = new byte[1024];
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            inStream.close();
            fs.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
		
}
