package myproject01.domain.dto.review;

import lombok.Data;
import myproject01.domain.entity.review.ReviewReply;

@Data
public class ReviewReplyWriteDto {
	
	private String reply;
	private String writer;
	
	public ReviewReply toEntity() {
		
		return ReviewReply.builder().reply(reply)
									.writer(writer)
									.build();
	}
}
