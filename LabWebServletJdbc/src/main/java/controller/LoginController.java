package controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.CustomerBean;
import model.CustomerService;

@Controller
@RequestMapping(
		path={"/secure/login.controller"},
		method={RequestMethod.GET, RequestMethod.POST}
)
public class LoginController {
	@Autowired
	@Resource(name="customerService")
	private CustomerService customerService;
	
	@RequestMapping
	public String service(
			@RequestParam(name="username") String username,
			@RequestParam(name="password") String password,
			Model model, HttpSession session) {
//接收資料
//驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);
		if(username==null || username.length()==0) {
			errors.put("username", "ID is required (mvc)");
		}
		if(password==null || password.length()==0) {
			errors.put("password", "PWD is required (mvc)");
		}
		if(errors!=null && !errors.isEmpty()) {
			return "login.error";
		}		
//呼叫Model
		CustomerBean bean = customerService.login(username, password);
		
//根據Model的執行結果，顯示View
		if(bean==null) {
			errors.put("password", "Login fail, please try again.");
			return "login.error";
		} else {
			session.setAttribute("user", bean);
			
			return "login.success";
		}
		
	}
}
