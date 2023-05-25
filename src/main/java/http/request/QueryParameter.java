package http.request;

import java.util.HashMap;
import java.util.Map;

public class QueryParameter {

	private final Map<String, String> queryParameter = new HashMap<>();

	public void addAllParameter(final Map<String, String> parameters) {
		queryParameter.putAll(parameters);
	}

	public Map<String, String> getQueryParameter() {
		return queryParameter;
	}

	@Override
	public String toString() {
		return queryParameter.toString();
	}
}
