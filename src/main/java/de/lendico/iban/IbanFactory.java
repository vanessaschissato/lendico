package de.lendico.iban;

import java.util.Locale;

import de.lendico.iban.strategy.IbanStrategy;
import de.lendico.iban.strategy.IbanStrategyFactory;

public class IbanFactory {

    /**
     * Factories from a String.
     * 
     * @param Locale
     * @return String
     */
    public String factory(Locale locale) {
        return generate(locale.getCountry());
    }    

    /**
     * Factories from a Locale.
     * 
     * @param String ISO 3166-1 alpha-2 (country code)
     * @return String IBAN
     */
    public String factory(String countryCode) {
        return generate(countryCode);
    } 
    
    String generate(String countryCode) {
        
        IbanStrategy ibanStrategy = IbanStrategyFactory.factory(countryCode);
        
        if (ibanStrategy == null) {
            throw new UnsupportedOperationException("Country is not supported");
        }
        
        return ibanStrategy.generate();
    }
}
