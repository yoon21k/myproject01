package myproject01.service.review;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import myproject01.domain.dto.review.ReviewReplyResultDto;
import myproject01.domain.dto.review.ReviewReplyUpdateDto;
import myproject01.domain.dto.review.ReviewReplyWriteDto;
import myproject01.domain.dto.review.ReviewResultDto;
import myproject01.domain.dto.review.ReviewUpdateDto;
import myproject01.domain.dto.review.ReviewWriteDto;
import myproject01.domain.entity.product.ProductEntity;
import myproject01.domain.entity.review.ReviewEntity;
import myproject01.domain.entity.review.ReviewEntityRepository;
import myproject01.domain.entity.review.ReviewReply;
import myproject01.domain.entity.review.ReviewReplyRepository;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	ReviewReplyRepository reviewReplyRepository;
	
	@Autowired
	ReviewEntityRepository reviewEntityRepository;
	
	// refurbish 상품 리뷰 작성(파일업로드)
	@Override
	public void write(long no, MultipartFile file, ReviewWriteDto dto) {
		
		long fileSize=file.getSize();
		String fileName=file.getOriginalFilename();
		String fileUrl="/images/product/review/";
		
		ClassPathResource cpr = new ClassPathResource("static"+fileUrl); 
		ClassPathResource tempCpr = new ClassPathResource("static"+fileUrl+"temp/");   
	      try {
	         File location=cpr.getFile();
	         
	         File tempFile= new File(tempCpr.getFile(), fileName);
	         if(tempFile.exists()) {
	            tempFile.renameTo(new File(location,fileName));
	            
	         }
	         
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
		
		dto.setFileName(fileName);
		dto.setFileUrl(fileUrl);
		dto.setFileSize(fileSize); 
		
		ReviewEntity entity=ReviewEntity.builder().review(dto.getReview())
												   .writer(dto.getWriter())
												   .fileName(dto.getFileName())
												   .fileSize(dto.getFileSize())
												   .fileUrl(dto.getFileUrl())
												   .product(ProductEntity.builder().no(no).build())
												   .build();
		
		reviewEntityRepository.save(entity);
	}
	
	// refurbish 상품 리뷰 파일 업로드(미리보기)
	@Override
	public String uploadTempFile(MultipartFile file) {
		
		long fileSize=file.getSize();
		if(fileSize>2*1024*1024) {
	         return null;
	      }
		String fileName=file.getOriginalFilename();
		String fileUrl="/images/product/review/temp/";
		
		ClassPathResource cpr=new ClassPathResource("static"+fileUrl);
		try {
			File location=cpr.getFile();
			
			for(File element:location.listFiles()) {
				element.delete();
			}
			file.transferTo(new File(location, fileName));
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileUrl+fileName;
	}

	// refurbish 상품 상세 페이지에 리뷰 불러오기
	@Override
	public ModelAndView getReview(long no) {
		ModelAndView mv=new ModelAndView("/store/refurbish/review");
		
		Sort sort=Sort.by("no").descending();
		List<ReviewResultDto> result=reviewEntityRepository.findAllByProductNo(no, sort)
										.stream()
										.map(ReviewResultDto::new)
										.collect(Collectors.toList());
		
		mv.addObject("review", result);
		
		return mv;
	}
	
	// refurbish 상품 리뷰 삭제
	@Override
	public void delete(long no) {
		
		reviewEntityRepository.deleteById(no);
	}
	
	// refurbish 상품 리뷰 업데이트
	@Override
	public void update(long no, ReviewUpdateDto dto) {
		
		Optional<ReviewEntity> result=reviewEntityRepository.findById(no);
		
		ReviewEntity entity=result.get();
		entity.update(dto);
		
		reviewEntityRepository.save(entity);
	}
	
	// refurbish 상품 리뷰 댓글 작성
	@Override
	public void reply(long no, ReviewReplyWriteDto dto) {
		
		ReviewReply rEntity=ReviewReply.builder().reply(dto.getReply())
												 .writer(dto.getWriter())
												 .review(ReviewEntity.builder().no(no).build())
												 .build();
		
		reviewReplyRepository.save(rEntity);
		
	}
	
	// refurbish 상품 리뷰 댓글 불러오기
	@Override
	public void getReply(long no, ModelAndView mv) {
		
		Sort sort=Sort.by("no").descending();
		List<ReviewReplyResultDto> result=reviewReplyRepository.findAllByReviewNo(no, sort)
												.stream()
												.map(ReviewReplyResultDto::new)
												.collect(Collectors.toList());
		
		mv.addObject("reply", result);
		
	}
	
	// refurbish 상품 리뷰 댓글 삭제
	@Override
	public void replyDelete(long no) {
		
		reviewReplyRepository.deleteById(no);
		
	}
	
	// refurbish 상품 리뷰 댓글 업데이트
	@Override
	public void replyUpdate(long no, ReviewReplyUpdateDto dto) {
		
		Optional<ReviewReply> result=reviewReplyRepository.findById(no);
		
		ReviewReply entity=result.get();
		entity.update(dto);
		
		reviewReplyRepository.save(entity);
	}
	

}










