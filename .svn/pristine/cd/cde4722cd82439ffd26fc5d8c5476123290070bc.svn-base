package com.bonus.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonus.newInput.beans.NewInputQrcodeBean;
import com.bonus.newInput.service.NewInputQrcodeService;
import com.bonus.sys.BaseController;

@Controller
@RequestMapping("/backstage/app/newInput/")
public class AppNewInputController extends BaseController<Object> {

	@Autowired
	private NewInputQrcodeService nService;

	/**
	 * 新购入库任务列表
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findNewInputList", method = RequestMethod.POST)
	@ResponseBody
	public List<NewInputQrcodeBean> findByPage(NewInputQrcodeBean o) {
		List<NewInputQrcodeBean> list = new ArrayList<>();
		try {
			list = nService.findNewInputList(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	/**
	 * 新购入库任务列表详情
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findNewInputListDetails", method = RequestMethod.POST)
	@ResponseBody
	public List<NewInputQrcodeBean> findQrcodeByTaskId(NewInputQrcodeBean o) {
		List<NewInputQrcodeBean> list = new ArrayList<>();
		try {
			list = nService.findNewInputListDetails(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	/**
	 * 新购数量入库
	 * 
	 * @param o
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findNewInputByNum", method = RequestMethod.POST)
	@ResponseBody
	public List<NewInputQrcodeBean> findNewInputByNum(NewInputQrcodeBean o) {
		List<NewInputQrcodeBean> list = new ArrayList<>();
		try {
			list = nService.findNewInputByNum(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	/**
	 * 根据二维码查询机具信息
	 * @param page
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
	 * 确认入库
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "confirmStorage", method = RequestMethod.POST)
	@ResponseBody
	public int add(NewInputQrcodeBean o) {
		int res = 0;
		try {
			res = nService.confirmStorage(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			res = -1;
		}
		return res;
	}
	
	
}
