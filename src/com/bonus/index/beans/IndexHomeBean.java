package com.bonus.index.beans;

public class IndexHomeBean {
	
	private String id ;
	
	private PartOneBean partOne;
	
	private PartTwoBean partTwo;
	
	private PartThreeBean partThree;
	
	private PartFourBean partFour;
	
	private PartFiveBean partFive;
	
	private PartSixBean partSix;

	private String type;
	
	private String time;
	
	private String proName;
	
	private Integer page;
	
	private Integer limit;
	
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PartOneBean getPartOne() {
		return partOne;
	}

	public void setPartOne(PartOneBean partOne) {
		this.partOne = partOne;
	}

	public PartTwoBean getPartTwo() {
		return partTwo;
	}

	public void setPartTwo(PartTwoBean partTwo) {
		this.partTwo = partTwo;
	}

	public PartThreeBean getPartThree() {
		return partThree;
	}

	public void setPartThree(PartThreeBean partThree) {
		this.partThree = partThree;
	}

	public PartFourBean getPartFour() {
		return partFour;
	}

	public void setPartFour(PartFourBean partFour) {
		this.partFour = partFour;
	}

	public PartFiveBean getPartFive() {
		return partFive;
	}

	public void setPartFive(PartFiveBean partFive) {
		this.partFive = partFive;
	}

	public PartSixBean getPartSix() {
		return partSix;
	}

	public void setPartSix(PartSixBean partSix) {
		this.partSix = partSix;
	}
	
	

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	
	@Override
	public String toString() {
		return "IndexHomeBean [id=" + id + ", partOne=" + partOne + ", partTwo=" + partTwo + ", partThree=" + partThree
				+ ", partFour=" + partFour + ", partFive=" + partFive + ", partSix=" + partSix + ", type=" + type
				+ ", time=" + time + "]";
	}
	
	
	
}
