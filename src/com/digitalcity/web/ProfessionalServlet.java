package com.digitalcity.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.digitalcity.dao.ProfessionalsDAO;
import com.digitalcity.model.Professional;
import com.digitalcity.model.ProfessionalPortfolio;

/**
 * Servlet implementation class ProfessionalServlet
 */
@WebServlet("/ProfessionalServlet")
public class ProfessionalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   private ProfessionalsDAO proDAO;
   
    public ProfessionalServlet() {
        this.proDAO = new ProfessionalsDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		try {
			
			switch(action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/signUp":
				registerProfessional(request, response);
				break;
			case "/search":
				searchByName(request, response);
				break;
			case "/addPort":
				addPortfolio(request, response);
				break;
			case "/updatePort":
				updatePortfolio(request, response);
				break;
			case "/deletePort":
				deletePortfolio(request, response);
				break;
			case "/allPros":
				selectAllProfessionals(request, response);
				break;
			default:
				break;
			}
			
		}catch(SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("pro-form.jsp");
		dispatcher.forward(request, response);
	}
	private void registerProfessional(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		Professional newPro = new Professional(firstName, lastName, email);
		proDAO.registerProfessional(newPro);
		response.sendRedirect("list");
	}
	
	private void searchByName(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		String name = request.getParameter("firstName");
		Professional searchUser = proDAO.searchByName(name);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		request.setAttribute("pro", searchUser);
		dispatcher.forward(request, response);
	}
	private void addPortfolio(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String postalAddress = request.getParameter("postalAddress");
		String cellNumber = request.getParameter("cellNumber");
		String dob = request.getParameter("dateOfBirth");
		String country = request.getParameter("country");
		String profession = request.getParameter("profession");
		String education = request.getParameter("education");
		ProfessionalPortfolio pro = new ProfessionalPortfolio(firstName, lastName, email, postalAddress, cellNumber, dob, country, profession, education);
		proDAO.addPortfolio(pro);
		response.sendRedirect("list");
	}
	
	private void updatePortfolio(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String postalAddress = request.getParameter("postalAddress");
		String cellNumber = request.getParameter("cellNumber");
		String dob = request.getParameter("dateOfBirth");
		String country = request.getParameter("country");
		String profession = request.getParameter("profession");
		String education = request.getParameter("education");
		
		ProfessionalPortfolio pro = new ProfessionalPortfolio(id, firstName, lastName, email, postalAddress, cellNumber, dob, country, profession, education);
		proDAO.updatePortfolio(pro);
		response.sendRedirect("list");
	}
	
	private void deletePortfolio(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		proDAO.deletePortfolio(id);
		response.sendRedirect("list");
	}
	private void selectAllProfessionals(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		List<Professional> pro = proDAO.selectAllProfessionals();
		request.setAttribute("selectAllProfessionals", pro);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}
	
}
