package api.specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.internal.RestAssuredResponseOptionsImpl;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.RequestSpecification;

import static dataread.DataRead.*;

public class Specifications {

    public static RequestSpecification requestSpecForToken = new RequestSpecBuilder().setBaseUri(configDataApiDTO.baseUri + configDataApiDTO.apiPath + configDataApiDTO.tokenRequestsPath).build();

    public static RequestSpecification requestSpecForTests = new RequestSpecBuilder().setBaseUri(configDataApiDTO.baseUri + configDataApiDTO.apiPath + configDataApiDTO.tokenRequestsPath).build();

    public static final Filter FORCE_JSON_RESPONSE_BODY;

    static {
        FORCE_JSON_RESPONSE_BODY = new Filter() {
            @Override
            public Response filter(FilterableRequestSpecification reqSpec, FilterableResponseSpecification respSpec, FilterContext ctx) {
                Response response = ctx.next(reqSpec, respSpec);
                ((RestAssuredResponseOptionsImpl<?>) response).setContentType(configDataApiDTO.contentTypeJson);
                return response;
            }
        };
    }
}
