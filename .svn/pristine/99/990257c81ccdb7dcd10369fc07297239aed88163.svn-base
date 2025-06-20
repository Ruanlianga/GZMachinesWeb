package com.bonus.ma.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bonus.lease.beans.OutStorageBean;
import com.bonus.ma.beans.MachineBean;
import com.bonus.sys.BaseService;
import com.bonus.sys.Page;

public interface MachineService extends BaseService<MachineBean>{
	
	public int insertBean(MachineBean o);
	
	public void deleteBatch(String chks);
	
	public void insertQRCode(MachineBean o);
	
	public List<MachineBean> findGps(MachineBean o);
	
	public List<MachineBean> findDev(MachineBean o);
	
	public List<MachineBean> findCode(MachineBean o);
	
	public void updateCode(MachineBean o);
	
	public String findStockNums(MachineBean o);

	public List<MachineBean> findByTaskId(String taskId,String maTypeId,String deviceCode,String batchStatus);

	public void inPuting(MachineBean o);
	
	public List<MachineBean> findType(MachineBean o);
	
	public List<MachineBean> findSums(MachineBean o);

	public List<MachineBean> findModel(MachineBean o);

	public void deleteByTaskIdAndNums(String taskId,String maTypeId, float nums);
	
	public List<MachineBean> getMachineStatus();
	
	public List<MachineBean> findByCode(MachineBean o);

	public MachineBean findByQrcode(MachineBean o);

	public void binding(MachineBean o);

	public void unbinding(MachineBean o);

	public MachineBean findByEpc(String epc);
	
    public MachineBean updateInput(String batchStatus);
    
    public MachineBean updateOut(String batchStatus);
    
	public int updateByEpc(MachineBean bean);
	
	public List<MachineBean> findByOutFactortNum(MachineBean o);

	public MachineBean findById(String id);

	public void updateCodeByQrcode(MachineBean o);

	public List<MachineBean> getBuyCompany();

	public List<MachineBean> export(MachineBean o);
	
	public void isFixedAssets(MachineBean o);

	public String findOneNoBinding(MachineBean o);

	public void intoByQrcode(MachineBean o);

	public MachineBean findByDevQrcode(MachineBean o);

	public MachineBean findByAccountName(MachineBean bean);

	public List<MachineBean> findCodeByEpc(MachineBean o);

	public void machineBinding(MachineBean o);

	public void addBuyTime(MachineBean o);

	public void updateMachineStatus(MachineBean bean);

	public MachineBean findByDeviceCode(MachineBean o);

	public void updAcpNum(String taskId, String maTypeId, String checkNum);
	
	public List<MachineBean> machinePicking(MachineBean o);
	
	public List<MachineBean> machineMaterialReturn(MachineBean o);
	
	public List<MachineBean> machineRepair(MachineBean o);
	
	public List<MachineBean> machineOverhaul(MachineBean o);

	public List<MachineBean> machineWarehousing(MachineBean o);

	public int updTime(String check, String nowchecktime, String nextchecktime);

	public int insCertificateBuId(MachineBean o);

	public int insMaterialsById(MachineBean o);

	public List<MachineBean> findCertificateById(MachineBean o);

	public int updCertificateBuId(MachineBean o);

	public int updMaterialsById(MachineBean o);

	public int updProceduresById(MachineBean o);

	public int insProceduresById(MachineBean o);

	public MachineBean findByLikeCode(MachineBean o);

	public List<MachineBean> getCode(MachineBean o);

	public List<MachineBean> findByAssetNum(MachineBean o);

	public void updateType(MachineBean o);

	public List<MachineBean> findByType(MachineBean o);

	public List<MachineBean> machineScrap(MachineBean o);

	public List<OutStorageBean> getAlOutStorageInfoList(OutStorageBean o);

	public List<MachineBean> findFile(MachineBean o);
	
	public List<MachineBean> findInfoData(MachineBean o);

	public int insOpmanualById(MachineBean o);
	
	public List<MachineBean> findMachineType(MachineBean o);
	
	public List<MachineBean> findMachineTypeId(MachineBean o);

	public int updateOptInfo(MachineBean o);

	public List<MachineBean> findhistory(MachineBean o);

	public List<MachineBean> exporthistory(MachineBean o);

	public List<MachineBean> findPhotoList(MachineBean  o);

	public List<MachineBean> findFileList(MachineBean o);

	public MachineBean findByQrcodePage(MachineBean o);

	public int updateOpmanual(MachineBean o);

	public List<MachineBean> findOptPhotoList(MachineBean o);

	public Integer updateInfo(List<List<String>> info, MachineBean o);

	public int updateMachinesUrl(MachineBean o);

	
}
