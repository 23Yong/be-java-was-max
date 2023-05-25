package servlet;

import http.request.HttpRequest;
import http.response.HttpResponse;

import java.io.IOException;

public interface Servlet {

	void service(HttpRequest request, HttpResponse response) throws IOException;
}
