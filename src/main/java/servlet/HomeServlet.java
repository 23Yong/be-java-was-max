package servlet;

import annotation.WebServlet;
import http.request.HttpRequest;
import http.response.HttpResponse;

import java.io.IOException;

@WebServlet(url = "/")
public class HomeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpRequest request, HttpResponse response) throws IOException {
		viewResolver.resolve("index.html", response);
	}
}
