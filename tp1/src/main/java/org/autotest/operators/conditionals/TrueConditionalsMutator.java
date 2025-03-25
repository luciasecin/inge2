package org.autotest.operators.conditionals;

import org.autotest.operators.MutationOperator;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtReturn;
import spoon.reflect.declaration.CtElement;

import java.util.Arrays;
import java.util.List;

/**
 * Operador de mutación basado en https://pitest.org/quickstart/mutators/#REMOVE_CONDITIONALS
 *
 * Este operador reemplaza los valores en las condiciones de guardas por true.
 */
public class TrueConditionalsMutator extends MutationOperator {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        if (!(candidate instanceof CtIf))
            return false;

        CtIf op = (CtIf)candidate;
        return !op.getCondition().toString().equals("true");
    }

    @Override
    public void process(CtElement candidate) {
        CtIf op = (CtIf)candidate;
        op.setCondition(op.getFactory().Code().createLiteral(true));
    }

    @Override
    public String describeMutation(CtElement candidate) {
        CtIf op = (CtIf)candidate;
        return this.getClass().getSimpleName() + ": Se reemplazó " +
                op.getCondition().toString() + " por true en la línea " + op.getPosition().getLine() + ".";
    }
}
