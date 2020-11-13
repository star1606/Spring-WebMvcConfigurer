package me.lee.demobootweb;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SamplerController {

	@GetMapping("/hello")
	public String hello(@PathVariable("id") Person person) {
		// method conversion argument error -> formatter 사용
		//ID 값 가져오기: JPA 기능을 이용
		
		
		return "hello" + person.getName();
	}
}
