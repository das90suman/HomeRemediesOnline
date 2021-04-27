package org.healthcare.remedies.homeremediesonline.controller;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.healthcare.remedies.homeremediesonline.dao.AccountDAO;
import org.healthcare.remedies.homeremediesonline.dao.OrderDAO;
import org.healthcare.remedies.homeremediesonline.dao.ProductDAO;
import org.healthcare.remedies.homeremediesonline.entity.SignUp;
import org.healthcare.remedies.homeremediesonline.model.DiseaseInfo;
import org.healthcare.remedies.homeremediesonline.model.OrderDetailInfo;
import org.healthcare.remedies.homeremediesonline.model.OrderInfo;
import org.healthcare.remedies.homeremediesonline.model.PaginationResult;
import org.healthcare.remedies.homeremediesonline.model.ProductInfo;
import org.healthcare.remedies.homeremediesonline.validator.ProductInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
@Controller
// Enable Hibernate Transaction.
@Transactional
// Need to use RedirectAttributes
@EnableWebMvc
public class AdminController {
 
    @Autowired
    private OrderDAO orderDAO;
 
    @Autowired
    private ProductDAO productDAO;
 
    @Autowired
    private AccountDAO accountDAO;
    
    @Autowired
    private ProductInfoValidator productInfoValidator;
 
    // Configurated In ApplicationContextConfig.
    @Autowired
    private ResourceBundleMessageSource messageSource;
 
    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);
 
        if (target.getClass() == ProductInfo.class) {
            dataBinder.setValidator(productInfoValidator);
            // For upload Image.
            dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        }
    }
 
    // GET: Show Login Page
    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login(Model model) {
 
        return "login";
    }
    
   /* @RequestMapping(value = "/signup", method = RequestMethod.GET) 
    public String displaySignUp(@ModelAttribute("signUpForm")SignUp signUpForm) { 
        return "signup"; 
    }*/
 
    @RequestMapping(value = { "/accountInfo" }, method = RequestMethod.GET)
    public String accountInfo(Model model, HttpSession session, HttpServletRequest request) {
 
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.isEnabled());
 
        model.addAttribute("userDetails", userDetails);
        return "accountInfo";
	
    }
 
    @RequestMapping(value = { "/orderList" }, method = RequestMethod.GET)
    public String orderList(Model model, //
            @RequestParam(value = "page", defaultValue = "1") String pageStr) {
    	 UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	 String userName = userDetails.getUsername();
    	 
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
        }
        final int MAX_RESULT = 5;
        final int MAX_NAVIGATION_PAGE = 10;
 
        
        PaginationResult<OrderInfo> paginationResult //
        = orderDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE,userName);
 
        model.addAttribute("paginationResult", paginationResult);
        return "orderList";
    }
    
    @RequestMapping(value = { "/userList" }, method = RequestMethod.GET)
    public String userList(Model model, //
            @RequestParam(value = "page", defaultValue = "1") String pageStr) {
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
        }
        final int MAX_RESULT = 5;
        final int MAX_NAVIGATION_PAGE = 10;
 
        PaginationResult<SignUp> paginationResult //
        = accountDAO.listUserInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
 
        model.addAttribute("paginationResult", paginationResult);
        return "userList";
    }
 
    // GET: Show product.
    @RequestMapping(value = { "/product" }, method = RequestMethod.GET)
    public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        ProductInfo productInfo = null;
 
        if (code != null && code.length() > 0) {
            productInfo = productDAO.findProductInfo(code);
        }
        if (productInfo == null) {
            productInfo = new ProductInfo();
            productInfo.setNewProduct(true);
        }
        model.addAttribute("productForm", productInfo);
        return "product";
    }
 
    // POST: Save product
    @RequestMapping(value = { "/product" }, method = RequestMethod.POST)
    // Avoid UnexpectedRollbackException (See more explanations)
    @Transactional(propagation = Propagation.NEVER)
    public String productSave(Model model, //
            @ModelAttribute("productForm") @Validated ProductInfo productInfo, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
 
        if (result.hasErrors()) {
            return "product";
        }
        try {
            productDAO.save(productInfo);
        } catch (Exception e) {
            // Need: Propagation.NEVER?
            String message = e.getMessage();
            model.addAttribute("message", message);
            // Show product form.
            return "product";
 
        }
        return "redirect:/productList";
    }
    
 // GET: Show product.
    @RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
    public String showUserDetails(Model model, @RequestParam(value = "email", defaultValue = "") String email) {
        SignUp signUp = null;
        if (email != null && email.length() > 0) {
        	signUp = productDAO.findUserDetails(email);
        }
        if (signUp == null) {
        	signUp = new SignUp();
        }
        
        model.addAttribute("signUpForm", signUp);
        return "signup";
    }
    

    
 // GET: Show product.
    @RequestMapping(value = { "/UpdateUserList" }, method = RequestMethod.GET)
    public String updateUserDetails(Model model, @RequestParam(value = "email", defaultValue = "") String email) {
        SignUp signUp = null;
 
        if (email != null && email.length() > 0) {
        	signUp = productDAO.findUserDetails(email);
        }
        if (signUp == null) {
        	signUp = new SignUp();
        }
        
        model.addAttribute("UpdateUserForm", signUp);
        return "UpdateUserList";
    }
    
    // POST: Save product
    @RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
    // Avoid UnexpectedRollbackException (See more explanations)
    @Transactional(propagation = Propagation.NEVER)
    public String userregistration(Model model, //
            @ModelAttribute("signUpForm") @Validated SignUp signUp, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes,HttpSession session, HttpServletRequest request) {
    	String message = null;
        if (result.hasErrors()) {
        	message = "Sorry!! User Details not saved or updated.";
        }
        String captcha = session.getAttribute("captcha_security").toString();
		String verifyCaptcha = request.getParameter("captcha");
		if (captcha.equals(verifyCaptcha)) {
        try {
            productDAO.saveUserDetails(signUp);
            message = "User Details saved successfully.";
        } catch (Exception e) {
            // Need: Propagation.NEVER?
            message = e.getMessage();
            // Show product form.
            message = "Sorry!! User Details not saved or updated.";
 
        }
		}
		else {
			 message = "Sorry!! Captcha invalid.";
		}
        model.addAttribute("message", message);
        return "signup";
    }
    
    // POST: Save product
    @RequestMapping(value = { "/UpdateUserList" }, method = RequestMethod.POST)
    // Avoid UnexpectedRollbackException (See more explanations)
    @Transactional(propagation = Propagation.NEVER)
    public String updateUserDetails(Model model, //
            @ModelAttribute("UpdateUserForm") @Validated SignUp signUp, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
    	String message = null;
        if (result.hasErrors()) {
        	message = "Sorry!! User Details not updated.";
        }
        try {
            productDAO.updateUserDetails(signUp);
            message = "User Details updated successfully.";
        } catch (Exception e) {
            // Need: Propagation.NEVER?
            message = e.getMessage();
            // Show product form.
            message = "Sorry!! User Details not updated.";
 
        }
        model.addAttribute("message", message);
        return "UpdateUserList";
    }
    
 // POST: Delete product
    @RequestMapping(value = { "/deleteUserList" }, method = RequestMethod.GET)
    // Avoid UnexpectedRollbackException (See more explanations)
    @Transactional(propagation = Propagation.NEVER)
    public String deleteUserList(Model model, //
    		@RequestParam(value = "email", defaultValue = "") String email) {
    	String message = null;
        
        try {
            productDAO.deleteUserDetails(email);
            message = "User Details of "+email+" deleted successfully.";
        } catch (Exception e) {
            // Need: Propagation.NEVER?
            message = e.getMessage();
            // Show product form.
            message = "Sorry!! User Details of "+email+" not deleted.";
 
        }
        model.addAttribute("message", message);
        return "userList";
    }
    
    
    // GET: Show disease.
    @RequestMapping(value = { "/diseaseform" }, method = RequestMethod.GET)
    public String showDisease(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
        DiseaseInfo diseaseInfo = null;
        
        if (code != null && code.length() > 0) {
        	diseaseInfo = productDAO.findDiseaseInfo(code);
        }
        
        if (diseaseInfo == null) {
        	diseaseInfo = new DiseaseInfo();
            diseaseInfo.setNewProduct(true);
        }
        System.out.println("before code value :"+code);
        if ("".equals(code)) {
        	System.out.println("inside code value :"+code);
        	diseaseInfo = productDAO.getAllProductCodes();
        }
        model.addAttribute("diseaseDetailsForm", diseaseInfo);
        return "diseaseform";
    }
 
    // POST: Save disease
    @RequestMapping(value = { "/diseaseform" }, method = RequestMethod.POST)
    // Avoid UnexpectedRollbackException (See more explanations)
    @Transactional(propagation = Propagation.NEVER)
    public String saveDisease(Model model, //
            @ModelAttribute("diseaseDetailsForm") @Validated DiseaseInfo diseaseInfo, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
    	String message = null;
        if (result.hasErrors()) {
        	message = "Sorry!! Disease Details not saved or updated.";
            //return "diseaseform";
        }
        try {
            productDAO.saveDisease(diseaseInfo);
            message = "Disease Details saved successfully.";
        } catch (Exception e) {
            // Need: Propagation.NEVER?
            message = "Sorry!! Disease Details not saved or updated.";
            // Show product form.
            //return "diseaseform";
 
        }
        model.addAttribute("message", message);
        //return "redirect:/productList";
        return "diseaseform";
    }
 
    @RequestMapping(value = { "/order" }, method = RequestMethod.GET)
    public String orderView(Model model, @RequestParam("orderId") String orderId) {
        OrderInfo orderInfo = null;
        if (orderId != null) {
            orderInfo = this.orderDAO.getOrderInfo(orderId);
        }
        if (orderInfo == null) {
            return "redirect:/orderList";
        }
        List<OrderDetailInfo> details = this.orderDAO.listOrderDetailInfos(orderId);
        orderInfo.setDetails(details);
 
        model.addAttribute("orderInfo", orderInfo);
 
        return "order";
    }
    
}