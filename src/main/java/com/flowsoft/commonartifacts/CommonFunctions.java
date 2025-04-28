package com.flowsoft.commonartifacts;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.java.Log;
import org.json.simple.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

@Log
public class CommonFunctions {

    static ActiontrackerParameters actionitemParameters = new ActiontrackerParameters();

    public Response getAllActionitemsGETCall(ActiontrackerParameters actionitemParameters) {

        RestAssured.baseURI = actionitemParameters.getBaseURI();
        return given()
                .expect().statusCode(200)
                .when()
                .get("/actionitems");
    }

    public int computeNumberOfItemsInResponse(Response response, String parameter) {

        return response.jsonPath().getInt(parameter);
    }
}
