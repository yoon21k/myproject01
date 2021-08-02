package myproject01.domain.dto.qna;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import myproject01.domain.entity.qna.QnaReply;

@NoArgsConstructor
@Data
public class QnaReplyResultDto {
	
	private long no;
	private String reply;
	private String writer;
	private LocalDateTime createdDate;
	
	// DB에서 entity가 넘어왔을때 매핑하는 생성자
	public QnaReplyResultDto(QnaReply entity) {
		this.no = entity.getNo();
		this.reply = entity.getReply();
		this.writer = entity.getWriter();
		this.createdDate = entity.getCreatedDate();
	}
	
	
}
