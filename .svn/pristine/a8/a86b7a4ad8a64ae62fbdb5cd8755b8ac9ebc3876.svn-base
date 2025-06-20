package com.bonus.ma.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bonus.ma.beans.MachineTypeBean;
import com.bonus.sys.BaseService;
import com.bonus.sys.Page;
import com.bonus.sys.beans.ZNode;

public interface MachineTypeService extends BaseService<MachineTypeBean> {

	List<ZNode> getMainTree(MachineTypeBean o);

	void treeInsert(MachineTypeBean o);

	int treeUpdate(MachineTypeBean o);

	int treeDelete(MachineTypeBean o);

	List<ZNode> getRoleBeans();

	MachineTypeBean findLastId();

	List<MachineTypeBean> findFirstName(MachineTypeBean o);

	Page<MachineTypeBean> findDetails(MachineTypeBean o, Page<MachineTypeBean> page);

	List<MachineTypeBean> findStoreDetails(MachineTypeBean o);

	List<MachineTypeBean> findStore(MachineTypeBean o);

	List<MachineTypeBean> findModel(MachineTypeBean o);

	void updateModel(MachineTypeBean o);

	List<MachineTypeBean> findMaTypeMsg(MachineTypeBean o);

	List<MachineTypeBean> findChilds(MachineTypeBean o);

	List<ZNode> maTypeTree(MachineTypeBean o);

	List<ZNode> maModelTree(MachineTypeBean o);

	Object uploadFile(HttpServletRequest request, MachineTypeBean o);

	MachineTypeBean findById(String id);

	List<MachineTypeBean> findNums(MachineTypeBean o); // 库存数量

	List<MachineTypeBean> findHouseNums(MachineTypeBean o); // 库存数量

	public void updateNums(MachineTypeBean o); // 修改库存数量

	Page<MachineTypeBean> findWarnModel(MachineTypeBean o,Page<MachineTypeBean> page);

	MachineTypeBean findTopClass(MachineTypeBean o);

	void addBuyTime(MachineTypeBean o);

	List<MachineTypeBean> findTestData();

	MachineTypeBean getIdByThreeName(MachineTypeBean ma);

	void updateTestDataById(MachineTypeBean type);

	List<MachineTypeBean> findZulinTest();

	void insetOne(MachineTypeBean type);

	void insetTwo(MachineTypeBean type1);

	void insetThree(MachineTypeBean o);

	void insertOrgRelation(MachineTypeBean o);

	void updateOrgModel(MachineTypeBean o);

	void deleteOrgRelation(MachineTypeBean o);

	void updateWarnValue(MachineTypeBean o);

	List<MachineTypeBean> typeNameList(MachineTypeBean o);

	List<MachineTypeBean> nameList(MachineTypeBean o);

}
