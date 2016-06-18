package de.lendico.bban.strategy;

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
}