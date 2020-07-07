package http.request;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestHeaderTest {
	@Test
	void create() {
		List<String> requestHeaderInfo = Arrays.asList("GET /index.html HTTP/1.1", "Host: localhost:8080", "Connection: keep-alive", "Accept: */*");
		RequestHeader requestHeader = new RequestHeader(requestHeaderInfo);
		assertThat(requestHeader.getRequestLine()).isEqualTo(new RequestLine("GET /index.html HTTP/1.1"));
		assertThat(requestHeader.getHost()).isEqualTo("localhost:8080");
		assertThat(requestHeader.getConnection()).isEqualTo("keep-alive");
		assertThat(requestHeader.getAccept()).isEqualTo("*/*");
	}

	@Test
	void getTemplatePath() {
		List<String> requestHeaderInfo = Arrays.asList("GET /index.html HTTP/1.1", "Host: localhost:8080", "Connection: keep-alive", "Accept: */*");
		RequestHeader requestHeader = new RequestHeader(requestHeaderInfo);
		assertThat(requestHeader.getTemplatePath()).isEqualTo("./templates/index.html");
	}
}
