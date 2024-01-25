package com.example.wolseytechhr;

import org.junit.Test;
import static org.junit.Assert.*;

public class RetrieveAuthCodeTest {

    @Test
    public void testMakeURLToGetAuthCode() throws Exception {
        RetrieveAuthCode retrieveAuthCode = new RetrieveAuthCode("user", "password", "company");
        String url = retrieveAuthCode.makeURLToGetAuthCode();
        assertTrue(url.startsWith("https://hr-demo.wolsey-tech.com/get_auth_code.asp"));
        assertTrue(url.contains("user_name=user"));
        assertTrue(url.contains("password=password"));
        assertTrue(url.contains("company_name=company"));
    }

    @Test
    public void testFindAuthCode() {
        RetrieveAuthCode retrieveAuthCode = new RetrieveAuthCode("user", "password", "company");
        retrieveAuthCode.findAuthCode();
        assertFalse(retrieveAuthCode.getIsErrorGettingAuthCode());
        assertNull(retrieveAuthCode.getAuth_code());
        assertFalse(retrieveAuthCode.getNeedToResetPassword());
        assertFalse(retrieveAuthCode.getIsIncorrectUserNameOrPassword());
        assertFalse(retrieveAuthCode.getIsIncorrectPassword());
    }

}
