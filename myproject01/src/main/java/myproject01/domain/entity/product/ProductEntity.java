package myproject01.domain.entity.product;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import myproject01.domain.dto.product.ProductUpdateDto;
import myproject01.domain.dto.product.ProductUploadDto;
import myproject01.domain.entity.review.ReviewEntity;
import myproject01.domain.entity.review.ReviewReply;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long no;
	
	@Column(nullable = false)
	private String productName;
	
	@Column(nullable = false)
	private String price;
	
	@Column
	private String salePrice;
	
	@Column(columnDefinition = "text")
	private String content;
	
	@Column(nullable = false)
	private String fileName;
	@Column(nullable = false)
	private String fileUrl;
	@Column(nullable = false)
	private long fileSize;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	List<ReviewEntity> reviews;
	
	public ProductEntity update(ProductUpdateDto dto) {
		productName=dto.getProductName();
		price=dto.getPrice();
		salePrice=dto.getSalePrice();
		content=dto.getContent();
		return this;
	}

}















