package com.tiy.ballad.web.restController;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.tiy.ballad.model.Ballad;
import com.tiy.ballad.model.User;
import com.tiy.ballad.service.BalladService;
import com.tiy.ballad.service.UserService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
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
    public void saveNewBallad(HttpSession session, String title, String content, Boolean isPublic){
        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
        if(userId == 0){
            session.setAttribute("title",title);
            session.setAttribute("ballad", content);

        }else{
            User owner = userService.findUserById(userId);
            Integer balladId = Integer.parseInt(session.getAttribute("balladId").toString());
            Ballad ballad;
            if(balladId != 0){
                ballad = balladService.findBalladById(balladId);
                ballad.setTitle(title);
                ballad.setBallad(content);
                ballad.setPublicView(isPublic);
                balladService.updateBallad(ballad,owner);

            }else{
                ballad = new Ballad(title,content, owner, isPublic);
                balladId = balladService.saveNewBallad(ballad);

            }
            session.setAttribute("balladId",balladId);
        }
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

    @PostMapping("/sortBallads")
    public List<Ballad> sortBalladsByRecent(HttpSession session,Boolean userOnly ,Boolean isPublic, Boolean isPrivate, Integer caseId,String search){
        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
        return balladService.sortBallads( userOnly, userId, isPublic,  isPrivate,  caseId,search);
    }
    @PostMapping("/profileBallads")
    public List<Ballad> profileBalladsByRecent(HttpSession session, Integer caseId,String search){
        Integer userId = Integer.parseInt(session.getAttribute("profileId").toString());
        List<Ballad> publicProfileBallads = balladService.sortBallads(true,userId,true,false,caseId,search);

        return publicProfileBallads;
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

        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentLength(output.length);
        headers.add("content-disposition", "inline;filename=" +title+".pdf");

//        headers.set("Content-disposition", "attachment; filename="");


        return new ResponseEntity<byte[]>(output, headers, HttpStatus.OK);
    }

    @PostMapping("/like-ballad")
    public void likeBallad(HttpSession session){
        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
        if(userId != 0){
            User user = userService.findUserById(userId);
            Integer balladId = Integer.parseInt(session.getAttribute("balladId").toString());
            Ballad ballad =balladService.findBalladById(balladId);
            ballad.setFavorite(true);
            balladService.likeBallad(ballad,user);
        }

    }
    @PostMapping("/user-public-ballads")
    public List<Ballad> getProfile(Integer userId){
//        Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
        User profile = userService.findUserById(userId);
        List<Ballad> publicProfileBallads = balladService.sortBallads(true,profile.getId(),true,false,0,"");

        return publicProfileBallads;
    }



}
