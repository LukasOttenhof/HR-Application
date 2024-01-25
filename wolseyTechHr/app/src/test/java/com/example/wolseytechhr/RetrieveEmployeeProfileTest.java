package com.example.wolseytechhr;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This class is used to test the RetrieveEmployeeProfile class. It contains 3 test methods,
 * one to make sure that the right link is being made, one to test that the raw data is being
 * grabbed, and one to test one of the bits of cleaned data.
 */
public class RetrieveEmployeeProfileTest {

    @Test
    public void testFindUserProfileDataLink() {
        RetrieveEmployeeProfile test = new RetrieveEmployeeProfile("30XONYJKS0RX2IQYGBUCDKGSB");

        assertEquals(test.getLink(), "https://hr-demo.wolsey-tech.com/get_data.asp?auth_code=30XONYJKS0RX2IQYGBUCDKGSB&query_type=personal_info");
    }
    @Test
    public void testGetRawProfileInfo() {
        RetrieveEmployeeProfile test = new RetrieveEmployeeProfile("30XONYJKS0RX2IQYGBUCDKGSB");

        assertEquals("SUCCESS 400: PERSONAL INFO REQUEST [first_name=Four],[middle_name=],[last_name=Suits],[address_1=1-105 Augustana Way],[address_2=Suite 20003],[city=Camrose],[province_name=AB],[postal_code=T4V 1T4],[home_phone=780-281-1971],[cell_phone=780-608-1971],[email=thefoursuits@wolsey-tech.com]", test.getRawProfileInfo());
    }

    @Test
    public void testMiddleName() {
        RetrieveEmployeeProfile test = new RetrieveEmployeeProfile("30XONYJKS0RX2IQYGBUCDKGSB");
        assertEquals(null, test.getMiddleName());
    }
}
