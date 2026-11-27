package com.MainApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MainApp.Entity.Employee;
import com.MainApp.Entity.User;
import com.MainApp.Service.EmployeeService;
import com.MainApp.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class RegisterLoginLogout {

	@Autowired
	UserService uService;
	@Autowired
	EmployeeService eService;
	
	@RequestMapping(value = "/RegistrationPage")
	public String showRegistrationPage()
	{
		return "Registration";
	}
	
	@RequestMapping(value = "/register")
	public String processRegister(@ModelAttribute User u, HttpServletRequest req)
	{
		boolean status = uService.saveUser(u);
		HttpSession s =  req.getSession();
		if(status)
		{
			s.setAttribute("msg", "Registration done. Please check your email and activate your account");
		}
		else
		{
			s.setAttribute("msg", "Registration failed. Something went wrong");
		}
		return "redirect:/RegistrationPage";
	}
	
	
	@RequestMapping(value = "/LoginPage")
	public String showLogin()
	{
		return "Login";
	}
	
	@RequestMapping(value = "/login")
	public String processLogin(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpServletRequest req,
			Model model
			)
	
	{
		String res = uService.checkUser(username, password);
		HttpSession s = req.getSession();
		if(res.equals("exists"))
		{
			s.setAttribute("token", username);
			List<Employee> l = eService.readAll(username);
			model.addAttribute("le" ,l);
			return "Home";
		}
		else
		{
			s.setAttribute("msg", res);
			return "redirect:/LoginPage";
		}
	}
	
	@RequestMapping(value = "/verify")
	public String processVerify(
			@Param("code") String code,
			HttpServletRequest req
			)
	{
		boolean status = uService.verify(code);
		HttpSession s = req.getSession();
		if(status)
		{
			s.setAttribute("msg", "Your account is verified.Try to login");
			
		}
		else
		{
			s.setAttribute("msg", "Account verification failed.Try again later.");
			
		}
		return "redirect:/LoginPage";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest req) {
	    req.getSession().removeAttribute("token");
	    req.getSession().setAttribute("msg", "You have been logged out successfully.");
	    return "redirect:/RegistrationPage";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
