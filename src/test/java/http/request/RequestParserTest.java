package http.request;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestParserTest {
	@Test
	void parseHeader() throws IOException {
		//given
		String data = "GET /index.html HTTP/1.1\n" +
				"Host: localhost:8080\n" +
				"Connection: keep-alive\n" +
				"Accept: */*";
		InputStream is = new ByteArrayInputStream(data.getBytes());

		//when
		RequestHeader requestHeader = RequestParser.parseRequestHeader(is);

		//then
		assertThat(requestHeader.getRequestLine()).isNotNull();
		assertThat(requestHeader.getHost()).isEqualTo("localhost:8080");
		assertThat(requestHeader.getConnection()).isEqualTo("keep-alive");
		assertThat(requestHeader.getAccept()).isEqualTo("*/*");
	}
}
