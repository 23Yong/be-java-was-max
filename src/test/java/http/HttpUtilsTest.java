package http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class HttpUtilsTest {

	@DisplayName("HTTP 리퀘스트의 헤더 한 줄이 주어질 때 헤더 한 줄을 파싱하면 Map 형태의 헤더 키, 밸류를 리턴한다.")
	@Test
	void givenHeaderLine_whenParsingHeaderLine_thenReturnsHeaderMap() {
		// given
		String headerLine = "accept-encoding: gzip, deflate, br";

		// when
		Map<String, List<String>> headerMap = HttpUtils.parseHeader(headerLine);

		// then
		assertThat(headerMap.get("accept-encoding")).isEqualTo(List.of("gzip", "deflate", "br"));
	}
}
