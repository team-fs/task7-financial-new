package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import model.CustomerDAO;
import model.EmployeeDAO;
import model.FundDAO;
import model.PositionDAO;
import model.PriceDAO;
import model.TransactionDAO;

import databeans.CustomerBean;
import databeans.EmployeeBean;
import databeans.FundBean;
import databeans.PositionBean;
import databeans.PriceBean;
import databeans.TransactionBean;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {

    public void init() throws ServletException {
        Model model = new Model(getServletConfig());
        
        CustomerDAO customerDAO = model.getCustomerDAO();
        EmployeeDAO employeeDAO = model.getEmployeeDAO();
        FundDAO fundDAO = model.getFundDAO();
        PositionDAO positionDAO = model.getPositionDAO();
        PriceDAO priceDAO = model.getPriceDAO();
        TransactionDAO transactionDAO = model.getTransactionDAO();
       
        Action.add(new ChangePwdAction(model));
        Action.add(new LoginAction(model));
        Action.add(new LogoutAction(model));
        Action.add(new ManageAction(model));
        Action.add(new CreateCustomerAction(model));
        Action.add(new CreateEmployeeAction(model));
        Action.add(new ResearchFundAction(model));
        Action.add(new ViewHistoryAction(model));
        Action.add(new BuyFundAction(model));
        Action.add(new SellFundAction(model));
        Action.add(new RequestCheckAction(model));
        Action.add(new DepositCheckAction(model));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = performTheAction(request);
        sendToNextPage(nextPage,request,response);
    }
    
    /*
     * Extracts the requested action and (depending on whether the user is logged in)
     * perform it (or make the user login).
     * @param request
     * @return the next page (the view)
     */
    private String performTheAction(HttpServletRequest request) {
        HttpSession session     = request.getSession(true);
        String      servletPath = request.getServletPath();
        User        user = (User) session.getAttribute("user");
        String      action = getActionName(servletPath);

        if (action.equals("register.do") || action.equals("login.do")) {
        	// Allow these actions without logging in
			return Action.perform(action,request);
        }
        
        if (user == null && !action.equals("list.do") && !action.equals("view.do")) {
        	// If the user hasn't logged in, direct him to the login page
			return Action.perform("login.do",request);
        }

      	// Let the logged in user run his chosen action
		return Action.perform(action,request);
    }

    /*
     * If nextPage is null, send back 404
     * If nextPage ends with ".do", redirect to this page.
     * If nextPage ends with ".jsp", dispatch (forward) to the page (the view)
     *    This is the common case
     */
    private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	if (nextPage == null) {
    		response.sendError(HttpServletResponse.SC_NOT_FOUND,request.getServletPath());
    		return;
    	}
    	
    	if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
    	}

    	if (nextPage.endsWith(".jsp")) {
	   		RequestDispatcher d = request.getRequestDispatcher("WEB-INF/" + nextPage);
	   		d.forward(request,response);
	   		return;
    	}
    	
    	if (nextPage.equals("image")) {
	   		RequestDispatcher d = request.getRequestDispatcher(nextPage);
	   		d.forward(request,response);
	   		return;
    	}
    	
    	throw new ServletException(Controller.class.getName()+".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
    }

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
    private String getActionName(String path) {
    	// We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash+1);
    }
}
