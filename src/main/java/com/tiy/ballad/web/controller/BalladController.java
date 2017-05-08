package com.tiy.ballad.web.controller;

import com.tiy.ballad.model.Ballad;
import com.tiy.ballad.model.User;
import com.tiy.ballad.service.BalladService;
import com.tiy.ballad.service.SessionService;
import com.tiy.ballad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Scanner;

/**
 * Created by josh on 4/27/17.
 */
@Controller
public class BalladController {
    @Autowired
    private UserService userService;
    @Autowired
    private BalladService balladService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping("/popular")
    public String popular(HttpSession session,Model model){
        Object[] current = sessionService.isSession(session);
        model.addAttribute("user", current[1]);
        model.addAttribute("isLoggedIn", current[0]);
        model.addAttribute("popular", balladService.getPopularBallads());
        return "popular";
    }

    @RequestMapping("/my-ballads")
    public String profile(HttpSession session, Model model){
        Object[] current = sessionService.isSession(session);
        User user =(User) current[1];
        model.addAttribute("user", current[1]);
        model.addAttribute("isLoggedIn", current[0]);
        List<Ballad> ballad = balladService.sortBallads(true,user.getId(),true, false,3);
        model.addAttribute("ballads", ballad);
        model.addAttribute("ballad_title","My Ballads");
        return "ballads";
    }

//TODO refactor these down to one function
    @RequestMapping("/editor")
    public String ballad(HttpSession session, Model model){
        Object[] current = sessionService.isSession(session);
        session.setAttribute("balladId",0);
        model.addAttribute("user", current[1]);
        model.addAttribute("isLoggedIn", current[0]);
        model.addAttribute("ballad",new Ballad());
        model.addAttribute("isHidden",true);
        return "editor";
    }

    @RequestMapping("/editor/{id}")
    public String displayBalladWithId(@PathVariable Integer id, Model model,HttpSession session){
        if(session.isNew()|| Integer.parseInt(session.getAttribute("userId").toString())==0){
            return "redirect:/";
        }
        Object[] current = sessionService.isSession(session);
        User user =(User) current[1];
        model.addAttribute("user", current[1]);
        model.addAttribute("isLoggedIn", current[0]);
        Ballad ballad = balladService.findBalladById(id);
        if(user.getId() == ballad.getOwner().getId() ){
            session.setAttribute("balladId",id);
            model.addAttribute("ballad", ballad);
            return "editor";
        }else{
            return "redirect:/";
        }


    }


}
