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
    public void saveNewBallad(HttpSession session,String title,  String content){
        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
        User owner = userService.findUserById(userId);
        Ballad ballad = new Ballad(title,content, owner);
        Integer balladId = balladService.saveNewBallad(ballad);
        System.out.println("This is ballad ID: "+balladId);
        session.setAttribute("balladId",balladId);
    }

    @PostMapping("/updateBallad")
    public void updateBallad(HttpSession session, String title, String content){
        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
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
    public void deleteBalladWithId(Integer balladId){
        balladService.deleteBallad(balladId);
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

    @GetMapping("/sortBallads")
    public List<Ballad> sortBalladsByRecent(HttpSession session,Boolean userOnly ,Boolean isPublic, Boolean isPrivate, Integer caseId){
        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
        return balladService.sortBallads( userOnly, userId, isPublic,  isPrivate,  caseId);
    }



}
