package listas.listaRevisao;

import java.util.Arrays;

public class Mergesort {

    static void main() {

        int[] vetor = {4, 5, 9, 10, 2, 1, 3, 6, 8, 7};

        System.out.println("Array sem ordenação: " + Arrays.toString(vetor));
        mergesort(vetor);
        System.out.println("Array ordenação: " + Arrays.toString(vetor));

        int n = 1;
        System.out.println("Busca binária iterativa do número " + n + ": " + buscaBinariaIterativa(n, vetor));
        System.out.println("Busca binária recursiva do número " + n + ": " + buscaBinariaRecursiva(n, vetor));

    }

    private static int buscaBinariaIterativa(int n, int[] vetor) {
        int start = 0, end = vetor[vetor.length - 1];
        int indMeio;

        while(start <= end) {
            indMeio = (start + end) / 2;
            if(indMeio == vetor.length) return -1;

            if(vetor[indMeio] == n) {
                return indMeio;

            } else if(vetor[indMeio] < n) {
                start = indMeio + 1;
            } else {
                end = indMeio - 1;
            }
        }

        return -1;
    }

    private static int buscaBinariaRecursiva(int n, int[] vetor) {
        return buscaBinariaRecursiva(n, vetor, 0, vetor.length - 1);
    }

    private static int buscaBinariaRecursiva(int n, int[] vetor, int start, int end) {
        if(start > end) return -1;
        int indMeio = (start + end) / 2;

        if(vetor[indMeio] == n) {
            return indMeio;

        } else if(vetor[indMeio] < n) {
            start = indMeio + 1;
        } else {
            end = indMeio - 1;
        }

        return buscaBinariaRecursiva(n, vetor, start, end);
    }

    private static void mergesort(int[] vetor) {
        if (vetor.length == 1) return;

        int[] esquerda = new int[vetor.length / 2];
        int[] direita = new int[vetor.length - esquerda.length];

        for (int i = 0; i < esquerda.length; i++) {
            esquerda[i] = vetor[i];
        }

        for (int i = esquerda.length; i < vetor.length; i++) {
            direita[i - esquerda.length] = vetor[i];
        }

        mergesort(esquerda);
        mergesort(direita);

        //Swap
        int i = 0, j = 0, k = 0; //Esquerda, Direita e Final

        while (i < esquerda.length && j < direita.length) {
            if (esquerda[i] < direita[j]) {
                vetor[k] = esquerda[i];
                i++;
            } else {
                vetor[k] = direita[j];
                j++;
            }
            k++;
        }

        while (i < esquerda.length) {
            vetor[k] = esquerda[i];
            i++;
            k++;
        }

        while (j < direita.length) {
            vetor[k] = direita[j];
            j++;
            k++;
        }
    }
}
