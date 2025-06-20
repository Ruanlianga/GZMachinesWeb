package com.bonus.ma.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.lease.beans.OutStorageBean;
import com.bonus.ma.beans.MachineBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface MachineDao extends BaseDao<MachineBean> {

	public int insertBean(MachineBean o);

	public void insertQRCode(MachineBean o);

	public String findStockNums(MachineBean o);
	
	public List<MachineBean> findGps(MachineBean o);
	
	public List<MachineBean> findDev(MachineBean o);
	
	public List<MachineBean> findCode(MachineBean o);
	
	public void updateCode(MachineBean o);
	
	public void insertMachinesUrl(MachineBean o);

	public List<MachineBean> findByTaskId(@Param("taskId") String taskId, @Param("maTypeId") String maTypeId, @Param("deviceCode") String deviceCode,@Param("batchStatus") String batchStatus);

	public void inPuting(MachineBean o);

	public List<MachineBean> findType(MachineBean o);

	public List<MachineBean> findSums(MachineBean o);

	public List<MachineBean> findModel(MachineBean o);

	public void deleteByTaskIdAndNums(@Param("taskId") String taskId, @Param("maTypeId") String maTypeId,
			@Param("nums") float nums);

	public List<MachineBean> getMachineStatus();// 获取机具状态

	public List<MachineBean> findByCode(MachineBean o);

	public MachineBean findByQrcode(MachineBean o);

	public void binding(MachineBean o);

	public void unbinding(MachineBean o);

	public MachineBean findByEpc(String epc);
	
    public MachineBean updateInput(String batchStatus);

    public MachineBean updateOut(String batchStatus);
    
	public int updateByEpc(MachineBean bean);
    
	public List<MachineBean> findByOutFactortNum(MachineBean o);

	public MachineBean findById(String maId);

	public void updateCodeByQrcode(MachineBean o);

	public List<MachineBean> getBuyCompany();
	
	public List<MachineBean> export(@Param("param")MachineBean o);
	
	public void isFixedAssets(MachineBean o);

	public String findOneNoBinding(MachineBean o);

	public void intoByQrcode(MachineBean o);

	public MachineBean findByDevQrcode(MachineBean o);

	public MachineBean findByAccountName(MachineBean bean);

	public List<MachineBean> findCodeByEpc(MachineBean o);

	public void machineBinding(MachineBean o);

	public void addBuyTime(MachineBean o);

	public void updateMachineStatus(MachineBean o);

	public MachineBean findByDeviceCode(MachineBean o);

	public MachineBean findMachineNum(MachineBean maBean);

	public void updByCode(MachineBean bean);

	public void updAcpNum(String taskId, String maTypeId, String checkNum);
	
	public List<MachineBean> machineMaterialReturn(MachineBean o);
	
	public List<MachineBean> machinePicking(MachineBean o);
	
	public List<MachineBean> machineRepair(MachineBean o);
	
	public List<MachineBean> machineOverhaul(MachineBean o);
	
	public List<MachineBean> machineWarehousing(MachineBean o);

	public int updateBatchStatus(MachineBean bean);

	public int updTime(String ckeck, String nowchecktime, String nextchecktime);

	public int insCertificateBuId(MachineBean o);

	public int insMaterialsById(MachineBean o);

	public List<MachineBean> findCertificateById(MachineBean o);

	public int updMaterialsById(MachineBean o);

	public int updProceduresById(MachineBean o);

	public int insProceduresById(MachineBean o);

	public MachineBean findByLikeCode(MachineBean o);

	public List<MachineBean> getCode(MachineBean o);

	public int updCertificateBuId(MachineBean o);

	public List<MachineBean> findByAssetNum(MachineBean o);

	public void updateType(MachineBean o);
	
	public List<MachineBean> findByType(MachineBean o);

	public List<MachineBean> machineScrap(MachineBean o);

	public List<MachineBean> findFile(MachineBean o);

	public List<OutStorageBean> getAlOutStorageInfoList(OutStorageBean o);

    
	public int insOpmanualById(MachineBean o);
	
    public List<MachineBean> findMachineType(MachineBean o);
	
	public List<MachineBean> findMachineTypeId(MachineBean o);
	
	public List<MachineBean> findInfoData(MachineBean o);

	public int updateOptInfo(MachineBean o);
	
	public List<MachineBean> findhistory(MachineBean o);

	public List<MachineBean> exporthistory(MachineBean o);

	public List<MachineBean> findPhotoList(MachineBean o);
	
	public List<MachineBean> findFileList(MachineBean o);

	public MachineBean findByQrcodePage(MachineBean o);
	
	public int updateOpmanual(MachineBean o);
	
	public List<MachineBean> findOptPhotoList(MachineBean o);

	public MachineBean findIdByName(MachineBean o);

	public int updateMachinesUrl(MachineBean o);


}
