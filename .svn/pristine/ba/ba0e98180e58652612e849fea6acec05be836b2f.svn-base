package com.bonus.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonus.ma.beans.DisassemblyManagementBean;
import com.bonus.ma.beans.GpsBindingBean;

public interface BaseDao<T> {
	/**
	 * 保存一个对象
	 * 
	 * @param o
	 *            对象
	 * @return 对象的ID
	 */

	/*
	 * roomid 用于导文件返回 roomId 值 用于判断表格数值
	 * 
	 * **
	 */

	public int addOne(T o);

	public int insert(T o);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public void delete(T o);

	/**
	 * 更新一个对象
	 * 
	 * @param o
	 *            对象
	 */
	public void update(T o);

	/**
	 * 批量删除一组对象
	 * 
	 * @param s
	 *            (主键)数组
	 */
	public void deleteBatch(List<T> os);

	public void deleteBatchone(List<T> os);

	/**
	 * 获得对象列表
	 * 
	 * @param o
	 *            对象
	 * @return List
	 */
	public List<T> find(T o);

	public List<T> findOrg(T o);

	/**
	 * 获得对象列表
	 * 
	 * @param o
	 *            对象
	 * @param page
	 *            分页对象
	 * @return List
	 */
	public List<T> findByPage(@Param("param") T o, @Param("t") Page<T> page);
	
	public List<T> fileViewFindByPage(@Param("param") T o, @Param("t") Page<T> page);
	/**
	 * 获得对象列表
	 * 
	 * @param o
	 *            对象
	 * @param page
	 *            分页对象
	 * @return List
	 */
	public List<T> findByPageTwo(@Param("param") T o,@Param("t") Page<T> page);

	/**
	 * 获得对象列表
	 * 
	 * @param o
	 *            对象
	 * @param page
	 *            分页对象
	 * @return List
	 */

	public List<T> findByPageOne(@Param("param") T o,@Param("t") Page<T> page);

	/**
	 * 统计数目
	 * 
	 * @param o
	 *            对象
	 * @return long
	 */
	public int count(T o);

	public List<T> findName(T o);

	public List<T> findByPageThree(@Param("param") T o,@Param("t") Page<T> page);


}
