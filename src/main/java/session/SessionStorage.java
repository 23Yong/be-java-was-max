package session;

import com.google.common.collect.Maps;

import java.util.Map;

public final class SessionStorage {

	private static Map<String, String> sessionStorage = Maps.newHashMap();

	private SessionStorage() {
	}

	public static String addSessionUserId(final String userId) {
		String session = SessionGenerator.generate();
		sessionStorage.put(session, userId);
		return session;
	}
}
