package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.ProductBean;
import model.ProductService;

@Controller
@RequestMapping(
		path={"/pages/product.controller"},
		method={RequestMethod.GET, RequestMethod.POST}
)
public class ProductController {
	@Autowired
	private SimpleDateFormat sdFormat = null;
	
	@Autowired
	@Resource(name="productService")
	private ProductService productService;
	@RequestMapping
	public String service(
			@RequestParam(name="id") String temp1,
			@RequestParam(name="name") String name,
			@RequestParam(name="price") String temp2,
			@RequestParam(name="make") String temp3,
			@RequestParam(name="expire") String temp4,
			@RequestParam(name="prodaction") String prodaction,
			Model model) {
//接收資料
//驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);

		if("Insert".equals(prodaction) || "Update".equals(prodaction) || "Delete".equals(prodaction)) {
			if(temp1==null || temp1.length()==0) {
				errors.put("id", "(mvc) Please enter ID for "+prodaction);
			}
		}
//轉換資料
		int id = 0;
		if(temp1!=null && temp1.length()!=0) {
			try {
				id = Integer.parseInt(temp1);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("id", "ID must be an integer (mvc)");
			}
		}
		
		double price = 0;
		if(temp2!=null && temp2.length()!=0) {
			try {
				price = Double.parseDouble(temp2);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("price", "Price must be a number (mvc)");
			}
		}
		
		java.util.Date make = null;
		if (temp3!=null && temp3.length()!=0) {
			try {
				make = sdFormat.parse(temp3);
			} catch (ParseException e) {
				e.printStackTrace();
				errors.put("make", "Make must be a date of YYYY-MM-DD (mvc)");
			} 
		}
		
		int expire = 0;
		if(temp4!=null && temp4.length()!=0) {
			try {
				expire = Integer.parseInt(temp4);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("expire", "Expire must be an integer (mvc)");
			}
		}
		
		if(errors!=null && !errors.isEmpty()) {
			return "product.error";
		}
		
//呼叫Model
		ProductBean bean = new ProductBean();
		bean.setId(id);
		bean.setName(name);
		bean.setPrice(price);
		bean.setMake(make);
		bean.setExpire(expire);

//根據Model的執行結果，顯示View
		if("Select".equals(prodaction)) {
			List<ProductBean> result = productService.select(bean);
			model.addAttribute("select", result);
			return "product.select";
		} else if("Insert".equals(prodaction)) {
			ProductBean result = productService.insert(bean);
			if(result==null) {
				errors.put("action", "Insert fail");
			} else {
				model.addAttribute("insert", result);
			}
			return "product.error";
		} else if("Update".equals(prodaction)) {
			ProductBean result = productService.update(bean);
			if(result==null) {
				errors.put("action", "Update fail");
			} else {
				model.addAttribute("update", result);
			}
			return "product.error";
		} else if("Delete".equals(prodaction)) {
			boolean result = productService.delete(bean);
			if(!result) {
				model.addAttribute("delete", 0);
			} else {
				model.addAttribute("delete", 1);
			}
			return "product.error";			
		} else  {
			errors.put("action", "Unknown Action:"+prodaction);
			return "product.error";
		}
	}
	
}
