package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    MessageRepository messageRepository;


    @RequestMapping("/")
    public String listMessages(Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return "listOfMessage";

    }

    @GetMapping("/add")
    public String messageInfo(Model model){
        model.addAttribute("message", new Message());
        return "messageInfo";
    }

    @PostMapping("/process")
    public String processInfo(@Valid Message message, BindingResult result){

        if (result.hasErrors()){
            return "messageInfo";
        }
        messageRepository.save(message);
        return "redirect:/";
    }
    @RequestMapping("/view/{id}")
    public String viewMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messageRepository.findOne(id));
        return "show";


    }
    /**@RequestMapping("/edit/{id}")
    public String editMessage(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messageRepository.findOne(id));
        return "messageInfo";
    }
    **/
}
