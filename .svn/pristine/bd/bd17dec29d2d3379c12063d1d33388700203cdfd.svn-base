package com.bonus.app.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.core.BackstageApplication;
import com.bonus.core.DateTimeHelper;
import com.bonus.core.qrcode.QRCodeUtil;
import com.bonus.ma.beans.QRCodeBean;
import com.bonus.ma.service.QRCodeService;
import com.bonus.newInput.beans.InputDetailsBean;
import com.bonus.newInput.beans.NewInputQrcodeBean;
import com.bonus.newInput.service.InputDetailsService;
import com.bonus.newInput.service.NewInputQrcodeService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

@Controller
@RequestMapping("/backstage/app/newQrcode/")
public class AppNewInputQrcodeController extends BaseController<Object> {

	@Autowired
	private NewInputQrcodeService nService;
	
	@Autowired
	private QRCodeService service;
	
	@Autowired
	private InputDetailsService idService;

	/**
	 * 新购二维码绑定列表
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "putInStorageList", method = RequestMethod.POST)
	@ResponseBody
	public List<NewInputQrcodeBean> putInStorageList(NewInputQrcodeBean o, HttpServletRequest request) {
		List<NewInputQrcodeBean> list = new ArrayList<>();
		try {
			list = nService.putInStorageList(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	/**
	 * 新购二维码绑定列表详情
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "putInStorageDetails", method = RequestMethod.POST)
	@ResponseBody
	public List<NewInputQrcodeBean> putInStorageDetails(NewInputQrcodeBean o, HttpServletRequest request) {
		List<NewInputQrcodeBean> list = new ArrayList<>();
		try {
			list = nService.putInStorageDetails(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	/**
	 * 新购二维码绑定记录
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "newBindingRecord", method = RequestMethod.POST)
	@ResponseBody
	public List<NewInputQrcodeBean> newBindingRecord(NewInputQrcodeBean o, HttpServletRequest request) {
		List<NewInputQrcodeBean> list = new ArrayList<>();
		try {
			list = nService.newBindingRecord(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	/**
	 * 新购二维码入库记录
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "newInputRecord", method = RequestMethod.POST)
	@ResponseBody
	public List<NewInputQrcodeBean> newInputRecord(NewInputQrcodeBean o, HttpServletRequest request) {
		List<NewInputQrcodeBean> list = new ArrayList<>();
		try {
			list = nService.newInputRecord(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	/**
	 *新购二维码绑定
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "building", method = RequestMethod.POST)
	@ResponseBody
	public int building(NewInputQrcodeBean o,HttpServletRequest request ) {
		int res = 0;
		try {
			//先去生成二维码
			
			String maModel = o.getMaModel();
			String venderName = o.getVenderName();
			String remark = o.getRemark();
			String taskId = o.getTaskId();
			o.setNums("1");
			String flag = "1";// 区别新购和盘点二维码打印
		
			try {
				String genMonth = DateTimeHelper.getNowMonth();
				QRCodeBean qRCodeBean = new QRCodeBean();
				qRCodeBean.setGenMonth(genMonth);
				
				List<QRCodeBean> list = service.find(qRCodeBean);
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
					o.setQrCode(code);
				}
				if ("1".equals(flag)) {
					InputDetailsBean idBean = new InputDetailsBean();
					idBean.setCheckStatus("6");
					idBean.setTaskId(o.getTaskId());
					idBean.setMaModelId(o.getMaModel());
					idService.updateCheckStatus(idBean);
				}
			} catch (Exception e) {
				logger.error(e.toString(), e);
			}
			
			
			res = nService.building(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			res = -1;
		}
		return res;
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
		String companyId = "";
		if(user!=null){
			companyId = user.getCompanyId();
		}else{
			companyId = "1";
		}
	    bean.setOrgId(companyId);
		service.insert(bean);
	}
	
	/**
	 *根据二维码查询机具信息
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "findByQrcode", method = RequestMethod.POST)
	@ResponseBody
	public List<NewInputQrcodeBean> findByQrcode(NewInputQrcodeBean o) {
		List<NewInputQrcodeBean> list = new ArrayList<>();
		try {
			list = nService.findByQrcode(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	
	/**
	 *根据机具编码查询机具信息
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "findByDevCode", method = RequestMethod.POST)
	@ResponseBody
	public List<NewInputQrcodeBean> findByDevCode(NewInputQrcodeBean o) {
		List<NewInputQrcodeBean> list = new ArrayList<>();
		try {
			list = nService.findByDevCode(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	
	/**
	 * 新购入库记录
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "newInputRecordStroageRack", method = RequestMethod.POST)
	@ResponseBody
	public List<NewInputQrcodeBean> newInputRecordStroageRack(NewInputQrcodeBean o, HttpServletRequest request) {
		List<NewInputQrcodeBean> list = new ArrayList<>();
		try {
			list = nService.newInputRecordStroageRack(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
}
