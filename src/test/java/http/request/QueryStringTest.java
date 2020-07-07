package http.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QueryStringTest {
	@Test
	void create() {
		QueryString queryString = new QueryString("userId=javajigi&password=password&name=JaeSung");
		assertThat(queryString.size()).isEqualTo(3);
		assertThat(queryString.valueOf("userId")).isEqualTo("javajigi");
		assertThat(queryString.valueOf("password")).isEqualTo("password");
		assertThat(queryString.valueOf("name")).isEqualTo("JaeSung");
	}

	@ParameterizedTest
	@ValueSource(strings = {"", "id", "userId=&password=password", "userId==javajigi", "userId=javajigi&&password=password"})
	void invalid(String queryString) {
		assertThatThrownBy(() -> new QueryString(queryString))
				.isInstanceOf(IllegalArgumentException.class);
	}
}
