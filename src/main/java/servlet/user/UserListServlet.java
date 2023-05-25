package servlet.user;

import annotation.WebServlet;
import http.request.HttpRequest;
import http.response.HttpResponse;
import servlet.HttpServlet;

import java.io.IOException;

@WebServlet(url = "/user/list")
public class UserListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpRequest request, HttpResponse response) throws IOException {
		String sessionId = request.getSessionId();
		if (sessionId.isEmpty()) {
			viewResolver.resolve("redirect:/user/login", response);
			return;
		}

		viewResolver.resolve("/user/list.html", response);
	}
}
