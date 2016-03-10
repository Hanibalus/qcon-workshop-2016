package com.example;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@SpringBootApplication
@EnableBinding(Sink.class)
public class GreetingsApplication {
	
	@Bean
	public InitializingBean initBinding(Sink sink) {
		return () -> {
			sink.input().subscribe(message -> {
				Greeting greeting = (Greeting) message.getPayload();
				repository.save(greeting);
			});
		};
	}

	@Autowired
	private GreetingRepository repository;

	@PostConstruct
	public void init() {
		if (repository.count() == 0) {
			repository.save(new Greeting("Hello JPA"));
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(GreetingsApplication.class, args);
	}
}

@RepositoryRestResource
interface GreetingRepository extends JpaRepository<Greeting, Long> {
}

@Entity
class Greeting {

	@Id
	@GeneratedValue
	private Long id;

	private String msg;

	@SuppressWarnings("unused")
	private Greeting() {
	}

	public Greeting(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Greeting [msg=" + msg + "]";
	}

}
