package me.lee.demobootweb;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SamplerController {
	
	// prehandle 1 전처리 작업
	// prehandle 2
	// 요청 처리
	// posthandle 2 후처리 
	// posthandle 1 순서 역순
	// 뷰 렌더링 (RestController는 뷰렌더링이 없다)
	// afterCompletion 2
	// afterCompletion 1 순서 역순
	
	@GetMapping("/hello")
	public String hello(@PathVariable("id") Person person) {
		// method conversion argument error -> formatter 사용
		//ID 값 가져오기: JPA 기능을 이용
		
		
		return "hello" + person.getName();
	}
}
