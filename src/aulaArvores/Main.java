package aulaArvores;

public class Main {

    static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();

        Double[] numeros = {20.0, 5.0, 22.0, 2.0, 9.0, 21.0, 98.0};
        for (int i = 0; i < numeros.length; i++) {
            arvore.insere(numeros[i]);
        }

        arvore.printArvore();
        System.out.println("Altura: " + arvore.getAltura());
    }

}
