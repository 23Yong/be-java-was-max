package registry;

import annotation.WebServlet;
import com.google.common.reflect.ClassPath;
import servlet.HttpServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ServletRegistry {

	private static final Map<String, Class<? extends HttpServlet>> handlers = new HashMap<>();
	private static final String PACKAGE_NAME = "servlet.*";

	static {
		registerServlets(findAllClassesUsingClassLoader());
	}

	private static void registerServlets(final Set<Class<? extends HttpServlet>> handlerClasses) {
		handlerClasses.stream()
				.filter(clazz -> clazz.isAnnotationPresent(WebServlet.class))
				.forEach(clazz -> handlers.put(clazz.getDeclaredAnnotation(WebServlet.class).url(), clazz));
	}

	private static Set<Class<? extends HttpServlet>> findAllClassesUsingClassLoader() {
		try {
			return ClassPath.from(ClassLoader.getSystemClassLoader())
					.getAllClasses()
					.stream()
					.filter(clazz -> clazz.getPackageName().matches(PACKAGE_NAME))
					.map(clazz -> (Class<? extends HttpServlet>) clazz.load())
					.collect(Collectors.toUnmodifiableSet());
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static Class<? extends HttpServlet> getHttpServlet(final String url) {
		return handlers.getOrDefault(url, HttpServlet.class);
	}
}
