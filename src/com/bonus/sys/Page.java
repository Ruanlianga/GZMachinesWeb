package com.bonus.sys;

import java.util.List;

/**
 * 对分页的基本数据进行封装
 */
public class Page<T> {

    private int pageNum = 1;//页码，默认是第一页
    private int pageSize = 10;//每页显示的记录数，默认是5
    private int totalRecord;//总记录数
    private int totalPage;//总页数
    private List<T> results;//对应的当前页记录
    private T obj; //对象
    private Boolean hasUUPage = false;
    private Boolean hasUPage = false;
    private Boolean hasNPage = false;
    private Boolean hasNNPage = false;
    private String companyId;
    
    private String isFinish;
    
    private String keyWord;

    
    public String getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Boolean getHasUUPage() {
		return hasUUPage;
	}

	public void setHasUUPage(Boolean hasUUPage) {
		this.hasUUPage = hasUUPage;
	}

	public Boolean getHasUPage() {
		return hasUPage;
	}

	public void setHasUPage(Boolean hasUPage) {
		this.hasUPage = hasUPage;
	}

	public Boolean getHasNPage() {
		return hasNPage;
	}

	public void setHasNPage(Boolean hasNPage) {
		this.hasNPage = hasNPage;
	}

	public Boolean getHasNNPage() {
		return hasNNPage;
	}

	public void setHasNNPage(Boolean hasNNPage) {
		this.hasNNPage = hasNNPage;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        //在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
        int totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
        this.setTotalPage(totalPage);
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getResults() {
    	if(null != results && results.size() == 0){
            return null;
    	}
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "Page [pageNum=" + pageNum + ", pageSize=" + pageSize + ", totalRecord=" + totalRecord + ", totalPage="
				+ totalPage + ", results=" + results + ", obj=" + obj + ", hasUUPage=" + hasUUPage + ", hasUPage="
				+ hasUPage + ", hasNPage=" + hasNPage + ", hasNNPage=" + hasNNPage + ", keyWord=" + keyWord + "]";
	}
}