package myproject01.domain.dto.qna;

import java.time.LocalDateTime;

import lombok.Data;
import myproject01.domain.entity.qna.QnaEntity;

@Data
public class QnaWriteDto {
	
	private String division;
	private String subject;
	private String content;
	private String writer;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	public QnaEntity toEntity() {
		
		return QnaEntity.builder().division(division)
								  .subject(subject)
								  .content(content)
								  .writer(writer)
								  .createdDate(createdDate)
								  .updatedDate(updatedDate)
								  .build();
		
	};

}
