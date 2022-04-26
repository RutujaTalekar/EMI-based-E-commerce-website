package com.lti.hr.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lti.fg.entities.ActiveUserCard;
import com.lti.fg.entities.EmiHistory;
import com.lti.fg.entities.OrderDetails;
import com.lti.fg.entities.OrderHistory;
import com.lti.fg.entities.Product;
import com.lti.fg.entities.User;
import com.lti.fg.exceptions.ActiveUserCardException;
import com.lti.fg.exceptions.ProductException;
import com.lti.fg.exceptions.UsersException;
import com.lti.fg.services.ActiveUserCardService;
import com.lti.fg.services.EmiHistoryService;
import com.lti.fg.services.OrderDetailsService;
import com.lti.fg.services.OrderHistoryService;
import com.lti.fg.services.OtpGenerator;
import com.lti.fg.services.ProductService;
import com.lti.fg.services.SendMailService;
import com.lti.fg.services.UserService;

@Controller
public class SaveFileController {

	@Resource
	private ActiveUserCardService userCardService;

	@Resource
	private UserService userService;

	@Resource
	private OrderHistoryService orderHistoryService;

	@Resource
	private OrderDetailsService orderDetailsService;

	@Resource
	private ProductService productService;

	@Resource
	private EmiHistoryService emiHistoryService;

	@Resource
	private SendMailService mailService;

	@Resource
	private OtpGenerator otpService;

	@Resource
	private ActiveUserCardService activeUserCardService;

	@RequestMapping("/userDash.hr")
	public ModelAndView getUserDashPage(HttpSession session, HttpServletResponse response)
			throws ActiveUserCardException, UsersException, ProductException, NullPointerException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Cache-Control", "no-Cache, no-Store, must-revalidate");
		httpResponse.setHeader("Pragma", "no-Cache");
		httpResponse.setDateHeader("Expires", 0);
		
		Integer userId = (Integer) session.getAttribute("userId");
		ModelAndView mv = null;
		if (userId != null) {
			ActiveUserCard auc = userCardService.getUserById(userId);
			List<Product> productList = productService.getAllProducts();
			User user = userService.getUserById(userId);
			List<OrderDetails> orderDetailsList = orderDetailsService.getOrderDetailsById(userId);
			List<OrderHistory> orderHistoryList = orderHistoryService.getOrderById(userId);
			List<EmiHistory> emiHistoryList = new ArrayList<>();
			List<List<EmiHistory>> emiListOfList = new ArrayList<>();
			for (OrderDetails od : orderDetailsList) {
				emiListOfList.add(emiHistoryService.getEmiHistoryById(od.getOrderId()));
			}

			mv = new ModelAndView("UserDash");
			mv.addObject("auc", auc);
			mv.addObject("user", user);
			mv.addObject("orderHistoryList", orderHistoryList);
			mv.addObject("orderDetailsList", orderDetailsList);
			mv.addObject("productList", productList);
			mv.addObject("emiHistoryList", emiListOfList);
			return mv;
		} else {
			mv = new ModelAndView("ProductDisplay");
			return mv;
		}
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullPointer()
	{
		ModelAndView mv = new ModelAndView("Error");
		return mv;
	}

	@RequestMapping(value = "/uploadFile.hr", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("name") String name, @RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Cache-Control", "no-Cache, no-Store, must-revalidate");
		httpResponse.setHeader("Pragma", "no-Cache");
		httpResponse.setDateHeader("Expires", 0);
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId != null) {

			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();

					// Creating the directory to store file
					String rootPath = "D:\\Springs\\FinanceGladiatorSessionManagement\\WebContent\\resources\\uploadedFiles\\";
					File dir = new File(rootPath + File.separator);
					if (!dir.exists())
						dir.mkdirs();

					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					userCardService.insertFile(userId, name);
					return "redirect:/userDash.hr";
				} catch (Exception e) {
					return "You failed to upload " + name + " => " + e.getMessage();
				}
			} else {
				return "You failed to upload " + name + " because the file was empty.";
			}
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/download.hr", method = RequestMethod.GET)
	public void getFile(HttpServletResponse response) {
		try {
			DefaultResourceLoader loader = new DefaultResourceLoader();
			InputStream is = loader.getResource("classpath:META-INF/resources/uploadedFiles/course.pdf")
					.getInputStream();
			IOUtils.copy(is, response.getOutputStream());
			response.setHeader("Content-Disposition", "attachment; filename=course.pdf");
			response.flushBuffer();
		} catch (IOException ex) {
			throw new RuntimeException("IOError writing file to output stream");
		}
	}

	@RequestMapping("/preGenerateOtpForEmi.hr")
	public ModelAndView getOtpPage(@PathParam("emiAmount") double emiAmount, @PathParam("id") int id,
			HttpServletRequest request) throws UsersException {

		ModelAndView mv = new ModelAndView("payEmiBills");
		mv.addObject("monthlyEmi", emiAmount);
		mv.addObject("id", id);
		return mv;
	}

	@RequestMapping("/generateOtpForEmi.hr")
	public ModelAndView generateOtp(HttpServletRequest request) throws UsersException {

		ModelAndView mv = new ModelAndView("payEmiBills");
		String otp = otpService.generatorOTP(6);
		request.setAttribute("otp", otp);
		double emiAmount = Double.parseDouble(request.getParameter("monthlyEmi"));
		int id = Integer.parseInt(request.getParameter("id"));
		mv.addObject("id", id);
		mv.addObject("monthlyEmi", emiAmount);
		int userId = Integer.parseInt(request.getParameter("userId"));
		User user = userService.getUserById(userId);
		String subject = "Re: EMI Payment";
		String text = otp + " is your One Time Password(OTP) for payment of Monthly EMI";
		mailService.send(user.getUserEmail(), subject, text);
		return mv;
	}
	
	

	@RequestMapping("/verifyOtpForEmi.hr")
	public String verifyOtp(HttpServletRequest request) throws ActiveUserCardException, UsersException {
		String generatedOtp = request.getParameter("generatedOtp");
		String enteredOtp = request.getParameter("otp");
		int userId = Integer.parseInt(request.getParameter("userId"));
		double emiAmount = Double.parseDouble(request.getParameter("monthlyEmi"));
		int id = Integer.parseInt(request.getParameter("id"));
		java.util.Date jToday = Calendar.getInstance().getTime();
		java.sql.Date sToday = new java.sql.Date(jToday.getTime());

		if (generatedOtp.equals(enteredOtp)) {
			emiHistoryService.makePayment(id, sToday);

			activeUserCardService.payEmi(emiAmount, userId);
			String text = "Your payment of Emi - " + emiAmount + " was succcessful";
			String subject = "Re:Acknowledgement for EMI";
			String email = userService.getUserById(userId).getUserEmail();
			mailService.send(email, subject, text);
			return "redirect:/userDash.hr";
		}
		return "redirect:/preGenerateOtpForEmi.hr?emiAmount=" + emiAmount;
	}
}
