package com.digitalcity.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.digitalcity.model.ITCompany;


public class ITCompanyDAO {

	private String url = "jdbc:mysql://localhost/digitalcity/useSSL=false";
	private String username = "root";
	private String pass = "";
	
	private static PreparedStatement prst;
    private static ResultSet rs;
    private static Connection conn;
	
    private static final String REGISTER_COMP = "INSERT INTO companies" + "(companyName, physicalAddress, telephoneNumber, website, email) VALUES" + "(?,?,?,?,?);" ;
    private static final String SEARCH_BY_NAME = "select * from companies where companyName LIKE '%\" +searchName + \"%'";
    private static final String UPDATE_INFO = "update companies set companyName=?, physicalAddress=?, telephoneNumber=?, website=?, email=? ";
    private static final String DELETE_PROFILE = "delete from companies where id=?";
    private static final String LIST_ALL_COMP = "select * from companies";
    
    protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.sql.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
    
    public void registerCompany(ITCompany comp) throws SQLException{
    	conn = getConnection();
    	prst = conn.prepareStatement(REGISTER_COMP);
    	prst.setString(1, comp.getComanyName());
    	prst.setString(2, comp.getPhysicalAddress());
    	prst.setString(3, comp.getTelephoneNumber());
    	prst.setString(4, comp.getWebsite());
    	prst.setString(5, comp.getEmail());
    	prst.executeUpdate();
    }
    
    public ITCompany searchByName(String name) throws SQLException{
    	ITCompany comp = null;
    	
    	conn=getConnection();
    	prst = conn.prepareStatement(SEARCH_BY_NAME);
    	prst.setString(1, name);
    	rs = prst.executeQuery();
    	
    	while(rs.next()) {
    		String companyName = rs.getString("companyName");
    		String physicalAddress = rs.getString("physicalAddress");
    		String telephoneNumber = rs.getString("telephoneNumber");
    		String website = rs.getString("website");
    		String email = rs.getString("email");
    		comp = new ITCompany(companyName, physicalAddress, telephoneNumber, website, email);
    	}
    	return comp;
    }
    
    public void updateCompany(ITCompany comp) throws SQLException{
    	conn = getConnection();
    	prst = conn.prepareStatement(UPDATE_INFO);
    	prst.setString(1, comp.getComanyName());
    	prst.setString(2, comp.getPhysicalAddress());
    	prst.setString(3, comp.getTelephoneNumber());
    	prst.setString(4, comp.getWebsite());
    	prst.setString(5, comp.getEmail());
    	prst.executeUpdate();
    }
    
    public void deleteComp(int id) throws SQLException {
    	conn = getConnection();
    	prst = conn.prepareStatement(DELETE_PROFILE);
    	prst.setInt(1, id);
    	prst.executeUpdate();
    }
    
    public List<ITCompany> selectAllComps(){
    	List<ITCompany> comp = new ArrayList<>();
    	try(Connection conn = getConnection();
				PreparedStatement prst = conn.prepareStatement(LIST_ALL_COMP);){
    		
    		ResultSet rs = prst.executeQuery();
    		
    		while(rs.next()) {
    			int id = rs.getInt("id");
    			String companyName = rs.getString("companyName");
        		String physicalAddress = rs.getString("physicalAddress");
        		String telephoneNumber = rs.getString("telephoneNumber");
        		String website = rs.getString("website");
        		String email = rs.getString("email");
        		comp.add(new ITCompany(id, companyName, physicalAddress, telephoneNumber, website, email));
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return comp;
    	
    }
    
}























