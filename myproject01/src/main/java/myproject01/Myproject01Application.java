package myproject01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableJpaAuditing   // AuditingEntityListener
@SpringBootApplication
public class Myproject01Application {

	public static void main(String[] args) {
		SpringApplication.run(Myproject01Application.class, args);
	}

}
