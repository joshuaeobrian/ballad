package com.tiy.ballad.web.controller;

import com.tiy.ballad.model.Ballad;
import com.tiy.ballad.model.Count;
import com.tiy.ballad.model.User;
import com.tiy.ballad.service.BalladService;
import com.tiy.ballad.service.ColorService;
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
    @Autowired
    private ColorService colorService;

    @RequestMapping("/popular")
    public String popular(HttpSession session,Model model){
        Object[] current = sessionService.isSession(session);
        User user =(User) current[1];
        model.addAttribute("user", user);
        model.addAttribute("isLoggedIn", current[0]);

        model.addAttribute("ballad_title","Popular");
        model.addAttribute("showProfile",false);
        return "ballads";
    }

    @RequestMapping("/my-ballads")
    public String profile(HttpSession session, Model model){
        Object[] current = sessionService.isSession(session);
        User user =(User) current[1];
        model.addAttribute("user", user);
        model.addAttribute("myColor",colorService.getColorByID(user.getColorCode()));
        model.addAttribute("isLoggedIn", current[0]);
        model.addAttribute("ballad_title","My Ballads");
        model.addAttribute("showProfile",true);
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
        model.addAttribute("allowEdit", true);
        return "editor";
    }

    @RequestMapping("/editor/{id}")
    public String displayBalladWithId(@PathVariable Integer id, Model model,HttpSession session){
        if(session.isNew()|| Integer.parseInt(session.getAttribute("userId").toString())==0){
            return "redirect:/";
        }
        Object[] current = sessionService.isSession(session);
        User user =(User) current[1];
        model.addAttribute("user", user);
        model.addAttribute("isLoggedIn", current[0]);
        Ballad ballad = balladService.findBalladById(id);
        if(user.getId() == ballad.getOwner().getId() ){
            session.setAttribute("balladId",id);
            model.addAttribute("ballad", ballad);
            model.addAttribute("isHidden",true);
            model.addAttribute("allowEdit", true);
            return "editor";
        }else{
            return "redirect:/";
        }


    }

    @RequestMapping("/viewBallad")
    public String viewThisBallad(HttpSession session,Model model, Integer balladId){
        Object[] current = sessionService.isSession(session);
        User user =(User) current[1];
        model.addAttribute("user", user);
        model.addAttribute("isLoggedIn", current[0]);
        Ballad ballad = balladService.findBalladById(balladId);
        if(user.getId() == ballad.getOwner().getId() ){
            model.addAttribute("allowEdit", true);
        }else{
            model.addAttribute("allowEdit", false);
        }

            session.setAttribute("balladId",balladId);
            model.addAttribute("ballad", ballad);
            model.addAttribute("isHidden",true);

        return "editor";
    }

    @RequestMapping("/user-profile")
    public String getUserProfile(HttpSession session, Integer userId, Model model){
        try {
            session.setAttribute("profileId", userId);
            Object[] current = sessionService.isSession(session);
            User user = userService.findUserById(userId);
            model.addAttribute("user", user);
            model.addAttribute("myColor", colorService.getColorByID(user.getColorCode()));
            model.addAttribute("isLoggedIn", current[0]);

            model.addAttribute("ballad_title", user.getFirstName() + "'s Ballads");
            model.addAttribute("showProfile", true);
            return "ballads";
        }catch (Exception e){
            return "redirect:/popular";
        }
    }


}
