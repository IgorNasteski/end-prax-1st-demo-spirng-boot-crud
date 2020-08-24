package com.example.demo.controller;

import com.example.demo.dao.MaintenanceRepository;
import com.example.demo.service.Method4ControllerMaintenance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.temporal.ChronoUnit;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Controller
public class DemoController {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private Method4ControllerMaintenance methods4Maintenance;

    @GetMapping("/")    //ova metoda je presretnuta u klasi "MaintenanceAspect" kako bi proverili da li je vreme odrzavanja(ako jeste salji na tu stranicu)
    public String showHome(){                   //ako nije nastavi dalje tj vrati "home"
        System.out.println("USAO U /");

        LocalDate today1 = LocalDate.now();
        String today = today1.toString();
        System.out.println("DANAS???   " + today);
        return "home";
    }

    @GetMapping("/homePageRedirect")
    public String showHomeRedirectProba(){
        return "home";
    }

    //dodajem putanju za access-denied-page
    @GetMapping("/access-denied")
    public String showAccessDenied(){
        return "access-denied";
    }

}
