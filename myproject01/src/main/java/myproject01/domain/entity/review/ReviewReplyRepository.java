package myproject01.domain.entity.review;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewReplyRepository extends JpaRepository<ReviewReply, Long>{

	List<ReviewReply> findAllByReviewNo(long no, Sort sort);

}
