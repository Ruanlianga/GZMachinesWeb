package com.bonus.newInput.service;

import java.util.List;

import com.bonus.newInput.beans.NewInputCheckBean;
import com.bonus.newInput.beans.NewInputQrcodeBean;
import com.bonus.sys.BaseService;
import com.bonus.sys.Page;

public interface NewInputQrcodeService extends BaseService<NewInputQrcodeBean> {

	int isSure(NewInputCheckBean o);

	Page<NewInputQrcodeBean> findQrcodeByTaskId(Page<NewInputQrcodeBean> page, NewInputQrcodeBean o);

	int building(NewInputQrcodeBean o);

	List<NewInputQrcodeBean> findByQrcode(NewInputQrcodeBean o);

	List<NewInputQrcodeBean> findByDevCode(NewInputQrcodeBean o);

	List<NewInputQrcodeBean> putInStorageList(NewInputQrcodeBean o);

	List<NewInputQrcodeBean> putInStorageDetails(NewInputQrcodeBean o);

	List<NewInputQrcodeBean> findNewInputList(NewInputQrcodeBean o);

	List<NewInputQrcodeBean> findNewInputListDetails(NewInputQrcodeBean o);

	int confirmStorage(NewInputQrcodeBean o);

	List<NewInputQrcodeBean> findNewInputByNum(NewInputQrcodeBean o);

	List<NewInputQrcodeBean> newBindingRecord(NewInputQrcodeBean o);

	List<NewInputQrcodeBean> newInputRecord(NewInputQrcodeBean o);

	List<NewInputQrcodeBean> newInputRecordStroageRack(NewInputQrcodeBean o);



}
