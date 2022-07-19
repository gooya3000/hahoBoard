package hahoBoard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("hahoBoard")
@MapperScan(basePackages = "hahoBoard.mapper")

public class HahoBoardApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(HahoBoardApplication.class, args);
	}

}
