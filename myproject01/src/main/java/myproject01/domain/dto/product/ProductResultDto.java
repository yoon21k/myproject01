package myproject01.domain.dto.product;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import myproject01.domain.entity.product.ProductEntity;

@RequiredArgsConstructor
@Data
public class ProductResultDto {


	private long no;
	
	private String productName;
	
	private String price;

	private String salePrice;
	
	private String content;
	
	private String fileName;
	
	private String fileUrl;
	
	private long fileSize;
	

	public ProductResultDto(ProductEntity entity) {
		this.no = entity.getNo();
		this.productName = entity.getProductName();
		this.price = entity.getPrice();
		this.salePrice = entity.getSalePrice();
		this.content = entity.getContent();
		this.fileName = entity.getFileName();
		this.fileUrl = entity.getFileUrl();
		this.fileSize = entity.getFileSize();
	}
	
	
	
}
