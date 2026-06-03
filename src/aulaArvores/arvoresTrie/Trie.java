package aulaArvores.arvoresTrie;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class Trie<Value> {

    private final static int R = 256; //Tamanho do alfabeto

    private static class No<Value> {
        Value value;

        @SuppressWarnings("unchecked") //Anotation pra ignorar o warning inútil da IDE
        No<Value>[] ponteiros = new No[R];
    }

    private No<Value> raiz;

    public Trie() {
        this.raiz = new No<>();
    }

    public Value get(String key) {
        No<Value> x = get(key, this.raiz, 0);
        return x == null ? null : x.value;
    }

    private No<Value> get(String key, No<Value> x, int d) {
        if(x == null) return null; //Valor ausente

        if(key.length() == d) return x; //Achou o nó que tem o valor

        char c = key.charAt(d); //Caracter que representa um índice no array ponteiros
        return get(key, x.ponteiros[c], d+1); //Próximo nó
    }

    public void put(String key, Value val) { this.raiz = put(key, val, this.raiz, 0); }

    private No<Value> put(String key, Value val, No<Value> x, int d) {
        if(x == null) x = new No<>(); //Cria novo nó se não existir
        if(d == key.length()) { //Achou o nó para inserir
            x.value = val; //Insere
            return x;
        }

        char c = key.charAt(d); //Caracter que representa um índice no array ponteiros
        x.ponteiros[c] = put(key, val, x.ponteiros[c], d+1); //Próximo nó
        return x;
    }

    public void delete(String key) { this.raiz = delete(key, this.raiz, 0); }

    private No<Value> delete(String key, No<Value> x, int d) {
        if(x == null) return null;

        if(d == key.length()) {
            x.value = null; //Apaga o registro atual
        } else {
            char c = key.charAt(d);
            x.ponteiros[c] = delete(key, x.ponteiros[c], d+1);
        }

        //O nó atual tem registro diferente de nulo?
        if(x.value != null) return x; //Caso 1: A ramificação atual permanece inalterada

        for(No<Value> p : x.ponteiros)
            //Se houver alguma ramificação diferente de nulo:
            if(p != null) return x; //Caso 2: A ramificação atual permanece inalterada

        //Todas as ramificações do No atual são nulas e o valor atual também é null:
        //Caso 3: esta ramificação desaparece
        return null;
    }

    //Retorna uma coleção com todas as chaves que tem o prefixo s
    public Iterable<String> keysWithPrefix(String prefixo) {
        Queue<String> queue = new LinkedList<>(); //Fila
        collect(get(prefixo, this.raiz, 0), prefixo, queue);
        return queue;
    }

    //Preenche a fila com todas as chaves com o prefixo atual que possuem valores na árvore
    private void collect(No<Value> x, String prefixo, Queue<String> queue) {

        if(x == null) return; //Acabou a busca nessa ramificação
        if(x.value != null) queue.add(prefixo); //Adiciona a chave que possui um valor na árvore

        //Atualiza o prefixo e pesquisa todas as ramificações do nó atual
        for(char c = 0; c < R; c++)
            collect(x.ponteiros[c], prefixo + c, queue);
    }

    //Retorna uma coleção com todas as chaves que 'casam' com s quando '.' é um coringa
    //Exemplos: Se s == "s.e", a coleção poderia incluir "she" e "see"
    public Iterable<String> keysThatMatch(String s) {
        Queue<String> queue = new ArrayDeque<>();
        char c = s.charAt(0);
        String chave = String.valueOf(c);

        return keysThatMatch(s, chave, 0, this.raiz, queue);
    }

    private Iterable<String> keysThatMatch(String s, String chave, int d, No<Value> x, Queue<String> queue) {
        if(x == null) return null;

        if(x.value != null) {
            queue.add(chave);
        }

        char c = s.charAt(d);

    }

    /*Retorna o prefixo mais longo com valor não nulo da chave s
    public String longestPrefixOf(String s) {

    }*/

}
