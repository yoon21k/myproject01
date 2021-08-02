package myproject01.service.product;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import myproject01.domain.dto.product.ProductUploadDto;
import myproject01.domain.dto.review.ReviewResultDto;
import myproject01.domain.dto.product.ProductUpdateDto;

public interface ProductService {

	void insert(MultipartFile file, ProductUploadDto dto);

	String uploadTempFile(MultipartFile file);

	void productList(Model model);

	void productList(ModelAndView mv);

	void delete(long no);

	void getDetailList(long no, Model model);

	void update(long no, ProductUpdateDto dto);
	

}
