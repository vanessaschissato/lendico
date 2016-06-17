package de.lendico;

import static org.testng.Assert.assertNotNull;

import java.util.Locale;

import org.testng.annotations.BeforeClass;
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

}
