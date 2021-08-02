package myproject01.domain.entity.qna;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

public interface QnaEntityRepository extends JpaRepository<QnaEntity, Long>{
	
	// select * from qna_entity where division="movie" order by no desc
	List<QnaEntity> findAllByDivisionOrderByNoDesc(String name);

	Page<QnaEntity> findAllByDivision(String name, Pageable pageable);
	
	// select * from qna_entity where division="movie"
	List<QnaEntity> findAllByDivision(String name);

	List<QnaEntity> findBySubjectContaining(String keyword);


}
