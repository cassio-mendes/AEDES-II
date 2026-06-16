package aulaArvores.arvoresB;

public class MeuItemChar implements Item {
    private char chave;

    public MeuItemChar(char chave) {
        this.chave = chave;
    }

    public int compara(Item it) {
        MeuItemChar item = (MeuItemChar) it;
        if (this.chave < item.chave) return -1;
        else if (this.chave > item.chave) return 1;
        return 0;
    }

    public void alteraChave(Object chave) {
        this.chave = (Character) chave;
    }

    public Object recuperaChave() {
        return this.chave;
    }

    public String toString() {
        return "" + this.chave;
    }

}