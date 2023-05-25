package webserver;

import http.request.HttpRequest;
import http.request.HttpRequestBuilder;
import http.response.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlet.DispatcherServlet;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

public class RequestHandler implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);
	private static final DispatcherServlet dispatcherServlet = new DispatcherServlet();

	private final Socket connection;

	public RequestHandler(Socket connectionSocket) {
		this.connection = connectionSocket;
	}

	public void run() {
		logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
				connection.getPort());

		try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		     OutputStream out = connection.getOutputStream()) {
			HttpRequest httpRequest = HttpRequestBuilder.build(br);
			HttpResponse httpResponse = new HttpResponse();

			dispatcherServlet.dispatch(httpRequest, httpResponse);

			logger.debug(httpRequest.toString());

			DataOutputStream dos = new DataOutputStream(out);
			response(dos, httpResponse);
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
		         IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private void response(DataOutputStream dos, HttpResponse httpResponse) {
		try {
			dos.writeBytes(httpResponse.getResponseLine().toString() + " \r\n");
			dos.writeBytes(httpResponse.getHttpHeaders().toString() + " \r\n");
			dos.writeBytes("\r\n");
			dos.write(httpResponse.getBody(), 0, httpResponse.getBody().length);
			dos.flush();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}
