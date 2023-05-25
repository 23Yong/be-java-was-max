package servlet.article;

import annotation.WebServlet;
import http.request.HttpRequest;
import http.response.HttpResponse;
import servlet.HttpServlet;

import java.io.IOException;

@WebServlet(url = "/articles/show")
public class ArticleDetailServlet extends HttpServlet {

	@Override
	protected void doGet(HttpRequest request, HttpResponse response) throws IOException {
		String sessionId = request.getSessionId();
		if (sessionId.isEmpty()) {
			viewResolver.resolve("redirect:/user/login", response);
			return;
		}
		viewResolver.resolve("qna/show.html", response);
	}
}
