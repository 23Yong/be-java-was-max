package http;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class HttpHeaders {

	private final TreeMap<String, List<String>> headersMap;

	public HttpHeaders() {
		this.headersMap = new TreeMap<>();
	}

	public void addHeader(final Map<String, List<String>> header) {
		headersMap.putAll(header);
	}

	public int getContentLength() {
		return Integer.parseInt(headersMap.getOrDefault("Content-Length", List.of("0")).get(0));
	}

	public List<String> allValues(final String name) {
		List<String> values = headersMap.get(name);

		return values != null ? values : List.of();
	}

	public String getSessionId() {
		String cookie = headersMap.getOrDefault("Cookie", List.of("")).get(0);
		if (cookie.isEmpty()) {
			return "";
		}
		return Arrays.stream(cookie.split(";"))
				.map(c -> c.toLowerCase().trim())
				.filter(c -> c.contains("sid"))
				.map(c -> c.split("=")[1])
				.collect(Collectors.joining());
	}

	@Override
	public String toString() {
		return headersMap.entrySet()
				.stream()
				.map(entry -> entry.getKey() + ": " + String.join(", ", entry.getValue()))
				.collect(Collectors.joining("\n"));
	}
}
