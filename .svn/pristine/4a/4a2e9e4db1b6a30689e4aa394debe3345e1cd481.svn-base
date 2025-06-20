package com.bonus.ma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.ma.beans.MachineVersionBean;
import com.bonus.ma.dao.MachineVersionDao;
import com.bonus.sys.BaseServiceImp;

@Service("machineVer")
public class MachineVersionServiceImp extends BaseServiceImp<MachineVersionBean> implements MachineVersionService {

	@Autowired
	private MachineVersionDao dao;

	@Override
	public String getDeviceType(String typeId) {
		String deviceType = "";
		try {
			//获取设备类型集合
			List<MachineVersionBean> list = dao.getDeviceTypeList();
			//根据规格id获取设备类型id
			String typeB = dao.getDeviceType(typeId);
			
			for(MachineVersionBean vb : list){
				String typeA = vb.getTypeId();
				String type = vb.getType();
				if(typeA.equals(typeB)){
					deviceType = type;
					break;
				}
			}
		} catch (Exception e) {
		     System.err.println(e.toString());
		}
	
		return deviceType;
	}





}
