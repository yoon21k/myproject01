package myproject01.domain.dto.review;

import lombok.Data;
import myproject01.domain.entity.review.ReviewEntity;

@Data
public class ReviewWriteDto {
	
	private String review;
	private String writer;
	
	private String fileName;
	private String fileUrl;
	private long fileSize;
	
	public ReviewEntity toEntity() {
		return ReviewEntity.builder().review(review)
									 .writer(writer)
									 .fileName(fileName)
									 .fileSize(fileSize)
									 .fileUrl(fileUrl)	
									 .build();
	}
	
}
