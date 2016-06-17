package de.lendico.iban;

import java.util.Locale;

import de.lendico.bban.strategy.BbanStrategy;
import de.lendico.bban.strategy.BbanStrategyFactory;

public class IbanFactory {

    /**
     * Factories from a String.
     * 
     * @param countryCode
     * @return string
     */
    public String factory(Locale locale) {

        return generate(locale.getCountry());
    }    

    /**
     * Factories from a Locale.
     * 
     * @param locale
     * @return string
     */
    public String factory(String countryCode) {

        return generate(countryCode);
    } 
    
    String generate(String countryCode) {
        
        BbanStrategy bbanStrategy = BbanStrategyFactory.factory(countryCode);
        
        if (bbanStrategy == null) {
            throw new UnsupportedOperationException("Country is not supported");
        }

        return new StringBuilder()
                .append(countryCode) // ISO 3166-1 alpha-2 (country code)
                .append("00") // Check digits
                .append(bbanStrategy.generate()) // BBAN (Basic Bank Account Number)
                .toString();
    }
  
}
