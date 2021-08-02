package myproject01.domain.entity.member;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import myproject01.domain.entity.BaseDate;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
public class Member extends BaseDate{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	// @Column(columnDefinition = "unique not null")   
	@Column(unique = true, nullable = false)   // 중복 체크
	private String userId;
	
	@Column(nullable = false)
	private String pass;
	
	@Column(nullable = false)
	private String name;
	
	@Column
	private String phone;
	
	@Column
	private String ip;
	
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)   // DB에 컬럼으로 들어갈때의 타입
	@Builder.Default
	private Set<MemberRole> roles = new HashSet<>();
	
	public void addRole(MemberRole role) {
		roles.add(role);
	}

	
}










