package com.scaler.productService.utility;

import io.micrometer.common.lang.Nullable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * The Class HttpUtil.
 */
public class HttpUtil {

	/**
	 * Request for entity.
	 *
	 * @param <T>          the generic type
	 * @param restTemplate the rest template
	 * @param httpMethod   the http method
	 * @param url          the url
	 * @param request      the request
	 * @param responseType the response type
	 * @param uriVariables the uri variables
	 * @return the response entity
	 * @throws RestClientException the rest client exception
	 */
	public static <T> ResponseEntity<T> requestForEntity(RestTemplateBuilder restTemplate, HttpMethod httpMethod,
			String url, @Nullable Object request, Class<T> responseType, Object... uriVariables)
			throws RestClientException {

		RestTemplate t = restTemplate.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();

		RequestCallback requestCallback = t.httpEntityCallback(request, responseType);
		ResponseExtractor<ResponseEntity<T>> responseExtractor = t.responseEntityExtractor(responseType);

		return t.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
	}

}
