package http.request;

import java.util.Objects;
import java.util.regex.Pattern;

public class RequestLine {
	private String method;
	private String path;
	private Protocol protocol;
	private QueryString queryString;

	public RequestLine(String requestLine) {
		String[] values = requestLine.split(" ");
		this.method = values[0];
		this.protocol = parseProtocol(values[2]);
		parsePathAndQueryString(values[1]);
	}

	public RequestLine(String method, String pathAndQueryString, Protocol protocol) {
		this.method = method;
		this.protocol = protocol;
		parsePathAndQueryString(pathAndQueryString);
	}

	private Protocol parseProtocol(String protocolAndVersion) {
		String[] values = protocolAndVersion.split("/");
		return new Protocol(values[0], values[1]);
	}

	private void parsePathAndQueryString(String pathAndQueryString) {
		boolean isQueryStringExist = Pattern.matches("([/][a-z,A-Z,0-9]+[\\?].+)", pathAndQueryString);
		if (isQueryStringExist) {
			String[] values = pathAndQueryString.split("\\?");
			this.path = values[0];
			this.queryString = new QueryString(values[1]);
			return;
		}

		this.path = pathAndQueryString;
	}

	public String getMethod() {
		return method;
	}

	public String getPath() {
		return path;
	}

	public String getProtocol() {
		return protocol.getProtocol();
	}

	public String getVersion() {
		return protocol.getVersion();
	}

	public QueryString getQueryParams() {
		return queryString;
	}

	public String getTemplatePath() {
		String path = this.path.endsWith(".html") ? this.path : this.path + ".html";
		return "./templates" + path;
	}

	@Override
	public String toString() {
		return "RequestLine{" +
				"method='" + method + '\'' +
				", path='" + path + '\'' +
				", protocol=" + protocol +
				", queryString=" + queryString +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RequestLine that = (RequestLine) o;
		return Objects.equals(method, that.method) &&
				Objects.equals(path, that.path) &&
				Objects.equals(protocol, that.protocol) &&
				Objects.equals(queryString, that.queryString);
	}

	@Override
	public int hashCode() {
		return Objects.hash(method, path, protocol, queryString);
	}
}
