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

    public Response addActionitemPOSTCall(ActiontrackerParameters actionitemParameters, JSONObject fullObject) {

        RestAssured.baseURI = actionitemParameters.getBaseURI();
        return given()
                .contentType("application/json")
                .body(fullObject)
                .expect().statusCode(201)
                .when()
                .post("/actionitems");
    }

    public Response getSingleActionitemGETCall(ActiontrackerParameters actionitemParameters) {

        RestAssured.baseURI = actionitemParameters.getBaseURI();
        return given()
                .pathParam("actionitemId", actionitemParameters.getActionitemId())
                .expect().statusCode(200)
                .when()
                .get("/actionitems/{actionitemId}");
    }

    public Response updateSingleActionitemPUTCall(ActiontrackerParameters actionitemParameters, JSONObject fullObject) {

        RestAssured.baseURI = actionitemParameters.getBaseURI();
        Response response =
                given()
                        .contentType("application/json")
                        .body(fullObject)
                        .pathParam("actionitemId", actionitemParameters.getActionitemId())
                        .expect().statusCode(200)
                        .when()
                        .put("/actionitems/{actionitemId}");
        response.body().prettyPeek();
        return response;
    }

    public Response deleteSingleActionitemDELETECall(ActiontrackerParameters actionitemParameters, int statusCode) {

        RestAssured.baseURI = actionitemParameters.getBaseURI();
        Response response = given()
                .pathParam("actionitemId", actionitemParameters.getActionitemId())
                .expect().statusCode(statusCode)
                .when()
                .delete("/actionitems/{actionitemId}");
        return response;
    }

    public int computeNumberOfItemsInResponse(Response response, String parameter) {

        return response.jsonPath().getInt(parameter);
    }

    public String returnStringValueInResponse(Response response, String parameter) {

        return response.jsonPath().getString(parameter);
    }
}
