package com.example.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.Dao.MemberRepository;
import com.example.Model.MemberAccount;
import com.example.Model.MemberAccountJPA;

//@EnableJpaRepositories(basePackages = {
//		"com.example.demo", "com.example.Service, com.example.Controller, com.example.Dao, com.example.Model"
//})

@Controller
public class MemberController {
//	//透過 @RequestMapping 指定從/會被對應到此hello()方法
//	@Autowired
//	MemberAccount memberAccount;
//	
//	@Autowired
//	MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	DataSource dataSource;
	
	@GetMapping("/addMemberPage")
    public String addMemberPage(){
		System.out.println("test!");
		List<MemberAccountJPA> memberAccountJPA= new ArrayList<MemberAccountJPA>();
		memberAccountJPA = memberRepository.findAll();

		for(int i=0;i<memberAccountJPA.size();i++){
			System.out.println(memberAccountJPA.get(i).getId());
		}
		
        return "addMemberPage";
		
	}
	
	@GetMapping("/login")
    public String login(@ModelAttribute MemberAccountJPA memberAccountJPA){

        return "login";
    }
	
	@PostMapping("/doLogin")
    public String doLogin(@ModelAttribute MemberAccountJPA memberAccountJPA,HttpSession session){
		String email = memberAccountJPA.getEmail();
		String password = memberAccountJPA.getPassword();
		List<MemberAccountJPA> MemberAccountJPAList = new ArrayList<MemberAccountJPA>();
		MemberAccountJPAList = memberRepository.findCheckMemberAccount(email, password);
		MemberAccount memberAccount = new  MemberAccount();
		memberAccount.setPassword(password);
		memberAccount.setEmail(email);;

		if(MemberAccountJPAList.size()==0){
			return "login";
		}
		else{
			session.setAttribute("uid", memberAccount);
	        return "welcome";
		}
    }
}