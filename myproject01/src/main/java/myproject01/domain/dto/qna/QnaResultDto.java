package myproject01.domain.dto.qna;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import myproject01.domain.entity.Division;
import myproject01.domain.entity.qna.QnaEntity;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QnaResultDto {
	
	private long no;
	private String division;
	private String subject;
	private String content;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private String writer;
	
	
	// DB에서 나온 결과를 세팅
	public QnaResultDto(QnaEntity entity){
		no=entity.getNo();
		
		String _division=entity.getDivision();
		division=Division.valueOf(_division).getTitle();   // Division의 한글값으로
		
		subject=entity.getSubject();
		content=entity.getContent();
		writer=entity.getWriter();
		createdDate=entity.getCreatedDate();
		updatedDate=entity.getUpdatedDate();
		
	}

}
