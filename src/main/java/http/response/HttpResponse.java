package http.response;

import http.HttpHeaders;

import java.util.List;
import java.util.Map;

public class HttpResponse {

	private StatusLine statusLine;
	private HttpHeaders httpHeaders;
	private byte[] body;

	public HttpResponse() {
		this.httpHeaders = new HttpHeaders();
		this.body = new byte[1];
	}

	public StatusLine getResponseLine() {
		return statusLine;
	}

	public void setResponseLine(StatusLine statusLine) {
		this.statusLine = statusLine;
	}

	public HttpHeaders getHttpHeaders() {
		return httpHeaders;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		httpHeaders.addHeader(Map.of("Content-Length", List.of(String.valueOf(body.length))));
		this.body = body;
	}
}
