package org.macchinetta.reqman.domain.request;

import java.net.URI;
import java.util.Collection;

import org.macchinetta.reqman.domain.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class VoteService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${reqman.server.baseUri}/votes/")
	String baseUrl;

	public Collection<Vote> getVotesByRequestId(long id){
		ParameterizedTypeReference<Resources<Vote>> resultType = new ParameterizedTypeReference<Resources<Vote>>(){};
		URI targetUri = UriComponentsBuilder.fromUriString(baseUrl)
				.pathSegment("search")
				.path("request")
				.queryParam("id", id)
				.build()
				.toUri();
		ResponseEntity<Resources<Vote>> result = restTemplate.exchange(targetUri, HttpMethod.GET, null, resultType);
		return result.getBody().getContent();
	}

}
