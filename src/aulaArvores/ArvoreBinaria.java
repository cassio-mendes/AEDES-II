package aulaArvores;

public class ArvoreBinaria {

    private class Node { Double valor; int profundidade; }

    private Node raiz = new Node();
    private ArvoreBinaria subArvoreEsq, subArvoreDir;
    private int numNodes = 0;

    public ArvoreBinaria(Double valorRaiz) {
        this.raiz.valor = valorRaiz;
        this.raiz.profundidade = 0;
    }

    public ArvoreBinaria() { this.raiz.profundidade = 0; }

    private ArvoreBinaria(int profundidade) {
        this.raiz.profundidade = profundidade;
    }

    public void insere(Double valor) {
        if(this.raiz.valor == null) {
            this.raiz.valor = valor;

        } else if(valor < this.raiz.valor) {
            if(this.subArvoreEsq == null)
                this.subArvoreEsq = new ArvoreBinaria(this.raiz.profundidade+1);
            this.subArvoreEsq.insere(valor);

        } else {
            if(this.subArvoreDir == null)
                this.subArvoreDir = new ArvoreBinaria(this.raiz.profundidade+1);
            this.subArvoreDir.insere(valor);
        }

        this.numNodes++;
    }

    public void printArvore() {
        System.out.println("Profundidade = " + this.raiz.profundidade + " | Valor = " + this.raiz.valor);

        if(this.subArvoreEsq != null)
            this.subArvoreEsq.printArvore();

        if(this.subArvoreDir != null)
            this.subArvoreDir.printArvore();
    }

    public Double get(Double valor) {
        if(this.raiz.valor.equals(valor)) return this.raiz.valor;

        if(this.subArvoreEsq != null) return this.subArvoreEsq.get(valor);

        if(this.subArvoreDir != null) return this.subArvoreDir.get(valor);

        return null;
    }

    //altura = log(base 2) n° de nós ---> APENAS PARA ÁRVORES BALANCEADAS!!
    public int getAltura() {
        return (int)(Math.floor(Math.log(this.numNodes) / Math.log(2)));
    }

}
