package com.diceTech.assessment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diceTech.assessment.service.TwitterService;;

@RestController
public class Controller {

	@Autowired
	TwitterService twitterService;

	@GetMapping(value = "get-user/{name}/")
	public ResponseEntity<String> getUsers(@PathVariable("name") String usernames, @RequestParam String bearerToken) {
		try {
			String response = twitterService.getUsers(usernames, bearerToken);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "get-tweet/{id}/")
	public ResponseEntity<String> getTweets(@PathVariable("id") String userId, @RequestParam String bearerToken) {
		try {
			String response = twitterService.getTweets(userId, bearerToken);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}
