package cn.java.ckEc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"cn.java.ckEc.controller,cn.java.ckEc.component,cn.java.ckEc.service.impl"})
@MapperScan(basePackages={"cn.java.ckEc.mapper"})
public class ImutCkEcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImutCkEcApplication.class, args);
	}

}

