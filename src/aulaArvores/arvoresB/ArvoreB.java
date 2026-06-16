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

    public void printEmOrdem() { printEmOrdem(this.raiz); }

    private void printEmOrdem(Pagina ap) {
        if(ap == null) return; //Se não houver uma página, retorna

        //Percorre todos os ponteiros e registros em ordem crescente
        for(int i = 0; i < ap.n; i++) {
            printEmOrdem(ap.p[i]);
            System.out.println("Item: " + ap.r[i]);
        }
        printEmOrdem(ap.p[ap.n]); //Vai ao último ponteiro da página
    }

    public Item pesquisa(Item reg) { return pesquisa(reg, this.raiz); }

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
                    //Uma página filha está subindo
                    if(ap.n < this.mm) { //Tem espaço para inserir na página ap
                        insereNaPagina(ap, regRetorno[0], apRetorno);
                        cresceu[0] = false;
                        apRetorno = ap;
                    } else { //Split
                        Pagina apTemp = new Pagina(this.mm);
                        apTemp.p[0] = null;

                        if(i <= this.m) { //Se o registro estiver na primeira metade
                            insereNaPagina(apTemp, ap.r[this.mm - 1], ap.p[this.mm]);
                            ap.n--;
                            insereNaPagina(ap, regRetorno[0], apRetorno);
                        } else {
                            insereNaPagina(apTemp, regRetorno[0], apRetorno);
                        }

                        for (int j = this.m + 1; j < this.mm; j++) { //Percorre a segunda metade
                            insereNaPagina(apTemp, ap.r[i], ap.p[j + 1]);
                            ap.p[j + 1] = null;
                        }

                        ap.n = this.m;
                        apTemp.p[0] = ap.p[this.m + 1];
                        regRetorno[0] = ap.r[this.m];
                        apRetorno = apTemp; //apRetorno recebe a nova página (página da direita do registro movido)
                    }
                }
            }

        }
        return cresceu[0] ? apRetorno : ap;
    }

    private void insereNaPagina(Pagina ap, Item reg, Pagina apDir) {
        int k = ap.n - 1; //Índice do último registro

        //Enquanto k for maior ou igual a zero E o registro a inserir for menor que o atual
        while((k >= 0) && (reg.compara(ap.r[k]) < 0)) {
            ap.r[k + 1] = ap.r[k];
            ap.p[k + 2] = ap.p[k + 1];
            k--;
        }

        //Atualiza ponteiros
        ap.r[k + 1] = reg;
        ap.p[k + 2] = apDir;
        ap.n++;
    }

    public void retira(Item reg) { retira(reg, this.raiz); }

    private void retira(Item reg, Pagina ap) {

    }

}
