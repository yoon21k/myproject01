package myproject01.domain.entity.qna;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import myproject01.domain.dto.qna.QnaUpdateDto;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class QnaEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long no;
	@Column(nullable = false)
	private String division;
	@Column(nullable = false)
	private String subject;
	@Column(nullable = false)
	private String content;
	
	@Column
	private String writer;
	
	@CreatedDate
	@Column
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	@Column
	private LocalDateTime updatedDate;
	
	@OneToMany(mappedBy = "qna", cascade = CascadeType.ALL)
	List<QnaReply> replies;
	
	public QnaEntity update(QnaUpdateDto dto) {   // setter의 기능
		// 세팅된 값 그대로 return
		subject=dto.getSubject();
		content=dto.getContent();
		return this;
	}
	

}
