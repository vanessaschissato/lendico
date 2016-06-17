package de.lendico.bban.strategy;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class BbanStrategyTest {

    @Test(dataProvider = "maskVsFormat")
    public void shouldGenerateBbanFromMask(String mask, String regex) {
        assertTrue(new StandardBbanStrategy(mask).generate().matches(regex));
    }
    
    @DataProvider(name = "maskVsFormat")
    public Object[][] provideCountryVsFormat() throws Exception {
        return new Object[][] {
                { "", "" },    
                { "aa", "[A-Z]{2}" },
                { "nnnnn", "[0-9]{5}" },
                { "aaaaannnnn", "[A-Z]{5}[0-9]{5}" },
                { "nanana", "[0-9]{1}[A-Z]{1}[0-9]{1}[A-Z]{1}[0-9]{1}[A-Z]{1}" },
        };
    }
 

}
