package de.lendico.bban.strategy;

import java.util.Random;

public abstract class BbanStrategy {

    private String pattern;

    public BbanStrategy() {
    }
    
    public BbanStrategy(String pattern) {
        this.pattern = pattern;
    }

    /**
     * n = numeric [0-9] 
     * a = upper case letters [A-Z]
     * 
     * @return String BBAN pattern
     */
    public String getPattern() {
        return this.pattern;
    }

    /**
     * Creates a random BBAN from the pattern 
     * 
     * @return String random BBAN
     */
    public String generate() {
        
        if (pattern == null || pattern.isEmpty()) return "";
        
        char[] ALPHA_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        
        StringBuilder bban = new StringBuilder();
        
        getPattern().chars().forEach(c -> {
            if (c == 'a') {
                bban.append(ALPHA_UPPERCASE[new Random().nextInt(ALPHA_UPPERCASE.length)]);
            } else if (c == 'n') {
                bban.append("0");
            }
        });
        
        return bban.toString();
    }
}