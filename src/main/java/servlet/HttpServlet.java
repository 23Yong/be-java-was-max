package servlet;

import http.HttpMethod;
import http.request.HttpRequest;
import http.response.HttpResponse;
import view.ViewResolver;

import java.io.IOException;

public class HttpServlet implements Servlet {

	protected static final ViewResolver viewResolver = new ViewResolver();

	@Override
	public void service(HttpRequest request, HttpResponse response) throws IOException {
		HttpMethod httpMethod = request.getHttpMethod();

		if (httpMethod == HttpMethod.GET) {
			doGet(request, response);
		} else if (httpMethod == HttpMethod.POST) {
			doPost(request, response);
		}
	}

	protected void doGet(HttpRequest request, HttpResponse response) throws IOException {
		viewResolver.resolve(request.getUri().getPath(), response);
	}

	protected void doPost(HttpRequest request, HttpResponse response) throws IOException {
	}
}
