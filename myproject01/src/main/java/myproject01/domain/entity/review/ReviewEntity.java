package myproject01.domain.entity.review;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import myproject01.domain.dto.review.ReviewUpdateDto;
import myproject01.domain.entity.BaseDate;
import myproject01.domain.entity.product.ProductEntity;
import myproject01.domain.entity.qna.QnaReply;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ReviewEntity extends BaseDate{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	@Column(nullable = false)
	private String review;
	
	@Column(nullable = false)
	private String writer;

	
	@Column(nullable = false)
	private String fileName;
	
	@Column(nullable = false)
	private String fileUrl;
	
	@Column(nullable = false)
	private long fileSize;
	
	// 상품과 리뷰의 관계 설정
	@ManyToOne
	@JoinColumn(name = "productNo")
	ProductEntity product;
	
	// 리뷰와 댓글의 관계 설정
	@OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
	List<ReviewReply> replies;
	
	public ReviewEntity update(ReviewUpdateDto dto) {
		review=dto.getReview();
		return this;
	}

}





