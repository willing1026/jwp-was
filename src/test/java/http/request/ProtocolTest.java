package http.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProtocolTest {
	@Test
	void create() {
		Protocol protocol = new Protocol("HTTP", "1.1");
		assertThat(protocol).isEqualTo(new Protocol("HTTP", "1.1"));
	}

	@Test
	void invalid() {
		assertThatThrownBy(() -> new Protocol("HTTP", null))
			.isInstanceOf(IllegalArgumentException.class);
	}
}