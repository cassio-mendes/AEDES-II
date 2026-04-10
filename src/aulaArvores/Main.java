package aulaArvores;

public class Main {

    static void main(String[] args) {
        //Preenchendo árvore
        ArvoreBinaria arvore = new ArvoreBinaria();

        /*Integer[] numeros = {20, 5, 22, 2, 9, 21, 98};
        for (int i = 0; i < numeros.length; i++) {
            arvore.insere(numeros[i]);
        }*/

        Character[] chars = {'C', 'A', 'B', 'M', 'Z'};
        for (int i = 0; i < chars.length; i++) {
            arvore.insere(chars[i]);
        }

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

        //buscarInteger(arvore);
        buscarCharacter(arvore);
    }

    static void buscarInteger(ArvoreBinaria arvore) {
        //Buscando valores na árvore
        int chave = 5;
        String resposta = arvore.get(chave) != null ? "Existe" : "Não existe";
        System.out.println("\n\nNúmero " + chave + ": " + resposta);

        chave = 100;
        resposta = arvore.get(chave) != null ? "Existe\n" : "Não existe\n";
        System.out.println("Número " + chave + ": " + resposta);
    }

    static void buscarCharacter(ArvoreBinaria arvore) {
        //Buscando valores na árvore
        char chave = 'A';
        String resposta = arvore.get(chave) != null ? "Existe" : "Não existe";
        System.out.println("\n\nLetra " + chave + ": " + resposta);

        chave = 'Q';
        resposta = arvore.get(chave) != null ? "Existe\n" : "Não existe\n";
        System.out.println("Letra " + chave + ": " + resposta);
    }

}
