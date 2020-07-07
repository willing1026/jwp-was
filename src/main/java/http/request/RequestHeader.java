package http.request;

import java.util.List;

public class RequestHeader {

	private RequestLine requestLine;
	private String host;
	private String connection;
	private String accept;

	public RequestHeader(List<String> requestHeaderInfo) {
		this.requestLine = new RequestLine(requestHeaderInfo.get(0));
		this.host = parse(requestHeaderInfo.get(1), " ");
		this.connection = parse(requestHeaderInfo.get(2), " ");
		this.accept = parse(requestHeaderInfo.get(3), " ");
	}

	private String parse(String value, String regex) {
		String[] values = value.split(regex);
		return values[1];
	}

	public RequestLine getRequestLine() {
		return this.requestLine;
	}

	public String getHost() {
		return this.host;
	}

	public String getConnection() {
		return this.connection;
	}

	public String getAccept() {
		return this.accept;
	}

	public String getTemplatePath() {
		return this.requestLine.getTemplatePath();
	}

	@Override
	public String toString() {
		return "RequestHeader{" +
				"requestLine=" + requestLine +
				", host='" + host + '\'' +
				", connection='" + connection + '\'' +
				", accept='" + accept + '\'' +
				'}';
	}
}
