package com.tiy.ballad.web.controller;

import com.tiy.ballad.model.User;
import com.tiy.ballad.service.ColorService;
import com.tiy.ballad.service.SessionService;
import com.tiy.ballad.service.UserService;
import com.tiy.ballad.web.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by josh on 5/2/17.
 */
@Controller
@SessionAttributes("loginIncorrect")
public class UserController {
    @Autowired
    private SessionService sessionService;
    @Autowired
    UserService userService;
    @Autowired
    ColorService colorService;

    @ModelAttribute("loginIncorrect")
    public boolean loginIncorrect(){
        return false;
    }

    @RequestMapping("/Profile")
    public String userProfile(HttpSession session, Model model){
        Object[] current = sessionService.isSession(session);
        User user =(User) current[1];

        model.addAttribute("user",user);
//        System.out.println(current[1].toString());
        model.addAttribute("colors",colorService.listColors());
        model.addAttribute("isLoggedIn",current[0]);
        model.addAttribute("myColor",colorService.getColorByID(user.getColorCode()));
        model.addAttribute("isHidden",true);
        return "profile";
    }

    @PostMapping("/update-profile")
    public String upload(HttpSession session,String firstname, String lastName,String email, String username,  String password,String about, Integer colorCode,@RequestParam MultipartFile file) throws PasswordStorage.CannotPerformOperationException {
        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
        System.out.println(file);
        User user = new User(userId,firstname,lastName,email,username,password,true,about,colorCode);

        User validate = userService.findUserById(userId);
        System.out.println("Updating user: \n"+user.toString());
        System.out.println("Valid user: \n"+validate.toString());
        try {
            System.out.println("File Bytes: "+ file.getBytes());
            System.out.println("File Size: "+ file.getSize());

            if(!file.isEmpty()) {
                user.setPhoto(file.getBytes());
                System.out.println("UPDATING IMAGE");
            }else{
                user.setPhoto(validate.getPhoto());
                System.out.println("NOT UPDATING IMAGE");
            }

        } catch (IOException e) {
            System.out.println("Unable to upload file");
        }
        if(user.getPassword().equals("")||user.getPassword() ==null || user.getPassword().isEmpty()||user.getPassword()==""|| user.getPassword().length()<0){
            System.out.println("?NOT UPDATING PASSWORD");
            user.setPassword(validate.getPassword());
        }else{
            System.out.println("UPDATING PASSWORD");
            user.setPassword(PasswordStorage.createHash(user.getPassword()));
        }

        System.out.println(user.toString());
        userService.updateUserInfo(user);
        return "redirect:/Profile";

    }
    @GetMapping("/user/image")
    @ResponseBody
    public byte[] userImage(HttpSession session){
        Object[] current = sessionService.isSession(session);
        User user =(User) current[1];

        return user.getPhoto();
    }


    @ResponseBody
    @PostMapping("/userlogin")
    public boolean[] validateUser(HttpSession session, String username, String password){
        boolean[] response = new boolean[2];
        System.out.println("USing COntroller");
        try {
            User user = userService.login(username);
//            System.out.println(user);
            boolean validate = PasswordStorage.verifyPassword(password, user.getPassword());
            if(validate){
                session.setAttribute("userId",user.getId());
                System.out.println(session.getAttribute("userId"));
                response[0] = true;
                response[1] = true;
                return response;
            }else{
                session.setAttribute("userId",0);

                response[0] = (!username.equals(user.getEmail())||!username.equals(user.getUsername()));
                response[1] = validate;

                return response;
            }
        }
        catch (Exception e){
            session.setAttribute("userId",0);
             return response;

        }

    }
}
