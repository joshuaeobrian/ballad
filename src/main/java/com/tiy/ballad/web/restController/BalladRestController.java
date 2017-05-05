package com.tiy.ballad.web.restController;

import com.tiy.ballad.model.Ballad;
import com.tiy.ballad.model.User;
import com.tiy.ballad.service.BalladService;
import com.tiy.ballad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/saveBallad")
    public void saveNewBallad(HttpSession session,String title,  String content){
        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
        if(userId == 0){
            session.setAttribute("title",title);
            session.setAttribute("ballad", content);
        }else{
            User owner = userService.findUserById(userId);
            Integer balladId = Integer.parseInt(session.getAttribute("balladId").toString());
            Ballad ballad = new Ballad(title,content, owner);
            if(balladId != 0){
                balladService.updateBallad(ballad,owner);
            }else{
                balladId = balladService.saveNewBallad(ballad);
            }

            session.setAttribute("balladId",balladId);
        }


    }

//    @PostMapping("/updateBallad")
//    public void updateBallad(HttpSession session, String title, String content){
//        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
//        Integer balladId = Integer.parseInt(session.getAttribute("balladId").toString());
//
//        User user = userService.findUserById(userId);
//        Ballad ballad = balladService.findBalladById(balladId);
//        ballad.setTitle(title);
//        ballad.setBallad(content);
//        balladService.updateBallad(ballad, user);
//
//    }

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

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadBallad(HttpSession session) throws Exception{
        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
        String title = "";
        String content = "";
        if(userId == 0){
            title = session.getAttribute("title").toString();
            content = session.getAttribute("ballad").toString();
        }else{
            Integer balladId = Integer.parseInt(session.getAttribute("balladId").toString());
            Ballad ballad =balladService.findBalladById(balladId);
            title = ballad.getTitle();
            content = ballad.getBallad();
        }



        byte[] output = content.getBytes();


        HttpHeaders headers = new HttpHeaders();
        headers.set("charset", "utf-8");
//        headers.setContentType(MediaType.valueOf("text/html"));
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentLength(output.length);
        headers.set("Content-disposition", "attachment; filename="+title+".txt");

        return new ResponseEntity<byte[]>(output, headers, HttpStatus.OK);
    }



}
