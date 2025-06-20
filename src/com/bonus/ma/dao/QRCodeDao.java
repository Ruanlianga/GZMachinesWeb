package com.bonus.ma.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.ma.beans.QRCodeBean;
import com.bonus.newInput.beans.NewInputQrcodeBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface QRCodeDao extends BaseDao<QRCodeBean> {

	List<QRCodeBean> findQRCode(QRCodeBean o);

	List<QRCodeBean> findQRCodeInfo(QRCodeBean o);

	List<QRCodeBean> findQRCodeByTaskType(QRCodeBean o);

	List<QRCodeBean> findAllCode();

	List<QRCodeBean> findQrcodeByTaskId(@Param("param") QRCodeBean o);
	
	
	List<NewInputQrcodeBean> findQrcodeByTaskIdApp(@Param("param") NewInputQrcodeBean o);
	
	List<QRCodeBean> findAll(QRCodeBean o);

	List<QRCodeBean> findAllDetails(QRCodeBean o);

}
