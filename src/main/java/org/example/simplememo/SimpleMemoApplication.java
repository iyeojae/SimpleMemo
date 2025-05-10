package org.example.simplememo;

import org.springframework.boot.SpringApplication; // Spring Boot 애플리케이션을 실행하기 위한 클래스
import org.springframework.boot.autoconfigure.SpringBootApplication; // 스프링 부트 애플리케이션을 설정하기 위한 어노테이션

@SpringBootApplication // 이 어노테이션은 Spring Boot 애플리케이션의 시작 클래스를 정의
public class SimpleMemoApplication {

	public static void main(String[] args) {
		// SpringApplication.run()을 호출하여 애플리케이션을 실행
		SpringApplication.run(SimpleMemoApplication.class, args);
	}
}