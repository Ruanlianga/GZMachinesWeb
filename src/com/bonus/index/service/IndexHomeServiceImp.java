package com.bonus.index.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.core.DateTimeHelper;
import com.bonus.index.beans.IndexDetailVo;
import com.bonus.index.beans.IndexHomeBean;
import com.bonus.index.beans.IndexHomeResourseBean;
import com.bonus.index.beans.IndexProAndNum;
import com.bonus.index.beans.PartFiveBean;
import com.bonus.index.beans.PartOneBean;
import com.bonus.index.beans.PartSixBean;
import com.bonus.index.beans.PartThreeBean;
import com.bonus.index.beans.PartTwoBean;
import com.bonus.index.dao.IndexHomeDao;
import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.UserShiroHelper;
import com.bonus.sys.beans.UserBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("indexHome")
public class IndexHomeServiceImp extends BaseServiceImp<IndexHomeBean> implements IndexHomeService{

	@Autowired
	private IndexHomeDao dao;

	@Override
	public List<IndexHomeBean> getPartOneData(IndexHomeBean o) {
		
		return dao.getPartOneData(o);
	}

	@Override
	public List<IndexHomeBean> getPartTwoData(IndexHomeBean o) {
		
		return dao.getPartTwoData(o);
	}

	@Override
	public List<PartThreeBean> getPartThreeData(IndexHomeBean o) {
		
		return dao.getPartThreeData(o);
	}

	@Override
	public List<IndexHomeBean> getPartFourData(IndexHomeBean o) {
		
		return dao.getPartFourData(o);
	}

	@Override
	public List<PartFiveBean> getPartFiveData(IndexHomeBean o) {
	
		return dao.getPartFiveData(o);
	}

	@Override
	public List<PartSixBean> getPartSixData(IndexHomeBean o) {
		List<PartSixBean> partSixData = dao.getPartSixData(o);
		for (int i = 0; i < partSixData.size(); i++) {
			PartSixBean partSixBean = partSixData.get(i);
			o.setTime(partSixBean.getDate());
			List<IndexDetailVo> proAndNum = dao.getProAndNum(o);
			if(CollectionUtils.isNotEmpty(proAndNum)) {
				StringBuffer sb = new StringBuffer();
				for (int j = 0; j < proAndNum.size(); j++) {
					IndexDetailVo vo = proAndNum.get(j);
					sb.append(vo.getProName()).append(": 领料:").append(vo.getLeaseNum()).append(", 退料:").append(vo.getBackNum()).append("\t\n").append("\t\n");
				}
				System.err.println(sb.toString());
				partSixBean.setProAndNum(sb.toString());
			}
		}
		return partSixData;
	}

	@Override
	public void insertIndexInfo() {
		
		String res = "";
		int rs = 0;
		try {
			rs  = createOver();
	        
			rs = createInput();
		
		
			res = "1";
		} catch (Exception e) {
			logger.error(e.toString(), e);
			res = "-1";
		}
	}

	private int createInput() {
		int rs = 0;
		PartTwoBean two = dao.getInputview();
		
		PartTwoBean type = dao.getTypeview();
		
		two.setEquipmentCount(type.getEquipmentCount());
		two.setTime(DateTimeHelper.getNowTime());
		two.setDeviceCount(type.getDeviceCount());
		rs = dao.insertPartTwo(two);
		return rs;
	}

	private int createOver() {
		int rs = 0;
		PartOneBean one = dao.getOverview();
		one.setTime(DateTimeHelper.getNowTime());
		rs = dao.insertPartOne(one);
		return rs;
	}

	@Override
	public List<IndexHomeResourseBean> getHomeResource(IndexHomeResourseBean o) {
		
		return dao.getHomeResource(o);
	}

	@Override
	public List<IndexHomeResourseBean> getResource(IndexHomeResourseBean o) {
		return dao.getResource(o);
	}

	@Override
	public int saveResourse(IndexHomeResourseBean o) {

		List<String> list = Arrays.asList(o.getList());
		if(list !=null && list.size()>0){
			dao.deleteResourse(o);
			 
			for(int i=0 ; i<list.size(); i++){
				String rsId = list.get(i) ;
				o.setRsId(rsId);
				dao.insertResourse(o);
			}
		}
		
		return 0;
	}

	@Override
	public PageInfo<IndexDetailVo> getProAndNum(IndexHomeBean o) {
		PageHelper.startPage(o.getPage(), o.getLimit());
		List<IndexDetailVo> list = new ArrayList<IndexDetailVo>();
		try {
			list = dao.getProAndNum(o);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			e.printStackTrace();
		}
        PageInfo<IndexDetailVo> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	
	
	
	
}

