package com.bonus.sys.beans;

import java.util.ArrayList;
import java.util.List;

public class ResourcesBean implements java.io.Serializable  {
	private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String parentId;
    
    private String parentName;

    private String layer;

    private String url;

    private String icon;

    private String type;

    private Integer sort;

    private String btnId;

    private String btnFun;

    private String target;
    
    private String description;

    private String isValid;
    
    private List<ResourcesBean> nodes =new ArrayList<ResourcesBean>();
        
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer == null ? null : layer.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
    
    public void setNodes(List<ResourcesBean> nodes) {
		this.nodes = nodes;
	}
    
    public List<ResourcesBean> getNodes() {
		return nodes;
	}

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getBtnId() {
        return btnId;
    }

    public void setBtnId(String btnId) {
        this.btnId = btnId == null ? null : btnId.trim();
    }

    public String getBtnFun() {
        return btnFun;
    }

    public void setBtnFun(String btnFun) {
        this.btnFun = btnFun == null ? null : btnFun.trim();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }
}