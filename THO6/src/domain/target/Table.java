package domain.target;

import java.util.ArrayList;

public class Table {
	
	private int ID;
	private String name;
	private int position;
	private ArrayList<Column> allColumns;
	
	public Table(int ID,String name,int position) {
		this.ID = ID;
		this.name = name;
		this.position = position;
		allColumns = new ArrayList<Column>();
	}
	
	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
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
