package com.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Autowired
	private WelFeignClient wel;
	private String  greetmsg;
	
	@GetMapping("/greet")
	public String getMsg_Ms() {
		String welmsg = wel.getMsg();
		String  greetmsg = "Micro_Services_02";
		return greetmsg.concat(welmsg);
	}
}
