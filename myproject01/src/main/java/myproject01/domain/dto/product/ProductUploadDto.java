package myproject01.domain.dto.product;

import lombok.Data;
import myproject01.domain.entity.product.ProductEntity;

@Data
public class ProductUploadDto {
	
	private String productName;
	
	private String price;
	
	private String salePrice;
	
	private String content;
	
	// 파일 관련 정보는 수동으로 세팅
	private String fileName;
	private String fileUrl;
	private long fileSize;
	
	public ProductEntity toEntity() {
		return ProductEntity.builder().productName(productName)
									  .price(price)
									  .salePrice(salePrice)
									  .content(content)
									  .fileName(fileName)
									  .fileUrl(fileUrl)
									  .fileSize(fileSize)
									  .build();
	}

}
