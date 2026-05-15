package aulaArvores.arvoresB;

public class ArvoreB {

    private static class Pagina {
        int n;      //N° de registros no nó
        Item[] r;   //Registros
        Pagina[] p; //Ramificações
    }

    private Pagina raiz;

    public ArvoreB(int m) { //m é o número de registros de cada página
        this.raiz = new Pagina();
        this.raiz.n = 0;
        this.raiz.r = new Item[m];
        this.raiz.p = new Pagina[m + 1];
    }

    public void insere(Item reg) { insere(reg, this.raiz); }

    public Item pesquisa(Item reg) { return pesquisa(reg, this.raiz); }

    private void insere(Item reg, Pagina ap) {
        if(ap == null) {

        }
    }

    private Item pesquisa(Item reg, Pagina ap) {
        if(ap == null) return null;

        int i = 0;
        //Incrementa i enquanto não tiver percorrido a árvore toda E não encontrar a página com valores maiores ou
        //iguais a reg
        while((i < ap.n-1) && (reg.compara(ap.r[i]) > 0)) i++;

        if(reg.compara(ap.r[i]) == 0) {
            return ap.r[i]; //Registro encontrado!
        } else if(reg.compara(ap.r[i]) < 0) {
            return pesquisa(reg, ap.p[i]); //Ramo com valores menores que ap.r[i]
        } else {
            return pesquisa(reg, ap.p[i+1]); //Ramo com valores maiores (possivelmente fim da árvore)
        }
    }

}
