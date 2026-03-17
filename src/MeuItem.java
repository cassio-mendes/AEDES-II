
public class MeuItem implements Item {

    private int chave; //Valor

    public MeuItem(int chave) { this.chave = chave; }

    @Override
    public int compara(Item i) {
        MeuItem item = (MeuItem) i;

        if(this.chave > item.chave) return 1;
        else if(this.chave < item.chave) return -1;
        else return 0;
    }

    @Override
    public void alteraChave(Object chave) {

    }

    @Override
    public Object recuperaChave() {
        return null;
    }
}
