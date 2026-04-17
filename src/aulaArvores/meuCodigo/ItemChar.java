package aulaArvores.meuCodigo;

public class ItemChar implements Item {

    private char chave; //Valor

    public ItemChar(char chave) {
        this.chave = chave;
    }

    @Override
    public int compara(Item i) {
        ItemChar item = (ItemChar)i;

        if(this.chave > item.chave) return 1;
        else if(this.chave < item.chave) return -1;
        else return 0;
    }

    @Override
    public void alteraChave(Object chave) {
        this.chave = (Character)chave;
    }

    @Override
    public Object recuperaChave() {
        return this.chave;
    }
}
