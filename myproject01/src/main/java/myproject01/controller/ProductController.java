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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import myproject01.domain.dto.product.ProductUpdateDto;
import myproject01.domain.dto.product.ProductUploadDto;
import myproject01.service.product.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	// admin 상품 목록보기 이동 
	@GetMapping("/admin/product")
	public String product(Model model) {
		productService.productList(model);
		return "/admin/product/list";
	}
	
	// refurbish 상품 목록보기 이동 
	@ResponseBody
	@GetMapping("/store/refurbish")
	public ModelAndView indexProduct(ModelAndView mv) {
		productService.productList(mv);
		mv.setViewName("/store/refurbish/listdata");
		return mv;
	}
	
	// refurbish 상품 상세 페이지
	@GetMapping("/store/refurbish/{no}")
	public String detail(@PathVariable long no, Model model) {
		
		productService.getDetailList(no, model);
		
		return "/store/refurbish/detail";
	}
	
	// admin 상품 등록 페이지 이동 
	@GetMapping("/admin/product/upload")
	public String upload() {
		return "/admin/product/upload";
	}
	
	// admin 상품 등록 파일 업로드
	@PostMapping("/admin/product/upload")
	public String upload(MultipartFile file, ProductUploadDto dto) {
		
		productService.insert(file, dto);	
		
		return "redirect:/admin/product";
	}

	// admin 미리보기(임시파일) 파일 업로드
	@ResponseBody
	@PostMapping("/admin/product/refurbish/temp")
	public String temp(MultipartFile file){
		
		//System.out.println(file.getOriginalFilename());
		
		return productService.uploadTempFile(file);
	}
	
	// admin 상품 등록 삭제
	@ResponseBody
	@DeleteMapping("/admin/product/{no}")
	public void productDelete(@PathVariable long no) {
		System.out.println("delete-no : " + no);
		
		productService.delete(no);
	}
	
	// admin 상품 등록 업데이트
	@ResponseBody
	@PutMapping("/admin/product/{no}")
	public void productUpdate(@PathVariable long no, ProductUpdateDto dto) {
		System.out.println(no);
		productService.update(no, dto);
	}
	
	// summernote editor file upload
	@PostMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile)  {
		JsonObject jsonObject = new JsonObject();
		
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명(원본파일명이 아니라 파일명이 변경)
		
		String url="/images/summernote/";
		ClassPathResource cpr=new ClassPathResource("static"+url);
		
		try {
			File location=cpr.getFile();   // 저장할 위치
			File targetFile = new File(location, savedFileName);
			multipartFile.transferTo(targetFile);   // 파일 업로드
			//파일 저장
			jsonObject.addProperty("url", url+savedFileName); // url + 저장 시 변경된 파일명
			jsonObject.addProperty("responseCode", "success");
				
		} catch (IOException e) {
			
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		String a = jsonObject.toString();
		return a;
	}
	
	
}











