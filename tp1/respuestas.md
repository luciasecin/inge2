# Taller #1 - Mutation Analysis

## Instrucciones
Completar este documento con las respuestas correspondientes a los ejercicios planteados en el enunciado del taller.

---

## Ejercicio 1: Resultados de generación de mutantes

1. ¿Cuántos mutantes se generaron en total?
   - Respuesta: 82

2. ¿Qué operador de mutación generó más mutantes? ¿Cuántos y por qué?
   - Respuesta: TrueConditionalsMutator y FalseConditionalsMutator ambos generaron 10 cada uno, porque el codigo provisto tiene muchos condicionales.

3. ¿Qué operador de mutación generó menos mutantes? ¿Cuántos y por qué?
   - Respuesta: ConditionalsBoundaryMutator, NullReturnsMutator, IncrementsMutator, y EmptyReturnsMutator todos generaron 3 porque:
     - NullReturnsMutator y EmptyReturnsMutator solo aplican a ciertos tipos, y hay 3 return sobre los que apliquen para cada uno.
     - ConditionalsBoundaryMutator y IncrementsMutator porque esos operadores solo se usan 3 veces.

---

## Ejercicio 2: Evaluación de test suites

1. ¿Cuántos mutantes vivos y muertos encontraron cada uno de los test suites?
   - **StackTests1**:
     - Mutantes vivos: 63
     - Mutantes muertos: 18
   - **StackTests2**:
     - Mutantes vivos: 45
     - Mutantes muertos: 36

2. ¿Cuál es el mutation score de cada test suite?
   - **StackTests1**: 22%
   - **StackTests2**: 44%

---

## Ejercicio 3: Mejora del test suite

1. ¿Cuál es el mutation score logrado para los tests de StackTests3?
   - Respuesta:

2. ¿Cuántos mutantes vivos y muertos encontraron?
   - Mutantes vivos:
   - Mutantes muertos:

3. Comente cuáles son todos los mutantes vivos que quedaron y por qué son equivalentes al programa original (si no lo fueran, todavía es posible mejorar el mutation score).
   - Respuesta:

4. ¿Cuál es el instruction coverage promedio que lograron para las clases mutadas?
   - Respuesta:

5. ¿Cuál es el peor instruction coverage que lograron para una clase mutada? ¿Por qué creen que sucede esto?
   - Respuesta:
