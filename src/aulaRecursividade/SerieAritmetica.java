package aulaRecursividade;

public class SerieAritmetica {

    static void main(String[] args) {
        System.out.println(calculaSerie(3));
    }

    static double calculaSerie(int n) {
        if(n <= 1) return 0.5; // 2/4
        return (n*n + 1.0) / (n + 3.0) + calculaSerie(((n-1) * n) / (n + 2));
    }

}
