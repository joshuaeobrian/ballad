package com.tiy.ballad.web.controller;

import com.tiy.ballad.model.Ballad;
import com.tiy.ballad.service.BalladService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by josh on 5/2/17.
 */
@Controller
public class HomeController {
    @Autowired
    private BalladService balladService;

    @RequestMapping("/")
    public String  index(HttpSession session, Model model){
        /**
         * if session contains user id > 0
         */
        model.addAttribute("isLoggedIn", false);
        model.addAttribute("topThree",balladService.getTopThreePopular());
        return "home";
    }


    @RequestMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("signup",false);
        return "login";
    }



}
