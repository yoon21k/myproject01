package myproject01.service.review;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import myproject01.domain.dto.review.ReviewReplyUpdateDto;
import myproject01.domain.dto.review.ReviewReplyWriteDto;
import myproject01.domain.dto.review.ReviewUpdateDto;
import myproject01.domain.dto.review.ReviewWriteDto;

public interface ReviewService {

	void write(long no, MultipartFile file, ReviewWriteDto dto);

	ModelAndView getReview(long no);

	String uploadTempFile(MultipartFile file);

	void delete(long no);

	void update(long no, ReviewUpdateDto dto);

	void reply(long no, ReviewReplyWriteDto dto);

	void getReply(long no, ModelAndView mv);

	void replyDelete(long no);

	void replyUpdate(long no, ReviewReplyUpdateDto dto);

}
