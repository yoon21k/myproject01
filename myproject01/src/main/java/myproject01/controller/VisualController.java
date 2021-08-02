package myproject01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import myproject01.domain.dto.visual.VisualDto;
import myproject01.service.visual.VisualService;

@Controller
public class VisualController {
	
	@Autowired
	private VisualService visualService;
	
	@ResponseBody
	@PostMapping("/admin/visual/temp")
	public String temp(MultipartFile tempFile) {
		// System.out.println("tempFile : " + tempFile.getOriginalFilename());
		return visualService.uploadByTemp(tempFile);
	}

	@GetMapping("/admin/visual/write")
	public String write() {
		return "/admin/visual/write";
	}
	
	@PostMapping("/admin/visual/write")
	public String write(VisualDto dto, MultipartFile file) {   // Dto와 따로
		// System.out.println("title : " + dto.getTitle());
		// System.out.println("link : " + dto.getLink());
		// System.out.println("file : " + file.getOriginalFilename());
		// System.out.println("file : " + file.getSize());
		
		visualService.uploadAndSave(dto, file);
		
		return "/admin/visual/write";
	}
	
	@PostMapping("/admin/visual/write2")
	public String write2(VisualDto dto, MultipartFile file) {   // Dto와 따로
		// System.out.println("title : " + dto.getTitle());
		// System.out.println("link : " + dto.getLink());
		// System.out.println("file : " + file.getOriginalFilename());
		// System.out.println("file : " + file.getSize());
		
		visualService.uploadAndSave2(dto, file);
		
		return "redirect:/admin/visual/write";
	}
	
	
	@GetMapping("/admin/visual/list")
	public String list(Model model) {
		
		visualService.getList(model);
		return "/admin/visual/list";
	}
	
	@ResponseBody
	@DeleteMapping("/admin/visual/{no}")
	public void visualDelete(@PathVariable long no) {
		//System.out.println("delete-no : " + no);
		
		visualService.delete(no);
	}
	
}








