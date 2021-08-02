package myproject01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	@GetMapping("/movie")
	public String movie() {
		return "/movie/list";
	}
	
	@GetMapping("/log/login")
	public String login() {
		return "/log/login";
	}
	
	@GetMapping("/log/join")
	public String join() {
		return "/log/join";
	}
	
	@GetMapping("/community/notice/list")
	public String noticeList(){
		return "/community/notice/list";
	}
	
	@GetMapping("/community/qna/list")
	public String qnaList() {
		
		return "/community/qna/list";
	}
	
	@GetMapping("/store/refurbish/list")
	public String refurbishList(){
		return "/store/refurbish/list";
	}

}
