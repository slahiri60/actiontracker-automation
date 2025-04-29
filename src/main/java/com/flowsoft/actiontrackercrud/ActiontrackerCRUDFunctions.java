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
        Assert.assertTrue( numberOfActionitems >= 0, "Validation of Action Item count validation failed" );
        log.info("Count of Action Items validated to be >= 0");
        actionitemParameters.setActionitemCount(numberOfActionitems);
    }

    public   void createActionitem(ActiontrackerParameters actionitemParameters) {
        log.info("\n\n====================== Issuing API POST call to create new Action Item  ============================");
        Response response = commonFunctions.addActionitemPOSTCall(actionitemParameters, returnJSONBody("actionitem"));
        log.info("\n\n=========================== API POST call completed successfully and HTTP Status Code of 201 validated ===========================");
        String actionitemId = commonFunctions.returnStringValueInResponse(response, "data._id");
        log.info("Action Item created with ID: " + actionitemId);
        actionitemParameters.setActionitemId(actionitemId);
    }

    public void getSingleActionitem(ActiontrackerParameters actionitemParameters) {

        log.info("\n\n=========================== Issuing API GET call to retrieve new Action Item  ============================");
        Response response = commonFunctions.getSingleActionitemGETCall(actionitemParameters);
        log.info("\n\n=========================== API GET call to retrieve new Action Item completed successfully and HTTP Status Code of 200 validated ===========================");
        log.info("\n+++++++++++++++++++++++++++++++ Comparing Action Item parameters +++++++++++++++++++++++++++++++ ");
        assertActionitemStringParameter(response, "Concert tickets", "data.summary", "Summary");
        assertActionitemStringParameter(response, "Book tickets for upcoming concert", "data.description", "Description");
        assertActionitemStringParameter(response, "high", "data.criticality[0]", "Criticality");
        assertActionitemStringParameter(response, "important", "data.importance[0]", "Importance");
        assertActionitemStringParameter(response, "2025-04-14T00:00:00.000Z", "data.dueDate", "Due Date");
    }

    public void updateSingleActionitem(ActiontrackerParameters actionitemParameters) {

        log.info("\n\n=========================== Issuing API PUT call to update new Action Item  ============================");
                Response response = commonFunctions.updateSingleActionitemPUTCall(actionitemParameters, returnJSONBody("actionitemupdated"));
        log.info("\n\n=========================== API PUT call to update new Action Item completed successfully and HTTP Status Code of 200 validated ===========================");
        log.info("\n+++++++++++++++++++++++++++++++ Comparing Action Item parameters +++++++++++++++++++++++++++++++ ");
        assertActionitemStringParameter(response, "Tax filing", "data.summary", "Summary");
        assertActionitemStringParameter(response, "File taxes for current year", "data.description", "Description");
        assertActionitemStringParameter(response, "medium", "data.criticality[0]", "Criticality");
        assertActionitemStringParameter(response, "unimportant", "data.importance[0]", "Importance");
        assertActionitemStringParameter(response, "2025-04-15T00:00:00.000Z", "data.dueDate", "Due Date");
    }

    public void deleteSingleActionitem(ActiontrackerParameters actionitemParameters) {

        log.info("\n\n=============== Issuing API DELETE call to delete new Action Item and validate deletion success ============================");
        int statusCode = 200;
        Response response = commonFunctions.deleteSingleActionitemDELETECall(actionitemParameters, statusCode);
        log.info("\n\n=========================== API DELETE call to delete new Action Item completed successfully and HTTP Status Code of " + statusCode + " validated ===========================");
        log.info("\n+++++++++++++++++++++++++++++++ Comparing response parameter +++++++++++++++++++++++++++++++ ");
        assertActionitemStringParameter(response, "true", "success", "success");
        log.info("\n\n=============== Re-issuing API DELETE call to delete new Action Item and validate deletion failure ============================");
        statusCode = 400;
        response = commonFunctions.deleteSingleActionitemDELETECall(actionitemParameters, statusCode);
        log.info("\n\n=========================== API DELETE call to delete new Action Item not completed successfully and HTTP Status Code of \" + statusCode + \" validated ===========================");
        log.info("\n+++++++++++++++++++++++++++++++ Comparing response parameter +++++++++++++++++++++++++++++++ ");
        assertActionitemStringParameter(response, "false", "success", "success");

    }

    public JSONObject returnJSONBody(String filename) {

        JSONParser jsonParser = new JSONParser();
        JSONObject fullObject = new JSONObject();

        try (FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/test/resources/staticjsonfiles/" + filename + ".json")) {
            Object obj = jsonParser.parse(reader);
            fullObject = (JSONObject) obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fullObject;
    }

    public void assertActionitemStringParameter(Response response, String expectedValue, String parameter, String parameterName) {
        String actualValue = commonFunctions.returnStringValueInResponse(response, parameter);
        log.info("Parameter \"" + parameterName + "\" Expected value: " + expectedValue);
        log.info("Parameter \"" + parameterName + "\" Actual value: " + actualValue);
        Assert.assertEquals(expectedValue, actualValue, "Validation failed for parameter: " + parameterName);
        log.info("Validation completed for Parameter \"" + parameterName + "\"");
    }

}
