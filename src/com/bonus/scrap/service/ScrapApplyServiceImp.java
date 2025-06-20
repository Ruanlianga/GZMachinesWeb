package com.bonus.scrap.service;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.exception.ZeroAffectRowsException;
import com.bonus.scrap.beans.ScrapApplyBean;
import com.bonus.scrap.beans.ScrapApplyFileBean;
import com.bonus.scrap.dao.ScrapApplyDao;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

@Service("apply")
public class ScrapApplyServiceImp extends BaseServiceImp<ScrapApplyBean> implements ScrapApplyService {

	
	@Autowired
	ScrapApplyDao dao;

	@Override
	public List<ScrapApplyBean> findParentTypeList(ScrapApplyBean bean) {
		// TODO Auto-generated method stub
		return dao.findParentTypeList(bean);
	}

	@Override
	public List<ScrapApplyBean> findTypeList(ScrapApplyBean bean) {
		// TODO Auto-generated method stub
		return dao.findTypeList(bean);
	}


	@Override
	public List<ScrapApplyBean> findMaCodeList(ScrapApplyBean bean) {
		// TODO Auto-generated method stub
		return dao.findMaCodeList(bean);
	}

	@Override
	@Transactional
	public AjaxRes saveInventoryScrap(ScrapApplyBean o) {
		AjaxRes ar = getAjaxRes();
		Integer result;
		try {
			String code;
			UserBean user = UserShiroHelper.getRealCurrentUser();
			ScrapApplyBean[] sfb = o.getSfb();
			if (sfb != null && sfb.length > 0) {
				List<String> existsMaCodeList = dao.findExistsMaCodeList(Arrays.stream(sfb).map(ScrapApplyBean::getMaCode).collect(Collectors.toList()));
				if (!existsMaCodeList.isEmpty()) {
					ar.setFailMsg("存在重复申请编号，请重新选择:" + existsMaCodeList);
					return ar;
				}
			}
			int userId = user.getId();
			o.setCreator(userId+"");
			o.setCreateTime(DateTimeHelper.getNowDate());
			code= genPDCode();
			o.setCode(code);
			result= dao.insertFromBean(o);//
			if (result == 0) {
				throw new ZeroAffectRowsException("新增报废申请单失败!");
			}
			int id = o.getId();

            for (ScrapApplyBean scrapApplyBean : sfb) {
                scrapApplyBean.setApplyId(id + "");
                result = dao.insertDetailsBean(scrapApplyBean);
                if (result == 0) {
                    throw new ZeroAffectRowsException("新增报废申请明细失败!");
                }
            }
			ar.setSucceedMsg("报废申请成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ar.setFailMsg(e.getMessage());
		}
		return ar;
     }
	
	
	public String genPDCode(){
		String code = "BF-" + DateTimeHelper.getNowDateFomart() + "00";
		int num = dao.findApplyNowDateNum();
		code = code + num;
		return code;
	}

	@Override
	public List<ScrapApplyBean> findInventoryScrapDetailsById(ScrapApplyBean o) {
		// TODO Auto-generated method stub
		return dao.findInventoryScrapDetailsById(o);
	}

	@Override
	public int fileUpload(ScrapApplyFileBean o) {
		// TODO Auto-generated method stub
		return dao.fileUpload(o);
	}

	@Override
	public List<ScrapApplyFileBean> findFileList(ScrapApplyFileBean o) {
		// TODO Auto-generated method stub
		return dao.findFileList(o);
	}

	@Override
	public ScrapApplyBean findRemark(ScrapApplyBean o) {
		// TODO Auto-generated method stub
		return dao.findRemark(o);
	}
}
