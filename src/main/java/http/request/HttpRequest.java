package http.request;

import http.HttpHeaders;
import http.HttpMethod;

import java.net.URI;
import java.util.Map;

public class HttpRequest {

	private final RequestLine requestLine;
	private final QueryParameter queryParameter;
	private final HttpHeaders httpHeaders;
	private final String body;

	public HttpRequest(final RequestLine requestLine, final QueryParameter queryParameter, final HttpHeaders httpHeaders, final String body) {
		this.requestLine = requestLine;
		this.queryParameter = queryParameter;
		this.httpHeaders = httpHeaders;
		this.body = body;
	}

	public HttpMethod getHttpMethod() {
		return requestLine.getMethod();
	}

	public URI getUri() {
		return requestLine.getUri();
	}

	public Map<String, String> getQueryParameter() {
		return queryParameter.getQueryParameter();
	}

	public String getSessionId() {
		return httpHeaders.getSessionId();
	}

	public String getBody() {
		return body;
	}

	@Override
	public String toString() {
		return "\n[HTTP Request]\n"
				+ requestLine
				+ "\n"
				+ queryParameter
				+ "\n"
				+ httpHeaders;
	}
}
