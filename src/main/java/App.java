

import java.util.Arrays;

import de.lendico.iban.IbanFactory;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        IbanFactory ibanFactory = new IbanFactory();
        
        Arrays.asList(ibanFactory.factory("BA"), ibanFactory.factory("DE"), ibanFactory.factory("NL"))
            .stream()
            .forEach(l -> System.out.println(l));

    }
}
