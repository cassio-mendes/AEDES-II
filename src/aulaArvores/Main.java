package aulaArvores;

public class Main {

    static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria(5, "Arroz");
        arvore.insere(2, "Feijão");
        arvore.insere(6, "Batata");
        arvore.insere(7, "Azeite");
        arvore.printArvore();

        System.out.println("\n" + arvore.get(2));
        System.out.println(arvore.get(5));
        System.out.println(arvore.get(10));
    }

}
