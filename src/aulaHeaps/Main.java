package aulaHeaps;

public class Main {

    static void main(String[] args) {
        heapsort(new Item[]{new MeuItem(21), new MeuItem(9), new MeuItem(10),
                new MeuItem(1), new MeuItem(3), new MeuItem(2), new MeuItem(4)}, 7);
    }

    static void heapsort(Item[] itens, int n) {
        HeapMax heap = new HeapMax(itens);
        int direita = n;

        heap.constroi(); //Constroi o Heap
        while(direita > 1) { //Ordena o vetor em ordem crescente (Maior prioridade == Menor chave)
            Item x = itens[1];
            itens[1] = itens[direita];
            itens[direita] = x;
            direita--;
            heap.refaz(1, direita);
        }
    }

}
