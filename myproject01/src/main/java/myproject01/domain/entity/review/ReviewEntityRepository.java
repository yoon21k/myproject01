package myproject01.domain.entity.review;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewEntityRepository extends JpaRepository<ReviewEntity, Long>{

	List<ReviewEntity> findAllByProductNo(long no, Sort sort);

}
