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
		String[][] allBusinessRuleNames = new String[3][];
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
				allBusinessRuleNames[0][counter] = rs.getString(0);
				allBusinessRuleNames[1][counter] = rs.getString(1);
				allBusinessRuleNames[2][counter] = rs.getString(2);
				allBusinessRuleNames[3][counter] = rs.getString(3);
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
