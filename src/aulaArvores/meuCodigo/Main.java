package aulaArvores.meuCodigo;

public class Main {

    static void main(String[] args) {
        //Preenchendo árvore
        ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();

        Integer[] numeros = {20, 5, 22, 2, 9, 21, 98};
        for (int i = 0; i < numeros.length; i++) {
            arvore.insere(numeros[i]);
        }

        /*Character[] chars = {'C', 'A', 'B', 'M', 'Z'};
        for (int i = 0; i < chars.length; i++) {
            arvore.insere(chars[i]);
        }*/

        //Imprimindo valores e profundidade dos nós
        arvore.printArvore();
        System.out.println("Altura: " + arvore.getAltura() + "\n"); //Altura da árvore

        //Imprimindo valores em diferentes ordens
        System.out.print("Pré-Ordem: ");
        arvore.printPreOrdem(); //Raiz -> Esquerda -> Direita

        System.out.print("\nEm Ordem: ");
        arvore.printEmOrdem(); //Esquerda -> Raiz -> Direita

        System.out.print("\nPós-Ordem: ");
        arvore.printPosOrdem(); //Esquerda -> Direita -> Raiz

        System.out.print("\nOrdem decrescente: ");
        arvore.printDecrescente();

        System.out.println("\n");
        System.out.println("Total da árvore: " + arvore.somarNodes());
        //buscarCharacter('A', arvore);
        //buscarCharacter('Q', arvore);

        //System.out.println("Impressão por níveis: ");
        //arvore.printNiveis();
    }

    //Buscando valores na árvore
    static void buscarInteger(int numero, ArvoreBinariaBusca arvore) {
        String resposta = arvore.get(numero) != null ? "Existe" : "Não existe";
        System.out.println("Número " + numero + ": " + resposta);
    }

    static void buscarCharacter(char caracter, ArvoreBinariaBusca arvore) {
        String resposta = arvore.get(caracter) != null ? "Existe" : "Não existe";
        System.out.println("Letra " + caracter + ": " + resposta);
    }

}
