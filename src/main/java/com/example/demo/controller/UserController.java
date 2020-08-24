package com.example.demo.controller;

import com.example.demo.dao.AdditionalMethodsCustomerRepository;
import com.example.demo.entity.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    AdditionalMethodsCustomerRepository customersRepository;

    @GetMapping("/admins")
    public String sendToAdminsPage(){
        return "admins";
    }

    @GetMapping("/managers")
    public String sendToManagersPage(){
        return "managers";
    }

    @GetMapping("/adminsListCrudCustomers")
    public String adminsListCrudCustomers(Model theModel){
        System.out.println("USAO U ADMINSLISCRUDCUSTOMERS");
        List<Customers> listOfCustomers = customersRepository.findAll();
        theModel.addAttribute("theCustomers", listOfCustomers);

        return "adminsListCrudCustomersPage";
    }

    @GetMapping("/showFormForAdd")
    public String showAddForm(Model theModel){
        Customers theCustomer = new Customers();
        theModel.addAttribute("customer", theCustomer);
        return "addCustomer";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer")Customers theCustomer){
        customersRepository.save(theCustomer);
				/* REDIREKTUJEM NA HomeController koji ima @RequestMapping("/") pa odma gadjam na endpoint metode
				 * A DA SAM REDIREKTOVAO NA NEKI ENDPOINT OVOG KONTROLERA MORAO BIH DA DODAM NPR "/customers/list";*/
        return "redirect:/customers/employeeAdminListCustomers";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId")int theId, Model theModel){
        Optional<Customers> theCustomer = customersRepository.findById(theId);//U BOOT-U KADA MI VRACA OBJEKAT
        //CE MI SE SPAKOVATI U OPTIONAL<Customer> A JA CU SVAKAKO OPET MORATI DA GA RASPAKUJEM
        //TJ UBACIM U NOVI OBJEKAT KLASE Customer, PA TEK ONDA PROSLEDIM MODELU...
        Customers c = theCustomer.get();
        System.out.println("USAO U UPDATE METODU, ID JE " + theId + " a i iz objekta id je " + c.getCustomerId());
        theModel.addAttribute("customer", c);
        return "addCustomer";//POSTO HOCU DA BACIM NA FORMU KAO ZA ADD, MORACE DA NAM SE U MODELU KOJI PAKUJEMO ATRIBUT ZOVE ISTO ("customer", ... )
        //addCustomer je html stranica, mora ovako da se gadja umesto redirekta
    }

    @GetMapping("/showFormForDelete")
    public String showFormForDelete(@RequestParam("customerId")int theId){
        customersRepository.deleteById(theId);
        System.out.println("ID CUSTOMERA ZA BRISANJE " + theId);
        return "redirect:/customers/adminsListCrudCustomers";
    }

}
