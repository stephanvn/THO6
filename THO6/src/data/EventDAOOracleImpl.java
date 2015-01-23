package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dto.constraints.EventDTO;

public class EventDAOOracleImpl implements EventDAO {

	private DAOFactory factory;

	public EventDAOOracleImpl() {
		factory = new DAOFactoryOracle();
	}

	public EventDTO getEventByID(int ID) {
		// TODO Auto-generated method stub
		Connection con = factory.getConnection();
		// statement
		Statement stmt1;
		try {
			stmt1 = con.createStatement();
			String sql1 = "SELECT * FROM EVENT WHERE EVENTID = " + ID + ";" ;
			ResultSet rs1 = stmt1.executeQuery(sql1);
			while (rs1.next()) {
				EventDTO edto = new EventDTO(rs1.getInt(1), rs1.getString(2));
				return edto;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		factory.closeConnection();
		return null;
	}
}
