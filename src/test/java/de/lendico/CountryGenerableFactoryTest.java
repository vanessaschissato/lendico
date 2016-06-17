package de.lendico;

import static org.testng.Assert.assertNotNull;

import java.util.Locale;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CountryGenerableFactoryTest {

    // Any concrete instance
    private IbanFactory ibanFactory;
    
    @BeforeClass
    public void setUp() {
        this.ibanFactory = new IbanFactory();
    }
    
    @Test
    public void shouldFactoryFromCountryCode() {
        
        assertNotNull(ibanFactory.factory("DE"));
    }
    
    @Test
    public void shouldFactoryFromLocale() {
        
        assertNotNull(ibanFactory.factory(Locale.GERMANY));
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
