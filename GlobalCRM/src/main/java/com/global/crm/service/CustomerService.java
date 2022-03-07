package com.global.crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global.crm.model.Customer;

@Service
public interface CustomerService {

	List<Customer> fetchAllCustomersFromDB();

	void saveCustomerDetailsToDB(Customer customer);

	Customer findCustomerByIdFromDB(int id);

	void deleteCustomerDetailsFromDB(Customer customer);

	List<Customer> searchCustomerInDB(Customer cust);

}
