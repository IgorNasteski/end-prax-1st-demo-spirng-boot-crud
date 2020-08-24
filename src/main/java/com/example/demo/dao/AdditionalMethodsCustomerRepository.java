package com.example.demo.dao;

import com.example.demo.entity.Customers;
import com.example.demo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Component
//@Repository
public interface AdditionalMethodsCustomerRepository extends JpaRepository<Customers,Integer>{

    //List<Customers> findByFirstName(String theSearchName);
    List<Customers> findByFirstNameOrLastName(String firstName, String lastName);
    List<Customers> findByFirstNameOrLastNameOrPhoneOrEmail(String firstName, String lastName, String phone, String email);
    List<Customers> findByFirstNameOrLastNameOrPhoneOrEmailOrStreetOrCityOrState(String firstName, String lastName, String phone, String email, String street, String city, String state);

    public Customers findByCustomerId(int customerId);


    @Query("SELECT c FROM Customers c WHERE c.firstName LIKE %?1")
    //@Query("SELECT c FROM Customers c WHERE c.firstName LIKE %?1%")
    public List<Customers> findByFirstName(String firstName);

    public List<Customers> findByFirstNameContaining(String firstName);

    public List<Customers> findByFirstNameStartsWith(String firstName);


}
