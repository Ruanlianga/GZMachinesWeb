package com.bonus.sq.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

import com.bonus.sq.beans.MachineStatusQueryBean;
import com.bonus.sq.dao.MachineStatusQueryDao;
import com.bonus.sys.BaseServiceImp;

@Service("query")
public class MachineStatusQueryServiceImp extends BaseServiceImp<MachineStatusQueryBean> implements MachineStatusQueryService {

	@Autowired
	MachineStatusQueryDao dao;

	@Override
	public List<MachineStatusQueryBean> machineStatusQuery(MachineStatusQueryBean o) {
		// TODO Auto-generated method stub
		List<MachineStatusQueryBean> list = dao.machineStatusQuery(o);
		if (list != null && !list.isEmpty()) {
			    // 查询收藏表，获取收藏机具类型id
			List<MachineStatusQueryBean> typeIdList = dao.findTypeId(o);
			if (typeIdList != null && !typeIdList.isEmpty()) {
				// 提取 typeIdList 中的 typeId 放入新的 Set<String> 中
				Set<String> typeIdSet = typeIdList.stream()
						.map(MachineStatusQueryBean::getTypeId)
						.collect(Collectors.toSet());
				// 使用 Stream 过滤 list 中的 typeId 在 typeIdSet 中的数据
				if (o.getIsCollect() != null && o.getIsCollect().equals(0)) {
					return list.stream()
							.filter(item -> typeIdSet.contains(item.getTypeId()))
							.collect(Collectors.toList());
				} else {
					// 遍历 list，修改每个 item 的 isCollect 字段
					List<MachineStatusQueryBean> filteredList = list.stream()
							.map(item -> {
								// 检查 typeId 是否存在于 typeIdSet 中，并设置 isCollect 值
								if (typeIdSet.contains(item.getTypeId())) {
									// typeId 存在，设置 isCollect 为 0
									item.setIsCollect(0);
								} else {
									// typeId 不存在，设置 isCollect 为 1
									item.setIsCollect(1);
								}
								return item;
							})
							.collect(Collectors.toList());
					return filteredList;
				}
			}
		}
		return list;
	}

	/**
	 * 添加或移除收藏
	 * @param o
	 * @return
	 */
	@Override
	public int addOrRemoveCollect(MachineStatusQueryBean o) {
		// 判断收藏状态并调用相应的 DAO 方法
		if (o.getIsCollect() != null && o.getIsCollect().equals(0)) {
			// 状态为0时执行添加收藏操作
			return dao.addCollect(o);
		} else if (o.getIsCollect() != null && o.getIsCollect().equals(1)) {
			// 状态为1时执行移除收藏操作
			return dao.removeCollect(o);
		} else {
			// 如果 o.getIsCollect() 不是 0 或 1，抛出异常
			throw new RuntimeException("Invalid collect state: " + o.getIsCollect());
		}
	}

	
}
