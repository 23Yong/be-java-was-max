package view;

import http.response.ContentType;
import http.response.HttpResponse;
import http.response.StatusLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

public class ViewResolver {

	private static final String CLASS_PATH = "src/main/resources/";
	private static final String TEMPLATE_PATH = CLASS_PATH + "templates/";
	private static final String STATIC_PATH = CLASS_PATH + "static/";
	private static final String REDIRECT_VIEW = "redirect:";

	public void resolve(final String viewName, final HttpResponse httpResponse) throws IOException {
		if (viewName.startsWith(REDIRECT_VIEW)) {
			redirect(viewName.replaceAll(REDIRECT_VIEW, ""), httpResponse);
			return;
		}
		forward(viewName, httpResponse);
	}

	private void redirect(final String viewName, final HttpResponse httpResponse) {
		httpResponse.setResponseLine(new StatusLine("HTTP/1.1", 302, "FOUND"));
		httpResponse.getHttpHeaders().addHeader(Map.of("Location", List.of(viewName)));
	}

	private void forward(final String viewName, final HttpResponse httpResponse) throws IOException {
		httpResponse.setResponseLine(new StatusLine("HTTP/1.1", 200, "OK"));
		httpResponse.getHttpHeaders().addHeader(Map.of("Content-Type", List.of(ContentType.findContentType(viewName).getValue())));

		if (isDynamicRequest(viewName)) {
			httpResponse.setBody(Files.readAllBytes(new File(TEMPLATE_PATH + viewName).toPath()));
			return;
		}
		if (isStaticRequest(viewName)) {
			httpResponse.setBody(Files.readAllBytes(new File(STATIC_PATH + viewName).toPath()));
			return;
		}
		throw new FileNotFoundException(viewName + " 파일이 존재하지 않습니다.");
	}

	private boolean isStaticRequest(final String viewName) {
		return new File(STATIC_PATH + viewName).exists();
	}

	private boolean isDynamicRequest(final String viewName) {
		return new File(TEMPLATE_PATH + viewName).exists();
	}
}
