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
public class QnaDetailDto {
	
	private long no;
	private String division;
	private String subject;
	private String content;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private String writer;
	
	
	// DB에서 나온 결과를 세팅
	public QnaDetailDto(QnaEntity entity){
		this.no=entity.getNo();
		
		String _division=entity.getDivision();
		this.division=Division.valueOf(_division).getTitle();   // Division의 한글값으로
		
		this.subject=entity.getSubject();
		this.content=entity.getContent();
		this.writer=entity.getWriter();
		this.createdDate=entity.getCreatedDate();
		this.updatedDate=entity.getUpdatedDate();
		
	}

}
