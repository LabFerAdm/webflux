package br.com.home.controller;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
public class HomeController {

	static int i = 0;

	@GetMapping("mono")
	public Mono<String> get() {

		Mono<String> just = Mono.just("foo");

		return just;

	}

	@GetMapping(value = "flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, String>> getTeste() {

		i++;

		Flux<String> stringFlux = Flux.just("Hello: " + i, "foo: " + i, "bar: " + i);

		Flux<Long> intervalo = Flux.interval(Duration.ofSeconds(3));

		System.out.println("Eventos Rodando");
		return Flux.zip(intervalo, stringFlux);
	}

}
