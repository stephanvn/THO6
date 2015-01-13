package data;

public interface DAOFactory {
	
	public void connect();
	
	public void close();
	
	public void chooseDAO(String type);

}
