package testers;

import exceptions.CucaException;
import models.Cuca;
import validators.CucaValidator;

public class CucaValidatorTester {

    private final Cuca cucaPerfect = new Cuca("origem", "tipo");
    private final Cuca cucaNoOrigem = new Cuca("", "tipo");
    private final Cuca cucaNoTipo = new Cuca("origem", "");
    private final CucaValidator validator = new CucaValidator();

    public boolean test() {
        try {
            validator.validate(cucaPerfect);
        } catch (final CucaException e) {
            System.out.println(e + " (cuca perfeita)");
            return true;
        }
        try {
            validator.validate(cucaNoOrigem);
        } catch (final CucaException e) {
            System.out.println(e + " (cuca sem origem)");
            return true;
        }
        try {
            validator.validate(cucaNoTipo);
        } catch (final CucaException e) {
            System.out.println(e + " (cuca sem tipo)");
            return true;
        }
        return false;
    }

    public static void main(final String[] args) {
        final CucaValidatorTester tester = new CucaValidatorTester();
        tester.test();
    }
}
