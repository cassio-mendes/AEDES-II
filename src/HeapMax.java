
public class HeapMax {

    private Item[] itens;
    private int n; //Último índice

    public HeapMax(Item[] itens) {
        this.itens = itens;
        this.n = this.itens.length - 1;
    }

    public Item max() { return this.itens[1]; }

    public void refaz(int esquerda, int direita) {
        int j = esquerda * 2;
        Item x = this.itens[esquerda];

        while(j <= direita) {

        }

    }

}
