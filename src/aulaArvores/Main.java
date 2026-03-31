package aulaArvores;

public class Main {

    static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();

        Double[] numeros = {4.0, 7.0, 16.0, 20.0, 37.0, 38.0, 43.0};
        for (int i = 0; i < numeros.length; i++) {
            arvore.insere(numeros[i]);
        }

        arvore.printArvore();
    }

}
