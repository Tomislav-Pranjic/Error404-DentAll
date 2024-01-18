package dentall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Component

@SpringBootApplication
public class Error404BeApplication {

	public static final String EMAIL_FORMAT = "[[a-zA-Z0-9]+[\\.-_]?[a-zA-Z0-9]+]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";

	public static final String PHONE_NUMBER_FORMAT = "[0-9]{3}[0-9]{3}[0-9]{3,4}";

	public static final String TIME_FORMAT = "[0-9]{2}:[0-9]{2}";

	public static final String WORKING_DAYS_FORMAT = "N?P?U?S?C?E?B?";

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public final static String REGISTRATION_FORMAT = "[A-Z]{2}[0-9]{3,4}[A-Z]{2}";

	public static final String USERNAME_FORMAT = "[a-z]+[0-9]*";

	@Bean
	public PasswordEncoder pswdEncoder(){
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(Error404BeApplication.class, args);
	}

}
