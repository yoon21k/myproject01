package myproject01.domain.dto.member;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import myproject01.domain.entity.member.Member;

@Getter
public class MemberDetails extends User{
	
	private String name;
	
	// MemberDetails extends User implements UserDetails
	// (String username, String password, Collection<? extends GrantedAuthority> authorities)
	// Service 에서 UserDetails에 해당하는 타입으로 return하기 위해서 
	// getRoles() 를 Collection<? extends GrantedAuthority 타입으로 변환
	public MemberDetails(Member entity) {
		super(entity.getUserId(), entity.getPass(),
						entity.getRoles().stream()
										 .map(role->new SimpleGrantedAuthority(role.getRole()))
										 .collect(Collectors.toSet()));
		
		name=entity.getName();
		
	}

}
