package in.karthik.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	/*ModelMapper is a Java library used to convert
	 * one object type to another 
	 * when both classes have variables (fields) with the same names*/
	
	/*@Bean-->Create only one ModelMapper object (Singleton).
	Automatically make it available everywhere
    so, we can use it easily with @Autowired.*/
	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();
	}

}
