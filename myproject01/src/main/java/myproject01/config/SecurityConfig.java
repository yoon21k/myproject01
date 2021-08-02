package myproject01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// BCryptPasswordEncoder : 복호화가 불가능한 암호화 방법을 제공하는 인코더
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()   // url 권한 관리를 설정하는 옵션
					.antMatchers(
							"/",
							"/member/**", 
							"/log/**", 
							"/community/**",
							"/store/**",
							"/movie/**",
							"/uploadSummernoteImageFile/**"
							).permitAll()   // 권한 모두 허용
					.antMatchers("/admin/**").hasRole("ADMIN")
				    .anyRequest().authenticated();   // 설정된 주소 외 나머지 주소에 대해선 인증 필요 
		
		http.formLogin().loginPage("/log/login")
						.loginProcessingUrl("/member/login")
						.usernameParameter("userId")   // username
						.passwordParameter("pass")   // password
						.failureForwardUrl("/member/login");
		
		http.csrf().disable();   
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "summernote/**");
	}

	
}
