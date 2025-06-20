package com.bonus.index.service;

import java.util.List;
import java.util.Map;

import com.bonus.index.beans.IndexDetailVo;
import com.bonus.index.beans.IndexHomeBean;
import com.bonus.index.beans.IndexHomeResourseBean;
import com.bonus.index.beans.IndexProAndNum;
import com.bonus.index.beans.PartFiveBean;
import com.bonus.index.beans.PartSixBean;
import com.bonus.index.beans.PartThreeBean;
import com.bonus.sys.BaseService;
import com.github.pagehelper.PageInfo;

public interface IndexHomeService extends BaseService<IndexHomeBean>{

	List<IndexHomeBean> getPartOneData(IndexHomeBean o);

	List<IndexHomeBean> getPartTwoData(IndexHomeBean o);

	List<PartThreeBean> getPartThreeData(IndexHomeBean o);

	List<IndexHomeBean> getPartFourData(IndexHomeBean o);

	List<PartFiveBean> getPartFiveData(IndexHomeBean o);

	List<PartSixBean> getPartSixData(IndexHomeBean o);

	void insertIndexInfo();

	List<IndexHomeResourseBean> getHomeResource(IndexHomeResourseBean o);

	List<IndexHomeResourseBean> getResource(IndexHomeResourseBean o);

	int saveResourse(IndexHomeResourseBean o);

	/**
	 * 查询工程领料、退料、维修检验、机具报废、修试后入库、新购入库、库存盘点数量
	 * @param o
	 * @return
	 */
	PageInfo<IndexDetailVo> getProAndNum(IndexHomeBean o);
	
}
