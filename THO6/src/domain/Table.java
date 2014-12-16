package domain;

import java.util.ArrayList;

public class Table {
	
	private String name;
	private TargetEntity theTargetEntity;
	private ArrayList<Column> allColumns;
	
	public Table(String n) {
		name = n;
		allColumns = new ArrayList<Column>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TargetEntity getTheTargetEntity() {
		return theTargetEntity;
	}

	public void setTheTargetEntity(TargetEntity theTargetEntity) {
		this.theTargetEntity = theTargetEntity;
	}

	public ArrayList<Column> getAllColumns() {
		return allColumns;
	}

	public void setAllColumns(ArrayList<Column> allColumns) {
		this.allColumns = allColumns;
	}
	
	public void addColumn(Column c) {
		allColumns.add(c);
	}
	
	

}
