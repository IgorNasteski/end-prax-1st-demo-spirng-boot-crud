package com.example.demo.service;

import com.example.demo.dao.AdditionalMethodsCustomerRepository;
import com.example.demo.dao.OrdersRepository;
import com.example.demo.entity.Customers;
import com.example.demo.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private AdditionalMethodsCustomerRepository additionalMethodsCustomerRepository;

    @Transactional
    public void saveCustomerAndOrderWhileIncreasingCustomerCounter(Orders theOrder){
        int theCustomerId = theOrder.getCustomer().getCustomerId();
            System.out.println("CUSTOMER ID =   "  + theCustomerId);

        Customers customer = additionalMethodsCustomerRepository.findByCustomerId(theCustomerId);
        int dohvatiCustomerOrderCounter = customer.getOrderCounter();
            System.out.println("VREDNOST COUNTERA U CUSTOMERU =   " + dohvatiCustomerOrderCounter);
            System.out.println("CUstomer id   = " + customer.getCustomerId() + "  orderCounter   " + customer.getOrderCounter());
        customer.setOrderCounter(dohvatiCustomerOrderCounter + 1);
        theOrder.setCustomer(customer);

        additionalMethodsCustomerRepository.save(customer);
        ordersRepository.save(theOrder);
    }

}
