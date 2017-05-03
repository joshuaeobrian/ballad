package com.tiy.ballad.web.controller;

import com.tiy.ballad.model.Ballad;
import com.tiy.ballad.service.BalladService;
import com.tiy.ballad.service.UserService;
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
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String  index(HttpSession session, Model model){
        Integer userId = 0;
        boolean userLoggedIn = false;
        if(session.isNew()){
            session.setAttribute("userId",0);
        }else{
            userId = Integer.parseInt(session.getAttribute("userId").toString());
            if(userId !=0){
                model.addAttribute("user", userService.findUserById(userId));
                userLoggedIn = true;
            }
        }
        /**
         * if session contains user id > 0
         */
        model.addAttribute("isLoggedIn", userLoggedIn);
        model.addAttribute("topThree",balladService.getTopThreePopular());
        return "home";
    }


    @RequestMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("signup",false);
        return "login";
    }



}
