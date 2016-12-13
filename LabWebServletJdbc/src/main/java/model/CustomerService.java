package model;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service(value="customerService")
public class CustomerService {
	@Autowired
	@Resource(name="customerDao")
	private CustomerDAO customerDao;
	
	public CustomerBean login(String username, String password) {
		CustomerBean bean = customerDao.select(username);
		if(bean!=null) {
			if(password!=null && password.length()!=0) {
				byte[] pass = bean.getPassword();	//
				byte[] temp = password.getBytes();	//
				if(Arrays.equals(pass, temp)) {
					return bean;
				}
			}
		}
		return null;
	}
	public boolean changePassword(
			String username, String oldPassword, String newPassword) {
		CustomerBean bean = this.login(username, oldPassword);
		if(bean!=null) {
			if(newPassword!=null && newPassword.length()!=0) {
				byte[] temp = newPassword.getBytes();
				return customerDao.update(temp,
						bean.getEmail(), bean.getBirth(), username);
			}
		}
		return false;
	}
	public static void main(String[] args) {
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext("beans.config.xml");
		
		CustomerService customerService = (CustomerService) context.getBean("customerService");
		CustomerBean bean = customerService.login("Alex", "A");
		System.out.println("bean="+bean);
		customerService.changePassword("Ellen", "EEE", "E");
		
		((ConfigurableApplicationContext) context).close();
	}
}
