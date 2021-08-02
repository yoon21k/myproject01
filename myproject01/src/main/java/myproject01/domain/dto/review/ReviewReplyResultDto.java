package myproject01.domain.dto.review;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import myproject01.domain.entity.review.ReviewReply;

@NoArgsConstructor
@Data
public class ReviewReplyResultDto {
	
	private long no;
	private String reply;
	private String writer;
	private LocalDateTime createdDate;
	
	
	public ReviewReplyResultDto(ReviewReply entity) {
		this.no = entity.getNo();
		this.reply = entity.getReply();
		this.writer = entity.getWriter();
		this.createdDate = entity.getCreatedDate();
	}
	
	
}
