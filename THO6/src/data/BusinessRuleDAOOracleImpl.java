package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import domain.constraints.ConstraintsFacade;
import domain.constraints.Event;
import domain.constraints.Operator;
import domain.constraints.Value;
import domain.definition.BusinessRule;
import domain.definition.BusinessRuleType;
import domain.errorHandling.ErrorMessage;
import domain.target.Column;
import domain.target.Table;
import domain.userManagement.BRGUser;

public class BusinessRuleDAOOracleImpl implements BusinessRuleDAO {
	
	private Connection con;
	
	public BusinessRuleDAOOracleImpl(Connection con) {
		this.con = con;
	}
	
	public ArrayList<BusinessRule> selectBusinessRules() {
		Connection connection = null;
		
		connection = con;
		
		ArrayList<BusinessRule> allBusinessRules = new ArrayList<BusinessRule>();
				
		try {
			Statement stmt1 = connection.createStatement();			
			//Read Business Rule Names
			 String sql1 = "select rule.ruleid,u.name,brt.name,brt.code from brguser u, businessruletype brt, businessrule rule" +
			" where u.userid = rule.userid" +
			" and brt.typeid = rule.typeid";
			
			ResultSet rs1 = stmt1.executeQuery(sql1);

			while(rs1.next()){
				BRGUser user = new BRGUser(rs1.getString(2));
				BusinessRuleType brt = new BusinessRuleType(rs1.getString(3),rs1.getString(4));
				
				BusinessRule br = new BusinessRule(rs1.getInt(1)); 
				
				br.setTheBRGUser(user);
				br.setType(brt);
				br.setAllTables(selectTables(br.getID(),connection));
				br.setAllErrorMessages(selectErrorMessages(br.getID(), connection));
				ConstraintsFacade cf = br.getConstrainsFacade();
				cf.setAllEvents(selectEvents(br.getID(),connection));
				cf.setAllOperators(selectOperators(br.getID(),connection));
				cf.setAllValues(selectValues(br.getID(),br.getType().getCode(),connection));
				
				allBusinessRules.add(br);
			}
			
			rs1.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return allBusinessRules;
	}
	
	public ArrayList<Table> selectTables(int ID,Connection connection) {
		ArrayList<Table> allTables = new ArrayList<Table>();
		try {
			Statement stmt = connection.createStatement();	
			String sql = "select tableid,name,position from tab where ruleid = "+ID;				
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Table tab = new Table(rs.getInt(1),rs.getString(2),rs.getInt(3));
				tab.setAllColumns(selectColumns(tab.getID(),connection));
				allTables.add(tab);
			}
			
			rs.close();
		} catch(SQLException e) {}
		
		return allTables;
	}
	
	public ArrayList<Column> selectColumns(int ID,Connection connection) {
		ArrayList<Column> allColumns = new ArrayList<Column>();
		try {
			Statement stmt = connection.createStatement();	
			String sql = "select name,position from col where tableid = "+ID;				
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				String colCode = rs.getString(1).substring(0, Math.min( rs.getString(1).length(), 3));
				Column col = new Column(rs.getString(1),colCode,rs.getInt(2));
				allColumns.add(col);
			}
			
			rs.close();
		} catch(SQLException e) {}
		
		return allColumns;
	}
	
	public ArrayList<ErrorMessage> selectErrorMessages(int ID,Connection connection) {
		ArrayList<ErrorMessage> allErrorMessages = new ArrayList<ErrorMessage>();
		try {
			Statement stmt = connection.createStatement();	
			String sql = "select code,message from errormessage where ruleid = "+ID;				
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				ErrorMessage em = new ErrorMessage(rs.getString(1),rs.getString(2));
				allErrorMessages.add(em);
			}
			
			rs.close();
		} catch(SQLException e) {}
		return allErrorMessages;
	}
	
	
	public ArrayList<Event> selectEvents(int ID,Connection connection) {
		ArrayList<Event> allEvents = new ArrayList<Event>();
		ArrayList<Event> brEvents = new ArrayList<Event>();
		try {
			Statement stmt = connection.createStatement();	
			String sql = "select eventid,type from event";				
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				Event generalEvent = new Event(rs.getInt(1),rs.getString(2));
				allEvents.add(generalEvent);
			}
			
			rs.close();
			
			Statement stmt2 = connection.createStatement();	
			String sql2 = "select eventid from businessruleevent where ruleid = "+ID;				
			ResultSet rs2 = stmt2.executeQuery(sql2);	

			while(rs2.next()) {
				for(Event e : allEvents) {
					if(e.getID()==rs2.getInt(1)) {
						brEvents.add(e);
					}
				}
			}
			
			rs2.close();
		} catch(SQLException e) {}
		
		return brEvents;
	}
	
	public ArrayList<Operator> selectOperators(int ID,Connection connection) {
		ArrayList<Operator> allOperators = new ArrayList<Operator>();
		ArrayList<Operator> brOperators = new ArrayList<Operator>();
		
		try {
			Statement stmt = connection.createStatement();	
			String sql = "select operatorid,type from operator";			
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				Operator generalOperator = new Operator(rs.getInt(1),rs.getString(2));
				allOperators.add(generalOperator);
			}
			
			rs.close();
			
			Statement stmt2 = connection.createStatement();	
			String sql2 = "select operatorid from businessruleoperator where ruleid = " + ID;				
			ResultSet rs2 = stmt2.executeQuery(sql2);

			while(rs2.next()) {
				for(Operator e : allOperators) {
					if(e.getID()==rs2.getInt(1)) {
						brOperators.add(e);
					}
				}
			}
			
			rs2.close();
		} catch(SQLException e) {}
		
		return brOperators;
	}

	
	public ArrayList<Value> selectValues(int ID,String code,Connection connection) {
		ArrayList<Value> allValues = new ArrayList<Value>();
		
		try {
			Statement stmt = connection.createStatement();	
			String sql = "select comparable from value where ruleid = "+ID;				
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				String type = code;
				if(type.equals("ALIS") || type.equals("MODI")) {
					String values[] = rs.getString(1).split(":");
					for(String s : values) {
						Value value = new Value(s);
						allValues.add(value);
					}
				}
				else {
					Value value = new Value(rs.getString(1));
					allValues.add(value);
				}					
			}
			
			rs.close();
		} catch(SQLException e) {}
		return allValues;
	}
	
}