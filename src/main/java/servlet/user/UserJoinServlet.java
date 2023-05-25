package servlet.user;

import annotation.WebServlet;
import db.Database;
import http.HttpUtils;
import http.request.HttpRequest;
import http.response.HttpResponse;
import model.User;
import servlet.HttpServlet;

import java.io.IOException;
import java.util.Map;

@WebServlet(url = "/user/create")
public class UserJoinServlet extends HttpServlet {

	@Override
	protected void doGet(HttpRequest request, HttpResponse response) throws IOException {
		viewResolver.resolve("/user/form.html", response);
	}

	@Override
	protected void doPost(HttpRequest request, HttpResponse response) throws IOException {
		Map<String, String> joinInfo = HttpUtils.parseQueryString(request.getBody());

		User user = new User(joinInfo.get("userId"), joinInfo.get("password"), joinInfo.get("name"), joinInfo.get("email"));

		Database.addUser(user);

		viewResolver.resolve("redirect:/", response);
	}
}
