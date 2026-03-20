package listas.lista01;

import aulaHeaps.HeapMax;
import aulaHeaps.Item;
import java.util.Random;

public class Heapsort {

    private static class Numero implements Item {

        int valor, chave;

        public Numero(int valor, int chave) {
            this.valor = valor;
            this.chave = chave;
        }

        @Override
        public int compara(Item i) {return 0;}

        @Override
        public void alteraChave(Object chave) {}

        @Override
        public Object recuperaChave() {return null;}
    }

    static void main(String[] args) {
        Numero[] numeros = new Numero[1000];
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = new Numero(new Random().nextInt(500), new Random().nextInt(1000));
        }

        long antes = System.currentTimeMillis();
        heapsort(numeros, numeros.length-1);
        long depois = System.currentTimeMillis();

        System.out.println("Tempo decorrido: " + (depois - antes) + "ms");
    }

    static void heapsort(Numero[] numeros, int n) {
        HeapMax heap = new HeapMax(numeros);
        heap.constroi();

        int direita = n;
        while(direita > 1) {
            Numero x = numeros[1];
            numeros[1] = numeros[direita] ;
            numeros[direita] = x;
            direita--;
            heap.refaz(1, direita);
        }
    }

}
