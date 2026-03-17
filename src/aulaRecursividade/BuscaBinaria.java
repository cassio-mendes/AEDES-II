package aulaRecursividade;

public class BuscaBinaria {

    static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        System.out.println(buscaBinaria(5, array));
    }

    static int buscaBinaria(int n, int[] array) {
        return buscaBinariaRecursiva(n, array, 0, array.length - 1);
    }

    static int buscaBinariaRecursiva(int n, int[] array, int start, int end) {
        if (end == start && array[start] != n)
            return -1;

        int i = (start + end) / 2;

        if (array[i] == n) {
            return i;

        } else if(array[i] > n) {
            end = i - 1;
            if (end < 0) return -1;
        } else {
            start = i + 1;
        }

        return buscaBinariaRecursiva(n, array, start, end);
    }

}
