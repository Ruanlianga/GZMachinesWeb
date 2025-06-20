package com.bonus.scrap.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.exception.ZeroAffectRowsException;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.beans.MachineTypeBean;
import com.bonus.ma.dao.MachineDao;
import com.bonus.ma.dao.MachineTypeDao;
import com.bonus.scrap.beans.ScrapAuditBean;
import com.bonus.scrap.dao.ScrapAuditDao;
import com.bonus.sys.AjaxRes;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;
@Service("scrapAudit")
public class ScrapAuditServiceImp extends BaseServiceImp<ScrapAuditBean> implements ScrapAuditService{
	
	@Autowired
	private ScrapAuditDao dao;
	
	
	@Autowired
	private MachineTypeDao mtDao;
	
	@Autowired
	private MachineDao maDao;

	@Override
	public int insertBean(ScrapAuditBean audit) {
		
		return dao.insertBean(audit);
	}

	@Override
	public int insertDetails(ScrapAuditBean details) {
		
		return	dao.insertDetails(details);
		
	}

	@Override
	public ScrapAuditBean findScrapApplyById(ScrapAuditBean o) {
	
		return dao.findScrapApplyById(o);
	}
	
	@Override
	public ScrapAuditBean findScrapFileById(ScrapAuditBean o) {
	
		return dao.findScrapFileById(o);
	}

	@Override
	@Transactional
	public AjaxRes saveScrapResult(ScrapAuditBean o) {
		AjaxRes ar = new AjaxRes();
		Integer result = 0; 
		try {
				/*-------------------------------------
				wf_scarp_apply
            	更新审核人，审核时间，审核状态
             
				-------------------------------------*/
				UserBean user = UserShiroHelper.getRealCurrentUser();
				int auditor = user.getId();
				String auditTime = DateTimeHelper.getNowTime();
				o.setStatus("1");
				o.setAuditor(auditor+"");
				o.setAuditTime(auditTime);
				result = dao.updateBean(o);
				if(result == 0){
					throw new ZeroAffectRowsException("更新wf_scarp_apply信息失败!");
				}
				
				ScrapAuditBean[] arr = o.getArr();
				
				int len = arr.length;
				
				for (int j = 0; j < len; j++) {
					// 修改ma_machine_type库存 --
					String typeId = arr[j].getTypeId();
					String maId = arr[j].getMaId();
					String num = arr[j].getNum();
					
					MachineTypeBean type = new MachineTypeBean();
			
					type.setId(typeId);
					type.setNums(num);
					result = mtDao.updateStorageNum(type);
					if (result == 0) {
						throw new ZeroAffectRowsException("修改mm_type信息失败!");
					}
					
					if(maId!=null){
						// 根据machine_id修改状态为已经报废
						MachineBean mabean = new MachineBean();
						mabean.setId(maId);
						mabean.setBatchStatus("11");
						result = maDao.updateBatchStatus(mabean);
						if (result == 0) {
							throw new ZeroAffectRowsException("修改ma_machine 状态信息失败!");
						}
					}
				}
				
				ar.setSucceedMsg("审核成功!");
		}catch (Exception e) {
			e.printStackTrace();
			ar.setFailMsg(e.getMessage());
			throw e;
		}
	
		return ar;
	}

	@Override
	@Transactional
	public AjaxRes rejectScrapApply(ScrapAuditBean o) {
		AjaxRes ar = new AjaxRes();
		Integer result = 0; 
		try {
			/*-------------------------------------
			wf_scarp_apply
	                更新审核人，审核时间，审核状态
	                 
			-------------------------------------*/
			UserBean user = UserShiroHelper.getRealCurrentUser();
			int auditor = user.getId();
			String auditTime = DateTimeHelper.getNowTime();
			o.setStatus("2");
			o.setAuditor(auditor+"");
			o.setAuditTime(auditTime);
			result = dao.updateBean(o);
			if(result == 0){
				throw new ZeroAffectRowsException("更新wf_scarp_apply信息失败!");
			}
			
			ar.setSucceedMsg("审核成功!");
		} catch (Exception e) {
			e.printStackTrace();
			ar.setFailMsg(e.getMessage());
			throw e;
		}
		return ar;
	}

	
	

}
