package myproject01.domain.entity.qna;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaReplyRepository extends JpaRepository<QnaReply, Long>{

	List<QnaReply> findAllByQnaNo(long no, Sort sort);

}
