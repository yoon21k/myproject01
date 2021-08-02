package myproject01.service.visual;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import myproject01.domain.dto.visual.VisualDto;
import myproject01.domain.entity.visual.VisualEntityRepository;

@Service
public class VisualServiceImpl implements VisualService {
	
	@Autowired
	private VisualEntityRepository visualEntityRepository;

	@Override
	public void uploadAndSave(VisualDto dto, MultipartFile file) {
		// 파일용량 validate
		long fileSize=file.getSize();   // 10MB 10*1024*1024
		if(fileSize > (2*1024*1024)) {
			System.out.println("파일용량제한 2MB");
			return;
		}
		
		
		// 1. file upload
		String fileName=file.getOriginalFilename();
		String fileUrl="/images/visual/";   // 파일이름과 주소형식으로 구분하기위해 뒤에 /
	
		// E:\spring\workspace\myproject01\src\main\resources\static\images\visual -> 물리적인 주소
		// E:\spring\workspace\myproject01\bin\main\static\images\visual -> location
		// bin은 컴파일되고 생성, 실제 실행되는 폴더
		ClassPathResource cpr=new ClassPathResource("static"+fileUrl);   // src 위치에는 없고 bin에만 존재
		try {
			File location=cpr.getFile();
			// System.out.println("location : "+location);
			file.transferTo(new File(location, fileName));   // location 위치에 fileName 이름으로 업로드
			System.out.println("파일업로드 완료 : "+ location + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		dto.setFileName(fileName);
		dto.setFileSize(fileSize);
		dto.setFileUrl(fileUrl);
		
		// 2. save
		visualEntityRepository.save(dto.toEntity());
		
		
	}
	
	
	@Override
	public void uploadAndSave2(VisualDto dto, MultipartFile file) {
		// 1. file upload
		long fileSize=file.getSize();
		String fileName=file.getOriginalFilename();
		String fileUrl="/images/visual/";  
	
		ClassPathResource cpr=new ClassPathResource("static"+fileUrl);  
		ClassPathResource tempcpr=new ClassPathResource("static"+fileUrl+"temp/");
		try {
			File tempFolder=tempcpr.getFile();
			File tempFile=new File(tempFolder, fileName);
			if(tempFile.exists()) {
				// 임시파일이 존재하면 파일이동
				tempFile.renameTo(new File(cpr.getFile(), fileName));
				System.out.println("임시파일-->visual경로로 이동");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dto.setFileName(fileName);
		dto.setFileSize(fileSize);
		dto.setFileUrl(fileUrl);
		
		// 2. save
		visualEntityRepository.save(dto.toEntity());
	}
	
	

	@Override
	public void getList(Model model) {
		
		/*
		List<VisualDto> result=visualEntityRepository.findAll().stream()
						   									   .map(VisualDto::new)
						   									   .collect(Collectors.toList());
		model.addAttribute("list", result);
		*/
		
		model.addAttribute("list", visualEntityRepository.findAll().stream()
																   .map(VisualDto::new)
																   .collect(Collectors.toList()));
		
	}

	@Override
	public String uploadByTemp(MultipartFile tempFile) {
		long fileSize=tempFile.getSize();  
		if(fileSize > (2*1024*1024)) {
			System.out.println("파일용량제한 2MB");
			return null;
		}
		
		// 1. file upload
		String fileName=tempFile.getOriginalFilename();
		String fileUrl="/images/visual/temp/";   // 파일이름과 주소형식으로 구분하기위해 뒤에 /
		
		ClassPathResource cpr=new ClassPathResource("static"+fileUrl);
		try {
			
			File tempLocation=cpr.getFile();
			
			// 새로운 미리보기 시 기존 임시파일 제거
			System.out.println("------- temp폴더 -------");
			for(File temp: tempLocation.listFiles()) {
				temp.delete();
				// System.out.println(temp);
			}
			System.out.println("-----------------------");
			
			// 업로드
			tempFile.transferTo(new File(tempLocation, fileName));
			System.out.println("임시파일 업로드");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileUrl+fileName;
	}


	@Override
	public void delete(long no) {
		visualEntityRepository.deleteById(no);
		
	}

	

	

}


















