package myproject01.service.member;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import myproject01.domain.dto.member.MemberDetails;
import myproject01.domain.entity.member.Member;
import myproject01.domain.entity.member.MemberRepository;

@Slf4j
@Service
public class MemberDetailsService implements UserDetailsService{
	
	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {  
		log.debug(userId);
		
		//return memberRepository.findByUserId(userId).map(MemberDetails::new).orElseThrow();
		
		
		Optional<Member> result = memberRepository.findByUserId(userId);
		if(result.isEmpty()) {
			throw new UsernameNotFoundException("존재하지 않는 이메일");
		}
		
		Member entity = result.get();
		MemberDetails memberDetails = new MemberDetails(entity);
		return memberDetails;
		
		
	}
	
}
