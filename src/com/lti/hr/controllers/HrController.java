package com.lti.hr.controllers;

import java.sql.Date;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.lti.fg.entities.ActiveUserCard;
import com.lti.fg.entities.Cart;
import com.lti.fg.entities.EmiHistory;
import com.lti.fg.entities.OrderDetails;
import com.lti.fg.entities.OrderHistory;
import com.lti.fg.entities.Product;
import com.lti.fg.entities.User;
import com.lti.fg.exceptions.ActiveUserCardException;
import com.lti.fg.exceptions.CartException;
import com.lti.fg.exceptions.OrderException;
import com.lti.fg.exceptions.ProductException;
import com.lti.fg.exceptions.UsersException;
import com.lti.fg.services.ActiveUserCardService;
import com.lti.fg.services.CartDetails;
import com.lti.fg.services.CartService;
import com.lti.fg.services.EmiHistoryService;
import com.lti.fg.services.OrderDetailsService;
import com.lti.fg.services.OrderHistoryService;
import com.lti.fg.services.OtpGenerator;
import com.lti.fg.services.ProductService;
import com.lti.fg.services.SendMailService;
import com.lti.fg.services.UserService;

@Controller
public class HrController {

	@Resource
	private UserService userService;

	@Resource
	private ProductService productService;

	@Resource
	private CartService cartService;

	@Resource
	private OrderDetailsService orderService;

	@Resource
	private ActiveUserCardService activeUserCardService;

	@Resource
	private SendMailService mailService;

	@Resource
	private OtpGenerator otpService;

	@Resource
	private CartDetails cartDetails;

	@Resource
	private OrderHistoryService orderHistoryService;

	@Resource
	private EmiHistoryService emiHistoryService;

	@RequestMapping("/adminDash.hr")
	public ModelAndView getHomePage() throws ProductException, ActiveUserCardException, UsersException {
		ModelAndView mv = new ModelAndView("AdminDash");
		List<ActiveUserCard> aucList = activeUserCardService.getAllUsers();
		List<User> userList = userService.getAllUsers();
		mv.addObject("aucList", aucList);
		mv.addObject("userList", userList);
		return mv;
	}

	@RequestMapping("/UserDetails.hr")
	public ModelAndView getAllUsers() throws UsersException {
		ModelAndView mv = new ModelAndView("UserList");
		List<User> userList = userService.getAllUsers();
		mv.addObject("userList", userList);
		return mv;
	}

	@RequestMapping("/preLogIn.hr")
	public ModelAndView getLogInPage() throws UsersException {
		ModelAndView mv = new ModelAndView("logIn");
		return mv;
	}

	@RequestMapping("/home.hr")
	public ModelAndView getProductPage(HttpServletResponse response, HttpSession session)
			throws ProductException, UsersException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Cache-Control", "no-Cache, no-Store, must-revalidate");
		httpResponse.setHeader("Pragma", "no-Cache");
		httpResponse.setDateHeader("Expires", 0);
		ModelAndView mv = new ModelAndView("ProductDisplay");
		if (session.getAttribute("userId") != null) {
			int userId = (int) session.getAttribute("userId");
			List<Product> productList = userService.viewCartDetailsByUserId(userId);
			mv.addObject("lengthOfCart", productList.size());
		} else {
			mv.addObject("lengthOfCart", 0);
		}

		List<Product> productList = productService.getAllProducts();

		mv.addObject("productList", productList);
		return mv;
	}

	@RequestMapping("/productDetails.hr")
	public ModelAndView getProductById(@RequestParam("id") int id, HttpSession session)
			throws ProductException, UsersException {
		ModelAndView mv = new ModelAndView("ProductDetails");
		if (session.getAttribute("userId") != null) {
			int userId = (int) session.getAttribute("userId");
			List<Product> productList = userService.viewCartDetailsByUserId(userId);
			mv.addObject("lengthOfCart", productList.size());
		} else {
			mv.addObject("lengthOfCart", 0);
		}
		Product product = productService.findProductById(id);

		mv.addObject("product", product);
		return mv;
	}

	@RequestMapping("/addToCart.hr")
	public String addToCart(@RequestParam("id") int id, HttpServletRequest request)
			throws CartException, ProductException, UsersException {
		HttpSession session = request.getSession(false);
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId != null) {

			Cart cart = new Cart(userId, id, 1);
			boolean isAdded = cartService.addToCart(cart);

			Product product = productService.findProductById(id);
			request.setAttribute("product", product);

			List<Product> productList = userService.viewCartDetailsByUserId(userId);
			request.setAttribute("lengthOfCart", productList.size());

			return "ProductDetails";

		} else {
			return "redirect:/";
		}

	}

	@RequestMapping("/viewCart.hr")
	public ModelAndView viewCart(@PathParam("userId") int userId, @PathParam("setAlert") String setAlert,
			HttpServletRequest request, HttpServletResponse response)
					throws UsersException, CartException, ActiveUserCardException {

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Cache-Control", "no-Cache, no-Store, must-revalidate");
		httpResponse.setHeader("Pragma", "no-Cache");
		httpResponse.setDateHeader("Expires", 0);

		ModelAndView mv = null;
		if (userId != 0) {
			double cardBalance = activeUserCardService.showCardBalance(userId);
			String userStatus = activeUserCardService.showUserStatus(userId);
			String cardStatus = activeUserCardService.showCardStatus(userId);

			mv = cartDetails.getCartDetails(userId);
			mv.setViewName("ViewCart");
			mv.addObject("cardBalance", cardBalance);
			mv.addObject("setAlert", setAlert);
			mv.addObject("userId", userId);
			mv.addObject("userStatus", userStatus);
			mv.addObject("cardStatus", cardStatus);
			return mv;
		} else {

			mv = new ModelAndView("redirect:/");
			return mv;
		}
	}

	@RequestMapping("/invoice.hr")
	public ModelAndView getInvoicePage(@PathParam("userId") int userId)
			throws UsersException, CartException, ActiveUserCardException {

		List<Integer> quant = new ArrayList<>();

		List<Product> productList = userService.viewCartDetailsByUserId(userId);
		double cartCost = 0.0;
		for (Product product : productList) {
			int quantt = cartService.getQuantity(userId, product.getProductId());

			cartCost += (product.getProductCost() * quantt);

			quant.add(quantt);
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("productList", productList);
		mv.addObject("Quantity", quant);

		mv.addObject("cartCost", cartCost);

		mv.setViewName("Invoice");

		return mv;
	}

	@RequestMapping("/selectCard.hr")
	public ModelAndView getCardPage() {
		ModelAndView mv = new ModelAndView("CardPage");
		return mv;
	}

	@RequestMapping("/preRegistration.hr")
	public ModelAndView getPreRegistrationPage(@PathParam("cardType") String cardType) {
		ModelAndView mv = new ModelAndView("PreRegistration");
		mv.addObject("cardType", cardType);
		return mv;
	}

	@RequestMapping("/registration.hr")
	public String getRegistrationPage(HttpServletRequest request) throws UsersException, ActiveUserCardException {
		User newUser = new User();
		ActiveUserCard auc = new ActiveUserCard();

		String userName = request.getParameter("username");
		newUser.setUserName(userName);

		String email = request.getParameter("useremail");
		newUser.setUserEmail(email);

		long phoneno = Long.parseLong(request.getParameter("phoneno"));
		newUser.setUserContactNumber(phoneno);

		String password = request.getParameter("psword");
		newUser.setUserPassword(password);

		String address = request.getParameter("address");
		newUser.setUserAddress(address);

		long savingsAccountNumber = Long.parseLong(request.getParameter("sba"));
		newUser.setUserSavingsAccNumber(savingsAccountNumber);

		String ifscNumber = request.getParameter("ifsc");
		newUser.setBankIfscCode(ifscNumber);

		String cardType = request.getParameter("cardType");
		auc.setCardType(cardType);

		double cardBalance = 0.0;
		if (cardType.equalsIgnoreCase("Gold"))
			cardBalance = 100000.0;
		else if (cardType.equalsIgnoreCase("Platinum"))
			cardBalance = 200000.0;
		auc.setCardBalance(cardBalance);

		java.util.Date today = Calendar.getInstance().getTime();
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(Calendar.YEAR, 3);
		java.util.Date validityDate = c.getTime();
		Date cardValidity = new Date(validityDate.getTime());
		auc.setCardValidity(cardValidity);

		int userId = 0;
		userId = userService.insertUser(newUser);
		auc.setUserId(userId);
		auc.setUserStatus("Not Verified");
		auc.setCardStatus("Not Paid");

		if (userId > 0) {
			long cardId = activeUserCardService.insertRecord(auc);
			request.setAttribute("cardId", cardId);
			request.setAttribute("userId", userId);
			return "CardCharges";
		}
		return "redirect:/selectCard.hr";
	}

	@RequestMapping("/confirmOrder.hr")
	public String orderPage(HttpServletRequest request, HttpServletResponse response)
			throws UsersException, CartException, OrderException, ActiveUserCardException {

		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Cache-Control", "no-Cache, no-Store, must-revalidate");
		httpResponse.setHeader("Pragma", "no-Cache");
		httpResponse.setDateHeader("Expires", 0);
		int userId = Integer.parseInt(request.getParameter("userId"));
		if (userId != 0) {
			double totalCost = Double.parseDouble(request.getParameter("totalCost"));

			int tenure = Integer.parseInt(request.getParameter("tenure"));
			int monthlyEmi = (int) Math.ceil(totalCost / tenure);
			String userStatus = activeUserCardService.showUserStatus(userId);
			String cardStatus = activeUserCardService.showCardStatus(userId);
			double cardBalance = Double.parseDouble(request.getParameter("cardBalance"));
			java.sql.Date timeStamp = new java.sql.Date(Calendar.getInstance().getTime().getTime());

			List<Integer> quant = new ArrayList<>();
			double cartCost = 0.0;
			List<Product> productList = userService.viewCartDetailsByUserId(userId);
//why here 
			for (Product product : productList) {
				int quantt = cartService.getQuantity(userId, product.getProductId());

				cartCost += (product.getProductCost() * quantt);

				quant.add(quantt);
			}

			if (userStatus.equalsIgnoreCase("verified") && cardBalance > totalCost
					&& cardStatus.equalsIgnoreCase("Paid")) {
				int orderId = orderService.placeOrder(new OrderDetails(userId, tenure, monthlyEmi, timeStamp, 0));
				if (orderId > 0) {
					for (int i = 0; i < productList.size(); i++)
						orderHistoryService.insertOrder(
								new OrderHistory(orderId, userId, productList.get(i).getProductId(), quant.get(i)));
					for (int i = 1; i <= tenure; i++) {
						Calendar c = Calendar.getInstance();
						c.setTime(timeStamp);
						c.add(Calendar.MONTH, i);
						java.util.Date d = c.getTime();
						Date nextPayDate = new Date(d.getTime());
						emiHistoryService
						.insertEmiHistoryRecord(new EmiHistory(userId, orderId, monthlyEmi, nextPayDate));
					}
					Calendar c = Calendar.getInstance();
					c.setTime(timeStamp);
					c.add(Calendar.MONTH, 1);
					java.util.Date d = c.getTime();
					Date firstEmi = new Date(d.getTime());

					activeUserCardService.deductCardBalance((cardBalance - totalCost), userId);
					User user = userService.getUserById(userId);
					String subject = "Re: Your Order Confirmation";
					String text = "Hi " + user.getUserName()
					+ ",\nYour bag of joy containing the products that you ordered is on it's way.\nBalance credits in your account are "
					+ (cardBalance - totalCost) + ".\nFirst emi due is on " + firstEmi
					+ ".\nFor Futher Details check EMI Details tab in your Profile.\nThankyou for shopping with us,\nFinShop.";
					mailService.send(user.getUserEmail(), subject, text);
					cartService.deleteRecordById(userId);
				}
				return "redirect:/home.hr";
			} else {
				return "redirect:/viewCart.hr?userId=" + userId + "&setAlert=alert";
			}

		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/deleteOrder.hr")
	public String deleteOrder(@PathParam("prodId") int prodId, HttpSession session) throws CartException {
		boolean isDeleted = cartService.deleteRecordByProduct(prodId);
		if (isDeleted)
			return "redirect:/viewCart.hr?userId=" + session.getAttribute("userId");
		return "redirect:/viewCart.hr";
	}

	@RequestMapping("/verifyUser.hr")
	public String verifyUser(@PathParam("userId") int userId) throws ActiveUserCardException, UsersException {
		User user = userService.getUserById(userId);
		String subject = "Re:Verification";
		String text = "Your details have been verified and you may use your card.";
		mailService.send(user.getUserEmail(), subject, text);
		boolean isVerified = activeUserCardService.verifyUser(userId);
		return "redirect:/adminDash.hr";
	}

	@RequestMapping("/rejectUser.hr")
	public String rejectUser(@PathParam("userId") int userId) throws ActiveUserCardException, UsersException {
		User user = userService.getUserById(userId);
		String subject = "Re:Verification";
		String text = "Your details don't match with uploaded documents. Kindly re-upload your documents.";
		mailService.send(user.getUserEmail(), subject, text);
		boolean isRejected = activeUserCardService.rejectUser(userId);
		return "redirect:/adminDash.hr";
	}

	@RequestMapping("/deleteUser.hr")
	public String deleteUser(@PathParam("userId") int userId) throws ActiveUserCardException, UsersException {
		User user = userService.getUserById(userId);
		String subject = "Re:Verification";
		String text = "Your account has been deactivated because of suspicious activities.";
		mailService.send(user.getUserEmail(), subject, text);
		boolean isdeleted = activeUserCardService.deleteUser(userId);
		userService.deleteUser(userId);
		return "redirect:/adminDash.hr";
	}

	@RequestMapping("/generateOtp.hr")
	public String generateOtp(HttpServletRequest request, HttpSession session) throws UsersException {
		String otp = otpService.generatorOTP(6);
		request.setAttribute("otp", otp);
		int userId = Integer.parseInt(request.getParameter("userId"));
		long cardId = Long.parseLong(request.getParameter("cardId"));
		User user = userService.getUserById(userId);
		request.setAttribute("userId", userId);
		request.setAttribute("cardId", cardId);
		
		String subject = "Re:OTP for Initial Card Charges";
		String text = otp + " is your One Time Password(OTP) for payment of preffered card type charges";
		mailService.send(user.getUserEmail(), subject, text);
		return "CardCharges";
	}

	@RequestMapping("/verifyOtp.hr")
	public String verifyOtp(HttpServletRequest request) throws ActiveUserCardException, UsersException {
		String generatedOtp = request.getParameter("generatedOtp");
		String enteredOtp = request.getParameter("otp");
		int userId = Integer.parseInt(request.getParameter("userId"));
		long cardId = Long.parseLong(request.getParameter("cardId"));
		String cardType = activeUserCardService.getUserById(cardId).getCardType();
		// long cardId= activeUserCardService.getUserById(cardId).getCardId();
		if (generatedOtp.equals(enteredOtp)) {
			String text = "Your payment of Card Number:" + cardId + " Type:" + cardType
					+ " has been paid succcessfully, Please upload your documents for further verification.";
			String subject = "Re: Card Payment";
			String email = userService.getUserById(userId).getUserEmail();
			mailService.send(email, subject, text);
			activeUserCardService.verifyPayment(cardId);
			return "redirect:/";
		}
		return "redirect:/generateOtp.hr?id=" + userId;
	}

	@RequestMapping("/goldFaq.hr")
	public String getGoldFaqPage() {
		return "Goldfaq";
	}

	@RequestMapping("/platinumFaq.hr")
	public String getPlatFaqPage() {
		return "Platinumfaq";
	}

}
