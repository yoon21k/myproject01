package myproject01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import myproject01.domain.dto.member.MemberDetails;
import myproject01.domain.dto.product.ProductUpdateDto;
import myproject01.domain.dto.qna.QnaWriteDto;
import myproject01.domain.dto.qna.QnaReplyWriteDto;
import myproject01.domain.dto.qna.QnaUpdateDto;
import myproject01.service.product.ProductService;
import myproject01.service.qna.QnaService;

@Controller
public class AdminController {

	@Autowired
	private QnaService qnaService;
	
	@Autowired
	ProductService productService;
	
	// admin index 이동
	@GetMapping("/admin/index")
	public String list() {
		return "/admin/index";
	}
	
	// admin qna list 이동
	@GetMapping("/admin/qna/list")
	public String qnaList() {
		return "/admin/qna/list";
	}
	
	// admin qna list 불러오기 + 페이징
	@ResponseBody
	@GetMapping("/admin/qna/{division}/{pageNo}")
	public ModelAndView qnaList(@PathVariable int division,@PathVariable int pageNo, ModelAndView mv) {   // Model : 페이지에 데이터를 전송시켜주는 역할
		System.out.println("division : " + division);
		System.out.println("pageNo : " + pageNo);
		// qnaService.getQnaList(division, pageNo, model);
		qnaService.getQnaList(division, pageNo, mv);
		mv.setViewName("/admin/qna/listdata");
		return mv;
	}
	
	// admin qna write 이동
	@GetMapping("/admin/qna/write")
	public String qnaWrite() {
		return "/admin/qna/write";
	}
	
	// admin qna 게시글 작성
	@PostMapping("/admin/qna/write")
	public String qnaWrite(QnaWriteDto dto) {
		qnaService.write(dto);
		return "redirect:/admin/qna/list";
	}
	
	// admin qna 게시글 삭제
	@ResponseBody
	@DeleteMapping("/admin/qna/{no}")
	public void qnaDelete(@PathVariable long no) {
		System.out.println("delete-no : " + no);
		qnaService.delete(no);
	}
	
	// admin qna 게시글 수정 및 저장
	@ResponseBody
	@PutMapping("/admin/qna/{no}")
	public void qnaUpdate(@PathVariable long no, QnaUpdateDto dto) {
		System.out.println("edit-no : " + no);
		qnaService.update(no, dto);
	}
	
	// admin qna 게시글에 댓글(답변) 작성
	@ResponseBody
	@PostMapping("/admin/qna/{no}/reply")
	public void qnaReply(@PathVariable long no, QnaReplyWriteDto dto) {
		
		qnaService.reply(no, dto);
	}
	
	// admin qna 댓글 불러오기
	@ResponseBody
	@GetMapping("/admin/qna/{no}/reply")
	public ModelAndView qnaReply(@PathVariable long no) {
		// ModelAndView mv=new ModelAndView();   // parameter 아니어도 가능
		
		return qnaService.getReply(no);
		
	}
	
	
}










