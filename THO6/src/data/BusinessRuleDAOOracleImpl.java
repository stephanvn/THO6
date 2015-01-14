package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import domain.defenition.BusinessRule;
import domain.defenition.BusinessRuleType;

public class BusinessRuleDAOOracleImpl implements BusinessRuleDAO {
	
	private DAOFactoryOracle factory;
	
	public BusinessRuleDAOOracleImpl(DAOFactoryOracle factory) {
		this.factory = factory;
	}
	
	public String[][] selectBusinessRules() {
		String[][] allBusinessRuleNames = new String[9999][5];
		String[][] allBusinessRuleTables = new String[9999][5];
		String[][] allBusinessRuleRows = new String[9999][5];
		String[][] allBusinessRuleColumns = new String[9999][5];
		String[][] allBusinessRuleOperators = new String[9999][5];
		String[][] allBusinessRuleEvents = new String[9999][5];
		
		Connection connection = null;
		
		connection = factory.getConnection();
				
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = null;
			String sql;
			
			//Read Business Rule Names
			sql = "select rule.ruleid,u.name,brt.code from brguser u, businessruletype brt, businessrule rule" +
			" where u.userid = rule.userid" +
			" and brt.typeid = rule.typeid";
			
			rs = stmt.executeQuery(sql);
			
			int counter = 0;
			while(rs.next()){
				BusinessRule br = new BusinessRule(rs.getInt(1));
				counter++;
			}
			
			rs.close();
			
			//Read BusinessRules
			String sql = "select rule.ruleid,u.name,brt.code from brguser u, tab tab, col col, businessruletype brt, businessrule rule" +
			" where u.userid = rule.userid" +
			" and tab.ruleid = rule.ruleid" +
			" and tab.position = 0" +
			" and col.tableid = tab.tableid"+
			" and col.position = 0" +
			" and brt.typeid = rule.typeid";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			int counter = 0;
			while(rs.next()){
				allBusinessRuleNames[counter][0] = rs.getInt(1)+"";
				allBusinessRuleNames[counter][1] = rs.getString(2);
				allBusinessRuleNames[counter][2] = rs.getString(3);
				allBusinessRuleNames[counter][3] = rs.getString(4);
				allBusinessRuleNames[counter][5] = rs.getString(6);
				counter++;
			}
			
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return allBusinessRuleNames;
	}
}
