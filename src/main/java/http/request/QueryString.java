package http.request;

import com.github.jknack.handlebars.internal.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class QueryString {
	private static final String PARAM_DELIMITER = "&";
	private static final String KEY_VALUE_DELIMITER = "=";
	private Map<String, String> params;

	public QueryString(String queryString) {
		validate(queryString);
		this.params = new HashMap<>();
		splitParams(queryString);
	}

	private void validate(String queryString) {
		if (StringUtils.isBlank(queryString)) {
			throw new IllegalArgumentException();
		}

		boolean paramsPattern = Pattern.matches("([a-z,A-Z,0-9]+=[a-z,A-Z,0-9]+)+(&[a-z,A-Z,0-9]+=[a-z,A-Z,0-9]+)*", queryString);
		if (!paramsPattern) {
			throw new IllegalArgumentException();
		}
	}

	private void splitParams(String queryString) {
		String[] keysAndValues = queryString.split(PARAM_DELIMITER);
		splitParam(keysAndValues);
	}

	private void splitParam(String[] keysAndValues) {
		for (String keyAndValue : keysAndValues) {
			addParam(keyAndValue);
		}
	}

	private void addParam(String keyAndValue) {
		String[] result = keyAndValue.split(KEY_VALUE_DELIMITER);
		this.params.put(result[0], result[1]);
	}

	public int size() {
		return params.size();
	}

	public String valueOf (String key) {
		return params.get(key);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		QueryString that = (QueryString) o;
		return Objects.equals(params, that.params);
	}

	@Override
	public int hashCode() {
		return Objects.hash(params);
	}
}
