package servlet;

import http.request.HttpRequest;
import http.response.HttpResponse;
import registry.ServletRegistry;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class DispatcherServlet {

	public void dispatch(final HttpRequest httpRequest, final HttpResponse httpResponse) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		String path = httpRequest.getUri().getPath();

		HttpServlet httpServlet = ServletRegistry.getHttpServlet(path).getDeclaredConstructor().newInstance();
		httpServlet.service(httpRequest, httpResponse);
	}
}
