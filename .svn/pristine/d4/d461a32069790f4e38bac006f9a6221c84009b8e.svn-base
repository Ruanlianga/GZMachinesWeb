package com.bonus.rm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.core.DateTimeHelper;
import com.bonus.core.StringHelper;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.dao.MachineDao;
import com.bonus.rm.beans.ReturnMaterialDetailsBean;
import com.bonus.rm.dao.ReturnMaterialDetailsDao;
import com.bonus.sys.BaseServiceImp;

@Service("returnMaterialDetails")
public class ReturnMaterialDetailsServiceImp extends BaseServiceImp<ReturnMaterialDetailsBean> implements ReturnMaterialDetailsService{

	@Autowired ReturnMaterialDetailsDao dao;
	
	@Autowired MachineDao mdao;

	@Override
	public Object findByCode(ReturnMaterialDetailsBean o) {
		//查询退料详细
		List<ReturnMaterialDetailsBean> back = dao.findByTaskId(o);
		String unitId = "";
		String workId = "";
		if(back.size() > 0){
			unitId = back.get(0).getUnitId();
			workId = back.get(0).getWorkId();
		}
		//查询领料信息
		List<ReturnMaterialDetailsBean> receive = dao.findByCode(o);
		String reUnitId = "";
		String reProjectId = "";
		String batchStatus = "";
		if(receive.size() > 0){
			reUnitId = receive.get(0).getReceiveUnitId();
			reProjectId = receive.get(0).getReceiveProjectId();
			batchStatus = receive.get(0).getBatchStatus();
			if("6".equals(batchStatus)){
				if(unitId.equals(reUnitId) && workId.equals(reProjectId)){
					return "1";
				}else{
					return receive;
				}
			}else{
				return "-2";
			}
		}else{
			return "-1";
		}
	}

	//退料接收
	@Override
	public Object receiveDevice(ReturnMaterialDetailsBean o) {
		//1-修改机具状态
		MachineBean ma = new MachineBean();
		ma.setId(o.getMaId());
		List<MachineBean> mList = mdao.find(ma);
		String batchStatus = mList.get(0).getBatchStatus();
		if("6".equals(batchStatus)){
			int updRes = updDevice(o);
			if(updRes == 1){
				//2-添加退料明细
				int detailsRes = rmDetails(o);
				return detailsRes;
			}else{
				return -1;
			}
		}else{
			return -1;
		}
		
		
	}
	
	public int updDevice(ReturnMaterialDetailsBean o){
		MachineBean bean = new MachineBean();
		bean.setId(o.getMaId());
		bean.setBatchStatus(o.getBatchStatus());
		try {
			mdao.updateBatchStatus(bean);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}
	
	public int rmDetails(ReturnMaterialDetailsBean o){
		MachineBean ma = new MachineBean();
		ma.setId(o.getMaId());
		List<MachineBean> list = mdao.find(ma);
		String modelId = "";
		try {
			if(list.size()==1){
				modelId = list.get(0).getTypeId();
				o.setModelId(modelId);
				//1-查询是否存在明细任务
				//2-存在则获取任务ID，存入详细表
				//3-不存在则新增明细任务，并存入详细表
				String supId = dao.findSupId(o);
				if(supId == "" || supId == null){
					o.setReturnMaterialTime(DateTimeHelper.getNowTime());
					o.setOperationTime(DateTimeHelper.getNowTime());
					dao.insert(o);
					int infoRes = rmInfo(o);
					if(infoRes == 1){
						return 1;
					}else{
						return -1;
					}
				}else{
					//3-添加退料详情
					o.setSupId(supId);
					o.setOperationTime(DateTimeHelper.getNowTime());
					int infoRes = rmInfo(o);
					if(infoRes == 1){
						return 1;
					}else{
						return -1;
					}
				}
			}else if(list.size() > 1){
				//设备编号重复
				return -2;
			}else{
				//设备编号重不存在
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int rmInfo(ReturnMaterialDetailsBean o) {
		MachineBean ma = new MachineBean();
		ma.setId(o.getMaId());
		List<MachineBean> list = mdao.find(ma);
		String modelId = "";
		String maId = "";
		try {
			if (list.size() == 1) {
				modelId = list.get(0).getTypeId();
				maId = list.get(0).getId();
				o.setModelId(modelId);
				o.setMaId(maId);
				o.setOperationTime(DateTimeHelper.getNowTime());
				o.setMaNum("1");
				o.setState("4");
				dao.insertInfo(o);
				return 1;
			} else if (list.size() > 1) {
				// 设备编号重复
				return -2;
			} else {
				// 设备编号重不存在
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Object findLeaseBackNum(ReturnMaterialDetailsBean o) {
		return dao.findLeaseBackNum(o);
	}

	@Override
	public Object reBackNums(ReturnMaterialDetailsBean o) {
		int detailsRes = addRmTask(o);
		return detailsRes;
	}
	/**
	 * 数量退料接收
	 */
	public int addRmTask(ReturnMaterialDetailsBean o){
		//1-查询是否存在明细任务
		//2-存在则获取任务ID，存入详细表
		//3-不存在则新增明细任务，并存入详细表
		try {
			
			String supId = dao.findSupId(o);
			if(StringHelper.isEmpty(supId) || supId == null){
				o.setReturnMaterialTime(DateTimeHelper.getNowTime());
				o.setOperationTime(DateTimeHelper.getNowTime());
				dao.insert(o);
				//3-添加退料详情
				if(StringHelper.isNotEmpty(o.getNum()) && !"0".equals(o.getNum())){//合格
					o.setMaNum(o.getNum());
					o.setRmStatus("1");
					int infoRes = addNumInfo(o);
					if(infoRes != 1){
						return -1;
					}
				}
				if(StringHelper.isNotEmpty(o.getRepairNum()) && !"0".equals(o.getRepairNum())){//维修
					o.setMaNum(o.getRepairNum());
					o.setRmStatus("2");
					int infoRes = addNumInfo(o);
					if(infoRes != 1){
						return -1;
					}
				}
				if(StringHelper.isNotEmpty(o.getScrapNum()) && !"0".equals(o.getScrapNum())){//报废
					o.setMaNum(o.getScrapNum());
					o.setRmStatus("3");
					int infoRes = addNumInfo(o);
					if(infoRes != 1){
						return -1;
					}
				}
			}else{
				//3-添加退料详情
				o.setSupId(supId);
				o.setOperationTime(DateTimeHelper.getNowTime());
				if(StringHelper.isNotEmpty(o.getNum()) && !"0".equals(o.getNum())){
					o.setMaNum(o.getNum());
					o.setRmStatus("1");
					int infoRes = addNumInfo(o);
					if(infoRes != 1){
						return -1;
					}
				}
				if(StringHelper.isNotEmpty(o.getRepairNum()) && !"0".equals(o.getRepairNum())){
					o.setMaNum(o.getRepairNum());
					o.setRmStatus("2");
					int infoRes = addNumInfo(o);
					if(infoRes != 1){
						return -1;
					}
				}
				if(StringHelper.isNotEmpty(o.getScrapNum()) && !"0".equals(o.getScrapNum())){
					o.setMaNum(o.getScrapNum());
					o.setRmStatus("3");
					int infoRes = addNumInfo(o);
					if(infoRes != 1){
						return -1;
					}
				}
			}
		return 1;
		} catch (Exception e) {
			return -1;
		}
	}
	
	public int addNumInfo(ReturnMaterialDetailsBean o){
		try {
			o.setState("4");
			dao.insertInfo(o);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public List<ReturnMaterialDetailsBean> findTaskDetail(ReturnMaterialDetailsBean o) {
		return dao.findTaskDetail(o);
	}

	@Override
	public List<ReturnMaterialDetailsBean> findTaskDetailInfo(ReturnMaterialDetailsBean o) {
		return dao.findTaskDetailInfo(o);
	}

}
