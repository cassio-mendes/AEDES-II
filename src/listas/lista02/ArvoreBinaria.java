package listas.lista02;

import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBinaria {
    private static class No {
        Item reg;
        No esq, dir, pai;
        int profundidade, N;
    }

    private No raiz;
    private int altura;

    public int getNumFilhos(int chave) {
        MeuItem item = new MeuItem(chave);
        return getNumFilhos(item, this.raiz);
    }

    private int getNumFilhos(Item item, No node) {
        if(item.compara(node.reg) == 0)
            return numFilhos(node, 0);

        if(node.esq != null && item.compara(node.reg) < 0)
            return getNumFilhos(item, node.esq);

        if(node.dir != null && item.compara(node.reg) > 0)
            return getNumFilhos(item, node.dir);

        return -1;
    }

    private int numFilhos(No node, int numero) {
        if(node.esq != null) return numFilhos(node.esq, numero+1);
        if(node.dir != null) return numFilhos(node.dir, numero+1);
        return numero;
    }

    public int getProfundidadeNo(int chave) {
        MeuItem item = new MeuItem(chave);
        return getProfundidadeNo(item,  this.raiz);
    }

    private int getProfundidadeNo(Item item, No node) {
        if(item.compara(node.reg) == 0)
            return node.profundidade;

        if(node.esq != null && item.compara(node.reg) < 0)
            return getProfundidadeNo(item, node.esq);

        if(node.dir != null && item.compara(node.reg) > 0)
            return getProfundidadeNo(item, node.dir);

        return -1;
    }

    public int getAlturaNo(int chave) {
        MeuItem item = new MeuItem(chave);
        this.altura = 0;

        return getAlturaNo(item, this.raiz);
    }

    private int getAlturaNo(Item item, No node) {
        if(item.compara(node.reg) == 0)
            return this.altura;

        if(node.esq != null && item.compara(node.reg) < 0) {
            this.altura++;
            return getAlturaNo(item, node.esq);
        }

        if(node.dir != null && item.compara(node.reg) > 0) {
            this.altura++;
            return getAlturaNo(item, node.dir);
        }

        return -1;
    }

    public void imprimeNiveis() {
        if (this.raiz == null) return;

        Queue<No> fila = new LinkedList<>();
        fila.add(this.raiz);

        while (!fila.isEmpty()) {
            No atual = fila.poll();
            System.out.print(atual.reg.recuperaChave() + " ");

            if (atual.esq != null) fila.add(atual.esq);
            if (atual.dir != null) fila.add(atual.dir);
        }
        System.out.println();
    }

    public int size() {
        return size(this.raiz);
    }

    private int size(No x) {
        if (x == null) return 0;
        return x.N;
    }

    public void preenchePai() {
        this.raiz.pai = null;
        preenchePai(this.raiz);
    }

    private void preenchePai(No node) {
        if (node.esq != null) {
            node.esq.pai = node;
            preenchePai(node.esq);
        }

        if (node.dir != null) {
            node.dir.pai = node;
            preenchePai(node.dir);
        }
    }

    private void printPais(No p) {
        if (p != null) {
            if(p.pai == null)
                System.out.print("null ");
            else
                System.out.print(p.pai.reg.recuperaChave() + " ");
            printPais(p.esq);
            printPais(p.dir);
        }
    }

    public void printPais() { printPais(this.raiz); }

    private void central(No p) { //Em-Ordem
        if (p != null) {
            central(p.esq);
            System.out.println(p.reg.toString());
            central(p.dir);
        }
    }

    private Item pesquisa(Item reg, No p) {
        if (p == null) return null; // @{\it Registro n\~ao econtrado}@
        else if (reg.compara(p.reg) < 0) return pesquisa(reg, p.esq);
        else if (reg.compara(p.reg) > 0) return pesquisa(reg, p.dir);
        else return p.reg;
    }

    private No insere(Item reg, No p, int profundidade) {
        if (p == null) {
            p = new No();
            p.reg = reg;
            p.esq = null;
            p.dir = null;
            p.profundidade = profundidade;
            p.N = 1; // Um novo nó começa com tamanho 1 (ele mesmo)
            return p;
        }

        if (reg.compara(p.reg) < 0) {
            p.esq = insere(reg, p.esq, profundidade + 1);
        } else if (reg.compara(p.reg) > 0) {
            p.dir = insere(reg, p.dir, profundidade + 1);
        } else {
            System.out.println("Erro: Registro ja existente");
            return p; // Retorna sem incrementar se não houve inserção
        }

        // Atualização ansiosa: o tamanho do nó pai é 1 + tamanho das subárvores
        p.N = 1 + size(p.esq) + size(p.dir);

        return p;
    }

    private No antecessor(No q, No r) {
        if (r.dir != null) r.dir = antecessor(q, r.dir);
        else {
            q.reg = r.reg;
            r = r.esq;
        }
        return r;
    }

    private No retira(Item reg, No p) {
        if (p == null) System.out.println("Erro: Registro nao encontrado");
        else if (reg.compara(p.reg) < 0) p.esq = retira(reg, p.esq);
        else if (reg.compara(p.reg) > 0) p.dir = retira(reg, p.dir);
        else {
            if (p.dir == null) p = p.esq;
            else if (p.esq == null) p = p.dir;
            else p.esq = antecessor(p, p.esq);
        }
        return p;
    }

    private void decrescente(No node) {
        if(node.dir != null) { decrescente(node.dir); }
        System.out.print(node.reg.recuperaChave() + " ");
        if(node.esq != null) { decrescente(node.esq); }
    }

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public int getNumNodes() { return this.raiz.N + 1; }

    public Item pesquisa(Item reg) {
        return this.pesquisa(reg, this.raiz);
    }

    public void insere(Item reg) {
        this.raiz = this.insere(reg, this.raiz, 1);
    }

    public void retira(Item reg) {
        this.raiz = this.retira(reg, this.raiz);
    }

    public void imprime() {
        this.central(this.raiz);
    }

    public void imprimeOrdemDecrescente() { this.decrescente(this.raiz); }

    // @{\it M\'etodo para testar o funcionamento da classe}@
    private void testa(No p) {
        if (p == null) return;
        if (p.esq != null) {
            if (p.reg.compara(p.esq.reg) < 0) {
                System.out.println("Erro: Pai " + p.reg.toString() + " menor que filho a esquerda " + p.esq.reg.toString());
                System.exit(1);
            }
        }
        if (p.dir != null) {
            if (p.reg.compara(p.dir.reg) > 0) {
                System.out.println("Erro: Pai " + p.reg.toString() + " maior que filho a direita " + p.dir.reg.toString());
                System.exit(1);
            }
        }
        testa(p.esq);
        testa(p.dir);
    }

    public void testa() {
        this.testa(this.raiz);
    }

    public Item alteraChave(Item chave, Object novaChave) {
        return alteraChave(chave, novaChave, this.raiz);
    }

    private Item alteraChave(Item chave, Object novaChave, No celula) {
        if(celula == null) return null; //A chave não está na árvore

        if(chave.compara(celula.reg) < 0)
            return alteraChave(chave, novaChave, celula.esq); //Vai pra esquerda

        if(chave.compara(celula.reg) > 0)
            return alteraChave(chave, novaChave, celula.dir); //Vai pra direita

        //Achou a chave na árvore
        celula.reg.alteraChave(novaChave); //Altera o valor
        return celula.reg; //Retorna o novo valor alterado
    }

    //Verifica se é uma árvore binária de busca
    public boolean checkBST() {
        return checkBST(this.raiz);
    }

    private boolean checkBST(No celula) {
        if(celula.esq != null && celula.reg.compara(celula.esq.reg) < 0)
            return false;

        if(celula.dir != null && celula.reg.compara(celula.dir.reg) > 0)
            return false;

        if(celula.esq != null) return checkBST(celula.esq);
        if(celula.dir != null) return checkBST(celula.dir);

        return true;
    }

}
