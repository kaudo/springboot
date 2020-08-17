package com.kaudo.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

	@GetMapping("/test")
	public Map<String, Object> getTest() {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("domain","https://spring.kaudo.com");
		resultMap.put("message","안녕하세요.");
		resultMap.put("domain2","https://github.com/kaudo/springboot");
		return resultMap;
	}

	@GetMapping("/sample")
	public String sample() {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("a",1);
		resultMap.put("b",2);
		resultMap.put("c",3);
		return "sample";
	}

}