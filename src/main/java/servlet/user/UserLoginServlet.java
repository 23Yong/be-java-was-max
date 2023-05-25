package servlet.user;

import annotation.WebServlet;
import db.Database;
import http.HttpHeaders;
import http.HttpUtils;
import http.request.HttpRequest;
import http.response.HttpResponse;
import model.User;
import servlet.HttpServlet;
import session.SessionStorage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(url = "/user/login")
public class UserLoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpRequest request, HttpResponse response) throws IOException {
		viewResolver.resolve("/user/login.html", response);
	}

	@Override
	protected void doPost(HttpRequest request, HttpResponse response) throws IOException {
		Map<String, String> loginInfo = HttpUtils.parseQueryString(request.getBody());

		String userId = loginInfo.get("userId");
		String password = loginInfo.get("password");

		User user = Database.findUserById(userId);
		if (user == null || !user.isSamePassword(password)) {
			viewResolver.resolve("user/login_failed.html", response);
			return;
		}

		String session = SessionStorage.addSessionUserId(userId);
		HttpHeaders httpHeaders = response.getHttpHeaders();
		httpHeaders.addHeader(Map.of("Set-Cookie", List.of("SID=" + session + "; Path=/")));

		viewResolver.resolve("redirect:/", response);
	}
}
