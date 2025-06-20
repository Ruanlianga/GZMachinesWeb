package com.bonus.ma.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bonus.core.DateTimeHelper;
import com.bonus.ma.beans.VenderBean;
import com.bonus.ma.dao.VenderDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.beans.ZNode;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service("vender")
public class VenderServiceImp extends BaseServiceImp<VenderBean> implements VenderService{

	@Autowired VenderDao dao;
	
	@Override
	public int insertBean(VenderBean o) {
		return dao.insertBean(o);
	}

	@Override
	public void deleteBatch(String chks) {
		// 事务删除
		if (StringUtils.isNotBlank(chks)) {
			String[] chk = chks.split(",");
			List<VenderBean> list = new ArrayList<VenderBean>();
			for (String s : chk) {
				try {
					//int id = Integer.parseInt(s);
					VenderBean sd = new VenderBean();
					sd.setId(s);
					list.add(sd);
				} catch (Exception e) {
				}
			}
			dao.deleteBatch(list);
		}
	}
	
	@Override
	public List<VenderBean> findVender(VenderBean o) {
		List<VenderBean> list = dao.findVender(o);
		return list;
	}

	@Override
	public List<ZNode> maVenderTree(VenderBean o) {
		return dao.maVenderTree(o);
	}
	

	@Override
	public void updateVend(VenderBean o) {
		// TODO Auto-generated method stub
		dao.updateVend(o);
	}

	@Override
	public List<ZNode> makeeper(VenderBean o) {
		return dao.makeeper(o);
	}

}
