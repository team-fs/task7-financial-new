package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import formbeans.CusLoginForm;

public class CusLoginAction extends Action {
	private FormBeanFactory<CusLoginForm> formBeanFactory = FormBeanFactory.getInstance(CusLoginForm.class);
	
	private CustomerDAO customerDAO;
	
	public CusLoginAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}
	
	public String getName() {
		return "cuslogin.do";
	}
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors",errors);
		
		try {
			CusLoginForm form = formBeanFactory.create(request);
			request.setAttribute("form",form);
			
			if(!form.isPresent()) {
				return "index.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			if(errors.size() != 0) {
				return "index.jsp";
			}
			
			CustomerBean customer = customerDAO.read(form.getUserName());
			
			if(customer == null) {
				errors.add("Username not found");
				return "index.jsp";
			}
			
			if(!(form.getPassword()).equals(customer.getPassword())) {
				errors.add("Incorrect password");
				return "index.jsp";
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("customer",customer);
			
			return "viewAccountByCus.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}

}
