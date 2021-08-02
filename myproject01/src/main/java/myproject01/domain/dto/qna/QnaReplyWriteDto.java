package myproject01.domain.dto.qna;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import myproject01.domain.entity.qna.QnaReply;

@RequiredArgsConstructor
@Data
public class QnaReplyWriteDto {
	
	private String reply;
	private String writer;
	
	public QnaReply toEntity() {
		
		return QnaReply.builder().reply(reply)
								 .writer(writer)
								 .build();
	}
}
