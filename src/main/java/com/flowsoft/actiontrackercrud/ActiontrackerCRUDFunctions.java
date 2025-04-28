package com.flowsoft.actiontrackercrud;

import com.flowsoft.commonartifacts.CommonFunctions;
import com.flowsoft.commonartifacts.ActiontrackerParameters;
import io.restassured.response.Response;
import lombok.extern.java.Log;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.testng.AssertJUnit.assertTrue;

@Log
public class ActiontrackerCRUDFunctions {

    static ActiontrackerParameters actionitemParameters = new ActiontrackerParameters();
    static CommonFunctions commonFunctions = new CommonFunctions();

    public void getAllActionitems(ActiontrackerParameters actionitemParameters) {

        log.info("\n\n=========================== Issuing API GET call to retrieve all Action Items  ===========================");
        Response response = commonFunctions.getAllActionitemsGETCall(actionitemParameters);
        log.info("\n\n======== API GET call completed successfully; HTTP Status Code of 200 validated =========================");
        int numberOfActionitems = commonFunctions.computeNumberOfItemsInResponse(response, "count");
        log.info("Count of Action Items - Expected: >= 0; Actual:  " + numberOfActionitems);
        Assert.assertTrue( numberOfActionitems >= 0, "Validation of Action Item count failed" );
        log.info("Count of Action Items validated to be >= 0");


    }
}
