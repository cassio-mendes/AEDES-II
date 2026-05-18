package aulaArvores.arvoresAVL;

import java.util.ArrayList;
import java.util.List;

public class ArvoreBBB {
    private static class No {
        Item reg;
        No esq, dir;
        int profundidade, balanceamento;
    }

    private No raiz;
    boolean mudouAltura;

    public void printArvore() {
        printArvore(this.raiz);
    }

    private void printArvore(No node) {
        System.out.println("Profundidade = " + node.profundidade + " | Valor = " + node.reg.recuperaChave());

        if(node.esq != null) printArvore(node.esq);
        if(node.dir != null) printArvore(node.dir);
    }

    private No insere(Item reg, No p, int profundidade, boolean ok) {
        if (p == null) {
            p = new No();
            p.reg = reg;
            p.esq = null;
            p.dir = null;
            p.profundidade = profundidade;
            ok = true;
            p.balanceamento = 0;
            this.mudouAltura = true;

        } else if (reg.compara(p.reg) == 0) ok = false; //Não insere
        else if (reg.compara(p.reg) < 0) {
            p.esq = insere(reg, p.esq, profundidade + 1, ok);

            if(this.mudouAltura) { //Precisa rebalancear
                switch(p.balanceamento) {
                    case 1:
                        p.balanceamento = 0;
                        this.mudouAltura = false; break;

                    case 0:
                        p.balanceamento = -1; break; //mudouAltura continua true

                    case -1: //Precisa rebalancear!!
                        if(p.esq.balanceamento == 1) {

                            //Rotação Dupla ED:
                            No filho = p.esq; //A
                            No neto = filho.dir; //B

                            filho.dir = neto.esq; //S2
                            neto.esq = filho; //A

                            p.esq = neto.dir; //S3
                            neto.dir = p; //C

                            switch(neto.balanceamento) {
                                case -1:
                                    p.balanceamento = 1; //Inseriu X1
                                    filho.balanceamento = 0;
                                    neto.balanceamento = 0; break;

                                case 1:
                                    p.balanceamento = 0; //Inseriu X2
                                    filho.balanceamento = -1;
                                    neto.balanceamento = 0; break;

                                case 0:
                                    p.balanceamento = 0; //Inseriu B
                                    filho.balanceamento = 0;
                                    neto.balanceamento = 0;
                            }

                            p = neto; //B
                            this.mudouAltura = false;
                        } else {
                            //Rotação Simples EE:
                            No filho = p.esq; //A
                            p.esq = filho.dir; //S2
                            filho.dir = p; //B

                            p.balanceamento = 0;
                            filho.balanceamento = 0;

                            p = filho; //A
                            this.mudouAltura = false;
                        }
                }
            }
        } else { //reg.compara(p.reg) > 0
            p.dir = insere(reg, p.dir, profundidade + 1, ok);

            if(this.mudouAltura) { //Precisa rebalancear
                switch(p.balanceamento) {
                    case -1:
                        p.balanceamento = 0;
                        this.mudouAltura = false; break;

                    case 0:
                        p.balanceamento = 1; break; //mudouAltura continua true

                    case 1: //Precisa rebalancear!!
                        if(p.dir.balanceamento == -1) {

                            //Rotação Dupla DE:
                            No filho = p.dir; //C
                            No neto = filho.esq; //B

                            p.dir = neto.esq; //S2
                            neto.esq = p; //A
                            filho.esq = neto.dir; //S3
                            neto.dir = filho; //C

                            switch(neto.balanceamento) {
                                case -1:
                                    p.balanceamento = 0;
                                    filho.balanceamento = 1; //Inseriu X1
                                    neto.balanceamento = 0; break;

                                case 1:
                                    p.balanceamento = -1; //Inseriu X2
                                    filho.balanceamento = 0;
                                    neto.balanceamento = 0; break;

                                case 0:
                                    p.balanceamento = 0; //Inseriu B
                                    filho.balanceamento = 0;
                                    neto.balanceamento = 0; break;
                            }

                            p = neto; //B
                            this.mudouAltura = false;

                        } else {
                            //Rotação Simples DD:
                            No filho = p.dir; //B
                            p.dir = filho.esq; //S2
                            filho.esq = p; //A

                            p.balanceamento = 0;
                            filho.balanceamento = 0;

                            p = filho; //B
                            this.mudouAltura = false;
                        }
                }
            }

        }
        return p;
    }

    public ArvoreBBB ordenaABB() {
        //Armazena os valores da árvore em ordem crescente
        List<Object> vetor = new ArrayList<>();
        percorreABB(vetor, this.raiz);

        //Preenche a nova árvore recursivamente
        ArvoreBBB retorno = new ArvoreBBB();
        preencheArvoreBalanceada(retorno, vetor);
        return retorno;
    }

    private void preencheArvoreBalanceada(ArvoreBBB retorno, List<Object> vetor) {
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

    private No antecessor(No q, No r) {
        if (r.dir != null) r.dir = antecessor(q, r.dir);
        else {
            q.reg = r.reg;
            r = r.esq;
        }
        return r;
    }

    private Item pesquisa(Item reg, No p) {
        if (p == null) return null; // @{\it Registro n\~ao econtrado}@
        else if (reg.compara(p.reg) < 0) return pesquisa(reg, p.esq);
        else if (reg.compara(p.reg) > 0) return pesquisa(reg, p.dir);
        else return p.reg;
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

    public ArvoreBBB() {
        this.raiz = null;
    }

    public Item pesquisa(Item reg) {
        return this.pesquisa(reg, this.raiz);
    }

    public void insere(Item reg) {
        this.raiz = this.insere(reg, this.raiz, 1, true);
    }

    public void retira(Item reg) {
        this.raiz = this.retira(reg, this.raiz);
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
