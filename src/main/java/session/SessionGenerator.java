package session;

import java.util.UUID;

public final class SessionGenerator {

	private SessionGenerator() {
	}

	public static String generate() {
		return UUID.randomUUID().toString();
	}
}
