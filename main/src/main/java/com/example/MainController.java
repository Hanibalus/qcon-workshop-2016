/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Dave Syer
 *
 */
@RestController
@RefreshScope
public class MainController {

	@Autowired
	private MainProperties main;

	@Autowired
	private RestTemplate restTemplate;

	private String uri;

	@PostConstruct
	public void init() {
		uri = main.getUri();
	}

	@RequestMapping("/greeting")
	public Greeting greeting() {
		return restTemplate.getForObject(uri + "/greetings/1", Greeting.class);
	}

}
