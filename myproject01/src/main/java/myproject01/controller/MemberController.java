package myproject01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import myproject01.domain.dto.member.MemberLoginDto;
import myproject01.domain.dto.member.MemberRequestDto;
import myproject01.service.member.MemberService;

@Slf4j
@Controller
public class MemberController {
	
	@Autowired  
	private MemberService service;
	
	// 회원가입 시 아이디 중복체크
	@ResponseBody   // html body에 return(boolean 타입)
	@PostMapping("/member/check")
	public boolean check(String id) {
		log.debug(id);
		return service.idCheck(id);
	}
	
	// 회원가입
	@PostMapping("/member/join")
	public String join(MemberRequestDto dto, Model model) {
		
		service.join(dto, model);
		
		return "/log/login";
	}
	
	// 로그인
	@PostMapping("/member/login")
	public String login(MemberLoginDto dto, Model model) {
		
		return service.login(dto, model);
	}
	

}
