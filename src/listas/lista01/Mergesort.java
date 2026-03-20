package listas.lista01;

import java.util.Arrays;
import java.util.Random;

public class Mergesort {

    static void main(String[] args) {
        int[] numeros = new int[10000];
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = new Random().nextInt(10000);
        }

        System.out.println("Antes: " + Arrays.toString(numeros));

        long antes = System.currentTimeMillis();
        mergesort(numeros);
        long depois = System.currentTimeMillis();

        System.out.println("Depois: " + Arrays.toString(numeros));
        System.out.println("Tempo decorrido: " + (depois - antes) + "ms");
    }

    static void mergesort(int[] numeros) {
        //Separar
        if(numeros.length < 2) return;

        int indiceMeio = numeros.length/2;
        int[] esquerda = new int[indiceMeio];
        int[] direita = new int[numeros.length - indiceMeio];

        for (int i = 0; i < indiceMeio; i++) {
            esquerda[i] = numeros[i];
        }

        for (int i = indiceMeio; i < numeros.length; i++) {
            direita[i - indiceMeio] = numeros[i];
        }

        mergesort(esquerda);
        mergesort(direita);

        //Merge
        int i = 0, j = 0, k = 0;

        while(i < esquerda.length && j < direita.length) {
            if(esquerda[i] <= direita[j]) {
                numeros[k] = esquerda[i];
                i++;
            } else {
                numeros[k] = direita[j];
                j++;
            }
            k++;
        }

        while(i < esquerda.length) {
            numeros[k] = esquerda[i];
            i++;
            k++;
        }

        while(j < direita.length) {
            numeros[k] = direita[j];
            j++;
            k++;
        }
    }

}
