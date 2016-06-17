package de.lendico.iban;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import java.util.Locale;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class IbanFactoryTest {

    private static final String AUSTRIA     = "AT";
    private static final String GERMANY     = "DE";
    private static final String NETHERLANDS = "NL";
    
    private static final String AUSTRIA_REGEX       = "AT[0-9]{18}";
    private static final String GERMANY_REGEX       = "DE[0-9]{20}";
    private static final String NETHERLANDS_REGEX   = "NL[0-9]{2}[A-Z]{4}[0-9]{10}";
    
    private IbanFactory ibanFactory;
    
    @BeforeClass
    public void setUp() {
        this.ibanFactory = new IbanFactory();
    }

    @Test(dataProvider = "countryVsFormat")
    public void shouldHaveSpecificFormatByCountry(String country, String regex) {
        assertTrue(ibanFactory.generate(country).matches(regex));
    }
    
    @DataProvider(name = "countryVsFormat")
    public Object[][] provideCountryVsFormat() throws Exception {
        return new Object[][] {
                { AUSTRIA, AUSTRIA_REGEX },    
                { GERMANY, GERMANY_REGEX },
                { NETHERLANDS, NETHERLANDS_REGEX }
        };
    }
    
    @Test
    public void shouldFactoryFromLocale() {
        
        assertTrue(ibanFactory.factory(Locale.GERMANY).matches(GERMANY_REGEX));
    }
    
    @Test(dataProvider = "unsupportedCountries", expectedExceptions = {UnsupportedOperationException.class})
    public void shouldReturnExceptionWhenUnsupportedCountry(Object country) {
        
        if (country instanceof String) {
            ibanFactory.factory((String)country);
        } else if (country instanceof Locale) {
            ibanFactory.factory((Locale)country);
        }
    }

    @DataProvider(name = "unsupportedCountries")
    public Object[][] provideUnsupportedCountries() throws Exception {
        return new Object[][] {
                { "" },
                { "WRONG_COUNTRY" },
                { new Locale("") },
                { Locale.TAIWAN },
                { Locale.TAIWAN.getCountry() },
        };
    }
    
    @Test(dataProvider = "validIbans")
    public void shouldValidateRightIbans(String iban) {
        assertTrue(ibanFactory.isValid(iban));
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
        assertFalse(ibanFactory.isValid(iban));
    }
    
    @DataProvider(name = "invalidIbans")
    public Object[][] provideInvalidIbans() throws Exception {
        return new Object[][] {
                { "DE44500105175407324930" },
                { "001601101250000000012300695" },
                { "GB29NWBK6016131926819" },
        };
    }

    @Test(dataProvider = "supportedCountries")
    public void shouldFactoryIbansWithValidCheckDigits(String country) {
        
        String iban = ibanFactory.generate(country);
        
        assertTrue(ibanFactory.isValid(iban));
    }
    
    @DataProvider(name = "supportedCountries")
    public Object[][] provideSupportedCountries() throws Exception {
        return new Object[][] {
                { "AT" },
                { "DE" },
                { "NL" },
        };
    }

}
