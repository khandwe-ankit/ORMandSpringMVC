package com.global.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.global.crm.model.Customer;
import com.global.crm.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public String fetchAllcustomers(Model model) {
		List<Customer> customers = customerService.fetchAllCustomersFromDB();
		model.addAttribute("customers", customers);
		return "customer-list";
	}

	@RequestMapping("/add")
	public String addCustomer(Model model) {
		Customer customer = new Customer();

		model.addAttribute("customer", customer);
		return "customer-form";
	}

//	@RequestMapping("/save")
//	public String saveCustomer(@ModelAttribute("customer") int id, String firstName, String lastName, String email) {
//		Customer customer;
//		if (id != 0) {
//			customer = customerService.findCustomerByIdFromDB(id);
//			customer.setFirstName(firstName);
//			customer.setLastName(lastName);
//			customer.setEmail(email);
//		} else
//			customer = new Customer(firstName, lastName, email);
//		customerService.saveCustomerDetailsToDB(customer);
//		return "redirect:/customers/list";
//	}

	@RequestMapping("/save")
	public String saveCustomer(@ModelAttribute("customer") Customer cust) {
		Customer customer;
		if (cust.getId() != 0) {
			customer = customerService.findCustomerByIdFromDB(cust.getId());
			customer.setFirstName(cust.getFirstName());
			customer.setLastName(cust.getLastName());
			customer.setEmail(cust.getEmail());
		} else
			customer = new Customer(cust.getFirstName(), cust.getLastName(), cust.getEmail());
		customerService.saveCustomerDetailsToDB(customer);
		return "redirect:/customers/list";
	}

	@RequestMapping("/update")
	public String updateCustomerDetails(@RequestParam("id") int id, Model model) {
		Customer customer = customerService.findCustomerByIdFromDB(id);
		model.addAttribute("customer", customer);
		return "customer-form";

	}

	@RequestMapping("/delete")
	public String deleteCustomerDetails(@RequestParam("id") int id) {
		Customer customer = customerService.findCustomerByIdFromDB(id);
		customerService.deleteCustomerDetailsFromDB(customer);
		return "redirect:/customers/list";
	}

	@RequestMapping("/search")
	public String searchCustomers(@ModelAttribute("customer") Customer cust, Model model) {
		if (cust.getFirstName().trim().isEmpty() && cust.getLastName().trim().isEmpty()
				&& cust.getEmail().trim().isEmpty()) {
			return "redirect:/customers/list";
		} else {
			List<Customer> customers = customerService.searchCustomerInDB(cust);
			model.addAttribute("searchedCustData", cust);
			model.addAttribute("customers", customers);
			return "customer-list";
		}
	}
}
