package com.tiy.ballad.service;

import com.tiy.ballad.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Created by josh on 5/3/17.
 */
@Component
public class SessionService {
    @Autowired
    UserService userService;



    public Object[] isSession(HttpSession session){
        Integer userId = 0;
        User user = new User();
        boolean userLoggedIn = false;
        if(session.isNew()){
            session.setAttribute("userId",userId);
        }else{
            userId = Integer.parseInt(session.getAttribute("userId").toString());
            if(userId !=0){
                user = userService.findUserById(userId);

                userLoggedIn = true;
            }
        }
        return new Object[] {userLoggedIn, user};
    }
}
