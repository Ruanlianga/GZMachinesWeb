package com.bonus.ma.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.core.BackstageApplication;
import com.bonus.core.DateTimeHelper;
import com.bonus.core.StringHelper;
import com.bonus.core.qrcode.QRCodeUtil;
import com.bonus.ma.beans.QRCodeBean;
import com.bonus.ma.service.QRCodeService;
import com.bonus.newInput.beans.InputDetailsBean;
import com.bonus.newInput.service.InputDetailsService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

@Controller
@RequestMapping("/backstage/qrcode/")
public class QRCodeController extends BaseController<QRCodeBean> {

	@Autowired
	private QRCodeService service;
	
	@Autowired
	private InputDetailsService idService;

	@RequestMapping("list")
	public String index(Model model) {
		return "/ma/qrcodelist";
	}

	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<QRCodeBean> page, QRCodeBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			UserBean user = UserShiroHelper.getRealCurrentUser();
		    String companyId = user.getCompanyId();
		    o.setOrgId(companyId);
			Page<QRCodeBean> result = service.findByPage(o, page);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
			ar.setSucceed(p);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}

	// 二维码打印
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes insert(QRCodeBean o, HttpServletRequest request) {
		String maModel = o.getMaModel();
		String venderName = o.getVenderName();
		String remark = o.getRemark();
		String taskId = o.getTaskId();
		String flag = request.getParameter("flag");// 区别新购和盘点二维码打印
	
		AjaxRes ar = getAjaxRes();
		try {
			String genMonth = DateTimeHelper.getNowMonth();
			o.setGenMonth(genMonth);
			List<QRCodeBean> list = service.find(o);
			QRCodeBean bean = new QRCodeBean();
			int num = 0;
			if (list != null && list.size() > 0) {
				bean = list.get(0);
				num = Integer.parseInt(bean.getCode().split("-")[1]);
			}
			for (int i = 1; i <= Float.parseFloat(o.getNums()); i++) {
				genMonth = genMonth.replace("-", "");
				String code = genMonth + "-" + String.format("%5d", num + i).replace(" ", "0");
				produceQRCode(taskId,code, request, maModel, venderName, remark);
			}
			if ("1".equals(flag)) {
				InputDetailsBean idBean = new InputDetailsBean();
				idBean.setCheckStatus("6");
				idBean.setTaskId(o.getTaskId());
				idBean.setMaModelId(o.getMaModel());
				idService.updateCheckStatus(idBean);
			}
			 ar.setSucceedMsg(GlobalConst.GEN_SUCCEED);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.GEN_FAIL);
		}
		return ar;
	}

	@RequestMapping(value = "rfidinsert", method = RequestMethod.POST)
	@ResponseBody
	public String rfidinsert(QRCodeBean o, HttpServletRequest request) {
		String res = "1";
		String maModel = o.getMaModel();
		String venderName = o.getVenderName();
		String remark = o.getRemark();
		String flag = request.getParameter("flag");
		try {
			String genMonth = DateTimeHelper.getNowMonth();
			o.setGenMonth(genMonth);
			List<QRCodeBean> list = service.find(o);
			QRCodeBean bean = new QRCodeBean();
			int num = 0;
			if (list != null && list.size() > 0) {
				bean = list.get(0);
				num = Integer.parseInt(bean.getCode().split("-")[1]);
			}
			for (int i = 1; i <= Float.parseFloat(o.getNums()); i++) {
				genMonth = genMonth.replace("-", "");
				String code = genMonth + "-" + String.format("%5d", num + i).replace(" ", "0");
				produceQRCode("",code, request, maModel, venderName, remark);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return res;
	}

	// 缺失二维码生成
	@RequestMapping(value = "produceQRCode", method = RequestMethod.GET)
	@ResponseBody
	public AjaxRes produceQRCode(HttpServletRequest request) {
		AjaxRes ar = getAjaxRes();
		try {
			initList(request);
			ar.setSucceedMsg(GlobalConst.GEN_SUCCEED);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.GEN_FAIL);
		}
		return ar;
	}

	public List<String> initList(HttpServletRequest request) {
		List<String> list = new ArrayList<String>();
		list.add("201808-00979");
		for (String code : list) {
			try {
				produceQRCode2(code, request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public void produceQRCode2(String code, HttpServletRequest request) throws Exception {
		String url = "http://140.210.216.134:1935/gz_imw/backstage/machine/qrCodePage?qrcode=" + code;
		//String url = "http://192.168.0.14:3980/GZMachinesWeb/backstage/machine/qrCodePage?qrcode=" + code;
		//112.31.212.128:1935/gz_imw
		// // 二维码的图片格式
		String format = "jpg";
		// // 生成二维码
		String realPath = request.getSession().getServletContext().getRealPath("/images");
		File files = new File(realPath);
		if (!files.exists()) {
			files.mkdirs();
		}
		QRCodeUtil.generateQRImage(url, realPath, code + ".jpg", format);
	}

	public void produceQRCode(String taskId,String code, HttpServletRequest request, String maModel, 
			String venderName, String remark) throws Exception {
		String url = BackstageApplication.getUrl() + "backstage/machine/qrCodePage?qrcode=" + code;
		// // 二维码的图片格式
		String format = "jpg";
		// // 生成二维码
		String realPath = request.getSession().getServletContext().getRealPath("/images");
		File files = new File(realPath);
		if (!files.exists()) {
			files.mkdirs();
		}
		QRCodeUtil.generateQRImage(url, realPath, code + ".jpg", format);
		String qrUrl = code + ".jpg";
		QRCodeBean bean = new QRCodeBean();
		bean.setTaskId(taskId);
		bean.setCode(code);
		bean.setQrUrl(qrUrl);
		bean.setMaModel(maModel);
		bean.setVenderName(venderName);
		bean.setRemark(remark);
		String genDate = DateTimeHelper.getNowDate();
		bean.setGenMonth(genDate);
		bean.setMaModelId(maModel);
		
		UserBean user = UserShiroHelper.getRealCurrentUser();
	    String companyId = user.getCompanyId();
	    bean.setOrgId(companyId);
		service.insert(bean);
	}

	@RequestMapping(value = "downloadPhoto", method = RequestMethod.GET)
	@ResponseBody
	public void downloadPhoto(HttpServletRequest request, HttpServletResponse response, QRCodeBean o) {
		String flag = request.getParameter("flag");
		List<QRCodeBean> alist = new ArrayList<QRCodeBean>();
		if ("1".equals(flag)) {
			alist = service.findQRCodeByTaskType(o);
			InputDetailsBean idBean = new InputDetailsBean();
			idBean.setCheckStatus("7");
			idBean.setTaskId(o.getTaskId());
			idBean.setMaModelId(o.getMaModel());
			idService.updateCheckStatus(idBean);
		} else {
			String chks = request.getParameter("chks");
			if (StringUtils.isNotBlank(chks)) {
				String[] chk = chks.split(",");
				List<String> list = new ArrayList<String>();
				for (String s : chk) {
					try {
						list.add(s);
					} catch (Exception e) {
					}
				}
				o.setChks(list);
			}
			alist = service.findQRCode(o);
		}
		download(request, response, alist, o, flag);
	}

	private void download(HttpServletRequest request, HttpServletResponse response, List<QRCodeBean> list,
			QRCodeBean bean, String flag) {
		try {
			if(list.size() > 0){
				
			}
			String startTime = bean.getStartTime();
			String endTime = bean.getEndTime();
			String keyWord = bean.getKeyWord();
			String downloadFilename = startTime;
			if (startTime != null && !startTime.equals(endTime)) {
				downloadFilename = startTime + "~" + endTime;
			}
			if (StringHelper.isNotEmpty(keyWord)) {
				keyWord = "包含" + keyWord;
			} else {
				keyWord = "";
			}
			downloadFilename += keyWord + "二维码图片.zip";// 文件的名称
			if ("1".equals(flag)) {
				downloadFilename = list.get(0).getType0() + "-" + list.get(0).getModel() + "二维码图片.zip";// 文件的名称
			}
			downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");// 转换中文否则可能会产生乱码
			response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
			response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);// 设置在下载框默认显示的文件名
			ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
			for (int i = 0; i < list.size(); i++) {
				String fileName = list.get(i).getCode();
				String realPath = request.getSession().getServletContext().getRealPath("/images");
				String path = realPath + "/" + fileName + ".jpg";
				logger.warn("path:" + path);
				// 判断照片是否存在
				File imageFile = new File(path);
				if (!imageFile.exists()) {
					continue;
				}
				zos.setLevel(0);
				String typeName = list.get(i).getType0();
				String modelName = list.get(i).getModel();
				zos.putNextEntry(new ZipEntry("[" + typeName + "-" + modelName + "]" + fileName + ".jpg"));
				InputStream fis = new FileInputStream(imageFile);
				byte[] buffer = new byte[1024];
				int r = 0;
				while ((r = fis.read(buffer)) != -1) {
					zos.write(buffer, 0, r);
				}
				fis.close();
			}
			zos.flush();
			zos.close();
		} catch (Exception e) {
			logger.warn("download qcore image error:", e);
		}
	}

	public String toMonth(String str) {
		String year = str.substring(0, 4);
		String month = str.substring(4, 6);
		return year + "-" + month;
	}

	@RequestMapping(value = "findQrcodeByTaskId", method = RequestMethod.POST)
	@ResponseBody
	public String findQrcodeByTaskId(QRCodeBean o) {
		String results = "";
		try {
			List<QRCodeBean> list = service.findQrcodeByTaskId(o);
			for (QRCodeBean bean : list) {
				results += bean.getMaModel() + ",";
				results += bean.getCode() + ";";
			}
			if (StringHelper.isNotEmpty(results)) {
				results = results.substring(0, results.length() - 1);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return results;
	}

}
