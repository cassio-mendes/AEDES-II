package aulaArvores.arvoresB;

public class TestaArvoreB {

    static void main(String[] args) {
        ArvoreB arvore = new ArvoreB(4);
        arvore.insere(new MeuItemChar('C'));
        arvore.insere(new MeuItemChar('G'));
        arvore.insere(new MeuItemChar('X'));
        arvore.insere(new MeuItemChar('J'));
        arvore.insere(new MeuItemChar('N'));
        arvore.insere(new MeuItemChar('S'));
        arvore.insere(new MeuItemChar('U'));
        arvore.insere(new MeuItemChar('J'));
        arvore.insere(new MeuItemChar('O'));
        arvore.insere(new MeuItemChar('A'));
        arvore.insere(new MeuItemChar('E'));
        arvore.insere(new MeuItemChar('B'));
        arvore.insere(new MeuItemChar('H'));
        arvore.insere(new MeuItemChar('I'));
        arvore.printEmOrdem();

    }

}
