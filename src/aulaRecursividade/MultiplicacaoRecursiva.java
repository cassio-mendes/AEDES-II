package aulaRecursividade;

public class MultiplicacaoRecursiva {

    static void main(String[] args) {
        System.out.println(multiplica(9.0, 5)); //Número a somado n vezes
    }

    static double multiplica(double a, int n) {
        if(n < 1) return 0;
        return a + multiplica(a, n-1);
    }

}
