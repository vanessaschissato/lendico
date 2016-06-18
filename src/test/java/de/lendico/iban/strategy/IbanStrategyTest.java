package de.lendico.iban.strategy;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class IbanStrategyTest {
    
    @Test(dataProvider = "validIbans")
    public void shouldValidateRightIbans(String iban) {
        assertTrue(IbanStrategy.isValid(iban));
    }
    
    @DataProvider(name = "validIbans")
    public Object[][] provideValidIbans() throws Exception {
        return new Object[][] {
                { "DE44500105175407324931" },
                { "GR1601101250000000012300695" },
                { "GB29NWBK60161331926819" },
                { "BA391290079401028494" }
        };
    }
    
    @Test(dataProvider = "invalidIbans")
    public void shouldNotValidateWrongIbans(String iban) {
        assertFalse(IbanStrategy.isValid(iban));
    }
    
    @DataProvider(name = "invalidIbans")
    public Object[][] provideInvalidIbans() throws Exception {
        return new Object[][] {
                { "DE44500105175407324930" },
                { "001601101250000000012300695" },
                { "GB29NWBK6016131926819" },
        };
    }

}
