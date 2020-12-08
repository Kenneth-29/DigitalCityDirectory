package com.digitalcity.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.digitalcity.model.Professional;
import com.digitalcity.model.ProfessionalPortfolio;

public class ProfessionalsDAO {

	private String url = "jdbc:mysql://localhost/digitalcity/useSSL=false";
	private String username = "root";
	private String pass = "";
	
	private static PreparedStatement prst;
    private static ResultSet rs;
    private static Connection conn;
	
	//SQL HERE
	private static final String REGISTER_PRO = "INSERT INTO professionals" + "(firstName, lastName, email) VALUES" + "(?,?,?);";
	private static final String SEARCH_BY_NAME = "SELECT * FROM professionals WHERE firstName LIKE '%\" + searchName + \"%'";
	private static final String INSERT_PORTFOLIO = "INSERT INTO proPortfolio" + "(firstName, lastName, email, postalAddress, cellNumber, dateOfBirth, country, profession, education) VALUES (?,?,?,?,?,?,?,?);";
	private static final String UPDATE_PORTFOLIO = "update proPortfolio set firstName=?, lastName=?, email=?, postalAddress=?, cellNumber=?, dateOfBirth=?, country=?, profession=? where id=?";
	private static final String DELETE_PORTFOLIO = "delete from proPortfolio where id=?";
	private static final String LIST_ALL_PROS = "select * from professionals";
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.sql.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, pass);
			System.out.println("Connected!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	//METHODS HERE
	public void registerProfessional(Professional pro) throws SQLException {
		conn = getConnection();
		prst = conn.prepareStatement(REGISTER_PRO);
		prst.setString(1, pro.getFirstName());
		prst.setString(1, pro.getLastName());
		prst.setString(3, pro.getEmail());
		prst.executeUpdate();
		
	}
	
	public Professional searchByName(String fName) throws SQLException{
		Professional pro = null;
		
		conn = getConnection();
		prst = conn.prepareStatement(SEARCH_BY_NAME);
		prst.setString(1, fName);
		rs = prst.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String email = rs.getString("email");
			pro = new Professional(id, firstName, lastName, email);
		}
		
		return pro;
	}
	
	public void addPortfolio (ProfessionalPortfolio proPort) throws SQLException {
		conn = getConnection();
		prst = conn.prepareStatement(INSERT_PORTFOLIO);
		prst.setString(1, proPort.getFirstName());
		prst.setString(2, proPort.getLastName());
		prst.setString(3, proPort.getEmail());
		prst.setString(4, proPort.getPostalAddress());
		prst.setString(5, proPort.getCellNumber());
		prst.setString(6, proPort.getDateOfBirth());
		prst.setString(7, proPort.getCountry());
		prst.setString(8, proPort.getProfession());
		prst.setString(9, proPort.getEducation());
		prst.executeUpdate();
		
	}
	
	public void updatePortfolio(ProfessionalPortfolio proPort) throws SQLException {
		conn = getConnection();
		prst = conn.prepareStatement(UPDATE_PORTFOLIO);
		prst.setString(1, proPort.getFirstName());
		prst.setString(2, proPort.getLastName());
		prst.setString(3, proPort.getEmail());
		prst.setString(4, proPort.getPostalAddress());
		prst.setString(5, proPort.getCellNumber());
		prst.setString(6, proPort.getDateOfBirth());
		prst.setString(7, proPort.getCountry());
		prst.setString(8, proPort.getProfession());
		prst.setString(9, proPort.getEducation());
		prst.setInt(10, proPort.getId());
		prst.executeUpdate();
		
	}
	
	public void deletePortfolio(int id) throws SQLException {
		conn = getConnection();
		prst = conn.prepareStatement(DELETE_PORTFOLIO);
		prst.setInt(1, id);
		prst.executeUpdate();
	}
	
	public List<Professional> selectAllProfessionals(){
		
		List<Professional> pro = new ArrayList<>();
		try(Connection conn = getConnection();
				PreparedStatement prst = conn.prepareStatement(LIST_ALL_PROS);){
			
			ResultSet rs = prst.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				pro.add(new Professional(id, firstName, lastName, email));
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return pro;
	}

















}
