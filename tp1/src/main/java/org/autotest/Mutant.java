package org.autotest;

import spoon.reflect.declaration.CtClass;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Clase que representa un mutante generado.
 */
public class Mutant {

    /**
     * El nombre del paquete donde se guardan los mutantes.
     */
    public static final String MUTANTS_PACKAGE_NAME = "org.autotest.mutants";

    /**
     * La instance de clase Spoon que representa al mutante.
     */
    private final CtClass mutatedSpoonClass;

    /**
     * Una descripción del mutante generado.
     */
    private final String mutationDescription;

    /**
     * El nuevo nombre de la clase mutada.
     */
    private String mutantClassName;

    public Mutant(CtClass mutatedSpoonClass, String mutationDescription) {
        this.mutatedSpoonClass = mutatedSpoonClass;
        this.mutationDescription = mutationDescription;

        // Creamos un nuevo nombre para la clase mutada, para diferenciarlo de la clase original (StackAr).
        this.mutantClassName = mutatedSpoonClass.getSimpleName() + "Mutated" + new Random().nextInt(10000);
    }

    /**
     * Devuelve la descripción del mutante.
     */
    public String getMutationDescription() {
        return mutationDescription;
    }

    /**
     * Borra todos los mutantes que se encuentren en la carpeta indicada.
     */
    public static void cleanMutantsInFolder(String folderPath) throws IOException {
        // Agregamos "org/autotest/mutants" al path para que corresponda con el paquete de la clase.
        Path destFolder = Paths.get(folderPath, MUTANTS_PACKAGE_NAME.split("\\."));

        // Borramos todos los archivos de la carpeta.
        if (destFolder.toFile().exists()) {
            for (File file : destFolder.toFile().listFiles()) {
                file.delete();
            }
        }
    }

    /**
     * Escribe el código del mutante a la carpeta indicada.
     */
    public void writeToFolder(String folderPath) throws IOException {
        // Agregamos "org/autotest/mutants" al path para que corresponda con el paquete de la clase.
        Path destFolder = Paths.get(folderPath, MUTANTS_PACKAGE_NAME.split("\\."));

        // Creamos la carpeta si no existe.
        if (!destFolder.toFile().exists()) {
            destFolder.toFile().mkdirs();
        }

        // Armamos el nombre del archivo y la ruta para la clase mutada.
        String mutantFileName = mutantClassName + ".java";
        Path mutantFilePath = Paths.get(destFolder.toString(), mutantFileName);

        // Armamos el código de la clase y reemplazamos el nombre original (StackAr) por el nuevo (StackArMutatedNNNN).
        String mutantCode = mutatedSpoonClass.toString();
        mutantCode = mutantCode.replace(mutatedSpoonClass.getSimpleName(), mutantClassName);
        mutantCode = mutantCode.replace(mutatedSpoonClass.getPackage().toString() + "." + mutantClassName,
                MUTANTS_PACKAGE_NAME + "." + mutantClassName);

        // Agregamos package name al principio del código.
        mutantCode = "package " + MUTANTS_PACKAGE_NAME + ";\n\n" + mutantCode;

        // Escribimos el archivo a disco.
        BufferedWriter writer = new BufferedWriter(new FileWriter(mutantFilePath.toString()));
        writer.write(mutantCode);
        writer.close();
    }

    @Override
    public String toString() {
        return mutantClassName + " - " + mutationDescription;
    }
}
