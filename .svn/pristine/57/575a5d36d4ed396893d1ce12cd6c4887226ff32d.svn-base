package com.bonus.newInput.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.newInput.beans.NewInputQrcodeBean;
import com.bonus.sys.BaseDao;
import com.bonus.sys.Page;

@BonusBatis
public interface NewInputQrcodeDao extends BaseDao<NewInputQrcodeBean> {

	List<NewInputQrcodeBean> findQrcodeByTaskId(@Param("param") NewInputQrcodeBean o, Page<NewInputQrcodeBean> page);

	List<NewInputQrcodeBean> findByQrcode(NewInputQrcodeBean o);

	List<NewInputQrcodeBean> findByDevCode(NewInputQrcodeBean o);

	List<NewInputQrcodeBean> putInStorageList(@Param("param") NewInputQrcodeBean o);

	List<NewInputQrcodeBean> putInStorageDetails(@Param("param") NewInputQrcodeBean o);

	List<NewInputQrcodeBean> findNewInputList(@Param("param") NewInputQrcodeBean o);

	List<NewInputQrcodeBean> findNewInputListDetails(@Param("param") NewInputQrcodeBean o);

	void insertInfoRecord(NewInputQrcodeBean o);

	NewInputQrcodeBean findAlInputInfo(NewInputQrcodeBean o);

	void updateAlInputNum(NewInputQrcodeBean o);

	List<NewInputQrcodeBean> findNewInputByNum(NewInputQrcodeBean o);

	List<NewInputQrcodeBean> newBindingRecord(NewInputQrcodeBean o);

	List<NewInputQrcodeBean> newInputRecord(NewInputQrcodeBean o);

	NewInputQrcodeBean findAlBindNum(NewInputQrcodeBean o);

	void updateNewBindTask(NewInputQrcodeBean o);

	void updateNewInputTask(NewInputQrcodeBean o);

	List<NewInputQrcodeBean> findIsSure(NewInputQrcodeBean o);

	void updateNewTask(NewInputQrcodeBean o);

	List<NewInputQrcodeBean> findInfoByDeviceCode(NewInputQrcodeBean o);

	List<NewInputQrcodeBean> newInputRecordStroageRack(NewInputQrcodeBean o);

	int findByDevCodeIsExist(NewInputQrcodeBean o);


}
