
public class MeuItem implements Item {

    private int chave; //Valor

    public MeuItem(int chave) { this.chave = chave; }

    @Override
    public int comparar(Object it) {
        MeuItem item = (MeuItem) it;

        if(this.chave > item.chave) return 1;
        else if(this.chave < item.chave) return -1;
        else return 0;
    }

    @Override
    public void alteraChave(Object chaveNova) {
        this.chave = (Integer) chaveNova;
    }

    @Override
    public Object recuperaChave() {
        return this.chave;
    }
}
