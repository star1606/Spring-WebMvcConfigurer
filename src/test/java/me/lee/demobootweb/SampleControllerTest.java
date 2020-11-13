package me.lee.demobootweb;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@WebMvcTest
public class SampleControllerTest {
	
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void hello() throws Exception {
		this.mockMvc.perform(get("/hello/lee"))
			.andDo(print());
			//.andExpect(content().string("hello")); 이거 작동안됨
	}
	
}
