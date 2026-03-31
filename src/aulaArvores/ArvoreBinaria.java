package aulaArvores;

public class ArvoreBinaria {

    private class Node { int chave; Object valor; int profundidade; }

    private Node raiz = new Node();
    private ArvoreBinaria subArvoreEsq, subArvoreDir;

    public ArvoreBinaria(int chaveRaiz, Object valorRaiz) {
        this.raiz.chave = chaveRaiz;
        this.raiz.valor = valorRaiz;
        this.raiz.profundidade = 0;
    }

    public ArvoreBinaria() { this.raiz.profundidade = 0; }

    private ArvoreBinaria(int profundidade) {
        this.raiz.profundidade = profundidade;
    }

    public void insere(int chave, Object valor) {
        if(this.raiz.valor == null) {
            this.raiz.chave = chave;
            this.raiz.valor = valor;

        } else if(chave < this.raiz.chave) {
            if(this.subArvoreEsq == null)
                this.subArvoreEsq = new ArvoreBinaria(this.raiz.profundidade+1);
            this.subArvoreEsq.insere(chave, valor);

        } else {
            if(this.subArvoreDir == null)
                this.subArvoreDir = new ArvoreBinaria(this.raiz.profundidade+1);
            this.subArvoreDir.insere(chave, valor);
        }
    }

    public void printArvore() {
        System.out.println("Profundidade = " + this.raiz.profundidade + " | Chave = " + this.raiz.chave + " | Valor = " + this.raiz.valor);

        if(this.subArvoreEsq != null)
            this.subArvoreEsq.printArvore();

        if(this.subArvoreDir != null)
            this.subArvoreDir.printArvore();
    }

    public Object get(int chave) {
        if(this.raiz.chave == chave) return this.raiz.valor;

        if(this.subArvoreEsq != null) return this.subArvoreEsq.get(chave);

        if(this.subArvoreDir != null) return this.subArvoreDir.get(chave);

        return null;
    }

}
