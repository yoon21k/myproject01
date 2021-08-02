package myproject01.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;
import myproject01.domain.dto.qna.QnaWriteDto;
import myproject01.domain.dto.qna.QnaReplyResultDto;
import myproject01.domain.dto.qna.QnaUpdateDto;
import myproject01.service.qna.QnaService;

@Slf4j
@Controller
public class QnaController {
	
	@Autowired
	QnaService qnaService;
	
	// community qna 상세 페이지 + 댓글 불러오기
	@GetMapping("/community/qna/{division}/{no}")
	public String detail(@PathVariable long no, QnaReplyResultDto dto, Model model) {
		
		qnaService.getDetail(no, dto, model);
		
		return "/community/qna/detail";
	}
	
	// community qna list 불러오기
	// @ResponseBody
	@GetMapping("/community/qna/{division}")
	public String list(@PathVariable int division, int page, Model model) {
		
		log.debug("division : " + division);
		qnaService.list(division, page, model);
		return "/community/qna/listdata";
	}
	
	// community qna write 이동
	@GetMapping("/community/qna/write")
	public String write() {
		return "/community/qna/write";
	}
	
	// community qna 게시글 작성
	@PostMapping("/community/qna/write")
	public String write(QnaWriteDto dto) {
		qnaService.write(dto);
		return "redirect:/community/qna/list";
	}
	
	// community qna 게시글 삭제
	@ResponseBody
	@DeleteMapping("/community/qna/{no}")
	public void Delete(@PathVariable long no) {
		System.out.println("delete-no : " + no);
		qnaService.delete(no);
	}
	
	// community qna 게시글 수정 및 저장
	@ResponseBody
	@PutMapping("/community/qna/{no}")
	public void Update(@PathVariable long no, QnaUpdateDto dto) {
		System.out.println("edit-no : " + no);
		qnaService.update(no, dto);
	}	
	
}










