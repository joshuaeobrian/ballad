package com.tiy.ballad.web.restController;

import com.tiy.ballad.model.User;
import com.tiy.ballad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by josh on 4/27/17.
 */
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/userlogin")
    public String validateUser(HttpSession session, String username, String password){

        try {
            User user = service.login(username, password);
            System.out.println(user);
            session.setAttribute("userId",user.getId());
            System.out.println(session.getAttribute("userId"));


            return "Success";
        }
        catch (Exception e){
            session.setAttribute("userId",0);
            return "Failure";
        }

    }

    @PostMapping("/signUp")
    public String saveNewUser(HttpSession session, User user){
        try {
            Integer id = service.saveNewUser(user);
            session.setAttribute("userId",id);
            System.out.println(session.getAttribute("userId"));
            return "Success";
        }catch (Exception e){
            return "Failed";
        }
    }

}
