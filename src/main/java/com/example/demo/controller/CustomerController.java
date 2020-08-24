package com.example.demo.controller;

import com.example.demo.dao.AdditionalMethodsCustomerRepository;
import com.example.demo.dao.OrdersRepository;
import com.example.demo.entity.Customers;
import com.example.demo.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private AdditionalMethodsCustomerRepository customerAdditionalMethodsRepository;

    @GetMapping("/adminsListCrudCustomers")
    public String adminsListCrudCustomers(Model theModel){
        System.out.println("USAO U ADMINSLISCRUDCUSTOMERS");
        List<Customers> listOfCustomers = customerAdditionalMethodsRepository.findAll();
        theModel.addAttribute("theCustomers", listOfCustomers);

        return "adminsListCrudCustomersPage";
    }

    @GetMapping("/showFormForAddAdmin")
    public String showAddForm(Model theModel){
        Customers theCustomer = new Customers();
        theModel.addAttribute("customer", theCustomer);
        return "addCustomer";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer")Customers theCustomer){
        customerAdditionalMethodsRepository.save(theCustomer);
				/* REDIREKTUJEM NA HomeController koji ima @RequestMapping("/") pa odma gadjam na endpoint metode
				 * A DA SAM REDIREKTOVAO NA NEKI ENDPOINT OVOG KONTROLERA MORAO BIH DA DODAM NPR "/customers/list";*/
        return "redirect:/customers/adminsListCrudCustomers";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId")int theId, Model theModel){
        Optional<Customers> theCustomer = customerAdditionalMethodsRepository.findById(theId);//U BOOT-U KADA MI VRACA OBJEKAT
        //CE MI SE SPAKOVATI U OPTIONAL<Customer> A JA CU SVAKAKO OPET MORATI DA GA RASPAKUJEM
        //TJ UBACIM U NOVI OBJEKAT KLASE Customer, PA TEK ONDA PROSLEDIM MODELU...
        Customers c = theCustomer.get();
        theModel.addAttribute("customer",c);
        return "addCustomer";
    }

    @GetMapping("/showFormForDelete")
    public String showFormForDelete(@RequestParam("customerId")int theId, Model theModel){
        customerAdditionalMethodsRepository.deleteById(theId);
        return "redirect:/customers/adminsListCrudCustomers";
    }




    @GetMapping("/search")                //dodao zbog searcha
    public String adminsListCrudCustomers(Model theModel, @RequestParam("theSearchName")String name){
        System.out.println("USAO U ADMINSLISCRUDCUSTOMERS");

        //List<Customers> listOfCustomers = customerAdditionalMethodsRepository.findByFirstNameOrLastNameOrPhoneOrEmail(name, name, name, name);


        //List<Customers> listOfCustomers = customerAdditionalMethodsRepository.findByFirstNameOrLastNameOrPhoneOrEmailOrStreetOrCityOrState(name, name, name, name, name, name, name);
        List<Customers> listOfCustomers = customerAdditionalMethodsRepository.findByFirstNameStartsWith(name);
        theModel.addAttribute("theCustomers", listOfCustomers);

        //List<Customers> listOfCustomers = customerAdditionalMethodsRepository.findByName(name);
       // theModel.addAttribute("theCustomers", customerAdditionalMethodsRepository.findByFirstName(name));

        return "adminsListCrudCustomersPage";
    }



}
