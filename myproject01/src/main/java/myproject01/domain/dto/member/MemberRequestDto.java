package myproject01.domain.dto.member;

import lombok.Data;
import myproject01.domain.entity.member.Member;

@Data
public class MemberRequestDto {
	
	private String userId;
	private String pass;
	private String name;
	private String phone;
	private String ip;   // ip : request에서 얻어오기
	
	public Member toEntity() {
		return Member.builder().userId(userId)
							   .pass(pass)
							   .name(name)
							   .phone(phone)
							   .ip(ip)
							   .build();
				   
		
	}

}
