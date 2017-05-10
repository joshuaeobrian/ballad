package com.tiy.ballad.web.restController;

import com.tiy.ballad.model.User;
import com.tiy.ballad.service.UserService;
import com.tiy.ballad.web.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by josh on 4/27/17.
 */
@RestController
public class UserRestController {

    @Autowired
    private UserService service;

    @PostMapping("/userlogin")
    public boolean validateUser(HttpSession session, String username, String password){

        try {
            User user = service.login(username);
//            System.out.println(user);
            boolean validate = PasswordStorage.verifyPassword(password, user.getPassword());
            if(validate){
                session.setAttribute("userId",user.getId());
                System.out.println(session.getAttribute("userId"));
                return true;
            }else{
                session.setAttribute("userId",0);
                return false;
            }
        }
        catch (Exception e){
            session.setAttribute("userId",0);
            return false;
        }

    }

    @PostMapping("/signUp")
    public String saveNewUser(HttpSession session, User user){
        try {
            user.setPassword(PasswordStorage.createHash(user.getPassword()));
            Integer id = service.saveNewUser(user);
            session.setAttribute("userId",id);
            System.out.println(session.getAttribute("userId"));
            return "Success";
        }catch (Exception e){
            return "Failed";
        }
    }

    @PostMapping("/updateUser")
    public void updateUser(HttpSession session, User user, MultipartFile file) throws PasswordStorage.CannotPerformOperationException {
        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
        user.setId(userId);
        try {
            user.setPhoto(file.getBytes());
        } catch (IOException e) {
            System.out.println("Unable to upload file");
        }
        if(!user.getPassword().contains("sha1")){
            user.setPassword(PasswordStorage.createHash(user.getPassword()));
        }

        System.out.println(user.toString());
        service.updateUserInfo(user);

    }

    @GetMapping("/disableAccount")
    public void disableAccount(HttpSession session){
        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
        service.deleteUser(userId);
    }

}