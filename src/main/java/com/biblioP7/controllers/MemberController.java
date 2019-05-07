package com.biblioP7.controllers;

import com.biblioP7.beans.Member;
import com.biblioP7.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MemberController {

    @Autowired
    private MemberDao memberDao;


    @RequestMapping(value="/listeMembers", method= RequestMethod.GET)
    public List<Member> listeUsers(){
        List<Member> members = memberDao.findAll();
        return members;
    }

    @GetMapping(value="/Member/{id}")
    public Member detailMembre(@PathVariable int id){
        return memberDao.findById(id);
    }

    @PostMapping(value="/addMember")
    public void addMember(@RequestBody Member member){
        memberDao.save(member);
    }



}
