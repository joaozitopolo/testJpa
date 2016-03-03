/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localhost.testjpa;

import localhost.testjpa.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class TestJPA {
	
	public static void main(String[] args) {
		SpringApplication.run(TestJPA.class, args);
	}
	
}


@Component
class StartServiceRunner implements ApplicationListener<ContextRefreshedEvent> {

	boolean executed;

	@Autowired
	AppService appService;

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		if (!executed) {
			executed = true;
			appService.run();
		}
	}

}
