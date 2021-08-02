package myproject01.domain.entity.review;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import myproject01.domain.dto.review.ReviewReplyUpdateDto;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ReviewReply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	@Column(nullable = false)
	private String reply;
	
	@Column(nullable = false)
	private String writer;
	
	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime createdDate;
	
	@ManyToOne
	@JoinColumn(name = "reviewNo")
	ReviewEntity review;
	
	public ReviewReply update(ReviewReplyUpdateDto dto) {
		reply=dto.getReply();
		return this;
	}

}













