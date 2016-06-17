package de.lendico;

import java.util.Locale;

public class IbanFactory extends CountryGenerableFactory {

    private BbanFactory bbanFactory;
    
    public IbanFactory() {
        this.bbanFactory = new BbanFactory();
    }
    
    @Override
    String generate(Locale locale) {
        
        return new StringBuilder()
                .append(locale.getCountry()) // ISO 3166-1 alpha-2 (country code)
                .append("00") // Check digits
                .append(bbanFactory.factory(locale)) // BBAN (Basic Bank Account Number)
                .toString();
    } 
  
}
