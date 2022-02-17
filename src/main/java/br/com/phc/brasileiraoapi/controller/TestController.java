package br.com.phc.brasileiraoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.phc.brasileiraoapi.event.MyCustomApplicationEvent;

@RestController
@RequestMapping("/api/v1/testes")
public class TestController {
	
	@Autowired
	ApplicationEventPublisher publisher;
	
	@GetMapping(value = "/publish")	
	public String publish() {
		System.out.println("TestController : publishing the message");

		MyCustomApplicationEvent event = new MyCustomApplicationEvent(this, "MyEvent");
		System.out.println("MyCustomApplicationEventPublisher : Source : "+this.getClass().getName() + ", Message : "+ event.getName());
		publisher.publishEvent(event);
				
		System.out.println("TestController : published the message");
		return event.getName();
	}

}
