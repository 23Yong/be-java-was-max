package http.request;

import http.HttpHeaders;
import http.HttpUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;

public final class HttpRequestBuilder {

	private static final String DELIMITER = " ";
	private static final String QUERY_STRING_DELIMITER = "\\?";

	private HttpRequestBuilder() {
	}

	public static HttpRequest build(final BufferedReader br) throws IOException {
		String startLine = br.readLine();
		RequestLine requestLine = parseStartLine(startLine);
		HttpHeaders httpHeaders = parseHeaders(br);

		QueryParameter queryParameter = parseQueryParameter(startLine.split(DELIMITER)[1]);

		return new HttpRequest(requestLine, queryParameter, httpHeaders);
	}

	private static RequestLine parseStartLine(final String startLine) {
		String[] tokens = startLine.split(DELIMITER);
		return new RequestLine(tokens[0], URI.create(tokens[1].split(QUERY_STRING_DELIMITER)[0]), tokens[2]);
	}

	private static QueryParameter parseQueryParameter(final String uri) {
		QueryParameter queryParameter = new QueryParameter();
		if (!uri.contains("?")) {
			return queryParameter;
		}
		try {
			queryParameter.addAllParameter(HttpUtils.parseQueryString(uri.split(QUERY_STRING_DELIMITER)[1]));
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new RuntimeException("올바른 쿼리파라미터가 존재하지 않습니다.", e);
		}
		return queryParameter;
	}

	private static HttpHeaders parseHeaders(final BufferedReader br) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		String headerLine;

		while (!(headerLine = br.readLine()).isEmpty()) {
			headers.addHeader(HttpUtils.parseHeader(headerLine));
		}

		return headers;
	}
}
