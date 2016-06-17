package de.lendico.bban.strategy;

import java.util.HashMap;
import java.util.Map;

import de.lendico.bban.strategy.custom.MySpecialCountryBbanStrategy;

public class BbanStrategyFactory {
    
    private static Map<String, BbanStrategy> bbanStrategies; // Flywweight??
    
    static {

        bbanStrategies = new HashMap<String, BbanStrategy>();

        bbanStrategies.put("AT", new StandardBbanStrategy("nnnnnnnnnnnnnnnn"));
        bbanStrategies.put("BA", new MySpecialCountryBbanStrategy());
        bbanStrategies.put("DE", new StandardBbanStrategy("nnnnnnnnnnnnnnnnnn"));
        bbanStrategies.put("NL", new StandardBbanStrategy("aaaannnnnnnnnn"));
    }
    
    public static BbanStrategy factory(String countryCode) {
        
        return bbanStrategies.get(countryCode);
    }

}
