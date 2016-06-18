package de.lendico.iban.strategy;

import java.math.BigInteger;

import de.lendico.bban.strategy.BbanStrategy;

public abstract class IbanStrategy {

    private static BigInteger IBAN_MOD = new BigInteger("97");
    
    private String countryCode;
    private BbanStrategy bbanStrategy;
    
    public IbanStrategy(String countryCode) {
        this.countryCode = countryCode;
    }
    
    public IbanStrategy(String countryCode, BbanStrategy bbanStrategy) {
        this.countryCode = countryCode;
        this.bbanStrategy = bbanStrategy;
    }
    
    /**
     * Generates a valid random IBAN 
     * 
     * @return String valid IBAN
     */
    public String generate() {
        
        String bban = this.bbanStrategy.generate();
        String checkDigits = calculateCheckDigits(bban);

        String iban = new StringBuilder()
                            .append(this.countryCode) // ISO 3166-1 alpha-2 (country code)
                            .append(checkDigits) // Check digits
                            .append(bban) // BBAN (Basic Bank Account Number)
                            .toString();
        
        return iban;
    }

    /**
     * Standard check digits calculation.
     * 
     * @param String countryCode ISO 3166-1 alpha-2 (country code)
     * @param String check digits
     * @return
     */
    public String calculateCheckDigits(String bban) {
        
        String base = new StringBuilder().append(this.countryCode).append("00").append(bban).toString();

        // Rotate
        String rotated = rotate4CharsToEnd(base);
        
        // Replace chars with number
        String numericString = convertCharsInStringToNumber(rotated);
        
        // mod(97)
        int result = 98 - new BigInteger(numericString).mod(IBAN_MOD).intValue();
        return (result < 10) ? "0" + result : "" + result;
    }
    
    /**
     * Validate IBAN against the check digits.
     * 
     * @param String iban
     * @return boolean
     */
    public static boolean isValid(String iban) {
        
        // Rotate
        String rotated = rotate4CharsToEnd(iban);
        
        // Replace chars with number
        String numericString = convertCharsInStringToNumber(rotated);
        
        // Check: mod(97) == 1
        return new BigInteger(numericString).mod(IBAN_MOD).intValue() == 1;
    }
    
    public static String rotate4CharsToEnd(String original) {
        return new StringBuilder().append(original.substring(4)).append(original.substring(0,  4)).toString();
    }
    
    public static String convertCharsInStringToNumber(String original) {
        
        StringBuilder numericString = new StringBuilder();
        original.chars().forEach(c -> {
            numericString.append(Character.getNumericValue(c));
        });
        
        return numericString.toString();
    }
}