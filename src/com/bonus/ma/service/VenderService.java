package com.bonus.ma.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bonus.ma.beans.VenderBean;
import com.bonus.sys.BaseService;
import com.bonus.sys.beans.ZNode;

public interface VenderService extends BaseService<VenderBean>{

	public int insertBean(VenderBean o);
	
	public void deleteBatch(String chks);
	
	public List<VenderBean> findVender(VenderBean o);

	public List<ZNode> maVenderTree(VenderBean o);
	
	public List<ZNode> makeeper(VenderBean o);
	
	public void updateVend(VenderBean o);  //修改路径
	
}
