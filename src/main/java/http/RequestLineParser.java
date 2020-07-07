package http;

public class RequestLineParser {
	public static RequestLine parseRequestLine(String requestLine) {
		String[] values = requestLine.split(" ");
		Protocol protocol = parseProtocol(values[2]);
		return new RequestLine(values[0], values[1], protocol);
	}

	public static Protocol parseProtocol(String protocolAndVersion) {
		String[] values = protocolAndVersion.split("/");
		return new Protocol(values[0], values[1]);
	}
}
