package myproject01.service.qna;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import myproject01.domain.dto.qna.QnaWriteDto;
import myproject01.domain.dto.qna.QnaReplyResultDto;
import myproject01.domain.dto.qna.QnaReplyWriteDto;
import myproject01.domain.dto.qna.QnaResultDto;
import myproject01.domain.dto.qna.QnaUpdateDto;

public interface QnaService {

	void write(QnaWriteDto dto);

	void list(int division, int page, Model model);

	// void getQnaList(int division, Model model);

	void getQnaList(int division, int pageNo, ModelAndView mv);

	void delete(long no);

	void update(long no, QnaUpdateDto dto);

	void getDetail(long no, QnaReplyResultDto dto, Model model);

	void reply(long no, QnaReplyWriteDto dto);

	ModelAndView getReply(long no);



}
