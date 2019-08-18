package webserver.http.response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpResponseTest {
    private static final Logger logger = LoggerFactory.getLogger(HttpResponse.class);

    @DisplayName("302 Found with Cookie")
    @Test
    void response() {
        String filePath = "/index.html";
        String cookie = "Set-Cookie: logined=true; Path=/";

        HttpResponse httpResponse = new HttpResponse().found(filePath, cookie);

        logger.debug(httpResponse.getResponseHeader());
    }
 }
