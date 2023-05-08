package http.request;

import java.net.URI;

import http.HttpHeaders;

public class HttpRequest {

	private final RequestLine requestLine;
	private final HttpHeaders httpHeaders;

	public HttpRequest(final RequestLine requestLine, final HttpHeaders httpHeaders) {
		this.requestLine = requestLine;
		this.httpHeaders = httpHeaders;
	}

	public URI getUri() {
		return requestLine.getUri();
	}

	@Override
	public String toString() {
		return "\n[HTTP Request]\n"
			+ requestLine
			+ "\n"
			+ httpHeaders;
	}
}
