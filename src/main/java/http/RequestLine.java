package http;

import java.util.Map;
import java.util.regex.Pattern;

public class RequestLine {
	private String method;
	private String path;
	private Protocol protocol;
	private QueryString queryString;

	public RequestLine(String method, String pathAndQueryString, Protocol protocol) {
		this.method = method;
		this.protocol = protocol;
		parsePathAndQueryString(pathAndQueryString);
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
}
