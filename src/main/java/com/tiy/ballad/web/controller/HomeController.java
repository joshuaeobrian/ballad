package com.tiy.ballad.web.controller;

import com.tiy.ballad.model.Ballad;
import com.tiy.ballad.model.User;
import com.tiy.ballad.service.BalladService;
import com.tiy.ballad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
            session.setAttribute("userId",userId);
        }else{
            userId = Integer.parseInt(session.getAttribute("userId").toString());
            if(userId !=0){
                User user = userService.findUserById(userId);
                model.addAttribute("firstName",user.getFirstName());
                model.addAttribute("user", user);
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

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("userId",0);
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("signup",false);
        return "login";
    }



}
