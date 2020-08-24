package com.example.demo.controller;

import com.example.demo.dao.AdditionalMethodsCustomerRepository;
import com.example.demo.dao.OrdersRepository;
import com.example.demo.entity.Customers;
import com.example.demo.entity.Orders;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController {

    // MORAO DA DODAM JER NIJE HTEO DA SACUVA DATUM U BAZU, I TO MORAM DA DODAJEM SA / NPR 20/02/2020
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       Date.class,            //MORAM DODATI SA TACKOM NPR 20.02.2020
                new CustomDateEditor(new SimpleDateFormat("dd.MM.yyyy"), true, 10));
    }

    @Autowired
    private OrderService orderService;


    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private AdditionalMethodsCustomerRepository additionalMethodsCustomerRepository;

    @GetMapping("/adminsListOfOrders")
    public String adminsListOfOrders(Model theModel){
        List<Orders> listOfOrders = ordersRepository.findAll();
        theModel.addAttribute("orders", listOfOrders);
        return "adminsListOfOrdersPage";
    }

    @GetMapping("/showFormForAddOrderAdmin")
    public String showAddForm(Model theModel){
         Orders theOrder = new Orders();
        theModel.addAttribute("order", theOrder);
        return "addOrder";
    }



                        //!!!!!!!!     // OVDE JE SMESTEN KOD ZA ORDER COUNTER

            // MORAO SAM DA DODAM cascade = CascadeType.ALL
            // NIJE HTELO BEZ TOGA
                    // @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //, cascade = CascadeType.MERGE
                    //  @JoinColumn(name = "customer_id", nullable = false)
                    //  private Customers customer;

    @PostMapping("/save")
    public String saveOrder(@ModelAttribute("order")Orders theOrder){

        //ideja je bila da posto ima 6 customera, kad god bih dodao order morao bih da navedem za kog od customera je taj order(napisem samo id njegov)
        //i onda da osim sto sacuvavam order, uvecam za 1 polje order_cuouter u tabeli Customer. Da bih to uradio, morao sam da napravim klasu koja ce
        //da mi glumi servis, njena metoda mora da bude @Transactional a sama klasa mora da bude @Service kako bi spring znao da tu radim neku logiku a i
        //da bih mogao da je autowirujem. U toj metodi prosledjujem objekat Ordera, iz njega raspakujem id customera, nadjem pomocu id-ja customera,
        //dohvatim trenutan iznos countera, setujem counter(trenutni iznos + 1), setujem customera u orderu, sacuvam customera, sacuvam order.
        orderService.saveCustomerAndOrderWhileIncreasingCustomerCounter(theOrder);

				/* REDIREKTUJEM NA HomeController koji ima @RequestMapping("/") pa odma gadjam na endpoint metode
				 * A DA SAM REDIREKTOVAO NA NEKI ENDPOINT OVOG KONTROLERA MORAO BIH DA DODAM NPR "/customers/list";*/
        return "redirect:/orders/adminsListOfOrders";
    }

    @GetMapping("/showFormForUpdateOrders")
    public String showFormForUpdate(@RequestParam("orderId")int theId, Model theModel){
        Optional<Orders> theOrders = ordersRepository.findById(theId);//U BOOT-U KADA MI VRACA OBJEKAT
        //CE MI SE SPAKOVATI U OPTIONAL<Customer> A JA CU SVAKAKO OPET MORATI DA GA RASPAKUJEM
        //TJ UBACIM U NOVI OBJEKAT KLASE Customer, PA TEK ONDA PROSLEDIM MODELU...
        Orders o = theOrders.get();
        theModel.addAttribute("order",o);
        return "addOrder";
    }

    @GetMapping("/showFormForDeleteOrders")
    public String showFormForDelete(@RequestParam("orderId")int theId){
        ordersRepository.deleteById(theId);
        return "redirect:/orders/adminsListOfOrders";
    }

    @GetMapping("/search")                //dodao zbog searcha
    public String adminsListCrudCustomers(Model theModel, @RequestParam("theSearchName")String name){
        System.out.println("USAO U ADMINS LIST CRUD ORDERS");

        //NECE, NE ZNAM ZASTO

        LocalDate today1 = LocalDate.now();
        //String today = today1.toString();
        String today = today1.minusDays(7).toString();

        List<Orders> list = ordersRepository.findAll();
        String a = list.get(0).getShippedDate();
        System.out.println("TODAY - IZ BAZE    =    " + today + " " + a);

        //List<Orders> listOfOrders = ordersRepository.findByOrderDateOrRequiredDateOrShippedDate(name, name, name);
        List<Orders> listOfOrders = ordersRepository.findByShippedDate(today);
        theModel.addAttribute("orders", listOfOrders);

        System.out.println("NAME POKUPLJEN    " + name);

        return "adminsListOfOrdersPage";
    }

}
