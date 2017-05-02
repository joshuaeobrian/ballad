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
    public String saveNewBallad(HttpSession session,String title,  String content){

        Integer userId = Integer.parseInt(session.getAttribute("id").toString());
        User owner = userService.findUserById(userId);
        Ballad ballad = new Ballad(title,content, owner);
        Integer balladId = balladService.saveNewBallad(ballad);
        System.out.println("This is ballad ID: "+balladId);
        session.setAttribute("balladId",balladId);



      return null;
    }

    @PostMapping("/updateBallad")
    public void updateBallad(HttpSession session, String title, String content){
        Integer userId = Integer.parseInt(session.getAttribute("id").toString());
        Integer balladId = Integer.parseInt(session.getAttribute("balladId").toString());
        System.out.println("This is user ID: "+userId);
        System.out.println("This is ballad ID: "+balladId);
        User user = userService.findUserById(userId);
        Ballad ballad = balladService.findBalladById(balladId);
        ballad.setTitle(title);
        ballad.setBallad(content);
        balladService.updateBallad(ballad, user);



    }

    @PostMapping("/deleteBallad")
    public String deleteBalladWithId(Integer balladId){
        balladService.deleteBallad(balladId);
        return null;
    }

    @PostMapping("/myBallads")
    public List<Ballad> showMyBallads(HttpSession session){
        if(!session.isNew()){
            Integer id = Integer.parseInt(session.getAttribute("id").toString());
            User user = userService.findUserById(id);
            return balladService.showMyBallads(user);
        }else{
            return null;
        }
    }

    @GetMapping("/sortByRecent")
    public List<Ballad> sortBalladsByRecent(){
        return null;
    }
    @PostMapping("/sortByAlphabetical")
    public List<Ballad> sortByAlphabetical(boolean isAz){
        return null;
    }

    @GetMapping("/sortByLikes")
    public List<Ballad> sortByLikes(){
        return null;
    }
    @GetMapping("/sortByTag")
    public List<Ballad> sortByTag(String tagName){
        return null;
    }

    @GetMapping("/showByTag")
    public List<Ballad> showAllWithTagName(){
        return null;
    }


}
