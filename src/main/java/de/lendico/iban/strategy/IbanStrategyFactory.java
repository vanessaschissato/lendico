package de.lendico.iban.strategy;

import java.util.HashMap;
import java.util.Map;

import de.lendico.bban.strategy.StandardBbanStrategy;
import de.lendico.iban.strategy.custom.MySpecialCountryIbanStrategy;

public class IbanStrategyFactory {
    
    private static Map<String, IbanStrategy> ibanStrategies; // Flywweight
    
    /**
     * @see de.lendico.bban.mask.BbanMaskElement
     */
    static {

        ibanStrategies = new HashMap<String, IbanStrategy>();

        ibanStrategies.put("AT", new StandardIbanStrategy("AT", new StandardBbanStrategy("nnnnnnnnnnnnnnnn")));
        ibanStrategies.put("DE", new StandardIbanStrategy("DE", new StandardBbanStrategy("nnnnnnnnnnnnnnnnnn")));
        ibanStrategies.put("NL", new StandardIbanStrategy("NL", new StandardBbanStrategy("aaaannnnnnnnnn")));
        ibanStrategies.put("ZZ", new MySpecialCountryIbanStrategy("ZZ"));
    }
    
    public static IbanStrategy factory(String countryCode) {
        
        return ibanStrategies.get(countryCode);
    }

}
