package com.example.demo.controller;

import com.example.demo.dao.FeedbackRepository;
import com.example.demo.entityMongodb.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping("/managersListCrudFeedbacks")
    public String listCrudFeedback(Model theModel){
        List<Feedback> feedbackList = feedbackRepository.findAll();

        //JUST TO CHECK IN CONSOLE IF I REALLY USING DATA FROM MONGODB
        for(Feedback f : feedbackList){
            System.out.println("Subject " + f.getSubject() + " Message " + f.getMessage() +
                    " Name " + f.getName() + " Contact Number " + f.getContactNumber() +
                    " Email " + f.getEmail() + " Received Date " + f.getReceivedDate());
        }

        theModel.addAttribute("theFeedbacks", feedbackList);
        //theModel.addAttribute("theFeedbacks", feedbackRepository.findAll());
        return "managerListOfFeedback";
    }

    @GetMapping("/showFormForAddManager")
    public String showAddForm(Model theModel){
        Feedback feedback = new Feedback();
        theModel.addAttribute("feedback", feedback);
        return "addFeedback";
    }

    @PostMapping("/save")
    public String saveFeedback(@ModelAttribute("customer")Feedback theFeedback){
        feedbackRepository.save(theFeedback);
				/* REDIREKTUJEM NA HomeController koji ima @RequestMapping("/") pa odma gadjam na endpoint metode
				 * A DA SAM REDIREKTOVAO NA NEKI ENDPOINT OVOG KONTROLERA MORAO BIH DA DODAM NPR "/customers/list";*/
        return "redirect:/feedback/managersListCrudFeedbacks";
    }





    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id")String theId, Model theModel){
        Optional<Feedback> theFeedback = feedbackRepository.findById(theId);//U BOOT-U KADA MI VRACA OBJEKAT
        //CE MI SE SPAKOVATI U OPTIONAL<Feedback> A JA CU SVAKAKO OPET MORATI DA GA RASPAKUJEM
        //TJ UBACIM U NOVI OBJEKAT KLASE Feedback, PA TEK ONDA PROSLEDIM MODELU...
        Feedback f = theFeedback.get();
        theModel.addAttribute("feedback", f);//attribute name must be the same as in the "showFormForAddManager" GetRequest
        return "addFeedback";                  //because they use the same thymeleaf page
    }

    @GetMapping("/showFormForDelete")
    public String showFormForDelete(@RequestParam("id")String theId, Model theModel){
        feedbackRepository.deleteById(theId);
        return "redirect:/feedback/managersListCrudFeedbacks";
    }





    @GetMapping("/search")                //dodao zbog searcha
    public String managerListCrudFeedback(Model theModel, @RequestParam("theSearchName")String name){
        List<Feedback> listOfFeedbacks = feedbackRepository.findByEmail(name);
        theModel.addAttribute("theFeedbacks", listOfFeedbacks);

        return "managerListOfFeedback";
    }

}
