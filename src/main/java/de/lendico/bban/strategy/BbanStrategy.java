package de.lendico.bban.strategy;

import java.math.BigInteger;

import de.lendico.bban.mask.BbanMaskElement;

public abstract class BbanStrategy {
    
    private String mask;

    public BbanStrategy() {
    }
    
    public BbanStrategy(String mask) {
        this.mask = mask;
    }

    /**
     * Generates a random BBAN from the mask 
     * 
     * @return String random BBAN
     */
    public String generate() {
        
        if (mask == null || mask.isEmpty()) return "";
        
        StringBuilder bban = new StringBuilder();
        
        mask.chars().forEach(c -> 
            bban.append(BbanMaskElement.fromChar(c).random())
        );
        
        return bban.toString();
    }

    /**
     * 
     * 
     * @param String countryCode ISO 3166-1 alpha-2 (country code)
     * @param String bban check digits
     * @return
     */
    public String calculateCheckDigits(String countryCode, String bban) {
        
        String base = new StringBuilder().append(countryCode).append("00").append(bban).toString();
        
        // Rotate
        String rotated = new StringBuilder().append(base.substring(4)).append(base.substring(0,  4)).toString();
        
        // Replace chars with number
        StringBuilder numericString = new StringBuilder();
        rotated.toUpperCase().chars().forEach(c -> {
            numericString.append(Character.getNumericValue(c));
        });
        
        // Convert numeric string to integer
        BigInteger numeric = new BigInteger(numericString.toString());
        
        // mod(97)
        int result = 98 - numeric.mod(new BigInteger("97")).intValue();
        
        return (result < 10) ? "0" + result : "" + result;
    }
}