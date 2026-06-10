package aulaArvores.pacoteZiviane;

import java.io.*;

public class CriaArvore {

    static void main(String[] args) throws Exception {
        ArvoreBinaria dicionario = new ArvoreBinaria();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int chave = Integer.parseInt(in.readLine());

        while (chave > 0) {
            MeuItem item = new MeuItem(chave);
            dicionario.insere(item);
            chave = Integer.parseInt(in.readLine());
        }

        System.out.println("Árvore em ordem:");
        dicionario.imprime();
        dicionario.printArvore();

        System.out.println("Árvore pós alteração de chave");
        dicionario.alteraChave(new MeuItem(99), 5);
        dicionario.imprime();

        System.out.print("A árvore é uma ABB: ");
        System.out.println(dicionario.checkBST());

        System.out.print("A árvore é uma AVL: ");
        System.out.println(dicionario.checkAVL());

        ArvoreBinaria arvoreBalanceada = dicionario.ordenaABB();
        System.out.println("Nova árvore:");
        arvoreBalanceada.imprime();
        arvoreBalanceada.printArvore();
    }



}
