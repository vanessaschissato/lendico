package de.lendico.iban;

import static org.testng.AssertJUnit.assertTrue;

import java.util.Locale;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import de.lendico.iban.IbanFactory;


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

}
