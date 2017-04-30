package com.tiy.ballad.web.restController;

import com.tiy.ballad.model.Ballad;
import com.tiy.ballad.model.User;
import com.tiy.ballad.service.BalladService;
import com.tiy.ballad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by josh on 4/28/17.
 */
@RestController
public class BalladRestController {
    @Autowired
    private BalladService balladService;
    @Autowired
    private UserService userService;

    @PostMapping("/saveNewBallad")
    public String saveNewBallad(HttpSession session,Ballad ballad){

        Integer id = Integer.parseInt(session.getAttribute("userId").toString());
        if(ballad.getUserId() == -1){
            ballad.setUserId(id);
//            System.out.println(ballad.getUserId());
            balladService.saveNewBallad(ballad);
            return "Successful";
        }else{
            return "Failed to Create";
        }

    }

    @PostMapping("/myBallads")
    public List<Ballad> showMyBallads(HttpSession session){
        if(!session.isNew()){
            Integer id = Integer.parseInt(session.getAttribute("userId").toString());
            User user = userService.findUserById(id);
            return balladService.showMyBallads(user);
        }else{
            return null;
        }
    }
}
