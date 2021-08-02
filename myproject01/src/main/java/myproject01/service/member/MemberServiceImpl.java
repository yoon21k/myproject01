package myproject01.service.member;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import myproject01.domain.dto.member.MemberLoginDto;
import myproject01.domain.dto.member.MemberRequestDto;
import myproject01.domain.entity.member.Member;
import myproject01.domain.entity.member.MemberRepository;
import myproject01.domain.entity.member.MemberRole;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private MemberRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 회원가입
	@Override
	public void join(MemberRequestDto dto, Model model) {
		// ip 정보
		String ip = request.getRemoteAddr();
		dto.setIp(ip);
		
		String encodedPass = passwordEncoder.encode(dto.getPass());
		dto.setPass(encodedPass);   // password -> encoding된 데이터로
		
		// 회원가입 시 Role 적용
		Member entity = dto.toEntity();   // dto 에는 addRole()이 없기때문에 entity로
		entity.addRole(MemberRole.GUEST);
		entity.addRole(MemberRole.USER);
		repository.save(entity);

		// repository는 entity 객체만 허용
		// toEntity를 통해서 dto를 entity(Member)로
		// repository.save(dto.toEntity());
		
		model.addAttribute("welcome", dto.getName() + "님! 회원가입을 축하합니다.");
		
	}
	
	// 로그인
	@Override
	public String login(MemberLoginDto dto, Model model) {
		// 1) id가 존재하는지 확인
		Optional<Member> opt = repository.findByUserId(dto.getUserId());
		if(opt.isPresent()) {
			// 2) 존재하는 id이면 비밀번호 확인
			Member entity = opt.get();
			System.out.println(entity);
			// 입력된 pass와 DB에 있는 pass(encoding)가 매치하는지 체크
			boolean result = passwordEncoder.matches(dto.getPass(), entity.getPass());   
			if(result) {
				System.out.println("비밀번호 일치");
				// 로그인 처리
				return "redirect:/";
			}
		}
		model.addAttribute("loginErr", "아이디가 존재하지 않거나 비밀번호가 일치하지 않습니다!");
		
		return "/log/login";	
	}
	
	
	// 회원가입 시 아이디 중복체크
	@Override
	public boolean idCheck(String id) {
		
		Optional<Member> result = repository.findByUserId(id);
		return result.isEmpty();
				
	}



}



