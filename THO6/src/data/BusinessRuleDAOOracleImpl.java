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

	@Override
	public ArrayList<BusinessRule> fillDomain() {
		Connection connection = null;
		
		connection = con;
		
		ArrayList<BusinessRule> allBusinessRules = new ArrayList<BusinessRule>();
				
		try {
			Statement stmt1 = connection.createStatement();			
			//Read Business Rule Names
			 String sql1 = "select rule.ruleid,u.name,brt.name,brt.description,brt.code from brguser u, businessruletype brt, businessrule rule" +
			" where u.userid = rule.userid" +
			" and brt.typeid = rule.typeid";
			
			ResultSet rs1 = stmt1.executeQuery(sql1);

			while(rs1.next()){
				BRGUser user = new BRGUser(rs1.getString(2));
				BusinessRuleType brt = new BusinessRuleType(rs1.getString(3),rs1.getString(4),rs1.getString(5));
				BusinessRule br = new BusinessRule(rs1.getInt(1)); 
				br.setTheBRGUser(user);
				br.setType(brt);
				allBusinessRules.add(br);
			}
			
			rs1.close();

			for(BusinessRule b : allBusinessRules) {
				int ID = b.getID();
				
				//Read Tables
				Statement stmt2 = connection.createStatement();	
				String sql2 = "select tableid,name,position from tab where ruleid = "+ID;				
				ResultSet rs2 = stmt2.executeQuery(sql2);
				
				while(rs2.next()){
					String tabCode = rs2.getString(2).substring(0, Math.min( rs2.getString(2).length(), 3));
					Table tab = new Table(rs2.getInt(1),tabCode,rs2.getInt(3));
					
					//Read Columns
					Statement stmt3 = connection.createStatement();	
					String sql3 = "select name,position from col where tableid = "+tab.getID();	
					ResultSet rs3 = stmt3.executeQuery(sql3);
					
					while(rs3.next()){
						String colCode = rs3.getString(1).substring(0, Math.min( rs3.getString(1).length(), 3));
						Column col = new Column(colCode,rs3.getInt(2));
						tab.addColumn(col);
					}
					
					rs3.close();
					
					b.addTable(tab);
				}
				
				rs2.close();
				
				//Read ErrorMessages
				Statement stmt4 = connection.createStatement();	
				String sql4 = "select code,message from errormessage where ruleid = "+ID;				
				ResultSet rs4 = stmt4.executeQuery(sql4);
				
				while(rs4.next()){
					ErrorMessage em = new ErrorMessage(rs4.getString(1),rs4.getString(2));
					b.addErrorMessage(em);
				}
				
				rs4.close();
				
				//Get ConstraintsFacade
				ConstraintsFacade cf = b.getConstrainsFacade();
				
				//Read Events
				Statement stmt9 = connection.createStatement();	
				String sql9 = "select eventid,type from event";				
				ResultSet rs9 = stmt9.executeQuery(sql9);
				
				ArrayList<Event> allEvents = new ArrayList<Event>();
				
				while(rs9.next()) {
					Event generalEvent = new Event(rs9.getInt(1),rs9.getString(2));
					allEvents.add(generalEvent);
				}
				
				rs9.close();
				
				Statement stmt5 = connection.createStatement();	
				String sql5 = "select eventid from businessruleevent where ruleid = "+ID;				
				ResultSet rs5 = stmt5.executeQuery(sql5);
				
				while(rs5.next()) {
					for(Event e : allEvents) {
						if(e.getID()==rs5.getInt(1)) {
							cf.addEvent(e);
						}
					}
				}
				
				rs5.close();
				
				//Read Operators
				Statement stmt6 = connection.createStatement();	
				String sql6 = "select operatorid,type from operator";				
				ResultSet rs6 = stmt6.executeQuery(sql6);
				
				ArrayList<Operator> allOperators = new ArrayList<Operator>();
				
				while(rs6.next()) {
					Operator generalOperator = new Operator(rs6.getInt(1),rs6.getString(2));
					allOperators.add(generalOperator);
				}
				
				rs6.close();
				
				Statement stmt7 = connection.createStatement();	
				String sql7 = "select operatorid from businessruleoperator where ruleid = " + ID;				
				ResultSet rs7 = stmt7.executeQuery(sql7);
				
				while(rs7.next()) {
					for(Operator o : allOperators) {
						if(o.getID()==rs7.getInt(1)) {
							cf.addOperator(o);
						}
					}
					
				}
				
				rs7.close();
				
				//Read Values
				Statement stmt8 = connection.createStatement();	
				String sql8 = "select comparable from value where ruleid = "+ID;				
				ResultSet rs8 = stmt8.executeQuery(sql8);
				
				while(rs8.next()) {
					if(b.getType().getCode().equals("ALIS")) {
						String values[] = rs8.getString(1).split(":");
						for(String s : values) {
							Value value = new Value(s);
							cf.addValue(value);
						}
					}
					else {
						Value value = new Value(rs8.getString(1));
						cf.addValue(value);
					}					
				}
				
				rs8.close();
				
			}
			
			//Check loaded data
			for(BusinessRule b : allBusinessRules) {
				System.out.println(b.getName());
				for(Table t : b.getAllTables()) {
					System.out.println("\tTable: " + t.getName());
					for(Column c : t.getAllColumns()) {
						System.out.println("\tColumn: " + c.getName());
					}
				}
				for(ErrorMessage em : b.getAllErrorMessages()) {
					System.out.println("\tErrorMessage: " + em.getCode() + " - " + em.getMessage());
				}
				for(Event e : b.getConstrainsFacade().getAllEvents()) {
					System.out.println("\tEvent: " + e.getType());
				}
				for(Operator o : b.getConstrainsFacade().getAllOperators()) {
					System.out.println("\tOperator: " + o.getType());
				}
				for(Value v : b.getConstrainsFacade().getAllValues()) {
					System.out.println("\tValue: " + v.getComparable());
				}
				System.out.println();
			}
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return allBusinessRules;
	}
}
