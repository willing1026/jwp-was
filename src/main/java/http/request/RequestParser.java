package http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RequestParser {
	public static RequestHeader parseRequestHeader(InputStream is) throws IOException {
		List<String> values = new ArrayList<>();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line = "";

		while (true) {
			line = br.readLine();
			if (line == null || line.equals("")) {
				break;
			}

			values.add(line);
		}

		return new RequestHeader(values);
	}
}
