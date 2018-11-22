package com.wu.pdm;


public class PDMColumn {
	private String id;
	private String name;
	private String code;
	private String dataType; // 数据类型
	private Integer length; // 数据长度
	private Integer precision; // 精度
	private Integer mandatory = 0; // 约束
	private String defaultValue; // 默认值
	private String lowValue;
	private String highValue;
	private String comment;
	private PDMTable table;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getPrecision() {
		return precision;
	}

	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	public Integer getMandatory() {
		return mandatory;
	}

	public void setMandatory(Integer mandatory) {
		this.mandatory = mandatory;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getLowValue() {
		return lowValue;
	}

	public void setLowValue(String lowValue) {
		this.lowValue = lowValue;
	}

	public String getHighValue() {
		return highValue;
	}

	public void setHighValue(String highValue) {
		this.highValue = highValue;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public PDMTable getTable() {
		return table;
	}

	public void setTable(PDMTable table) {
		this.table = table;
	}
}
