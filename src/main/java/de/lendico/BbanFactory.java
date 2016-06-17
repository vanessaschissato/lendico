package de.lendico;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class BbanFactory extends CountryGenerableFactory {
    
    private static char[] ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    
    private static Map<String, Integer> bbanStrategy;
    
    static {
        bbanStrategy = new HashMap<String, Integer>();
        bbanStrategy.put("AT", 16);
        bbanStrategy.put("DE", 18);
        bbanStrategy.put("NL", 14);
    }
    
    @Override
    String generate(Locale locale) {
        
        Integer length = bbanStrategy.get(locale.getCountry());
        
        if (length == null) {
            throw new UnsupportedOperationException("Country is not supported");
        }
        
        StringBuilder bban = new StringBuilder();
        for (int i = 0; i < length; i++) {
            bban.append(ALPHA_NUMERIC[new Random().nextInt(ALPHA_NUMERIC.length)]);
        }
        
        return bban.toString();

    }

}
