package org.autotest.operators.constants;

import org.autotest.operators.MutationOperator;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.UnaryOperatorKind;
import spoon.reflect.declaration.CtElement;

import java.util.Arrays;
import java.util.List;

/**
 * Operador de mutación basado en https://pitest.org/quickstart/mutators/#EXPERIMENTAL_CRCR
 *
 * Este operador reemplaza los valores de las constantes por cero.
 */
public class ZeroConstantMutator extends MutationOperator {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        if (!(candidate instanceof CtLiteral))
            return false;

        CtLiteral op = (CtLiteral)candidate;
        String type = getLiteralType(op);
        List<String> targetTypes = Arrays.asList(
                "int"
        );

        if (!targetTypes.contains(type))
            return false;

        // No hay que reemplazar el "0" de "-0" ni "+0"
        if (op.getParent().toString().contains("-0") || op.getParent().toString().contains("+0"))
            return false;

        return !op.toString().equals("0");
    }

    @Override
    public void process(CtElement candidate) {
        CtLiteral op = (CtLiteral)candidate;
        op.setValue(op.getFactory().Code().createLiteral(0));
    }

    private static String getLiteralType(CtLiteral op) {
        return op.getType().toString();
    }

    @Override
    public String describeMutation(CtElement candidate) {
        CtLiteral op = (CtLiteral)candidate;
        return this.getClass().getSimpleName() + ": Se reemplazó " +
                op.getValue().toString() + " por 0" +
                " en la línea " + op.getPosition().getLine() + ".";
    }
}
