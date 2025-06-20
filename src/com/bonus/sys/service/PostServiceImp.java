package com.bonus.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonus.sys.BaseServiceImp;
import com.bonus.sys.beans.PostBean;
import com.bonus.sys.dao.PostDao;

@Service("post")
public class PostServiceImp extends BaseServiceImp<PostBean> implements PostService{

	@Autowired PostDao dao;
	
	@Override
	public int insertBean(PostBean o) {
		return dao.insertBean(o);
	}

	@Override
	public void deleteBatch(String chks) {
		// 事务删除
		if (StringUtils.isNotBlank(chks)) {
			String[] chk = chks.split(",");
			List<PostBean> list = new ArrayList<PostBean>();
			for (String s : chk) {
				try {
					//int id = Integer.parseInt(s);
					PostBean sd = new PostBean();
					sd.setId(s);
					list.add(sd);
				} catch (Exception e) {
				}
			}
			dao.deleteBatch(list);
		}
	}

	@Override
	public List<PostBean> findPost(PostBean o) {
		List<PostBean> list = dao.findPost(o);
		return list;
	}

}
