//Jiayi Zhu
//jiayiz
//08-600
//Dec 10, 2014

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.FundDAO;
import model.Model;
import model.PriceDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.FundBean;
import databeans.PriceBean;

public class TransitionAction extends Action {
	private FundDAO fundDAO;
	private PriceDAO  priceDAO;
	
    public TransitionAction(Model model) {
    	fundDAO = model.getFundDAO();
    	priceDAO  = model.getPriceDAO();
	}

    public String getName() { return "transition.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
            
	    	
    		FundBean[] fundList = fundDAO.getFundList();
    		if (fundList == null) {
    			errors.add("No funds.");
    			return "error.jsp";
    		}
    		request.setAttribute("fundList",fundList);
    		return "transition.jsp";
    	} catch (RollbackException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	}
    }
}
