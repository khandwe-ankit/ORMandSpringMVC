package com.global.crm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.global.crm.model.Customer;

@Repository
public class CustomerServiceImpl implements CustomerService {

	private SessionFactory sessionFactory;

	private Session session;

	@Autowired
	public CustomerServiceImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
	}

	@Override
	@Transactional
	public List<Customer> fetchAllCustomersFromDB() {
		Transaction tx = session.beginTransaction();
		List<Customer> customers = session.createQuery("from Customer").list();
		tx.commit();
//		session.getTransaction().commit();
		return customers;
	}

	@Override
	@Transactional
	public void saveCustomerDetailsToDB(Customer customer) {
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(customer);
		tx.commit();
	}

	@Override
	@Transactional
	public Customer findCustomerByIdFromDB(int id) {
		Transaction tx = session.beginTransaction();
		Customer customer = session.get(Customer.class, id);
		tx.commit();
		return customer;
	}

	@Override
	@Transactional
	public void deleteCustomerDetailsFromDB(Customer customer) {
		Transaction tx = session.beginTransaction();
		session.delete(customer);
		tx.commit();

	}

	@Override
	public List<Customer> searchCustomerInDB(Customer cust) {
		String query = "";
		String firstName = cust.getFirstName();
		String lastName = cust.getLastName();
		String email = cust.getEmail();
		if (firstName.length() != 0 && lastName.length() != 0 && email.length() != 0)
			query = "from Customer where firstName like'%" + firstName + "%' or lastName like'%" + lastName
					+ "%' or email like'%" + email + "%'";
		else if (firstName.length() != 0 && lastName.length() != 0)
			query = "from Customer where firstName like'%" + firstName + "%' or lastName like'%" + lastName + "%'";
		else if (firstName.length() != 0 && email.length() != 0)
			query = "from Customer where firstName like'%" + firstName + "%' or email like'%" + email + "%'";
		else if (lastName.length() != 0 && email.length() != 0)
			query = "from Customer where lastName like'%" + lastName + "%' or email like'%" + email + "%'";
		else if (firstName.length() != 0)
			query = "from Customer where firstName like'%" + firstName + "%'";
		else if (lastName.length() != 0)
			query = "from Customer where lastName like'%" + lastName + "%'";
		else
			query = "from Customer where email like'%" + email + "%'";
		Transaction tx = session.beginTransaction();
		List<Customer> customers = session.createQuery(query).list();
		tx.commit();
		return customers;
	}

}
