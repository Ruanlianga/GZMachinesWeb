package com.bonus.ma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.ma.beans.QRCodeBean;
import com.bonus.ma.dao.QRCodeDao;
import com.bonus.newInput.beans.NewInputQrcodeBean;
import com.bonus.sys.BaseServiceImp;

@Service("QRCodeService")
public class QRCodeServiceImp extends BaseServiceImp<QRCodeBean> implements QRCodeService {

	@Autowired
	private QRCodeDao dao;

	@Override
	public List<QRCodeBean> findQRCode(QRCodeBean o) {
		return dao.findQRCode(o);
	}

	@Override
	public List<QRCodeBean> findQRCodeInfo(QRCodeBean o) {
		return dao.findQRCodeInfo(o);
	}

	@Override
	public List<QRCodeBean> findQRCodeByTaskType(QRCodeBean o) {
		return dao.findQRCodeByTaskType(o);
	}

	@Override
	public List<QRCodeBean> findAllCode() {
		return dao.findAllCode();
	}

	@Override
	public List<QRCodeBean> findQrcodeByTaskId(QRCodeBean o) {
		return dao.findQrcodeByTaskId(o);
	}

	@Override
	public List<QRCodeBean> findAll(QRCodeBean o) {
		return dao.findAll(o);
	}

	@Override
	public List<QRCodeBean> findAllDetails(QRCodeBean o) {
		return dao.findAllDetails(o);
	}

	@Override
	public List<NewInputQrcodeBean> findQrcodeByTaskIdApp(NewInputQrcodeBean o) {
		return dao.findQrcodeByTaskIdApp(o);
	}

}
