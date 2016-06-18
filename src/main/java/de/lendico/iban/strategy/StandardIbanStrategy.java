package de.lendico.iban.strategy;

import de.lendico.bban.strategy.BbanStrategy;

public class StandardIbanStrategy extends IbanStrategy {
    
    public StandardIbanStrategy(String countryCode, BbanStrategy bbanStrategy) {
        super(countryCode, bbanStrategy);
    }
}