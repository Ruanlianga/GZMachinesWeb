package com.bonus.ma.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.StringHelper;
import com.bonus.ma.beans.DisassemblyManagementBean;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.dao.DisassemblyManagementDao;
import com.bonus.ma.dao.MachineDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;

/**
 * 拆组管理业务实现层
 * @author xj
 */
@Service("DisassemblyManagement")
public class DisassemblyManagementServiceImpl extends BaseServiceImp<DisassemblyManagementBean> implements DisassemblyManagementService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private String taskId;
	@Autowired
	private DisassemblyManagementDao dao;
	
	@Autowired
	private MachineDao machDao;

	@Override
	public List<DisassemblyManagementBean> getListDataById(DisassemblyManagementBean o) {
		 List<DisassemblyManagementBean> list=new ArrayList<DisassemblyManagementBean>();
		try {
			list= dao.getListDataById(o) ;
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}

	@Override
	public List<DisassemblyManagementBean> getListDataByDeviceCode(DisassemblyManagementBean o) {
		List<DisassemblyManagementBean> list=new ArrayList<DisassemblyManagementBean>();
		try {
			list= dao.getListDataByDeviceCode(o) ;
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return list;
	}
	/**
	 * 机具数据拆分信息处理 
	 */
	@Override
	public String insertDatasplit(DisassemblyManagementBean o) {
		String msg="保存成功";
		String ids=o.getId();
		try {
			if(StringHelper.isNotEmpty(ids)){
				boolean b=true;
				String[] arrIds=ids.split("@");
				for (String string : arrIds) {
					String id=string.split("_")[0];//信息id
					String deviceCode=string.split("_")[1];//设备code
					String typeId=string.split("_")[2];//设备code
					MachineBean vo=new MachineBean();
					vo.setId(id);
					List<MachineBean>  list= machDao.find(vo) ;
					if(list!=null && list.size()>0){
						MachineBean bean=list.get(0);
						bean.setType(bean.getTypeId());
						bean.setOriginNum(bean.getDeviceCode());
						bean.setDeviceCode(deviceCode);
						String state=bean.getBatchStatus();
						bean.setBatchStatus("5");
						bean.setType(typeId);
						bean.setGpsCode(null);
						machDao.insert(bean);//数据插入 5  在库  11 报废
						//历史数据更改为 报废
						bean.setNewId(bean.getId());
						bean.setId(id); 
						bean.setBatchStatus("20");
						machDao.updateBatchStatus(bean);//致次数据添加完成
						if(!"5".equals(state)){
							updaetOrgRelationNum(bean,true);
						}
						//数据记录表信息插入
						if(b){
							insertRecode(bean,b);
							b=false;
						}
						
						//数据库信息增加或者减少
						
					}
					
				}
				
			}
		} catch (Exception e) {
			msg="保存失败";
			logger.error(e.toString(), e);
		}
		return msg;
	}

	private void insertRecode(MachineBean bean,boolean b) {
		UserBean user = UserShiroHelper.getRealCurrentUser();
	    String orgId = user.getCompanyId();
	    
		DisassemblyManagementBean vo=new DisassemblyManagementBean();
		try {
			vo.setOpter(user.getId().toString());
			vo.setOptTime(DateTimeHelper.getNowTime());
			vo.setType("1");//1 代表就旧设备及历史设备
			vo.setMaId(bean.getId());
			vo.setOrgId(orgId);
			String code=StringHelper.getCode(18);
			vo.setCode(code);
			if(b){
				dao.insertData(vo);
				taskId=vo.getId();
			}
			
			vo.setDeviceCode(bean.getDeviceCode());//新编号
			vo.setOriginNum(bean.getOriginNum());//老编号
			vo.setTaskId(taskId);
			vo.setMaId(bean.getId());//机具id
			vo.setRemark(bean.getRemarks());
			vo.setTypeId(bean.getTypeId());
			dao.insertRecode(vo);
			vo.setId(bean.getNewId());
			vo.setTypeId(bean.getType());
			dao.inserNewRecode(vo);//新生记录表
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		
	}

		/**
		 * 机具合并信息
		 */
	@Override
	public String addDataSynthesis(DisassemblyManagementBean o) {
		String msg="保存成功";
		String ids=o.getId();
		String deviceCode=o.getDeviceCode();
		String originNum=o.getOriginNum();
		MachineBean vo=new MachineBean();
		boolean b=true;
		if(StringHelper.isNotEmpty(ids)){
			String[] arrIds=ids.split("@");
			for (String id : arrIds) {
				vo.setId(id);
				List<MachineBean>  list= machDao.find(vo) ;
				if(list!=null && list.size()>0){
					
					MachineBean bean=list.get(0);
					bean.setType(bean.getTypeId());
					bean.setOriginNum(originNum);
					bean.setDeviceCode(deviceCode);
					String state=bean.getBatchStatus();
					bean.setType(o.getType());
					bean.setBatchStatus("5");
					if(b){
						bean.setGpsCode(null);
						machDao.insert(bean);//数据插入 5  在库  11 报废
					}
					//历史数据更改为 报废
					bean.setNewId(bean.getId());
					bean.setId(id); 
					bean.setBatchStatus("21");
					
					machDao.updateBatchStatus(bean);//致次数据添加完成
					if(!"5".equals(state)){
						updaetOrgRelationNum(bean,true);
					}
					//数据记录表信息插入
					insertDataRecode(bean,b,originNum);
					b=false;
					//数据库信息增加或者减少
				}
			}
		}
		return msg;
	}
	
	
	private void insertDataRecode(MachineBean bean,boolean b,String originNum) {
		UserBean user = UserShiroHelper.getRealCurrentUser();
	    String orgId = user.getCompanyId();
	    
		DisassemblyManagementBean vo=new DisassemblyManagementBean();
		try {
			vo.setOpter(user.getId().toString());
			vo.setOptTime(DateTimeHelper.getNowTime());
			vo.setType("2");//1 代表就旧设备及历史设备
			vo.setMaId(bean.getId());
			vo.setOrgId(orgId);
			String code=StringHelper.getCode(18);
			vo.setCode(code);
			if(b){
			dao.insertData(vo);
			taskId=vo.getId();
			}
			vo.setDeviceCode(bean.getDeviceCode());//新编号
			vo.setOriginNum(bean.getOriginNum());//老编号
			vo.setTaskId(taskId);
			vo.setMaId(bean.getId());//机具id
			vo.setRemark(bean.getRemarks());
			vo.setTypeId(bean.getTypeId());
			dao.insertRecode(vo);
			vo.setId(bean.getNewId());
			if(b){
				vo.setOriginNum(originNum);//老编号
				vo.setTypeId(bean.getType());
				dao.inserNewRecode(vo);//新生记录表
			}
		
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		
	}	
	/**
	 * /修改库存数量
	 */
	public void updaetOrgRelationNum(MachineBean bean,Boolean isAdd){
		try {
		List<MachineBean>	 list=dao.getOrgRelationNum(bean);
		if(list!=null && list.size()>0){
			MachineBean vo=list.get(0);
			Integer num=vo.getNum();
			if(isAdd){
				num=num+1;
			}else{
				num=num-1;
			}
			vo.setNum(num);
			dao.updaDataeRelationNum(vo);
		}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		
	}
	/**
	 * 获取机具类型
	 */
	@Override
	public Map<String, Object> getInitSelect(DisassemblyManagementBean bean) {
		 Map<String, Object> map=new HashMap<String, Object>();
		try {
			List<DisassemblyManagementBean> list=dao.getInitSelect(bean);
			map.put("data", list);
			map.put("msg", "success");
		} catch (Exception e) {
			map.put("msg", "error");
			logger.error(e.toString(), e);
		}
		
		return map;
	}
}
