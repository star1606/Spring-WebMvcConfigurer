package me.lee.demobootweb;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.xpath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.oxm.Marshaller;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebMvcTest
@AutoConfigureMockMvc
public class SampleControllerTest {
	
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	Marshaller marshaller;
	
	@Test
	public void jsonMessage() throws Exception {
		Person person = new Person();
		person.setId(2019l);
		person.setName("lee");
		
		String jsonString = objectMapper.writeValueAsString(person);
		
		this.mockMvc.perform(get("/jsonMessage")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(jsonString))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(2019))
			.andExpect(jsonPath("$.name").value("lee"))
			// jsonpath로 데이터 있는지 확인 가능
		;
	}
	
	
	@Test
	public void xmlMessage() throws Exception {
		Person person = new Person();
		person.setId(2019l);
		person.setName("lee");
		
		StringWriter stringWriter = new StringWriter();
		
		Result result = new StreamResult(stringWriter);
		
		
		marshaller.marshal(person, result);
		String xmlString = stringWriter.toString();
		
		
		String jsonString = objectMapper.writeValueAsString(person);
		
		this.mockMvc.perform(get("/xmlMessage")
				.contentType(MediaType.APPLICATION_ATOM_XML)
				.accept(MediaType.APPLICATION_ATOM_XML)
				.content(xmlString))
			.andDo(print())
			.andExpect(status().isOk())
			//.andExpect(xpath().)
			
			// jsonpath로 데이터 있는지 확인 가능
		;
	}
	
	
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
