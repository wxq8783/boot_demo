package com.wu.pdm;



import java.util.ArrayList;
import java.util.List;

public class PDMTable {

	private String firstTopic;
	private String secondTopic;
	public String id;
	private String name;
	private String code;

	private List<PDMColumn> columns = new ArrayList<PDMColumn>();
	private List<PDMKey> keys = new ArrayList<PDMKey>();
	private PDMKey primaryKey;


	public String getFirstTopic() {
		return firstTopic;
	}

	public void setFirstTopic(String firstTopic) {
		this.firstTopic = firstTopic;
	}

	public String getSecondTopic() {
		return secondTopic;
	}

	public void setSecondTopic(String secondTopic) {
		this.secondTopic = secondTopic;
	}

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



	public List<PDMColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<PDMColumn> columns) {
		this.columns = columns;
	}

	public List<PDMKey> getKeys() {
		return keys;
	}

	public void setKeys(List<PDMKey> keys) {
		this.keys = keys;
	}

	public PDMKey getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(PDMKey primaryKey) {
		this.primaryKey = primaryKey;
	}



	public void addColumn(PDMColumn column) {
		columns.add(column);
	}

	public void addKey(PDMKey key) {
		keys.add(key);
	}



	public PDMColumn getPDMColumn(String id) throws Exception {
		for (PDMColumn column : columns) {
			if (id.equals(column.getId())) {
				return column;
			}
		}
		throw new Exception("Id编号" + id + "没有找到");
	}

	public PDMKey getPDMKey(String id) throws Exception {
		for (PDMKey key : keys) {
			if (id.equals(key.getId())) {
				return key;
			}
		}
		throw new Exception("Id编号" + id + "没有找到");
	}
        
        @Override
        public String toString() {
            return this.name + "(" + this.code + ")";
        }
}
