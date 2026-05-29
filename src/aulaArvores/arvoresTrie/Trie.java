package aulaArvores.arvoresTrie;

public class Trie<Value> {

    private final static int R = 256; //Tamanho do alfabeto

    private static class No {
        Object value;
        No[] ponteiros = new No[R];
    }

    private No raiz;

    public Trie(char[] alfabeto) {
        this.raiz = new No();
    }

    public Value get(String key) {
        No x = get(key, this.raiz, 0);
        return x == null ? null : (Value) x.value;
    }

    private No get(String key, No x, int d) {
        if(x == null) return null; //Valor ausente

        if(key.length() == d) return x; //Achou o nó que tem o valor

        char c = key.charAt(d); //Caracter que representa um índice no array ponteiros
        return get(key, x.ponteiros[c], d+1); //Próximo nó

    }

    public void put(String key, Value val) { this.raiz = put(key, val, this.raiz, 0); }

    private No put(String key, Value val, No x, int d) {
        if(x == null) x = new No(); //Cria novo nó se não existir
        if(d == key.length()) { //Achou o nó para inserir
            x.value = val; //Insere
            return x;
        }

        char c = key.charAt(d); //Caracter que representa um índice no array ponteiros
        x.ponteiros[c] = put(key, val, x.ponteiros[c], d+1); //Próximo nó
        return x;
    }

    public void delete(String key) {

    }

    /*public String longestPrefixOf(String s) {

    }*/

}
