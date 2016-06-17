package de.lendico.bban.mask;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class BbanMaskElementTest {

    @Test(dataProvider = "maskElementVsFormat")
    public void shouldGenerateCharFromMaskElement(char maskElement, String regex) {
        char randomChar = BbanMaskElement.fromChar(maskElement).random();
        assertTrue(String.valueOf(randomChar).matches(regex));
    }
    
    @DataProvider(name = "maskElementVsFormat")
    public Object[][] provideCountryVsFormat() throws Exception {
        return new Object[][] {
                { 'a', "[A-Z]{1}" },    
                { 'n', "[0-9]{1}" }
        };
    }
    
    @Test(dataProvider = "unsupportedMaskElement", expectedExceptions = {UnsupportedOperationException.class})
    public void shouldThrowExceptionWhenUsingUnsupportedMaskElement(char maskElement) {
        BbanMaskElement.fromChar(maskElement).random();
    }
    
    @DataProvider(name = "unsupportedMaskElement")
    public Object[][] provideUnsupportedMaskElement() throws Exception {
        return new Object[][] {
                { 'c' },    
                { 'e' }
        };
    }
}
