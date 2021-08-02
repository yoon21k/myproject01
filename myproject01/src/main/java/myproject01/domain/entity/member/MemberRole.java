package myproject01.domain.entity.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {

	GUEST("ROLE_GUEST", "비회원"),   
	USER("ROLE_USER", "회원"),  
	ADMIN("ROLE_ADMIN", "관리자");   
	
	final String role;
	final String title;

}
