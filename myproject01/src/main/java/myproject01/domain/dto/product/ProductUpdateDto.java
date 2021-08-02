package myproject01.domain.dto.product;

import lombok.Data;

@Data
public class ProductUpdateDto {
	
	
	private String productName;
	
	private String price;
	
	private String salePrice;
	
	private String content;
	
}
