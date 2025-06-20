package com.bonus.index.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.core.DateTimeHelper;
import com.bonus.index.beans.IndexCheckWarnBean;
import com.bonus.index.beans.IndexHomeCalendarBean;
import com.bonus.index.beans.IndexHomeDetailsBean;
import com.bonus.index.beans.IndexHomeTaskBean;
import com.bonus.index.beans.IndexInuseWarnBean;
import com.bonus.index.beans.IndexProjectBean;
import com.bonus.index.beans.IndexStorageWarnBean;
import com.bonus.index.beans.IndexTodoWarnBean;
import com.bonus.index.beans.IndexTotalWarnBean;
import com.bonus.index.dao.IndexHomeDetailsDao;
import com.bonus.sys.BaseServiceImp;

import sun.java2d.pipe.AATextRenderer;

@Service("indexHomeDetails")
public class IndexHomeDetailsServiceImp extends BaseServiceImp<IndexHomeDetailsBean> implements IndexHomeDetailsService{

	@Autowired
	private IndexHomeDetailsDao dao;

	@Override
	public List<IndexHomeDetailsBean> getMaTypeDetails(IndexHomeDetailsBean o) {
		
		return dao.getMaTypeDetails(o);
	}

	@Override
	public List<IndexHomeTaskBean> getMaChangeInfo(IndexHomeTaskBean o) {
		List<IndexHomeTaskBean> rsList = new ArrayList<>();
		IndexHomeTaskBean inStorage = dao.getInStorage(o);
		String date1 = inStorage != null ? inStorage.getCurrentDate() : null;
		IndexHomeTaskBean newStorage = dao.getNewStorage(o);
		String date2 = newStorage != null ? newStorage.getCurrentDate() : null;
		int rsDay = DateTimeHelper.calcDate(date1, date2);
		if(rsDay > 0){
			rsList.add(inStorage);
		}else{
			rsList.add(newStorage);
		}
		IndexHomeTaskBean outStorage = dao.getOutStorage(o);
		rsList.add(outStorage);
		IndexHomeTaskBean backStorage = dao.getBackStorage(o);
		rsList.add(backStorage);
		IndexHomeTaskBean scrapStorage = dao.getScrapStorage(o);
		rsList.add(scrapStorage);
		
		IndexHomeTaskBean pdStorage = dao.getPdStorage(o);
		rsList.add(pdStorage);
		return rsList;
	}

	@Override
	public List<IndexHomeTaskBean> getMaUseInfo(IndexHomeTaskBean o) {
		List<IndexHomeTaskBean> list = dao.getMaUseInfo(o);
		return list;
	}

	@Override
	public List<IndexHomeTaskBean> getMaChangeTask(IndexHomeTaskBean o) {
		List<IndexHomeTaskBean> rsList = new ArrayList<>();
		List<IndexHomeTaskBean> out = dao.getMaOutTask(o);
		List<IndexHomeTaskBean> back = dao.getMaBackTask(o);
		List<IndexHomeTaskBean> scrap = dao.getMaScrapTask(o);
		List<IndexHomeTaskBean> pd = dao.getMaPdTask(o);
		List<IndexHomeTaskBean> newList = dao.getMaNewTask(o);
		List<IndexHomeTaskBean> inList = dao.getMaInTask(o);
		rsList.addAll(out);
		rsList.addAll(back);
		rsList.addAll(scrap);
		rsList.addAll(pd);
		rsList.addAll(newList);
		rsList.addAll(inList);
		return rsList;
	}

	@Override
	public List<IndexHomeTaskBean> getMaOutTask(IndexHomeTaskBean o) {
		
		return dao.getMaOutTask(o);
	}

	@Override
	public List<IndexHomeTaskBean> getMaBackTask(IndexHomeTaskBean o) {
		
		return dao.getMaBackTask(o);
	}

	@Override
	public List<IndexHomeTaskBean> getMaScrapTask(IndexHomeTaskBean o) {

		return  dao.getMaScrapTask(o);
	}

	@Override
	public List<IndexHomeTaskBean> getMaPdTask(IndexHomeTaskBean o) {

		return dao.getMaPdTask(o);
	}

	@Override
	public List<IndexHomeTaskBean> getMaNewTask(IndexHomeTaskBean o) {

		return dao.getMaNewTask(o);
	}

	@Override
	public List<IndexHomeTaskBean> getMaInTask(IndexHomeTaskBean o) {
		
		return dao.getMaInTask(o);
	}

	@Override
	public List<IndexStorageWarnBean> getStorageWarn(IndexStorageWarnBean o) {
		return dao.getStorageWarn(o);
	}

	@Override
	public List<IndexCheckWarnBean> getCheckWarn(IndexCheckWarnBean o) {
		return dao.getCheckWarn(o);
	}

	@Override
	public List<IndexInuseWarnBean> getInUseWarn(IndexInuseWarnBean o) {
		return dao.getInUseWarn(o);
	}

	@Override
	public List<IndexInuseWarnBean> getPlanDetailsAboutExpireList(IndexInuseWarnBean o) {
		List<IndexInuseWarnBean> list = dao.getPlanDetailsAboutExpireList(o);
		list.removeIf(Objects::isNull);
		for (IndexInuseWarnBean obj : list) {
			if (obj.getBackDate() == null || obj.getBackDate().trim().isEmpty()) {
				continue;
			}
			int daysRemaining = calculateDaysRemaining(obj.getBackDate());

			if (daysRemaining > 0) {
				obj.setPlanStatus("还有 " + daysRemaining + " 天到期");
			} else if (daysRemaining == 0) {
				obj.setPlanStatus("今天到期");
			} else {
				obj.setPlanStatus("已过期 " + Math.abs(daysRemaining) + " 天");
			}
		}
		return list;
	}

	/**
	 * 计算传入日期与当前日期的天数差
	 *
	 * @param dateStr 传入的日期字符串，格式为 yyyy-MM-dd
	 * @return 返回天数差：
	 * - 正数：还有几天到期
	 * - 0：今天到期
	 * - 负数：已过期几天
	 */
	public static int calculateDaysRemaining(String dateStr) {
		// 定义日期格式
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		// 将字符串解析为 LocalDate
        LocalDate targetDate = null;
        try {
            targetDate = LocalDate.parse(dateStr, formatter);
        } catch (Exception e) {
            return 0;
        }

        // 获取当前日期
		LocalDate currentDate = LocalDate.now();

		// 计算两个日期的天数差
		long daysDifference = ChronoUnit.DAYS.between(currentDate, targetDate);

		// 返回天数差（转换为 int）
		return (int) daysDifference;
	}

	@Override
	public List<IndexTotalWarnBean> getTotalChangeWarn(IndexTotalWarnBean o) {
		List<IndexTotalWarnBean> rs = dao.getTotalChangeWarn(o);
		if(rs!=null && !rs.isEmpty()){
			for(IndexTotalWarnBean b : rs){
				b.setTypeId(b.getId());
				IndexTotalWarnBean rb = dao.getTotalInfo(b);
                if (rb != null) {
					b.setTotal(rb.getTotal());
                    b.setStorageNum(rb.getStorageNum());
					b.setRepairNum(rb.getRepairNum());
					b.setInuseNum(rb.getInuseNum());
                }
            }
			 
		}
		return rs;
	}

	@Override
	public List<IndexProjectBean> getProjectDiff(IndexProjectBean o) {

		return dao.getProjectDiff(o);
	}

	@Override
	public List<IndexProjectBean> getProjectCompany(IndexProjectBean o) {
	
		return dao.getProjectCompany(o);
	}

	@Override
	public List<IndexTodoWarnBean> getTodoList(IndexTodoWarnBean o) {
		List<IndexTodoWarnBean> rs = new ArrayList<>();
		
		List<IndexTodoWarnBean> toOut = dao.getToOutList(o);
		List<IndexTodoWarnBean> toBack = dao.getToBackList(o);
		List<IndexTodoWarnBean> toNew = dao.getToNewList(o);
		rs.addAll(toOut);
		rs.addAll(toBack);
		rs.addAll(toNew);
		return rs;
	}

	@Override
	public List<IndexTodoWarnBean> getToOutList(IndexTodoWarnBean o) {
		
		return dao.getToOutList(o);
	}

	@Override
	public List<IndexTodoWarnBean> getToBackList(IndexTodoWarnBean o) {
		
		return dao.getToBackList(o);
	}

	@Override
	public List<IndexTodoWarnBean> getToNewList(IndexTodoWarnBean o) {
		
		return dao.getToNewList(o);
	}

	@Override
	public List<IndexProjectBean> getProjectMaDiff(IndexProjectBean o) {
		
		return dao.getProjectMaDiff(o);
	}

	@Override
	public List<IndexHomeTaskBean> getProjectMaRecord(IndexHomeTaskBean o) {
		
		List<IndexHomeTaskBean> rsList = new ArrayList<>();
		List<IndexHomeTaskBean> out = dao.getMaOutTask(o);
		List<IndexHomeTaskBean> back = dao.getMaBackTask(o);
	
		rsList.addAll(out);
		rsList.addAll(back);
	
		return rsList;
	}

	@Override
	public List<IndexHomeCalendarBean> getCalendarOut(IndexHomeCalendarBean o) {
		
		return dao.getCalendarOut(o);
	}

	@Override
	public List<IndexHomeCalendarBean> getCalendarBack(IndexHomeCalendarBean o) {
		
		return dao.getCalendarBack(o);
	}

	@Override
	public List<IndexHomeCalendarBean> getCalendarRepair(IndexHomeCalendarBean o) {
		
		return  dao.getCalendarRepair(o);
	}

	@Override
	public List<IndexHomeCalendarBean> getCalendarScrap(IndexHomeCalendarBean o) {
		
		return dao.getCalendarScrap(o);
	}

	@Override
	public List<IndexHomeCalendarBean> getCalendarRepairInput(IndexHomeCalendarBean o) {
		
		return dao.getCalendarRepairInput(o);
	}

	@Override
	public List<IndexHomeCalendarBean> getCalendarNewInput(IndexHomeCalendarBean o) {
		
		return dao.getCalendarNewInput(o);
	}

	@Override
	public List<IndexHomeCalendarBean> getCalendarPd(IndexHomeCalendarBean o) {
	
		return  dao.getCalendarPd(o);
	}


	
}

