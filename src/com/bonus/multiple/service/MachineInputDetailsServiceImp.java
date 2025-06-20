package com.bonus.multiple.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.multiple.beans.MachineInputDetailsBean;
import com.bonus.multiple.dao.MachineInputDetailsDao;
import com.bonus.sys.BaseServiceImp;

@Service("MachineInputDetailsService")
public class MachineInputDetailsServiceImp extends BaseServiceImp<MachineInputDetailsBean>
		implements MachineInputDetailsService {

	@Autowired
	private MachineInputDetailsDao dao;

	@Override
	public List<MachineInputDetailsBean> findInputDetails(MachineInputDetailsBean o) {
		return dao.findInputDetails(o);
	}

}
