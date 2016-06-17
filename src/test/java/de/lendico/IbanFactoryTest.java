package de.lendico;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.util.Locale;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class IbanFactoryTest {

    private static final Locale AUSTRIA         = new Locale("", "AT");
    private static final Locale GERMANY         = new Locale("", "DE");
    private static final Locale NETHERLANDS     = new Locale("", "NL");
    
    private IbanFactory ibanFactory;
    
    @BeforeClass
    public void setUp() {
        this.ibanFactory = new IbanFactory();
    }
    
    @Test(dataProvider = "countryVsPrefix")
    public void shouldHaveCountryCodePrefix(Locale country, String prefix) {
        
        assertTrue(ibanFactory.generate(country).startsWith(prefix));
    }
    
    @DataProvider(name = "countryVsPrefix")
    public Object[][] provideCountryVsPrefix() throws Exception {
        return new Object[][] {
                { GERMANY, "DE" },
                { AUSTRIA, "AT" },
                { NETHERLANDS, "NL" }
        };
    }
    
    @Test(dataProvider = "countryVsLength")
    public void shouldHaveSpecificLengthByCountry(Locale country, int length) {
        
        assertEquals(ibanFactory.generate(country).length(), length);
    }
    
    @DataProvider(name = "countryVsLength")
    public Object[][] provideCountryVsLength() throws Exception {
        return new Object[][] {
                { GERMANY, 22 },
                { AUSTRIA, 20 },
                { NETHERLANDS, 18 }
        };
    }    

}
