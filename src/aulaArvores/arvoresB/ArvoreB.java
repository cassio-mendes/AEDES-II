package aulaArvores.arvoresB;

public class ArvoreB {

    private static class Pagina {
        int n;      //N° de registros no nó
        Item[] r;   //Registros
        Pagina[] p; //Ramificações

        public Pagina(int mm) { //mm == 2*m
            this.n = 0;
            this.r = new Item[mm];
            this.p = new Pagina[mm + 1];
        }
    }

    private Pagina raiz;
    private int m, mm;

    //m é o número de mínimo registros (ordem) de cada página, exceto a raiz:
    //Raiz: Mínimo == 1; Máximo == 2*m
    //Demais páginas: Mínimo == m; Máximo == 2*m
    public ArvoreB(int m) {
        this.raiz = null;
        this.m = m;
        this.mm = 2*m;
    }

    public Item pesquisa(Item reg) { return pesquisa(reg, this.raiz); }

    public void insere(Item reg) {
        Item[] regRetorno = new Item[1];    //registro que foi promovido para o nível atual
        boolean[] cresceu = new boolean[1]; //cresceu == true -> alguém foi promovido

        Pagina apRetorno = this.insere(reg, this.raiz, regRetorno, cresceu);

        if(cresceu[0]) { //Alguém foi promovido da raiz
            Pagina apTemp = new Pagina(this.mm); //Nova raiz

            //Atualiza ponteiros da nova raiz, apontando para a raiz antiga, à esquerda, e para a página nova, á direita
            apTemp.r[0] = regRetorno[0];
            apTemp.p[0] = this.raiz;
            apTemp.p[1] = apRetorno;

            //Atualiza o ponteiro da raiz
            this.raiz = apTemp;
            this.raiz.n++;
        } else {
            this.raiz = apRetorno;
        }
    }

    public void retira(Item reg) { retira(reg, this.raiz); }

    //Custo máximo de pesquisa: log n (base: 2*m + 1)
    private Item pesquisa(Item reg, Pagina ap) {
        if(ap == null) return null;

        int i = 0;
        //Incrementa i enquanto não tiver percorrido a árvore toda E não encontrar a página com valores maiores ou
        //iguais a reg
        while((i < ap.n-1) && (reg.compara(ap.r[i]) > 0)) i++;

        if(reg.compara(ap.r[i]) == 0) {
            return ap.r[i]; //Registro encontrado!
        } else if(reg.compara(ap.r[i]) < 0) {
            return pesquisa(reg, ap.p[i]); //Ramo com valores menores que ap.r[i] (possivelmente fim da árvore)
        } else {
            return pesquisa(reg, ap.p[i+1]); //Ramo com valores maiores (possivelmente fim da árvore)
        }
    }

    private Pagina insere(Item reg, Pagina ap, Item[] regRetorno, boolean[] cresceu) {
        Pagina apRetorno = null; //Nova página criada em caso de cisão

        if(ap == null) { //Esta é uma folha nula: a página pai é o ponto de inserção
            cresceu[0] = true;
            regRetorno[0] = reg; //Registro a ser inserido
        } else { //ap não é uma folha

            //Incrementa o índice de acesso enquanto ele for menor que o índice do último registro E reg for maior que
            //o registro atual da página
            int i = 0;
            while((i < ap.n-1) && (reg.compara(ap.r[i]) > 0)) i++;

            if(reg.compara(ap.r[i]) == 0) { //Registro já existente na árvore
                System.out.println("ERRO: Registro já existente...");
                cresceu[0] = false;
            } else { //Desce para uma subárvore

                //i pode ser igual a ap.n-1, então verifica se reg é maior que o último registro
                if(reg.compara(ap.r[i]) > 0) i++; //Vai para o ponteiro mais à direita da página
                apRetorno = insere(reg, ap.p[i], regRetorno, cresceu); //Retorna a página da subárvore (esq ou dir)

                if(cresceu[0]) {

                }
            }

        }
        return null;
    }

    private void retira(Item reg, Pagina ap) {

    }

}
