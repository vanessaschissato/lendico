package de.lendico.bban.mask;

import java.util.Random;

/**
 * Based on IBAN Registry spec: 
 * https://www.swift.com/sites/default/files/resources/swift_standards_ibanregistry.pdf
 * 
 * n = numeric [0-9] 
 * a = upper case letters [A-Z]
 * c = upper and lower case letters + numeric [a-zA-Z0-9]
 * e = blank space
 *
 */
public enum BbanMaskElement {
     
    ALPHA_UPPERCASE('a') {
        @Override
        public char random() {
            return uppercaseChars[new Random().nextInt(uppercaseChars.length)];
        }
    },
    NUMERIC('n') {
        @Override
        public char random() {
            return numeric[new Random().nextInt(numeric.length)];
        }
    },
    ALPHA('c') {
        @Override
        public char random() {
            throw new UnsupportedOperationException("Mask element 'c' not implemented yet");
        }
    },
    BLANK('e') {
        @Override
        public char random() {
            throw new UnsupportedOperationException("Mask element 'e' not implemented yet");
        }
    };

    static char[] uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    static char[] numeric = "0123456789".toCharArray();
    
    private char ch;
    
    BbanMaskElement(char ch) {
        this.ch = ch;
    }
    
    public char getChar() {
        return ch;
    }
    
    public abstract char random();
    
    public static BbanMaskElement fromChar(int c) {
        for (BbanMaskElement mask : BbanMaskElement.values()) {
            if (mask.getChar() == c) return mask;
        }
        throw new RuntimeException("Unsupported mask element = " + c);
    }
    
} 