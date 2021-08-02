package myproject01;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import myproject01.domain.entity.Division;
import myproject01.domain.entity.member.Member;
import myproject01.domain.entity.member.MemberRepository;
import myproject01.domain.entity.member.MemberRole;
import myproject01.domain.entity.qna.QnaEntity;
import myproject01.domain.entity.qna.QnaEntityRepository;

@SpringBootTest
class Myproject01ApplicationTests {
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	QnaEntityRepository qnaEntityRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	// @Test
	void QNA데이터삽입() {
		
		// Division.values();   // Enum 요소들을 순서대로 배열
		for(Division div : Division.values()) {
			IntStream.rangeClosed(1, 100).forEach(i->{
				QnaEntity entity=QnaEntity.builder().division(div.name())
													.subject(div.getTitle()+" 제목"+i)
													.content(div.getTitle()+" 내용"+i)
													.writer("작성자"+(i%10+1))
													.build();
				
				qnaEntityRepository.save(entity);
			});
		}
		
	}
	
	// @Test
	void 관리자계정추가() {
		Member entity=Member.builder().userId("admin@test.com")
									  .pass(passwordEncoder.encode("1234"))
									  .name("관리자")
									  .phone("01099998888")
									  .ip("127.0.0.1")
									  .build();
		
		entity.addRole(MemberRole.GUEST);
		entity.addRole(MemberRole.USER);
		entity.addRole(MemberRole.ADMIN);
		memberRepository.save(entity);
		
	}

}
