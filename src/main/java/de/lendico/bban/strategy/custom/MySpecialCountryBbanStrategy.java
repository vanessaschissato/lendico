package de.lendico.bban.strategy.custom;

import de.lendico.bban.strategy.BbanStrategy;

/**
 * Custom BBAN strategy
 */
public class MySpecialCountryBbanStrategy extends BbanStrategy {

    @Override
    public String generate() {
        return "CUSTOMBBANRULE";
    }
}