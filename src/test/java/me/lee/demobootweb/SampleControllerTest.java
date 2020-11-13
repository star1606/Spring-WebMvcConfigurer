package me.lee.demobootweb;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebMvcTest
public class SampleControllerTest {
	
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	PersonRepository personRepository;
	
	@Test
	public void hello() throws Exception {
		Person person = new Person();
		person.setName("lee");
		Person savedPerson = personRepository.save(person);
		
		this.mockMvc.perform(get("/hello/lee"))
			//	.param("id", savedPerson.getId().toString()) 작동안된
				.andDo(print());
			//.andExpect(content().string("hello")); 이거 작동안됨
	}
	
	@Test
	public void helloStatic() throws Exception {
		this.mockMvc.perform(get("index.html"))
		.andDo(print())
		.andExpect(status().isOk());
		//.andExpect(content().string(Matchers.containsString("hello index"));
		
	}
	
}
