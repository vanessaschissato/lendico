package de.lendico.iban;

import java.math.BigInteger;
import java.util.Locale;

import de.lendico.bban.strategy.BbanStrategy;
import de.lendico.bban.strategy.BbanStrategyFactory;

public class IbanFactory {

    private static BigInteger IBAN_MOD_NUMBER = new BigInteger("97");
    
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
        
        BbanStrategy bbanStrategy = BbanStrategyFactory.factory(countryCode);
        
        if (bbanStrategy == null) {
            throw new UnsupportedOperationException("Country is not supported");
        }
        
        String bban = bbanStrategy.generate();
        String checkDigits = bbanStrategy.calculateCheckDigits(countryCode, bban);

        return new StringBuilder()
                .append(countryCode) // ISO 3166-1 alpha-2 (country code)
                .append(checkDigits) // Check digits
                .append(bban) // BBAN (Basic Bank Account Number)
                .toString();
    }
    
    boolean isValid(String iban) {
        
        // Rotate
        String rotated = new StringBuilder().append(iban.substring(4)).append(iban.substring(0,  4)).toString();
        
        // Replace chars with number
        StringBuilder numericString = new StringBuilder();
        rotated.chars().forEach(c -> {
            numericString.append(Character.getNumericValue(c));
        });
        
        // Convert numeric string to integer
        BigInteger numeric = new BigInteger(numericString.toString());
        
        // Check: mod(97) == 1
        return numeric.mod(IBAN_MOD_NUMBER).intValue() == 1;
    }
  
}
