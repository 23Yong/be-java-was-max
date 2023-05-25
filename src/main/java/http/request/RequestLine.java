package http.request;

import http.HttpMethod;

import java.net.URI;

public class RequestLine {

	private final HttpMethod method;
	private final URI uri;
	private final String httpVersion;

	public RequestLine(String method, URI uri, String httpVersion) {
		this.method = HttpMethod.get(method);
		this.uri = uri;
		this.httpVersion = httpVersion;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public URI getUri() {
		return uri;
	}

	@Override
	public String toString() {
		return method.name()
				+ " "
				+ uri.toString()
				+ " "
				+ httpVersion;
	}
}
