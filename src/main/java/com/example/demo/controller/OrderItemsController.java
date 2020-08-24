package com.example.demo.controller;

import com.example.demo.dao.AdditionalMethodsCustomerRepository;
import com.example.demo.dao.OrderItemsRepository;
import com.example.demo.dao.OrdersRepository;
import com.example.demo.entity.Customers;
import com.example.demo.entity.OrderItems;
import com.example.demo.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/items")
public class OrderItemsController {

    @Autowired
    OrderItemsRepository orderItemsRepository;

    @GetMapping("/adminsListOfOrdersItems")
    public String adminsListOfOrdersItems(Model theModel){
        List<OrderItems> listOfOrderItems = orderItemsRepository.findAll();
        //List<Orders> listOfOrders = ordersRepository.findAll();

        //sve ok, lista ordersa pokupi customere sa kojima je povezan
        //ali u thymeleaf stranici nece da ih ispise???
        /*for(Orders o: listOfOrders){
            System.out.println("cust id   " + o.getCustomer().getCustomerId());
            System.out.println("custo fn   "  + o.getCustomer().getFirstName());
        }*/

        //probao da izvucem listu ordersa pa da ih setujem u polje orderItemsa u listuOrdersa
        /*for(OrderItems o : listOfOrderItems){
            o.setListOfOrders(listOfOrders);
        }*/
        theModel.addAttribute("ordersItems", listOfOrderItems);
        //theModel.addAttribute("listOfOrders", listOfOrders);
        return "adminsListOfOrdersItemsPage";
    }

    @GetMapping("/showFormForAddOrderItemsAdmin")
    public String showFormForAddOrderItemsAdmin(Model theModel){
        OrderItems orderItems = new OrderItems();
        theModel.addAttribute("item", orderItems);
        return "addOrderItem";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("item")OrderItems theOrderItems){
        orderItemsRepository.save(theOrderItems);
				/* REDIREKTUJEM NA HomeController koji ima @RequestMapping("/") pa odma gadjam na endpoint metode
				 * A DA SAM REDIREKTOVAO NA NEKI ENDPOINT OVOG KONTROLERA MORAO BIH DA DODAM NPR "/customers/list";*/
        return "redirect:/items/adminsListOfOrdersItems";
    }

    @GetMapping("/showFormForUpdateOrdersItems")
    public String showFormForUpdate(@RequestParam("itemId")int theId, Model theModel){
        Optional<OrderItems> theOrderItems = orderItemsRepository.findById(theId);//U BOOT-U KADA MI VRACA OBJEKAT
        //CE MI SE SPAKOVATI U OPTIONAL<Customer> A JA CU SVAKAKO OPET MORATI DA GA RASPAKUJEM
        //TJ UBACIM U NOVI OBJEKAT KLASE Customer, PA TEK ONDA PROSLEDIM MODELU...
        OrderItems o = theOrderItems.get();
        theModel.addAttribute("item",o);
        return "addOrderItem";
    }

    @GetMapping("/showFormForDeleteOrdersItems")
    public String showFormForDelete(@RequestParam("itemId")int theId){
        orderItemsRepository.deleteById(theId);
        return "redirect:/items/adminsListOfOrdersItems";
    }

    @GetMapping("/search")                //dodao zbog searcha
    public String adminsListCrudCustomers(Model theModel, @RequestParam("theSearchName")String name){
        System.out.println("USAO U ADMINSLISCRUDORDERS");

        int itemId = Integer.parseInt(name);
        int productId = Integer.parseInt(name);
        int quantity = Integer.parseInt(name);
        int discount = Integer.parseInt(name);
        //double listPrice = Double.parseDouble(name);
        //List<OrderItems> listOfOrderItems = orderItemsRepository.findByItemId(itemId);
        //List<OrderItems> listOfOrderItems = orderItemsRepository.findByItemIdOrProductId(itemId, productId);
        //List<OrderItems> listOfOrderItems = orderItemsRepository.findByItemIdOrProductIdOrQuantity(itemId, productId, quantity);
        List<OrderItems> listOfOrderItems = orderItemsRepository.findByItemIdOrProductIdOrQuantityOrDiscount(itemId, productId, quantity, discount);
        //List<OrderItems> listOfOrderItems = orderItemsRepository.findByItemIdOrProductIdOrQuantityOrListPriceOrDiscount(itemId, productId, quantity, listPrice, discount);
        theModel.addAttribute("ordersItems", listOfOrderItems);

        return "adminsListOfOrdersItemsPage";
    }

}
