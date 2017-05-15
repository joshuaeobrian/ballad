package com.tiy.ballad.web.restController;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import com.tiy.ballad.model.User;
import com.tiy.ballad.service.UserService;
import com.tiy.ballad.web.EmailReset;
import com.tiy.ballad.web.PasswordStorage;
import com.tiy.ballad.web.RandomPasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by josh on 4/27/17.
 */
@RestController
public class UserRestController {

    @Autowired
    private UserService service;
    @Autowired
    private EmailReset reset;
    @Autowired
    private RandomPasswordGenerator passwordGenerator;

    @PostMapping("/forgot-email")
    public void forgotEmail(HttpSession session, String username) throws PasswordStorage.CannotPerformOperationException {
        User user = service.login(username);
        user.setPassword(passwordGenerator.generateRandomPassword());
        reset.setUpEmail(user);
        user.setPassword(PasswordStorage.createHash(user.getPassword()));
        service.updateUserInfo(user);


    }
    @PostMapping("/update-password")
    public boolean UpdatePassword(HttpSession session, String username,String recoveryKey, String newPassword) throws PasswordStorage.CannotPerformOperationException, PasswordStorage.InvalidHashException {
        User user = service.login(username);
        boolean validate =PasswordStorage.verifyPassword(recoveryKey,user.getPassword());
        if(validate){
            user.setPassword(PasswordStorage.createHash(newPassword));
            service.updateUserInfo(user);
            session.setAttribute("userId",user.getId());

        }
        return validate;
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


    @GetMapping("/disableAccount")
    public void disableAccount(HttpSession session){
        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
        service.deleteUser(userId);
    }
    @PostMapping("/check-username")
    public HashMap<String,Boolean> isUsernameTaken(String username) {
        HashMap<String, Boolean> map = new HashMap<>();
        try {
            User user = service.login(username);

            if (user.getUsername().equals(username)) {
                map.put("usernameExist", true);
            }
            if (user.getEmail().equals(username)) {
                map.put("emailExist", true);
            }
        return map;
        } catch (Exception e) {
            map.put("usernameExist", false);
            map.put("emailExist", false);
            return map;
        }

    }

}
