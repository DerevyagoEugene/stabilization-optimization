package api.requests;

import static api.specifications.Specifications.*;
import static dataread.DataRead.*;
import static io.restassured.RestAssured.given;

public class TokenRequests {
    public static String getToken(String variant) {
        return given()
                .spec(requestSpecForToken)
                .queryParam(configDataApiDTO.variantParam, variant)
                .post(configDataApiDTO.getTokenInTokenRequests)
                .asString();
    }
}
