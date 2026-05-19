package aulaArvores.arvoresAVL;

import java.util.LinkedList;

public class ArvoreAVL {
    private static class No {
        Item reg;
        No esq, dir, pai;
        int balanceamento;
    }

    private No raiz;
    boolean mudouAltura;

    public void printArvore() {
        printArvore(this.raiz, 1);
    }

    private void printArvore(No node, int profundidade) {
        Object pai = node.pai == null ? null : node.pai.reg.recuperaChave();
        System.out.println("Profundidade = " + profundidade + " | Valor = " + node.reg.recuperaChave() + " | Pai: " + pai);

        if(node.esq != null) printArvore(node.esq, profundidade + 1);
        if(node.dir != null) printArvore(node.dir, profundidade + 1);
    }

    private No insere(Item reg, No p, boolean ok) {
        if (p == null) {
            p = new No();
            p.reg = reg;
            p.esq = null;
            p.dir = null;
            ok = true;
            p.balanceamento = 0;
            this.mudouAltura = true;

        } else if (reg.compara(p.reg) == 0) ok = false; //Não insere
        else if (reg.compara(p.reg) < 0) {
            p.esq = insere(reg, p.esq, ok);

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
                            p = rotDuplaED(p);
                        } else {
                            //Rotação Simples EE:
                            p = rotSimplesEE(p);
                        }
                }
            }
        } else { //reg.compara(p.reg) > 0
            p.dir = insere(reg, p.dir, ok);

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
                            p = rotDuplaDE(p);
                        } else {
                            //Rotação Simples DD:
                            p = rotSimplesDD(p);
                        }
                }
            }
        }
        return p;
    }

    private No rotSimplesEE(No p) {
        No filho = p.esq; //A
        p.esq = filho.dir; //S2
        filho.dir = p; //B

        p.balanceamento = 0;
        filho.balanceamento = 0;

        p = filho; //A
        this.mudouAltura = false;

        return p;
    }

    private No rotDuplaED(No p) {
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

        return p;
    }

    private No rotSimplesDD(No p) {
        No filho = p.dir; //B
        p.dir = filho.esq; //S2
        filho.esq = p; //A

        p.balanceamento = 0;
        filho.balanceamento = 0;

        p = filho; //B
        this.mudouAltura = false;

        return p;
    }

    private No rotDuplaDE(No p) {
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

        return p;
    }

    private Item pesquisa(Item reg, No p) {
        if (p == null) return null; // @{\it Registro n\~ao econtrado}@
        else if (reg.compara(p.reg) < 0) return pesquisa(reg, p.esq);
        else if (reg.compara(p.reg) > 0) return pesquisa(reg, p.dir);
        else return p.reg;
    }

    private No retira(Item reg, No p) {
        if (p == null) {
            System.out.println("Erro: Registro nao encontrado");
            this.mudouAltura = false;

        } else if (reg.compara(p.reg) < 0) {
            p.esq = retira(reg, p.esq);

            if(this.mudouAltura) {
                switch(p.balanceamento) {
                    case -1:
                        p.balanceamento = 0; break; //mudouAltura não muda

                    case 0:
                        p.balanceamento = 1;
                        this.mudouAltura = false; break;

                    case 1: //Precisa rebalancear!!
                        int balanceamentoIrmao = p.dir.balanceamento;

                        if(balanceamentoIrmao >= 0) { //O filho da direita pode ter balanceamento 1 ou 0
                            // Rotação Simples DD:
                            p = rotSimplesDD(p);

                            if(balanceamentoIrmao == 0) { //Caso especial: irmão de balanceamento neutro
                                p.balanceamento = -1;
                                p.esq.balanceamento = 1;
                                this.mudouAltura = false;
                            }
                        } else {
                            //Rotação Dupla DE:
                            p = rotDuplaDE(p);
                        }
                }
            }

        } else if (reg.compara(p.reg) > 0) {
            p.dir = retira(reg, p.dir);

            if(this.mudouAltura) {
                switch(p.balanceamento) {
                    case 1:
                        p.balanceamento = 0;
                        this.mudouAltura = false; break;

                    case 0:
                        p.balanceamento = -1; break; //mudouAltura não muda

                    case -1: //Precisa rebalancear!!
                        int balanceamentoIrmao = p.esq.balanceamento;

                        if(balanceamentoIrmao <= 0) {
                            //Rotação Simples EE:
                            p = rotSimplesEE(p);

                            if(balanceamentoIrmao == 0) { //Caso especial
                                p.balanceamento = 1;
                                p.dir.balanceamento = -1;
                                this.mudouAltura = false;
                            }
                        } else {
                            //Rotação Dupla ED:
                            p = rotDuplaED(p);
                        }
                }
            }

        } else {
            if (p.dir == null) {
                p = p.esq;
                this.mudouAltura = true; //Encolheu!!

            } else if (p.esq == null) {
                p = p.dir;
                this.mudouAltura = true; //Encolheu!!

            } else {
                p.esq = antecessor(p, p.esq);
            }
        }
        return p;
    }

    private No antecessor(No q, No r) { //q é o nó a ser removido; r é p.esq
        if (r.dir != null) {
            r.dir = antecessor(q, r.dir); //Busca recursiva pelo maior nó da subárvore esquerda

            if(this.mudouAltura) {
                switch(r.balanceamento) {
                    case 1:
                        r.balanceamento = 0; break;

                    case 0:
                        r.balanceamento = -1;
                        this.mudouAltura = false; break;

                    case -1: //Encolheu!!
                        int balanceamentoIrmao = r.esq.balanceamento;

                        if(balanceamentoIrmao <= 0) {
                            r = rotSimplesEE(r);

                            if(balanceamentoIrmao == 0) { //Caso especial
                                r.balanceamento = 1;
                                r.dir.balanceamento = -1;
                                this.mudouAltura = false;
                            }
                        } else {
                            r = rotDuplaED(r);
                        }
                }
            }

        } else { //r é uma folha, o maior nó da subárvore à esquerda de q
            q.reg = r.reg; //Copiamos o valor de r para q (o valor antigo de q desaparece)
            r = r.esq; //r recebe a subávore à esquerda, ajustando os ponteiros

            this.mudouAltura = true; //Encolheu!!
        }

        return r; //Retorna o valor de r
    }

    public void printNiveis() { //Imprime cada nível, em ordem crescente
        LinkedList<No> fila = new LinkedList<>();
        No item = this.raiz;
        fila.add(item);

        while(!fila.isEmpty()) {
            item = fila.poll();
            System.out.print(item.reg.recuperaChave() + " ");

            if(item.esq != null) fila.add(item.esq);
            if(item.dir != null) fila.add(item.dir);
        }
    }

    private int getAltura(Item reg, No p) {
        if(p.esq != null && reg.compara(p.reg) < 0) return getAltura(reg, p.esq);
        if(reg.compara(p.reg) == 0) { //Achou
            return calcularAltura(p, 0, 0);
        }
        if(p.dir != null && reg.compara(p.reg) > 0) return getAltura(reg, p.dir);

        return -1;
    }

    private int calcularAltura(No p, int alturaEsq, int alturaDir) {
        if(p.esq != null) alturaEsq = calcularAltura(p.esq, alturaEsq+1, alturaDir);
        if(p.dir != null) alturaDir = calcularAltura(p.dir, alturaEsq, alturaDir+1);

        return  Math.max(alturaEsq, alturaDir);
    }

    private int getProfundidade(Item reg, No p, int profundidade) {
        if(p.esq != null && reg.compara(p.reg) < 0) return getProfundidade(reg, p.esq, profundidade+1);
        if(reg.compara(p.reg) == 0) return profundidade;
        if(p.dir != null && reg.compara(p.reg) > 0) return getProfundidade(reg, p.dir, profundidade+1);

        return -1;
    }

    private int getSize(Item reg, No p) {
        if(p.esq != null && reg.compara(p.reg) < 0) return getSize(reg, p.esq);
        if(reg.compara(p.reg) == 0) { //Achou
            return calcularSize(p, 0);
        }
        if(p.dir != null && reg.compara(p.reg) > 0) return getSize(reg, p.dir);

        return -1;
    }

    private int calcularSize(No p, int altura) {
        if(p.esq != null) altura = calcularSize(p.esq, altura+1);
        if(p.dir != null) altura = calcularSize(p.dir, altura+1);

        return altura;
    }

    private void preenchePai(No p) {
        if(p.esq != null) p.esq.pai = p;
        if(p.dir != null) p.dir.pai = p;

        if(p.esq != null) preenchePai(p.esq);
        if(p.dir != null) preenchePai(p.dir);
    }

    private boolean checkAVL(No p) {
        if(!(p.balanceamento > -2 && p.balanceamento < 2)) return false;
        if(p.esq != null) checkAVL(p.esq);
        if(p.dir != null) checkAVL(p.dir);

        return true;
    }

    public ArvoreAVL() {
        this.raiz = null;
    }

    public int getAltura(Item reg) { return getAltura(reg, this.raiz); }

    public int getProfundidade(Item reg) { return getProfundidade(reg, this.raiz, 1); }

    public int getSize(Item reg) { return getSize(reg, this.raiz); }

    public void preenchePai() {
        this.raiz.pai = null;
        preenchePai(this.raiz);
    }

    public String checkAVL() { return checkAVL(this.raiz) ? "Sim" : "Não"; }

    public Item pesquisa(Item reg) {
        return this.pesquisa(reg, this.raiz);
    }

    public void insere(Item reg) {
        this.raiz = this.insere(reg, this.raiz, true);
    }

    public void retira(Item reg) {
        this.raiz = this.retira(reg, this.raiz);
    }

}
