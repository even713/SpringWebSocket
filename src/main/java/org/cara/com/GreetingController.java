package org.cara.com;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetingController {
	
	@Autowired
    private SimpMessagingTemplate template;
	
//	@MessageMapping("/hello")
//	@SendTo("/topic/greetings")
//	public Greeting greeting(HelloMessage message) throws InterruptedException {
//		Thread.sleep(1000); //simulated delay
//		return new Greeting("Hello, " + message.getName());
//	}
	
	@MessageMapping("/hello")
	@SendToUser(value = "/topic/greetings", broadcast = false)
	public Greeting greeting(HelloMessage message, Principal principal) throws InterruptedException {
		Thread.sleep(1000); //simulated delay
		return new Greeting("Hello, " + message.getName());
	}	
	
	@RequestMapping(value = "/simMessage")
    public String greeting() throws Exception {
        template.convertAndSend("/topic/greetings",
                new Greeting("Hello, Other!"));
        return "sample";
    }
}
