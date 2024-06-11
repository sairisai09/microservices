package com.eureka;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value="Welcome-api")
public interface WelFeignClient {

	@GetMapping("/welcome")
	public String  getMsg();
}
