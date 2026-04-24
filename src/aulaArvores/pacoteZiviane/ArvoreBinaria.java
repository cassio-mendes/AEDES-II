package aulaArvores.pacoteZiviane;

import java.util.ArrayList;
import java.util.List;

public class ArvoreBinaria {
    private static class No {
        Item reg;
        No esq, dir;
        int profundidade;
    }

    private No raiz;

    public void printArvore() {
        printArvore(this.raiz);
    }

    private void printArvore(No node) {
        System.out.println("Profundidade = " + node.profundidade + " | Valor = " + node.reg.recuperaChave());

        if(node.esq != null) printArvore(node.esq);
        if(node.dir != null) printArvore(node.dir);
    }

    public ArvoreBinaria ordenaABB() {
        //Armazena os valores da árvore em ordem crescente
        List<Object> vetor = new ArrayList<>();
        percorreABB(vetor, this.raiz);

        //Preenche a nova árvore recursivamente
        ArvoreBinaria retorno = new ArvoreBinaria();
        preencheArvoreBalanceada(retorno, vetor);
        return retorno;
    }

    private void preencheArvoreBalanceada(ArvoreBinaria retorno, List<Object> vetor) {
        if(vetor.isEmpty()) return;

        int indMeio = vetor.size()/2;
        List<Object> esquerda = new ArrayList<>();
        List<Object> direita = new ArrayList<>();
        Object itemMeio = vetor.get(indMeio);

        for (int i = 0; i < indMeio; i++) {
            esquerda.add(vetor.get(i));
        }

        for (int i = indMeio + 1; i < vetor.size(); i++) {
            direita.add(vetor.get(i));
        }

        retorno.insere(new MeuItem((Integer)itemMeio));
        preencheArvoreBalanceada(retorno, esquerda);
        preencheArvoreBalanceada(retorno, direita);
    }

    private void percorreABB(List<Object> vetor, No node) {
        if(node.esq != null) percorreABB(vetor, node.esq);
        vetor.add(node.reg.recuperaChave());
        if(node.dir != null) percorreABB(vetor, node.dir);
    }

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

        } else if (reg.compara(p.reg) < 0) p.esq = insere(reg, p.esq, profundidade+1);
        else if (reg.compara(p.reg) > 0) p.dir = insere(reg, p.dir, profundidade+1);
        else System.out.println("Erro: Registro ja existente");
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

    public ArvoreBinaria() {
        this.raiz = null;
    }

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
