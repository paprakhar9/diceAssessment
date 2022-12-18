package com.diceTech.assessment.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.omg.CORBA.NameValuePair;
import org.springframework.stereotype.Service;

import com.diceTech.assessment.service.TwitterService;

@Service
public class TwitterServiceImpl implements TwitterService {

	public String getUsers(String usernames, String bearerToken) throws IOException, URISyntaxException {
		String userResponse = null;

		try {
			HttpClient httpClient = HttpClients.custom()
					.setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
					.build();

			URIBuilder uriBuilder = new URIBuilder("https://api.twitter.com/2/users/by");
			ArrayList<BasicNameValuePair> queryParameters;
			queryParameters = new ArrayList();
			queryParameters.add(new BasicNameValuePair("usernames", usernames));
			queryParameters.add(new BasicNameValuePair("user.fields", "created_at,description,pinned_tweet_id"));
			uriBuilder.addParameters(queryParameters);

			HttpGet httpGet = new HttpGet(uriBuilder.build());
			((HttpMessage) httpGet).setHeader("Authorization", String.format("Bearer %s", bearerToken));
			((HttpMessage) httpGet).setHeader("Content-Type", "application/json");

			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (null != entity) {
				userResponse = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return userResponse;
	}

	public String getTweets(String userId, String bearerToken) throws IOException, URISyntaxException {
		String tweetResponse = null;

		try {
			HttpClient httpClient = HttpClients.custom()
					.setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
					.build();

			URIBuilder uriBuilder = new URIBuilder(String.format("https://api.twitter.com/2/users/%s/tweets", userId));
			ArrayList<BasicNameValuePair> queryParameters;
			queryParameters = new ArrayList();
			queryParameters.add(new BasicNameValuePair("tweet.fields", "created_at"));
			uriBuilder.addParameters(queryParameters);

			HttpGet httpGet = new HttpGet(uriBuilder.build());
			httpGet.setHeader("Authorization", String.format("Bearer %s", bearerToken));
			httpGet.setHeader("Content-Type", "application/json");

			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (null != entity) {
				tweetResponse = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return tweetResponse;
	}

}
