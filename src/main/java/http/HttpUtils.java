package http;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class HttpUtils {

	private HttpUtils() {
	}

	public static Map<String, List<String>> parseHeader(final String headerLine) {
		String[] tokens = Arrays.stream(headerLine.split(":"))
				.map(String::trim)
				.toArray(String[]::new);
		String name = tokens[0];
		List<String> values = Arrays.stream(tokens[1].split(","))
				.map(String::trim)
				.collect(Collectors.toUnmodifiableList());

		return Map.of(name, values);
	}

	public static Map<String, String> parseQueryString(final String queryString) {
		Map<String, String> queryParameter = new HashMap<>();
		for (String entry : queryString.split("&")) {
			String[] tokens = entry.split("=");
			queryParameter.put(tokens[0], tokens[1]);
		}
		return queryParameter;
	}
}
