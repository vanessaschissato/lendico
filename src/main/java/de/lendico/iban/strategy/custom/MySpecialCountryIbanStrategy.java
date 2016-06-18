package de.lendico.iban.strategy.custom;

import de.lendico.iban.strategy.IbanStrategy;

/**
 * Custom BBAN strategy
 */
public class MySpecialCountryIbanStrategy extends IbanStrategy {

    public MySpecialCountryIbanStrategy(String countryCode) {
        super(countryCode);
    }

    @Override
    public String generate() {
        return "CUSTOMIBANRULE";
    }
}