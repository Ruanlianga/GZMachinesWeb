package com.bonus.ma.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.ma.beans.MachineTypeBean;
import com.bonus.sys.BaseDao;
import com.bonus.sys.Page;
import com.bonus.sys.beans.ZNode;

@BonusBatis
public interface MachineTypeDao extends BaseDao<MachineTypeBean> {

	List<ZNode> getMainTree(MachineTypeBean o);

	void treeInsert(MachineTypeBean o);

	int treeUpdate(MachineTypeBean o);

	int treeDelete(MachineTypeBean o);

	public List<ZNode> getRoleBeans();

	MachineTypeBean findLastId();

	public List<MachineTypeBean> findFirstName(MachineTypeBean o);

	List<MachineTypeBean> findDetails(@Param("param") MachineTypeBean o, Page<MachineTypeBean> page);

	List<MachineTypeBean> findStore(MachineTypeBean o);
	
	List<MachineTypeBean> findStoreDetails(MachineTypeBean o);
	
	List<MachineTypeBean> findModel(MachineTypeBean o);

	void updateModel(MachineTypeBean o);

	List<MachineTypeBean> findMaTypeMsg(MachineTypeBean o);

	List<MachineTypeBean> findChilds(MachineTypeBean o);

	List<ZNode> maTypeTree(@Param("param") MachineTypeBean o);

	// syruan 优化maTypeTree查询
	List<ZNode> maTypeTreeCope(@Param("param") MachineTypeBean o);

	List<ZNode> maModelTree(MachineTypeBean o);

	MachineTypeBean findById(String id);
	
	List<MachineTypeBean> findNums(MachineTypeBean o); //查询库存数量
	List<MachineTypeBean> findHouseNums(MachineTypeBean o); //查询库管员库存数量
	
	public void updateNums(MachineTypeBean o);  //修改库存数量

	List<MachineTypeBean> findWarnModel(@Param("param") MachineTypeBean o,Page<MachineTypeBean> page);

	MachineTypeBean findTopClass(MachineTypeBean o);

	void addBuyTime(MachineTypeBean o);

	MachineTypeBean findByModelId(MachineTypeBean mtBean);

	List<MachineTypeBean> findTestData();

	MachineTypeBean getIdByThreeName(MachineTypeBean ma);

	void updateTestDataById(MachineTypeBean type);

	List<MachineTypeBean> findZulinTest();

	void insetTwo(MachineTypeBean o);

	void insetThree(MachineTypeBean o);

	void insetOne(MachineTypeBean type);

	void insertOrgRelation(MachineTypeBean o);

	void updateOrgModel(MachineTypeBean o);

	void deleteOrgRelation(MachineTypeBean o);

	void updateStorageNums(MachineTypeBean type);
	
	int updateStorageNum(MachineTypeBean o);
	
	void updateWarnValue(MachineTypeBean o);

	List<MachineTypeBean> typeNameList(@Param("param") MachineTypeBean o);

	List<MachineTypeBean> nameList(@Param("param") MachineTypeBean o);
	
	
	
}
