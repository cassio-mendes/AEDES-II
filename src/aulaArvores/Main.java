package aulaArvores;

public class Main {

    static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();

        int[] numeros = {4, 7, 16, 20, 37, 38, 43};
        for (int i = 0; i < numeros.length; i++) {
            arvore.insere(i, numeros[i]);
        }

        arvore.printArvore();
    }

}
