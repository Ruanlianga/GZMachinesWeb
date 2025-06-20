package com.bonus.warningManage.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.ma.service.MachineService;
import com.bonus.warningManage.beans.CheckWarningBean;
import com.bonus.warningManage.beans.DirectAssignBean;
import com.bonus.warningManage.service.CheckWarningService;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseController;
import com.bonus.sys.GlobalConst;
import com.bonus.sys.Page;
import com.bonus.sys.UserShiroHelper;

@Controller
@RequestMapping("/backstage/checkWarning/")
public class CheckWarningController extends BaseController<DirectAssignBean> {

	/*@Autowired
	private DirectAssignService service;
	@Autowired
	private SendNoticeService snService;*/

	@Autowired
	private MachineService maservice;
	
	@Autowired
	private CheckWarningService warnService;

	@RequestMapping("list")
	public String index(Model model) {
		return "/warningManage/checkWarningList";
	}
	
	/**
	 * @Author 无畏
	 * @Date 2019-05-20
	 * @function 获得预警列表
	 * @param o 
	 * @return 
	 */
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(Page<CheckWarningBean> page, CheckWarningBean o) {
		AjaxRes ar = getAjaxRes();
		try {
			String companyId = UserShiroHelper.getRealCurrentUser().getCompanyId();
			o.setOrgId(companyId);
			Page<CheckWarningBean> result = warnService.findByPage(o, page);
		    Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", result);
		    ar.setSucceed(page);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			ar.setFailMsg(GlobalConst.DATA_FAIL);
		}
		return ar;
	}
	
 
	    @RequestMapping(value = "updatearray", method = RequestMethod.POST)
	    @ResponseBody
	    public AjaxRes test(@RequestParam(value = "check_val[]") String[] check_val,HttpServletRequest request){
	    	AjaxRes ar = getAjaxRes();
	    	try {
	    		 String nowchecktime = request.getParameter("nowchecktime");
		         String nextchecktime = request.getParameter("nextchecktime");
		         System.out.println(nowchecktime + nextchecktime);
		         boolean flag = true;
		    	 for(int i=0;i<check_val.length;i++){
		             int res = maservice.updTime(check_val[i],nowchecktime,nextchecktime);
		             if(res <= 0){
		            	 flag = false;
		             }
		         }
		    	 if(flag){
		    		 ar.setSucceedMsg("保存成功");
		    	 }else{
		    		 ar.setSucceedMsg("保存失败");
		    	 }
		    	 
			} catch (Exception e) {
				 ar.setFailMsg(GlobalConst.UPDATE_FAIL);
				 
			}
	         return ar;
	    }
	    /**
	     * 
	     * @param req
	     * @return
	     * 发送的后台保存并且短信通知的功能
	     *//*
	   @ResponseBody
		@RequestMapping(value = "sendNotice", method = RequestMethod.POST)
		public AjaxRes sendNotice(HttpServletRequest req) {
			AjaxRes ar = getAjaxRes();
			String content = req.getParameter("content");
			String telepment = req.getParameter("telepment");
			String hiddenid = req.getParameter("hiddenid");
			SendNoticeBean snBean = new SendNoticeBean();
			SmsClientSend scs = new SmsClientSend();
				try {
					UserBean currentUser = UserShiroHelper.getCurrentUser();
					Integer id = currentUser.getId();
					snBean.setId(hiddenid);
					snBean.setTelephone(telepment);
					snBean.setUserId(id+"");
					snBean.setNotice(content);
					snService.insert(snBean);
				scs.sendSms(telepment, "【智能机具管理系统】" + content);
				ar.setSucceedMsg(GlobalConst.NOTICE_SUCCEED);
			} catch (Exception e) {
				e.printStackTrace();
				ar.setFailMsg(GlobalConst.NOTICE_FAIL);
			} 
			 
			return ar;
		}*/
 
	 

}
