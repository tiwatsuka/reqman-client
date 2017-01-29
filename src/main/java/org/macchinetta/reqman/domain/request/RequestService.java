package org.macchinetta.reqman.domain.request;

import java.net.URI;
import java.util.Collection;

import org.macchinetta.reqman.domain.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RequestService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${reqman.server.baseUri}/requests/")
	String baseUrl;

	public Collection<Request> getAllRequests(){
		ParameterizedTypeReference<Resources<Request>> resultType = new ParameterizedTypeReference<Resources<Request>>(){}; 
		ResponseEntity<Resources<Request>> result = restTemplate.exchange(baseUrl, HttpMethod.GET, null, resultType);
		return result.getBody().getContent();
	}

	public Request getRequest(long id){
		ParameterizedTypeReference<Resource<Request>> resultType = new ParameterizedTypeReference<Resource<Request>>(){};
		URI targetUri = UriComponentsBuilder.fromUriString(baseUrl)
				.path(Long.toString(id))
				.queryParam("projection", "requestProjection")
				.build()
				.toUri();
		ResponseEntity<Resource<Request>> result = restTemplate.exchange(targetUri, HttpMethod.GET, null, resultType);
		return result.getBody().getContent();
	}
}
