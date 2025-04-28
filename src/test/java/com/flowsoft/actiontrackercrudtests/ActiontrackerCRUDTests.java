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
    public void retrieveAllBootcamps() {
        log.info("\n\n====================================================================================");
        log.info("*********************************************** TEST001 - Test to retrieve all Action Items ***********************************************");
        actionitemCRUDFunctions.getAllActionitems(actionitemParameters);
    }
}
