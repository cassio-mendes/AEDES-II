package aulaArvores;

public class Main {

    static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();

        Integer[] numeros = {20, 5, 22, 2, 9, 21, 98};
        for (int i = 0; i < numeros.length; i++) {
            arvore.insere(numeros[i]);
        }

        arvore.printArvore();
        System.out.println("Altura: " + arvore.getAltura() + "\n");

        System.out.print("Pré-Ordem: ");
        arvore.printPreOrdem();

        System.out.print("\nEm Ordem: ");
        arvore.printEmOrdem();

        System.out.print("\nPós-Ordem: ");
        arvore.printPosOrdem();
    }

}
