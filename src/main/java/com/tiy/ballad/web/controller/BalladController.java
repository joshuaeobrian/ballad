package com.tiy.ballad.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by josh on 4/27/17.
 */

@Controller
@SessionAttributes("id")
public class BalladController {

    @ModelAttribute("id")
    public Integer getID(){
        return 0;
    }

    @RequestMapping("/")
    public String  index(){

        return "index";
    }
}
