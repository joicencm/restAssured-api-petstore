package api.base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseService {

    protected RequestSpecification request() {
        return RestAssured
                .given()
                .contentType("application/json")
                .accept("application/json");
    }
}
