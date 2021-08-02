package myproject01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import myproject01.domain.dto.review.ReviewReplyUpdateDto;
import myproject01.domain.dto.review.ReviewReplyWriteDto;
import myproject01.domain.dto.review.ReviewUpdateDto;
import myproject01.domain.dto.review.ReviewWriteDto;
import myproject01.service.review.ReviewService;

@Controller
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	// refurbish 상품 상세 페이지 리뷰 작성(파일 업로드)
	@PostMapping("/store/refurbish/{no}/review")
	public String review(@PathVariable long no, MultipartFile file, ReviewWriteDto dto) {
		
		reviewService.write(no, file, dto);
		
		return "redirect:/store/refurbish/{no}";
		
	}
	
	// refurbish 상품 리뷰 파일 미리보기
	@ResponseBody
	@PostMapping("/store/refurbish/temp")
	public String temp(MultipartFile file) {
		
		return reviewService.uploadTempFile(file);
	}
	
	// refurbish 상품 리뷰 불러오기
	@GetMapping("/store/refurbish/{no}/review")
	public ModelAndView getReview(@PathVariable long no) {
		
		return reviewService.getReview(no);
	}
	
	// refurbish 상품 리뷰 삭제
	@ResponseBody
	@DeleteMapping("/store/refurbish/review/{no}")
	public void delete(@PathVariable long no) {
		// System.out.println(no);
		reviewService.delete(no);
	}
	
	// refurbish 상품 리뷰 업데이트
	@ResponseBody
	@PutMapping("/store/refurbish/review/{no}")
	public void update(@PathVariable long no, ReviewUpdateDto dto) {
		reviewService.update(no, dto);
	}
	
	// refurbish 상품 리뷰 댓글 작성
	@ResponseBody
	@PostMapping("/store/refurbish/review/{no}/reply")
	public void Reply(@PathVariable long no, ReviewReplyWriteDto dto) {
		// System.out.println(no);
		
		reviewService.reply(no, dto);
	}
	
	// refurbish 상품 리뷰 댓글 불러오기
	@ResponseBody
	@GetMapping("/store/refurbish/review/{no}/reply")
	public ModelAndView getReply(@PathVariable long no, ModelAndView mv) {
		mv.setViewName("/store/refurbish/reply");
		
		reviewService.getReply(no, mv);
		
		return mv;
	}
	
	// refurbish 상품 리뷰 댓글 삭제
	@ResponseBody
	@DeleteMapping("/store/refurbish/review/reply/{no}")
	public void replyDelete(@PathVariable long no) {
		
		reviewService.replyDelete(no);
	}
	
	// refurbish 상품 리뷰 댓글 업데이트
	@ResponseBody
	@PutMapping("/store/refurbish/review/reply/{no}")
	public void replyUpdate(@PathVariable long no, ReviewReplyUpdateDto dto) {
		
		reviewService.replyUpdate(no, dto);
	}
	
}









