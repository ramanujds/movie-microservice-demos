package com.capg.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/user/demo")
	public String helloUser(){
		return "Hello User";
	}
	@GetMapping("/admin/demo")
	public String helloAdmin() {
		return "Hello Admin";
	}
	@GetMapping("/")
	public String helloAll() {
		return "Hello World";
	}
}
