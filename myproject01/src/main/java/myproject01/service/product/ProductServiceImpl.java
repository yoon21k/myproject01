package myproject01.service.product;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import myproject01.domain.dto.product.ProductResultDto;
import myproject01.domain.dto.product.ProductUploadDto;
import myproject01.domain.dto.product.ProductUpdateDto;
import myproject01.domain.entity.product.ProductEntity;
import myproject01.domain.entity.product.ProductEntityRepository;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductEntityRepository productEntityRepository;
	
	// admin 상품 등록 파일 업로드
	@Override
	public void insert(MultipartFile file, ProductUploadDto dto) {
      
      //1.file정보
      long fileSize = file.getSize();
      if(fileSize>2*1024*1024) {
         log.error("파일 용량 초과!");
         return;
         
      }
      
      String fileName=file.getOriginalFilename();
      String fileUrl="/images/product/refurbish/";
     
      
      ClassPathResource cpr = new ClassPathResource("static"+fileUrl);   // ClassPathResource : 리소스폴더 경로 static/images/product/ 
      ClassPathResource tempCpr = new ClassPathResource("static"+fileUrl+"temp/");   
      try {
         File location=cpr.getFile();
         // 파일 업로드 처리 -> temp 폴더에 존재하면 location으로 이동시키기
         // file.transferTo(new File(location,fileName));
         File tempFile= new File(tempCpr.getFile(), fileName);
         if(tempFile.exists()) {
            tempFile.renameTo(new File(location,fileName));
            
         }
         
      } catch (IOException e) {
         e.printStackTrace();
      }
      
      // 2.DB에저장
  
      // dto 파일 정보 세팅
      dto.setFileName(fileName);
      dto.setFileUrl(fileUrl);
      dto.setFileSize(fileSize);
      
      // entity 타입으로 변환 저장
      productEntityRepository.save(dto.toEntity()); 
      
    }
	
	// admin 상품 등록 파일 미리보기(임시파일 업로드)
	@Override
    public String uploadTempFile(MultipartFile file) {
      //1.file정보
      long fileSize = file.getSize();
      if(fileSize>2*1024*1024) {
         log.error("파일용량초과!");
         return null;
      }
      
      String fileName=file.getOriginalFilename();
      String fileUrl="/images/product/refurbish/temp/";
      // src 물리주소로 저장 -> 미리보기에 바로 반영이 되지않음 / 서버환경에서는 물리주소 상관없지만 테스트환경에서는 반영 x 
      // String hardFileUrl="E:/spring/workspace/myproject01/src/main/resources/static/images/product/temp";   
     
           
      ClassPathResource cpr = new ClassPathResource("static"+fileUrl);
      try {
         File location=cpr.getFile();
        
         for(File element:location.listFiles()) {
            element.delete();
         };
         
         file.transferTo(new File(location,fileName));   // location에 fileName으로 업로드
         
      } catch (IOException e) {
         e.printStackTrace();
      }
      
      return fileUrl+fileName;    //   "/images/product/refurbish/temp/"+"파일명"

      
    }

	// admin 상품 목록보기
	@Override
	public void productList(Model model) {
		
		List<ProductResultDto> result=productEntityRepository.findAll().stream()
										 .map(ProductResultDto::new)
										 .collect(Collectors.toList());
		
		model.addAttribute("list", result);
	}
	
	// refurbish 상품 목록보기
	@Override
	public void productList(ModelAndView mv) {
		List<ProductResultDto> result=productEntityRepository.findAll().stream()
				 .map(ProductResultDto::new)
				 .collect(Collectors.toList());

		mv.addObject("list", result);
		
	}
	
	// admin 상품 삭제
	@Override
	public void delete(long no) {
		
		productEntityRepository.deleteById(no);
	}
	
	// admin 상품 등록 정보 업데이트
	@Override
	public void update(long no, ProductUpdateDto dto) {
		
		Optional<ProductEntity> result=productEntityRepository.findById(no);
		
		if(result.isPresent()) {
			ProductEntity entity=result.get();
			entity.update(dto);
			
		    productEntityRepository.save(entity);
		}
		
	}
	
	// refurbish 상품 상세 페이지 이동
	@Override
	public void getDetailList(long no, Model model) {
		
		ProductResultDto result=productEntityRepository.findById(no).map(ProductResultDto::new).get();
		model.addAttribute("detail", result);
	}
	

}


















