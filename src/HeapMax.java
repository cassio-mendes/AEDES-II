
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
            if((j < direita) && (this.itens[j].compara(this.itens[j + 1]) < 0)) {
                j++;
            }

            if(x.compara(this.itens[j]) >= 0) break;
            this.itens[esquerda] = this.itens[j];

            esquerda = j;
            j = esquerda * 2;
        }

        this.itens[esquerda] = x;
    }

    public void constroi() {
        int esquerda = this.n / 2 + 1;

        while(esquerda > 1) {
            esquerda--;
            this.refaz(esquerda, this.n);
        }
    }

    public Item retiraMax() throws Exception {
        if(this.n < 1) throw new Exception("Erro: Heap vazio");
        else {
            Item maximo = this.itens[1];
            this.itens[1] = this.itens[this.n--];
            this.refaz(1, this.n);

            return maximo;
        }
    }

    public void aumentaChave(int i, Object chaveNova) throws Exception {
        if(chaveNova == null) {
            throw new Exception("Erro: nova chave não pode ser nula");
        }

        Item x = this.itens[i];
        x.alteraChave(chaveNova);

        while((i > 1) && (x.compara(this.itens[i/2]) >= 0)) {
            this.itens[i] = this.itens[i/2];
            i /= 2;
        }

        this.itens[i] = x;
    }

    public void insere (Item x) throws Exception {
        this.n++;
        if(this.n == this.itens.length) throw new Exception("Erro: Heap cheio");

        Object chaveNova = x.recuperaChave();
        this.itens[this.n] = x;
        this.itens[n].alteraChave(new Integer(Integer.MIN_VALUE));
        this.aumentaChave(this.n, chaveNova);
    }

}
