package listas.lista02;

public interface Item {
    int compara(Item it);
    void alteraChave(Object chave);
    Object recuperaChave();
}
