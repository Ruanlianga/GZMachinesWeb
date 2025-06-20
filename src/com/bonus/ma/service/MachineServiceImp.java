package com.bonus.ma.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.core.ExcelUtils;
import com.bonus.core.exception.ZeroAffectRowsException;
import com.bonus.lease.beans.OutStorageBean;
import com.bonus.ma.beans.MachineBean;
import com.bonus.ma.dao.MachineDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.UserShiroHelper;
import com.google.zxing.Result;

//import jdk.nashorn.internal.runtime.Undefined;
//import sun.nio.cs.ext.MacArabic;

@Service("machine")
public class MachineServiceImp extends BaseServiceImp<MachineBean> implements MachineService {

	@Autowired
	private MachineDao dao;

	@Override
	public int insertBean(MachineBean o) {
		return dao.insertBean(o);
	}
	

	@Override
	public void deleteBatch(String chks) {
		// 事务删除
		if (StringUtils.isNotBlank(chks)) {
			String[] chk = chks.split(",");
			List<MachineBean> list = new ArrayList<MachineBean>();
			for (String s : chk) {
				try {
					// int id = Integer.parseInt(s);
					MachineBean sd = new MachineBean();
					sd.setId(s);
					list.add(sd);
				} catch (Exception e) {
				}
			}
			dao.deleteBatch(list);
		}
	}

	@Override
	public void insertQRCode(MachineBean o) {
		dao.insertQRCode(o);
	}

	@Override
	public String findStockNums(MachineBean o) {
		String stockNums = dao.findStockNums(o);
		return stockNums;
	}

	@Override
	public List<MachineBean> findByTaskId(String taskId, String maTypeId,String deviceCode,String batchStatus) {
		return dao.findByTaskId(taskId, maTypeId,deviceCode,batchStatus);
	}

	@Override
	public void inPuting(MachineBean o) {
		dao.inPuting(o);
	}

	@Override
	public List<MachineBean> findType(MachineBean o) {
		List<MachineBean> list = dao.findType(o);
		return list;
	}

	@Override
	public List<MachineBean> findSums(MachineBean o) {
		List<MachineBean> list = dao.findSums(o);
		return list;
	}

	@Override
	public List<MachineBean> findModel(MachineBean o) {
		List<MachineBean> list = dao.findModel(o);
		return list;
	}

	@Override
	public void deleteByTaskIdAndNums(String taskId, String maTypeId, float nums) {
		dao.deleteByTaskIdAndNums(taskId, maTypeId, nums);
	}

	@Override
	public List<MachineBean> getMachineStatus() {
		return dao.getMachineStatus();
	}

	@Override
	public List<MachineBean> findByCode(MachineBean o) {
		return dao.findByCode(o);
	}

	@Override
	public MachineBean findByQrcode(MachineBean o) {
		return dao.findByQrcode(o);
	}

	@Override
	public void binding(MachineBean o) {
		dao.binding(o);
	}

	@Override
	public void unbinding(MachineBean o) {
		dao.unbinding(o);
	}

	@Override
	public MachineBean findByEpc(String epc) {
		return dao.findByEpc(epc);
	}
	@Override
	public MachineBean updateInput(String batchStatus) {
		return dao.updateInput(batchStatus);
	}
	@Override
	public MachineBean updateOut(String batchStatus) {
		return dao.updateOut(batchStatus);
	}
	@Override
	public List<MachineBean> findCode(MachineBean o) {
		return dao.findCode(o);
	}

	@Override
	public void updateCode(MachineBean o) {
		dao.updateCode(o);
	}

	@Override
	public List<MachineBean> findGps(MachineBean o) {
		return dao.findGps(o);
	}

	@Override
	public List<MachineBean> findDev(MachineBean o) {
		return dao.findDev(o);
	}

	@Override
	public int updateByEpc(MachineBean bean) {
		return dao.updateByEpc(bean);
	}

	@Override
	public List<MachineBean> findByOutFactortNum(MachineBean o) {
		return dao.findByOutFactortNum(o);
	}

	@Override
	public MachineBean findById(String id) {
		return dao.findById(id);
	}

	@Override
	public void updateCodeByQrcode(MachineBean o) {
		dao.updateCodeByQrcode(o);
	}

	@Override
	public List<MachineBean> getBuyCompany() {
		return dao.getBuyCompany();

	}

	@Override
	public List<MachineBean> export(MachineBean o) {
		return dao.export(o);
	}

	@Override
	public void isFixedAssets(MachineBean o) {
		dao.isFixedAssets(o);
	}

	@Override
	public String findOneNoBinding(MachineBean o) {
		return dao.findOneNoBinding(o);
	}

	@Override
	public void intoByQrcode(MachineBean o) {
		dao.intoByQrcode(o);
	}

	@Override
	public MachineBean findByDevQrcode(MachineBean o) {
		return dao.findByDevQrcode(o);
	}

	@Override
	public MachineBean findByAccountName(MachineBean bean) {
		return dao.findByAccountName(bean);
	}

	@Override
	public List<MachineBean> findCodeByEpc(MachineBean o) {
		return dao.findCodeByEpc(o);
	}

	@Override
	public void machineBinding(MachineBean o) {
		dao.machineBinding(o);
		
	}

	@Override
	public void addBuyTime(MachineBean o) {
		dao.addBuyTime(o);
	}

	@Override
	public void updateMachineStatus(MachineBean o) {
		dao.updateMachineStatus(o);
		
	}

	@Override
	public MachineBean findByDeviceCode(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.findByDeviceCode(o);
	}

	@Override
	public void updAcpNum(String taskId, String maTypeId, String checkNum) {
		dao.updAcpNum(taskId, maTypeId, checkNum);
	}

	@Override
	public List<MachineBean> machinePicking(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.machinePicking(o);
	}

	@Override
	public List<MachineBean> machineMaterialReturn(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.machineMaterialReturn(o);
	}
	
	@Override
	public List<MachineBean> machineRepair(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.machineRepair(o);
	}

	@Override
	public List<MachineBean> machineOverhaul(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.machineOverhaul(o);
	}

	@Override
	public List<MachineBean> machineWarehousing(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.machineWarehousing(o);
	}

	@Override
	public int updTime(String ckeck, String nowchecktime, String nextchecktime) {
		// TODO Auto-generated method stub
		return dao.updTime(ckeck,nowchecktime,nextchecktime);
	}

	@Override
	public int insCertificateBuId(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.insCertificateBuId(o);
	}

	@Override
	public int insMaterialsById(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.insMaterialsById(o);
	}

	@Override
	public List<MachineBean> findCertificateById(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.findCertificateById(o);
	}

	@Override
	public int updCertificateBuId(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.updCertificateBuId(o);
	}

	@Override
	public int updMaterialsById(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.updMaterialsById(o);
	}

	@Override
	public int updProceduresById(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.updProceduresById(o);
	}

	@Override
	public int insProceduresById(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.insProceduresById(o);
	}
    
	
	@Override
	public MachineBean findByLikeCode(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.findByLikeCode(o);
	}

	@Override
	public List<MachineBean> getCode(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.getCode(o);
	}

	@Override
	public List<MachineBean> findByAssetNum(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.findByAssetNum(o);
	}

	@Override
	public void updateType(MachineBean o) {
		dao.updateType(o);
		
	}

	@Override
	public List<MachineBean> findByType(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.findByType(o);
	}

	@Override
	public List<MachineBean> machineScrap(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.machineScrap(o);
	}


	@Override
	public List<OutStorageBean> getAlOutStorageInfoList(OutStorageBean o) {
		// TODO Auto-generated method stub
		return dao.getAlOutStorageInfoList(o);
	}

	@Override
	public List<MachineBean> findFile(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.findFile(o);
	}



	@Override
	public int insOpmanualById(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.insOpmanualById(o);
	}


	@Override
	public List<MachineBean> findMachineType(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.findMachineType(o);
	}


	@Override
	public List<MachineBean> findMachineTypeId(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.findMachineTypeId(o);
	}


	@Override
	public int updateOptInfo(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.updateOptInfo(o);
	}


	@Override
	public List<MachineBean> findhistory(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.findhistory(o);
	}


	@Override
	public List<MachineBean> exporthistory(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.exporthistory(o);
	}


	@Override
	public List<MachineBean> findPhotoList(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.findPhotoList(o);
	}


	@Override
	public List<MachineBean> findFileList(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.findFileList(o);
	}


	@Override
	public List<MachineBean> findInfoData(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.findInfoData(o);
	}


	@Override
	public MachineBean findByQrcodePage(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.findByQrcodePage(o);
	}


	@Override
	public int updateOpmanual(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.updateOpmanual(o);
	}


	@Override
	public List<MachineBean> findOptPhotoList(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.findOptPhotoList(o);
	}
	
	@Override
	public int updateMachinesUrl(MachineBean o) {
		// TODO Auto-generated method stub
		return dao.updateMachinesUrl(o);
	}


	@Override
	public Integer updateInfo(List<List<String>> info, MachineBean o) {
		// TODO Auto-generated method stub
		Integer result = 0;
		int size = info.size();
		for(int i=1;i<size;i++){
			Integer res = 0;
			String typeName=info.get(i).get(0);
			String machineName = info.get(i).get(1);
			o.setTypeName(typeName);
			o.setMachineName(machineName);
			MachineBean bean1 = dao.findIdByName(o);
			String typeId =bean1.getTypeId();
			Integer userId = UserShiroHelper.getRealCurrentUser().getId();
			String status = "5";
			String code =info.get(i).get(4);
			String remark = info.get(i).get(6);
			String property = info.get(i).get(2);
			String use = info.get(i).get(3);
			String assetNum = "";
			if(info.get(i).size()>7){
			if(info.get(i).get(7) != null ||info.get(i).get(7) != ""){
				assetNum = info.get(i).get(7);
			}else{
				assetNum = "";
			}
			}
			MachineBean bean = new MachineBean();
			bean.setType(typeId);
			bean.setCreator(userId);
			bean.setBatchStatus(status);
			bean.setAssetsNum(assetNum);
			bean.setRemarks(remark);
			bean.setPropertyDepartment(property);
			bean.setUseDepartment(use);
			bean.setDeviceCode(code);
			res=dao.insert(bean);
			if(res==0){
				throw new ZeroAffectRowsException("设备/工器具入库失败,工器具入库信息基本表插入操作错误!");
			}
		}
		result=1;
		return result;
	}
}
