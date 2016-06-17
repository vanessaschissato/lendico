

import java.util.Arrays;

import de.lendico.IbanFactory;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {

        IbanFactory ibanFactory = new IbanFactory();
        
        Arrays.asList(ibanFactory.factory("BR"), ibanFactory.factory("DE"))
            .stream()
            .forEach(l -> System.out.println(l));

    }
}
