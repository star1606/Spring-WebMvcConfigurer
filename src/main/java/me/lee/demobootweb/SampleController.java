package me.lee.demobootweb;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	
	// prehandle 1 전처리 작업
	// prehandle 2
	// 요청 처리
	// posthandle 2 후처리 
	// posthandle 1 순서 역순
	// 뷰 렌더링 (RestController는 뷰렌더링이 없다)
	// afterCompletion 2
	// afterCompletion 1 순서 역순
	
	@GetMapping("/hello")
	public String hello(/* @RequestParam("id")*/ @PathVariable("id") Person person) {
		// method conversion argument error -> formatter 사용
		//ID 값 가져오기: JPA 기능을 이용
		
		
		return "hello" + person.getName();
	}
	
	@GetMapping("/message")
	public String message(@RequestBody Person person) {
		// HTTP 컨버터 : @ResponseBody, @RequestBody를 사용할 때 적용
		// 문자열로 변환 ,객체로 변환 등등
		// @ResponseBody는 응답.
		return "hello person";
	}

	@GetMapping("/jsonMessage")
	public Person jsonMessage(@RequestBody Person person) {
		// 요청헤더 정보의 contentType : 내가 보내는 정보가 어떤 타입이라고 서버에게 알려주는 것
		// Accept Type : 요청에 대한 응답으로 어떠한 데이터 타입을 원한다.
		// @RequestBody - JSON으로 온 문자열을 객체로 변환
		// @ResponseBody - 요청에 대한 응답을 객체로 함 
		
		return person;
	}
	
	
	
	
}
