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

import com.digitalcity.dao.ITCompanyDAO;
import com.digitalcity.model.ITCompany;

/**
 * Servlet implementation class CompanyServlet
 */
@WebServlet("/CompanyServlet")
public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ITCompanyDAO compDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyServlet() {
        this.compDAO = new ITCompanyDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		try {
			switch(action) {
			case "/newForm":
				showNewForm(request, response);
				break;
			case "/registerComp":
				registerCompany(request, response);
				break;
			case "/search":
				searchByName(request, response);
			case "updateComp":
				updateCompany(request, response);
				break;
			case "/delComp":
				deleteCompany(request, response);
				break;
			case "/allComps":
				selectAllComps(request, response);
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("company-form.jsp");
		dispatcher.forward(request, response);
	}
	private void registerCompany(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String compName = request.getParameter("companyName");
		String phyAdd = request.getParameter("physicalAddress");
		String tellNumb = request.getParameter("telephoneNumber");
		String website = request.getParameter("website");
		String email = request.getParameter("email");
		
		ITCompany comp = new ITCompany(compName, phyAdd, tellNumb, website, email);
		compDAO.registerCompany(comp);
		response.sendRedirect("compsList");
	}
	private void searchByName(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String name = request.getParameter("companyName");
		ITCompany searchComp = compDAO.searchByName(name);
		RequestDispatcher dispatcher = request.getRequestDispatcher("companies.jsp");
		request.setAttribute("searchByName", searchComp);
		dispatcher.forward(request, response);
	}
	private void updateCompany(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String compName = request.getParameter("companyName");
		String phyAdd = request.getParameter("physicalAddress");
		String tellNumb = request.getParameter("telephoneNumber");
		String website = request.getParameter("website");
		String email = request.getParameter("email");
		
		ITCompany comp = new ITCompany(id, compName, phyAdd, tellNumb, website, email);
		compDAO.updateCompany(comp);
		response.sendRedirect("list");
	}
	
	private void deleteCompany(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		compDAO.deleteComp(id);
		response.sendRedirect("list");
	}
	
	private void selectAllComps(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ITCompany> comps = compDAO.selectAllComps();
		request.setAttribute("selectAllComps", comps);
		RequestDispatcher dispatcher = request.getRequestDispatcher("companies.jsp");
		dispatcher.forward(request, response);
	}
	

}
