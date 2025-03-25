package org.autotest.operators.constants;

import org.autotest.operators.MutationOperator;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.declaration.CtElement;

import java.util.Arrays;
import java.util.List;

/**
 * Operador de mutación basado en https://pitest.org/quickstart/mutators/#EXPERIMENTAL_CRCR
 *
 * Este operador reemplaza los valores de las constantes por uno.
 */
public class OneConstantMutator extends MutationOperator {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        if (candidate instanceof CtUnaryOperator) {
            // Manejamos el "-1"
            CtUnaryOperator op = (CtUnaryOperator)candidate;
            return op.toString().equals("-1");
        }

        if (!(candidate instanceof CtLiteral))
            return false;

        CtLiteral op = (CtLiteral)candidate;
        String type = getLiteralType(op);
        List<String> targetTypes = Arrays.asList(
                "int"
        );

        if (!targetTypes.contains(type))
            return false;

        // No hay que reemplazar el "1" de "-1"
        if (op.getParent().toString().contains("-1"))
            return false;

        return !op.toString().equals("1");
    }

    @Override
    public void process(CtElement candidate) {
        CtLiteral newValue = candidate.getFactory().Code().createLiteral(1);
        if (candidate instanceof CtUnaryOperator) {
            // Es "-1"
            CtUnaryOperator op = (CtUnaryOperator)candidate;
            op.setOperand(newValue);
        } else {
            CtLiteral op = (CtLiteral)candidate;
            op.setValue(newValue);
        }
    }

    private static String getLiteralType(CtLiteral op) {
        return op.getType().toString();
    }

    @Override
    public String describeMutation(CtElement candidate) {
        return this.getClass().getSimpleName() + ": Se reemplazó " +
                candidate.toString() + " por 1" +
                " en la línea " + candidate.getPosition().getLine() + ".";
    }
}
