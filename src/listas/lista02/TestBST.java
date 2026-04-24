package listas.lista02;

public class TestBST {

    static void main() {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.insere(new MeuItem(1));
        arvore.insere(new MeuItem(10));
        arvore.insere(new MeuItem(2));
        arvore.insere(new MeuItem(8));
        arvore.insere(new MeuItem(5));

        arvore.imprimeOrdemDecrescente();
        System.out.println("\n" + arvore.checkBST());
        //arvore.alteraChave(new MeuItem(2), 20);
        arvore.imprimeOrdemDecrescente();
        System.out.println("\n" + arvore.checkBST());

        arvore.preenchePai();
        arvore.printPais();

        System.out.println("\nAltura do nó 5: " + arvore.getAlturaNo(5));
        System.out.println("Profundidade do nó 8: " + arvore.getProfundidadeNo(8));
        System.out.println("Número de filhos do nó 10: " + arvore.getNumFilhos(10));
        System.out.print("Impressão por níveis: ");
        arvore.imprimeNiveis();

        /*ArvoreBinaria arvore2 = new ArvoreBinaria();
        arvore2.insere(new MeuItem(10));
        arvore2.insere(new MeuItem(2));
        arvore2.insere(new MeuItem(20));
        arvore2.insere(new MeuItem(1));
        arvore2.insere(new MeuItem(3));
        arvore2.insere(new MeuItem(12));
        arvore2.insere(new MeuItem(25));

        System.out.println();
        arvore2.imprimeNiveis();*/
    }

}
