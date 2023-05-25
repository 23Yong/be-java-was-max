package http.response;

public enum ContentType {
	HTML("text/html;charset=utf-8", ".*\\.html"),
	CSS("text/css;charset=utf-8", ".*\\.css"),
	JS("application/javascript", ".*\\.js"),
	FONTS("application/octet-stream", ".*\\.woff$|.*\\.woff2$.*\\.woff3$"),
	PNG("image/png", ".*\\.png"),
	ICO("image/avif", ".*\\.ico");

	private final String value;
	private final String regex;

	ContentType(String value, String regex) {
		this.value = value;
		this.regex = regex;
	}

	public static ContentType findContentType(final String viewName) {
		for (ContentType contentType : ContentType.values()) {
			if (viewName.matches(contentType.getRegex())) {
				return contentType;
			}
		}
		throw new RuntimeException("[" + viewName + "] 지원하지 않는 Content-Type 입니다.");
	}

	public String getValue() {
		return value;
	}

	public String getRegex() {
		return regex;
	}
}
