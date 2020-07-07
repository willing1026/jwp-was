package http.request;

import com.github.jknack.handlebars.internal.lang3.StringUtils;

import java.util.Objects;

public class Protocol {

	private String protocol;
	private String version;

	public Protocol(String protocol, String version) {
		if (StringUtils.isBlank(protocol) || StringUtils.isBlank(version)) {
			throw new IllegalArgumentException("value is empty");
		}

		this.protocol = protocol;
		this.version = version;
	}

	public String getProtocol() {
		return this.protocol;
	}

	public String getVersion() {
		return this.version;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Protocol protocol1 = (Protocol) o;
		return Objects.equals(protocol, protocol1.protocol) &&
				Objects.equals(version, protocol1.version);
	}
}
