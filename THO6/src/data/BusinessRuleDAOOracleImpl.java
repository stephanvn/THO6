package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BusinessRuleDAOOracleImpl implements BusinessRuleDAO {
	
	private DAOFactoryOracle factory;
	
	public BusinessRuleDAOOracleImpl(DAOFactoryOracle factory) {
		this.factory = factory;
	}
	
	public String[][] selectNames() {
		String[][] allBusinessRuleNames = new String[9999][4];
		Connection connection = null;
		
		connection = factory.getConnection();
				
		try {
			Statement stmt = connection.createStatement();
			
			String sql = "select u.name,tab.name,col.name,brt.code from brguser u, tab tab, col col, businessruletype brt, businessrule rule" +
			" where u.userid = rule.userid" +
			" and tab.ruleid = rule.ruleid" +
			" and tab.position = 0" +
			" and col.tableid = tab.tableid"+
			" and col.position = 0" +
			" and brt.typeid = rule.typeid";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			int counter = 0;
			while(rs.next()){
				allBusinessRuleNames[counter][0] = rs.getString(1);
				allBusinessRuleNames[counter][1] = rs.getString(2);
				allBusinessRuleNames[counter][2] = rs.getString(3);
				allBusinessRuleNames[counter][3] = rs.getString(4);
				counter++;
			}
			
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		factory.closeConnection();
		return allBusinessRuleNames;
	}
}
