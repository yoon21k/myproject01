 package myproject01.service.qna;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import myproject01.domain.dto.qna.QnaDetailDto;
import myproject01.domain.dto.qna.QnaWriteDto;
import myproject01.domain.dto.qna.QnaReplyResultDto;
import myproject01.domain.dto.qna.QnaReplyWriteDto;
import myproject01.domain.dto.qna.QnaResultDto;
import myproject01.domain.dto.qna.QnaUpdateDto;
import myproject01.domain.dto.util.PageInfo;
import myproject01.domain.entity.Division;
import myproject01.domain.entity.qna.QnaEntity;
import myproject01.domain.entity.qna.QnaEntityRepository;
import myproject01.domain.entity.qna.QnaReply;
import myproject01.domain.entity.qna.QnaReplyRepository;

@Service
public class QnaSerivceImpl implements QnaService{
	
	@Autowired
	private QnaEntityRepository qnaEntityRepository;
	
	@Autowired
	private QnaReplyRepository qnaReplyRepository;
	
	// qna 게시글 작성
	@Override
	public void write(QnaWriteDto dto) {
		
		qnaEntityRepository.save(dto.toEntity());
	}
	
	// community qna list 불러오기
	@Override
	public void list(int division, int page, Model model) {  
		// Division[] arr=Division.values();
		// Division div=arr[0];   // 0:교환반품, 1:주문결제, 2:배송 ...
		Division div=Division.values()[division];
		
		Sort sort=Sort.by(Direction.DESC, "no");
		int size=10;
		Pageable pageable=PageRequest.of(page-1, size, sort);
		
		// qnaEntityRepository.findAll(pageable);
		// qnaEntityRepository.findAllByDivisionOrderByNoDesc(div.name())
		Page<QnaEntity> result=qnaEntityRepository.findAllByDivision(div.name(), pageable);
		
		if(!result.isEmpty()) {
			int tot=result.getTotalPages();
			long listTot=result.getTotalElements();
			/*
			int psize=4;
			int bno = page/psize;   
			if(page%psize>0) bno++;   
			
			int to = bno*psize;
			int from = to-psize+1;
			
			if(to>tot) to=tot;
			*/
			
			// System.out.println(pageTot);
			// System.out.println(result.getTotalElements());
			
			PageInfo pageInfo=PageInfo.builder().from(1)
												.to(tot)
												//.psize(psize)   // 페이징 갯수
												.size(size)   // 한 페이지 게시글 수
												.page(page)
												//.tot(tot)   // 총 페이지 수
												.totE(listTot)   // 총 게시글 수
												.build();
			
		
			model.addAttribute("pi", pageInfo);
			
			// QueryMethod
			List<QnaResultDto> list = 
					result.getContent().stream()
									   // .map(entity->new QnaResultDto(entity))
									   .map(QnaResultDto::new)   
									   .collect(Collectors.toList());
			
			model.addAttribute("today", LocalDate.now());
			model.addAttribute("list", list);
		}
	}
	
	// admin qna list 불러오기
	@Override
	public void getQnaList(int division, int pageNo, ModelAndView mv) {
		// QueryMethod
		// select * from qna_entity -> findAll()
		// where division="movie"
		Division div=Division.values()[division]; 
		
		// int page=0;
		int size=10;   // 한 페이지에 표시할 갯수
		Pageable pageable=PageRequest.of(pageNo-1, size, Direction.DESC, "no");
		Page<QnaEntity> result = qnaEntityRepository.findAllByDivision(div.name(), pageable);
		
		if(!result.isEmpty()) {   // result.isEmpty() -> DB에 데이터가 하나도 없으면
			int pageTotal = result.getTotalPages();

			// model.addAttribute("to", pageTotal);   // page 총 갯수
			mv.addObject("to", pageTotal);
			
			List<QnaResultDto> list= 
					// qnaEntityRepository.findAllByDivision(div.name())
					result.getContent().stream()   // pageable 까지
									  // entity 정보를 하나씩 가져와서 QnaResultDto 타입으로 저장
									  // .map(QnaResultDto::new)
									  .map(entity->new QnaResultDto(entity))
									  .collect(Collectors.toList());
			
			// 데이터를 페이지로
			// model.addAttribute("list", list);
			mv.addObject("list", list);
		}
		
	}
	
	// qna 게시글 삭제
	@Override
	public void delete(long no) {

		qnaEntityRepository.deleteById(no);
	}
	
	// qna 게시글 업데이트
	// @Transactional
	@Override
	public void update(long no, QnaUpdateDto dto) {
		
		// 해당하는 데이터가 DB에 있는지 먼저 확인
		Optional<QnaEntity> result = qnaEntityRepository.findById(no);
		
		if(result.isPresent()) {
			// 현재 DB에 저장되어있는 원본(수정 전) 데이터
			QnaEntity entity=result.get();
			entity.update(dto);
			
			// 수정된 정보 저장
			qnaEntityRepository.save(entity);
		}
		
		
		/*
		qnaEntityRepository.findById(no).map(entity->entity.update(dto))
										.get();
		*/
		/*
		qnaEntityRepository.findById(no).get().update(dto);
		*/
	}
	
	
	// community qna detail 게시글 상세 페이지 + 댓글(답변) 불러오기
	@Override
	public void getDetail(long no, QnaReplyResultDto dto, Model model) {
		
		
		
		QnaDetailDto result=qnaEntityRepository.findById(no).map(QnaDetailDto::new).orElseThrow();
		
		model.addAttribute("detail", result);
		
		// detail 페이지에 qna 답변 가져오기
		Sort sort=Sort.by("no").descending();
		List<QnaReplyResultDto> reply=qnaReplyRepository.findAllByQnaNo(no, sort)
											 .stream()
											 .map(QnaReplyResultDto::new)
											 .collect(Collectors.toList());
		
		model.addAttribute("reply", reply);
		
		model.addAttribute("today", LocalDate.now());
	}
	
	// admin qna 게시글에 댓글(답변) 작성(ManyToOne)
	@Override
	public void reply(long no, QnaReplyWriteDto dto) {
		
		QnaReply rEntity=QnaReply.builder().reply(dto.getReply())
									       .writer(dto.getWriter())
									       .qna(QnaEntity.builder().no(no).build())
										   .build();
		
		qnaReplyRepository.save(rEntity);
		
	}
	
	// admin qna 댓글(답변) 불러오기
	@Override
	public ModelAndView getReply(long no) {
		ModelAndView mv=new ModelAndView("/admin/qna/reply"); 
		
		Sort sort=Sort.by("no").descending();
		List<QnaReplyResultDto> result=qnaReplyRepository.findAllByQnaNo(no, sort)
											 .stream()
											 .map(QnaReplyResultDto::new)
											 .collect(Collectors.toList());
		
		mv.addObject("reply", result);  
		
		return mv;
	}
	
	
}















