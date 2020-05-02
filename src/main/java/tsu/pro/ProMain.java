package tsu.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling	
public class ProMain extends SpringBootServletInitializer{
	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProMain.class);
    }
    //http://localhost:8080/pro_Servers/users/login/huchao/123456
	public static void main(String[] args) {
		SpringApplication.run(ProMain.class,args);
	}

}
