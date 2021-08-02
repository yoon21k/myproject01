package myproject01.domain.dto.member;

import lombok.Data;
import myproject01.domain.entity.member.Member;

@Data
public class MemberLoginDto {
	
	private String userId;
	private String pass;
	
	public Member toEntity() {
		return Member.builder().userId(userId)
							   .pass(pass)
							   .build();
				   
	}

}
