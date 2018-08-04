package com.example.Controller;

import com.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class userController {

    @Autowired
    UserService userService;

    @GetMapping("/Id")
    public String index(Map<String, Object> model) {

        System.out.println("進入了index() method.");
        model.put("ID", userService.getUserId());
        return "id";
    }

    @RequestMapping("/MyFirstPage")
    public String greeting(@RequestParam(value = "title", required = false, defaultValue = "鐵人賽 加油!") String title, Model model) {
        System.out.println("進入了greeting() method.");
        model.addAttribute("name", title);
        return "index";
    }
}