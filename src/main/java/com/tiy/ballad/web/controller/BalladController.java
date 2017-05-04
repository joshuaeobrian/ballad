package com.tiy.ballad.web.controller;

import com.tiy.ballad.model.Ballad;
import com.tiy.ballad.model.User;
import com.tiy.ballad.service.BalladService;
import com.tiy.ballad.service.SessionService;
import com.tiy.ballad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by josh on 4/27/17.
 */

@Controller
@SessionAttributes("id")
public class BalladController {
    @Autowired
    private UserService userService;
    @Autowired
    private BalladService balladService;
    @Autowired
    private SessionService sessionService;

    @ModelAttribute("id")
    public Integer getID(){
        return 0;
    }


   @PostMapping("/validate")
    public String validate(ModelMap modelMap,String username, String password){
        try {
            User user = userService.login(username, password);
            modelMap.put("id",user.getId());
            return "redirect:/profile";
        }catch (Exception e){
            return "redirect:/login";
        }

    }

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
        List<Ballad> ballad = balladService.showMyBallads(user);

        model.addAttribute("ballads", ballad);
        model.addAttribute("ballad_title","My Ballads");


        return "ballads";
    }


    @RequestMapping("/editor")
    public String ballad(HttpSession session, Model model){
        Object[] current = sessionService.isSession(session);
        model.addAttribute("user", current[1]);
        model.addAttribute("isLoggedIn", current[0]);
        model.addAttribute("ballad",new Ballad());
        return "editor";
    }

    @RequestMapping("/ballad/{id}")
    public String displayBalladWithId(@PathVariable Integer id, Model model,HttpSession session){
        session.setAttribute("balladId",id);
        Ballad ballad = balladService.findBalladById(id);

        model.addAttribute("ballad", ballad);
        return "testing/ballad";
    }


}
