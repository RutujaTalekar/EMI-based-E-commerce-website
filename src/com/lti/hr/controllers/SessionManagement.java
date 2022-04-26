package com.lti.hr.controllers;

import java.sql.SQLSyntaxErrorException;

import javax.annotation.Resource;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.lti.fg.entities.User;
import com.lti.fg.exceptions.UsersException;
import com.lti.fg.services.OtpGenerator;
import com.lti.fg.services.SendMailService;
import com.lti.fg.services.UserService;

@Controller
public class SessionManagement {
	@Resource
	private UserService userService;

	@Resource
	private OtpGenerator otpService;
	
	@Resource
	private SendMailService mailService;
	
	@RequestMapping("/login.hr")
	public String login(HttpServletRequest request, HttpSession session) throws UsersException {
		String userEmail = request.getParameter("email");
		String userPass = request.getParameter("uPass");
		int userId = userService.logIn(new User(userEmail, userPass));
		if (session.getAttribute("userId") == null && userId != -1) {
			session = request.getSession();
			session.setAttribute("userId", userId);
			return "redirect:/";
		} else {
			return "redirect:/";
		}

	}

	@RequestMapping("/logout.hr")
	public String logout(HttpSession session, HttpServletResponse response) {
		session.invalidate();
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Cache-Control", "no-Cache, no-Store, must-revalidate");
		httpResponse.setHeader("Pragma", "no-Cache");
		httpResponse.setDateHeader("Expires", 0);
		return "redirect:/";
	}
	
	@RequestMapping("forgotPassword.hr")
	public ModelAndView getForgotPasswordPage()
	{
		ModelAndView mv = new ModelAndView("ForgotPassword");
		mv.addObject("otp", "notSent");
		return mv;
	}
	
	@RequestMapping("generateOtpForgotPassword.hr")
	public ModelAndView generateOtpForForgotPassword(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("ForgotPassword");
		String email = request.getParameter("email");
		String otp = otpService.generatorOTP(6);
		String subject = "Re:OTP for Password Reset";
		String text = otp + " is your One Time Password(OTP) for resetting your password.";
		mailService.send(email, subject, text);
		mv.addObject("otpp", otp);
		mv.addObject("otp", "sent");
		mv.addObject("email", email);
		return mv;
	}
	
	@RequestMapping("verifyOtpForgotPassword.hr")
	public ModelAndView verifyOtpForForgotPassword(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("UpdatePassword");
		String email = request.getParameter("email");
		int enteredOtp = Integer.parseInt(request.getParameter("enteredOtp"));
		int generatedOtp = Integer.parseInt(request.getParameter("sentOtp"));
		if(generatedOtp==enteredOtp) {
			mv.addObject("isVerified", "yes");
			mv.addObject("email", email);
			return mv;
		}
		mv.addObject("isVerified", "no");
		return mv;
	}
	
	@RequestMapping("changePassword.hr")
	public String changePassword(HttpServletRequest request) throws UsersException
	{
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String rpass = request.getParameter("rpassword");
		if(pass.equals(rpass))
		{
			boolean isUpdated = userService.updatePassword(email,pass);
			if(isUpdated)
			{
				String subject = "Re:Password Reset";
				String text = "Your Password has been successfully updated. Login with your new Password";
				mailService.send(email, subject, text);
			}
		}
		return "redirect:/";
	}
}
