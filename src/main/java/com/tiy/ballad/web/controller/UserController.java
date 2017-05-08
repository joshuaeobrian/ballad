package com.tiy.ballad.web.controller;

import com.tiy.ballad.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by josh on 5/2/17.
 */
@Controller
public class UserController {
    @Autowired
    private SessionService sessionService;

    @RequestMapping("/Profile")
    public String userProfile(HttpSession session, Model model){
        Object[] current = sessionService.isSession(session);
        model.addAttribute("user",current[1]);
        System.out.println(current[1].toString());
        model.addAttribute("isLoggedIn",current[0]);
        model.addAttribute("isHidden",true);
        return "profile";
    }
}
