package aulaArvores;

import java.util.PriorityQueue;

public class ArvoreBinariaBusca {

    private static class Node { Item valor; int profundidade; }

    private Node raiz = new Node();
    private ArvoreBinariaBusca subArvoreEsq, subArvoreDir;
    private int numNodes = 0;

    public ArvoreBinariaBusca() { this.raiz.profundidade = 0; }

    private ArvoreBinariaBusca(int profundidade) {
        this.raiz.profundidade = profundidade;
    }

    public void insere(Integer valor) {
        if(this.raiz.valor == null) {
            this.raiz.valor = new MeuItem(valor);

        } else {
            MeuItem novoValor = new MeuItem(valor);
            this.inserirNovaFolha(novoValor);
        }

        this.numNodes++;
    }

    public void insere(Character c) {
        if(this.raiz.valor == null) {
            this.raiz.valor = new ItemChar(c);

        } else {
            ItemChar novoValor = new ItemChar(c);
            this.inserirNovaFolha(novoValor);
        }

        this.numNodes++;
    }

    private void inserirNovaFolha(Item novoValor) {
        if(this.raiz.valor.compara(novoValor) == 1) { //Se o novo valor é menor que a raiz, vai pra esquerda
            if(this.subArvoreEsq == null)
                this.subArvoreEsq = new ArvoreBinariaBusca(this.raiz.profundidade+1);

            if(novoValor instanceof MeuItem)
                this.subArvoreEsq.insere((Integer)novoValor.recuperaChave());
            else
                this.subArvoreEsq.insere((Character)novoValor.recuperaChave());

        } else {
            if (this.subArvoreDir == null)
                this.subArvoreDir = new ArvoreBinariaBusca(this.raiz.profundidade + 1);

            if(novoValor instanceof MeuItem)
                this.subArvoreDir.insere((Integer)novoValor.recuperaChave());
            else
                this.subArvoreDir.insere((Character)novoValor.recuperaChave());
        }
    }

    public void printArvore() {
        System.out.println("Profundidade = " + this.raiz.profundidade + " | Valor = " + this.raiz.valor.recuperaChave());

        if(this.subArvoreEsq != null)
            this.subArvoreEsq.printArvore();

        if(this.subArvoreDir != null)
            this.subArvoreDir.printArvore();
    }

    public Object get(Object valor) {

        if(valor instanceof Integer) {
            Integer chaveItem = (Integer)this.raiz.valor.recuperaChave();
            if(chaveItem.equals(valor)) return chaveItem;

        } else {
            Character chaveItem = (Character)this.raiz.valor.recuperaChave();
            if(chaveItem.equals(valor)) return chaveItem;
        }

        if(this.subArvoreEsq != null) return this.subArvoreEsq.get(valor);
        if(this.subArvoreDir != null) return this.subArvoreDir.get(valor);
        return null;
    }

    //altura = log(base 2) n° de nós ---> APENAS PARA ÁRVORES BALANCEADAS!!
    public int getAltura() {
        return (int)(Math.floor(Math.log(this.numNodes) / Math.log(2)));
    }

    public void remover(Integer valor) {
        removerValor(valor);
    }

    private ArvoreBinariaBusca removerValor(Integer valor) {
        MeuItem novoValor = new MeuItem(valor);

        if(this.subArvoreEsq != null && this.raiz.valor.compara(novoValor) == -1) {
            this.subArvoreEsq = this.subArvoreEsq.removerValor(valor);
            return this;
        }

        if(this.subArvoreDir != null && this.raiz.valor.compara(novoValor) == 1) {
            this.subArvoreDir = this.subArvoreDir.removerValor(valor);
            return this;
        }

        //Achou o nó a ser removido
        return null;
    }

    public void printPreOrdem() {
        System.out.print(this.raiz.valor.recuperaChave() + " ");
        if(this.subArvoreEsq != null) { this.subArvoreEsq.printPreOrdem(); }
        if(this.subArvoreDir != null) { this.subArvoreDir.printPreOrdem(); }
    }

    public void printEmOrdem() { //Em ordem crescente
        if(this.subArvoreEsq != null) { this.subArvoreEsq.printEmOrdem(); }
        System.out.print(this.raiz.valor.recuperaChave() + " ");
        if(this.subArvoreDir != null) { this.subArvoreDir.printEmOrdem(); }
    }

    public void printPosOrdem() {
        if(this.subArvoreEsq != null) { this.subArvoreEsq.printPosOrdem(); }
        if(this.subArvoreDir != null) { this.subArvoreDir.printPosOrdem(); }
        System.out.print(this.raiz.valor.recuperaChave() + " ");
    }

    public void printDecrescente() {
        if(this.subArvoreDir != null) { this.subArvoreDir.printDecrescente(); }
        System.out.print(this.raiz.valor.recuperaChave() + " ");
        if(this.subArvoreEsq != null) { this.subArvoreEsq.printDecrescente(); }
    }

    public void printNiveis() { //Imprime cada nível, em ordem crescente
        PriorityQueue<Character> fila = new PriorityQueue<>();

        ItemChar item = (ItemChar) this.raiz.valor;
        fila.add((Character)item.recuperaChave());

        boolean flag = true;

        while(!fila.isEmpty()) {
            Character i = fila.poll();
            System.out.print(i + " ");

            if(flag) {
                item = (ItemChar) this.subArvoreEsq.raiz.valor;
                fila.add((Character)item.recuperaChave());
            } else {
                item = (ItemChar) this.subArvoreDir.raiz.valor;
                fila.add((Character)item.recuperaChave());
            }

            flag = !flag;
        }
    }

}
