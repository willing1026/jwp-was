package http;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestLineParserTest {
	@Test
	void parseGetMethod() {
		RequestLine requestLine = RequestLineParser.parseRequestLine("GET /users HTTP/1.1");
		assertThat(requestLine.getMethod()).isEqualTo(HttpMethod.GET.toString());
		assertThat(requestLine.getPath()).isEqualTo("/users");
		assertThat(requestLine.getProtocol()).isEqualTo("HTTP");
		assertThat(requestLine.getVersion()).isEqualTo("1.1");
	}

	@Test
	void parseGetMethodWithQueryString() {
		RequestLine requestLine = RequestLineParser.parseRequestLine("GET /users?userId=javajigi&password=password&name=JaeSung HTTP/1.1");
		assertThat(requestLine.getMethod()).isEqualTo(HttpMethod.GET.toString());
		assertThat(requestLine.getPath()).isEqualTo("/users");
		assertThat(requestLine.getProtocol()).isEqualTo("HTTP");
		assertThat(requestLine.getVersion()).isEqualTo("1.1");
		assertThat(requestLine.getQueryParams()).isEqualTo(new QueryString("userId=javajigi&password=password&name=JaeSung"));
	}

	@Test
	void parsePostMethod() {
		RequestLine requestLine = RequestLineParser.parseRequestLine("POST /users HTTP/1.1");
		assertThat(requestLine.getMethod()).isEqualTo(HttpMethod.POST.toString());
		assertThat(requestLine.getPath()).isEqualTo("/users");
		assertThat(requestLine.getProtocol()).isEqualTo("HTTP");
		assertThat(requestLine.getVersion()).isEqualTo("1.1");
	}

	@Test
	void parseProtocol() {
		Protocol protocol = RequestLineParser.parseProtocol("HTTP/1.1");
		assertThat(protocol).isEqualTo(new Protocol("HTTP", "1.1"));
	}
}
