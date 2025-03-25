# Taller #1 - Mutation Analysis

## Instrucciones
Completar este documento con las respuestas correspondientes a los ejercicios planteados en el enunciado del taller.

---

## Ejercicio 1: Resultados de generación de mutantes

1. ¿Cuántos mutantes se generaron en total?
   - Respuesta: 76

2. ¿Qué operador de mutación generó más mutantes? ¿Cuántos y por qué?
   - Respuesta: TrueConditionalsMutator y FalseConditionalsMutator ambos generaron 10 cada uno, porque el código provisto tiene muchos condicionales.

3. ¿Qué operador de mutación generó menos mutantes? ¿Cuántos y por qué?
   - Respuesta: ConditionalsBoundaryMutator, NullReturnsMutator, IncrementsMutator, y EmptyReturnsMutator todos generaron 3 porque:
     - NullReturnsMutator y EmptyReturnsMutator solo aplican a ciertos tipos, y hay 3 return sobre los que apliquen para cada uno.
     - ConditionalsBoundaryMutator y IncrementsMutator porque esos operadores solo se usan 3 veces.

---

## Ejercicio 2: Evaluación de test suites

1. ¿Cuántos mutantes vivos y muertos encontraron cada uno de los test suites?
   - **StackTests1**:
     - Mutantes vivos: 56
     - Mutantes muertos: 20
   - **StackTests2**:
     - Mutantes vivos: 38
     - Mutantes muertos: 38

2. ¿Cuál es el mutation score de cada test suite?
   - **StackTests1**: 26%
   - **StackTests2**: 50%

---

## Ejercicio 3: Mejora del test suite

1. ¿Cuál es el mutation score logrado para los tests de StackTests3?
   - Respuesta: 93%

2. ¿Cuántos mutantes vivos y muertos encontraron?
   - Mutantes vivos: 5
   - Mutantes muertos: 71

3. Comente cuáles son todos los mutantes vivos que quedaron y por qué son equivalentes al programa original (si no lo fueran, todavía es posible mejorar el mutation score).
   - Respuesta: Los mutantes vivos son los siguientes:
     - StackArMutated1577 (TrueReturnsMutator: Se reemplazó false por true en la línea 82.), si se llega a esta linea, la condición del if (línea 81) ya es necesariamente falsa, porque al tener los mismos elementos tienen el mismo índice. Por lo que nunca se llega a ejecutar esta línea.
     - StackArMutated776 (FalseConditionalsMutator: Se reemplazó isEmpty() por false en la línea 45.), en la línea 48 se llama a `.top()`, ese método hace el mismo chequeo y tira la misma excepción. Es redundante este if.
     - StackArMutated1518 (FalseConditionalsMutator: Se reemplazó this == obj por false en la línea 72.), que es equivalente porque es redundante con las siguientes condiciones del método.
     - StackArMutated7729 (MathMutator: Se reemplazó * por / en la línea 65.), x*1 == x/1 
     - StackArMutated341 (FalseConditionalsMutator: Se reemplazó readIndex != other.readIndex por false en la línea 81.), similar al anterior, esta condición es redundante.

4. ¿Cuál es el instruction coverage promedio que lograron para las clases mutadas?
   - Respuesta: 68%

5. ¿Cuál es el peor instruction coverage que lograron para una clase mutada? ¿Por qué creen que sucede esto?
   - Respuesta: 5%, el mutante es StackArMutated7374 (NegateConditionsMutator: Se reemplazó < por >= en la línea 18.), esta mutación causa que fallen todas las instanciaciones, por lo que no se llega a testear el resto del código.
