package com.bonus.index.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.core.BonusBatis;
import com.bonus.index.beans.IndexDetailVo;
import com.bonus.index.beans.IndexHomeBean;
import com.bonus.index.beans.IndexHomeResourseBean;
import com.bonus.index.beans.IndexProAndNum;
import com.bonus.index.beans.PartFiveBean;
import com.bonus.index.beans.PartOneBean;
import com.bonus.index.beans.PartSixBean;
import com.bonus.index.beans.PartThreeBean;
import com.bonus.index.beans.PartTwoBean;
import com.bonus.sys.BaseDao;


@BonusBatis
public interface IndexHomeDao extends BaseDao<IndexHomeBean>{

	List<IndexHomeBean> getPartOneData(IndexHomeBean o);

	List<IndexHomeBean> getPartTwoData(IndexHomeBean o);

	List<PartThreeBean> getPartThreeData(IndexHomeBean o);

	List<IndexHomeBean> getPartFourData(IndexHomeBean o);

	List<PartFiveBean> getPartFiveData(IndexHomeBean o);

	List<PartSixBean> getPartSixData(IndexHomeBean o);

	PartOneBean getOverview();

	int insertPartOne(PartOneBean one);

	PartTwoBean getInputview();

	PartTwoBean getTypeview();

	int insertPartTwo(PartTwoBean two);

	List<IndexHomeResourseBean> getHomeResource(IndexHomeResourseBean o);

	List<IndexHomeResourseBean> getResource(IndexHomeResourseBean o);

	void deleteResourse(IndexHomeResourseBean o);

	void insertResourse(IndexHomeResourseBean o);

	/**
	 * 查询工程领料、退料、维修检验、机具报废、修试后入库、新购入库、库存盘点数量
	 * @param o
	 * @return
	 */
	List<IndexDetailVo> getProAndNum(IndexHomeBean o);

    
}
