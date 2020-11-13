package me.lee.demobootweb;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

public class PersonFormatter implements Formatter<Person> {
	
	
	// 문자열 "name" 파라미터를 받아서 -> person객체에 저장하고 리턴
	@Override
	public Person parse(String text, Locale locale) throws ParseException {
		Person person = new Person();
		person.setName(text);
		return person;
	}
	
	@Override
	public String print(Person object, Locale locale) {
		
		
		return object.toString();
	}
}
