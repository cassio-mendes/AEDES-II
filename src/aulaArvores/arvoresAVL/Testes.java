package aulaArvores.arvoresAVL;

public class Testes {

    static void main(String[] args) {
        //Questão 1
        ArvoreAVL arvore = new ArvoreAVL();
        for (int i = 1; i < 15; i++) {
            arvore.insere(new MeuItem(i));
        }
        arvore.preenchePai();
        arvore.printArvore();

        //Questão 2
        arvore.retira(new MeuItem(10));
        arvore.retira(new MeuItem(2));
        arvore.retira(new MeuItem(9));
        System.out.println("\nQuestão 2:");
        arvore.printArvore();

        //Questão 3
        ArvoreAVL arvore2 = new ArvoreAVL();

        int[] nums = {70, 61, 90, 65, 82, 100, 87, 109};
        for (int i = 0; i < nums.length; i++) {
            arvore2.insere(new MeuItem(nums[i]));
        }

        System.out.println("\nQuestão 3:");
        arvore2.preenchePai();
        arvore2.printArvore();
        System.out.println("\nPrint em níveis");
        arvore2.printNiveis();

        //Questão 4 a)
        System.out.println("\nQuestão 4:");
        System.out.println("Altura do nó 90: " + arvore2.getAltura(new MeuItem(90)));
        System.out.println("Altura do nó 70: " + arvore2.getAltura(new MeuItem(70)));
        System.out.println("Altura do nó 87: " + arvore2.getAltura(new MeuItem(87)));

        //Questão 4 b)
        System.out.println("\nProfundidade do nó 61: " + arvore2.getProfundidade(new MeuItem(61)));
        System.out.println("Profundidade do nó 65: " + arvore2.getProfundidade(new MeuItem(65)));
        System.out.println("Profundidade do nó 100: " + arvore2.getProfundidade(new MeuItem(100)));

        //Questão 4 c)
        System.out.println("\nTamanho da subárvore 61: " + arvore2.getSize(new MeuItem(61)));
        System.out.println("Tamanho da subárvore 65: " + arvore2.getSize(new MeuItem(65)));
        System.out.println("Tamanho da subárvore 70: " + arvore2.getSize(new MeuItem(70)));
        System.out.println("Tamanho da subárvore 90: " + arvore2.getSize(new MeuItem(90)));

        //Questão 4 d)
        System.out.println();
        arvore2.preenchePai();
        arvore2.printArvore();

        //Questão 5
        System.out.println("\nA Árvore 2 é AVL? Resposta: " + arvore2.checkAVL());
    }

}
