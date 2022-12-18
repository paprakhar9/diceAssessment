package com.diceTech.assessment.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;

@Service
public interface TwitterService {

	/**
	 * Service to find user on twitter
	 * 
	 * @param usernames
	 * @param bearerToken
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	String getUsers(String usernames, String bearerToken) throws IOException, URISyntaxException;

	/**
	 * Service to find user tweets
	 * 
	 * @param userId
	 * @param bearerToken
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	String getTweets(String userId, String bearerToken) throws IOException, URISyntaxException;

}
