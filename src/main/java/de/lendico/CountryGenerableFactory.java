package de.lendico;

import java.util.Locale;

abstract class CountryGenerableFactory {

    abstract String generate(Locale country);
    
    /**
     * Factories from a Locale.
     * 
     * @param locale
     * @return string
     */
    public String factory(Locale locale) {

        return generate(locale);
    }
    
    /**
     * Factories from a String.
     * 
     * @param countryCode
     * @return string
     */
    public String factory(String countryCode) {

        return factory(new Locale("", countryCode)); // TODO: creating a lot of similar objects - Flyweight pattern?
    }

}
