package com.tiy.ballad.web.controller;

import com.tiy.ballad.model.User;
import com.tiy.ballad.service.SessionService;
import com.tiy.ballad.service.UserService;
import com.tiy.ballad.web.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by josh on 5/2/17.
 */
@Controller
public class UserController {
    @Autowired
    private SessionService sessionService;
    @Autowired
    UserService userService;

    @RequestMapping("/Profile")
    public String userProfile(HttpSession session, Model model){
        Object[] current = sessionService.isSession(session);
        User user =(User) current[1];

        model.addAttribute("user",current[1]);
//        System.out.println(current[1].toString());

        model.addAttribute("isLoggedIn",current[0]);
        model.addAttribute("isHidden",true);
        return "profile";
    }

    @PostMapping("/update-profile")
    public String upload(HttpSession session, User user,@RequestParam MultipartFile file) throws PasswordStorage.CannotPerformOperationException {
        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
        System.out.println(file);
        user.setId(userId);
        try {
            user.setPhoto(file.getBytes());
        } catch (IOException e) {
            System.out.println("Unable to upload file");
        }
//        if(user.getPassword().contains("sha1")){
//        }else{
//            user.setPassword(PasswordStorage.createHash(user.getPassword()));
//
//        }

        System.out.println(user.toString());
        userService.updateUserInfo(user);
        return "redirect:/";

    }
}
