package com.kaudo.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.model.IModelVisitor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

	@RequestMapping(value="/")
	public ModelAndView index(HttpServletResponse response,HttpServletRequest request){
		ModelAndView model=new ModelAndView("index");
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("domain","https://spring.kaudo.com");
		resultMap.put("message","안녕하세요.");
		resultMap.put("domain2","https://github.com/kaudo/springboot");

		model.addObject("result",resultMap);
		return model;
	}

	@RequestMapping(value="/jsptest")
	public String jsptest() {
		return "jsptest";
	}

}