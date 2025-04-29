package com.flowsoft.actiontrackercrudtests;

import com.flowsoft.commonartifacts.CommonFunctions;
import com.flowsoft.commonartifacts.ActiontrackerParameters;
import com.flowsoft.actiontrackercrud.ActiontrackerCRUDFunctions;
import lombok.extern.java.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Log
public class ActiontrackerCRUDTests {

    static ActiontrackerParameters actionitemParameters = new ActiontrackerParameters();
    static ActiontrackerCRUDFunctions actionitemCRUDFunctions = new ActiontrackerCRUDFunctions();
    static CommonFunctions commonFunctions = new CommonFunctions();

    @BeforeClass
    @Parameters({"baseURI"})
    public void init(String baseURI) {
        actionitemParameters.setBaseURI(baseURI);
    }

    @Test(priority=1)
    public void retrieveAllActionitems() {
        log.info("\n\n====================================================================================");
        log.info("*********************************************** TEST001 - Test to retrieve all Action Items ***********************************************");
        actionitemCRUDFunctions.getAllActionitems(actionitemParameters);
    }

    @Test(priority=2)
    public void createNewActionitem() {
        log.info("\n\n====================================================================================");
        log.info("*********************************************** TEST002 - Test to create new Action Item ************************************************");
        actionitemCRUDFunctions.createActionitem(actionitemParameters);
    }

    @Test(priority=3, dependsOnMethods = {"createNewActionitem"})
    public void retrieveNewActionitem() {
        log.info("\n\n====================================================================================");
        log.info("*********************************************** TEST003 - Test to retrieve new Action Item ************************************************");
        actionitemCRUDFunctions.getSingleActionitem(actionitemParameters);
    }

    @Test(priority=4, dependsOnMethods = {"createNewActionitem"})
    public void updateNewActionitem() {
        log.info("\n\n====================================================================================");
        log.info("*********************************************** TEST004 - Test to update new Action Item ************************************************");
        actionitemCRUDFunctions.updateSingleActionitem(actionitemParameters);
    }

    @Test(priority=5,dependsOnMethods = {"createNewActionitem"})
    public void deleteNewActionitem() {
        log.info("\n\n====================================================================================");
        log.info("*********************************************** TEST004 - Test to update new Action Item ************************************************");
        actionitemCRUDFunctions.deleteSingleActionitem(actionitemParameters);
    }
}
