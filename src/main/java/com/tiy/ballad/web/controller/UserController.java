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
        System.out.println(user);
        model.addAttribute("user",user);
        model.addAttribute("colors",colorService.listColors());
        model.addAttribute("isLoggedIn",current[0]);
        model.addAttribute("myColor",colorService.getColorByID(user.getColorCode()));
        model.addAttribute("isHidden",true);
        return "profile";
    }

    @PostMapping("/update-profile")
    public String upload(HttpSession session,String firstname, String lastName,String email, String username,  String password,String about, Integer colorCode,@RequestParam MultipartFile file) throws PasswordStorage.CannotPerformOperationException {
        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());

        User user = new User(userId,firstname,lastName,email,username,password,true,about,colorCode);

        User validate = userService.findUserById(userId);


        try {

            if(!file.isEmpty()) {
                user.setPhoto(file.getBytes());

            }else{
                user.setPhoto(validate.getPhoto());
            }

        } catch (IOException e) {
            System.out.println("Unable to upload file");
        }
        if(user.getPassword().equals("")||user.getPassword() ==null || user.getPassword().isEmpty()||user.getPassword()==""|| user.getPassword().length()<0){

            user.setPassword(validate.getPassword());
        }else{

            user.setPassword(PasswordStorage.createHash(user.getPassword()));
        }

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

    @GetMapping("/account-recovery")
    public String recovery(Model model){


        model.addAttribute("isHidden",true);

        return "login";
    }

    @GetMapping("/user/image/profile")
    @ResponseBody
    public byte[] userImage(Integer userId){
        User user = userService.findUserById(userId);

        return user.getPhoto();
    }



    @ResponseBody
    @PostMapping("/userlogin")
    public Object[] validateUser(HttpSession session, String username, String password){
        Object[] response = new Object[3];

        try {
            User user = userService.login(username);

            boolean validate = PasswordStorage.verifyPassword(password, user.getPassword());
            if(validate){
                session.setAttribute("userId",user.getId());

                response[0] = true;
                response[1] = true;
                response[2] = user.getFirstName();
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
