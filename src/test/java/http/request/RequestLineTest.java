package http.request;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;

import static org.assertj.core.api.Assertions.assertThat;

class RequestLineTest {
	@Test
	void parseGetMethod() {
		RequestLine requestLine = new RequestLine("GET /users HTTP/1.1");
		assertThat(requestLine.getMethod()).isEqualTo(HttpMethod.GET.toString());
		assertThat(requestLine.getPath()).isEqualTo("/users");
		assertThat(requestLine.getProtocol()).isEqualTo("HTTP");
		assertThat(requestLine.getVersion()).isEqualTo("1.1");
	}

	@Test
	void parseGetMethodWithQueryString() {
		RequestLine requestLine = new RequestLine("GET /users?userId=javajigi&password=password&name=JaeSung HTTP/1.1");
		assertThat(requestLine.getMethod()).isEqualTo(HttpMethod.GET.toString());
		assertThat(requestLine.getPath()).isEqualTo("/users");
		assertThat(requestLine.getProtocol()).isEqualTo("HTTP");
		assertThat(requestLine.getVersion()).isEqualTo("1.1");
		assertThat(requestLine.getQueryParams()).isEqualTo(new QueryString("userId=javajigi&password=password&name=JaeSung"));
	}

	@Test
	void parsePostMethod() {
		RequestLine requestLine = new RequestLine("POST /users HTTP/1.1");
		assertThat(requestLine.getMethod()).isEqualTo(HttpMethod.POST.toString());
		assertThat(requestLine.getPath()).isEqualTo("/users");
		assertThat(requestLine.getProtocol()).isEqualTo("HTTP");
		assertThat(requestLine.getVersion()).isEqualTo("1.1");
	}

	@Test
	void getTemplatePath() {
		RequestLine requestLine = new RequestLine("GET /index.html HTTP/1.1");
		assertThat(requestLine.getMethod()).isEqualTo(HttpMethod.GET.toString());
		assertThat(requestLine.getTemplatePath()).isEqualTo("./templates/index.html");
	}
}