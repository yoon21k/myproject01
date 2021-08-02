package myproject01.domain.dto.review;

import java.time.LocalDateTime;

import lombok.Data;
import myproject01.domain.entity.review.ReviewEntity;

@Data
public class ReviewResultDto {
	
	private long no;
	
	String review;
	
	String writer;
	
	String fileName;
	
	String fileUrl;
	
	long fileSize;
	
	LocalDateTime createdDate;

	public ReviewResultDto(ReviewEntity entity) {
		this.no = entity.getNo();
		this.review = entity.getReview();
		this.writer = entity.getWriter();
		this.fileName = entity.getFileName();
		this.fileUrl = entity.getFileUrl();
		this.fileSize = entity.getFileSize();
		this.createdDate = entity.getCreatedDate();
	}
	


}
