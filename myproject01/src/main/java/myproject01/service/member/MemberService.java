package myproject01.service.member;

import org.springframework.ui.Model;

import myproject01.domain.dto.member.MemberLoginDto;
import myproject01.domain.dto.member.MemberRequestDto;

public interface MemberService {

	void join(MemberRequestDto dto, Model model);

	String login(MemberLoginDto dto, Model model);

	boolean idCheck(String id);

}
