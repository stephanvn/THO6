package domain.target;

import java.util.ArrayList;

public class Table {
	
	private String name;
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

	public ArrayList<Column> getAllColumns() {
		return allColumns;
	}

	public void setAllColumns(ArrayList<Column> allColumns) {
		this.allColumns = allColumns;
	}
	
	public void addColumn(Column c) {
		allColumns.add(c);
	}
	
	public String getTargetColumn(){
		return allColumns.get(0).getName();
	}
	
	

}
