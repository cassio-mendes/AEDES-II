package listas.lista01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProntoAtendimento {

    static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Paciente> lista = new ArrayList<>();
        lista.add(new Paciente("A", "14:30", 2));
        lista.add(new Paciente("B", "15:00", 1));
        lista.add(new Paciente("C", "15:40", 3));
        lista.add(new Paciente("D", "15:30", 3));

        int tamanhoInicial = lista.size();
        for (int i = 0; i < tamanhoInicial; i++) {
            //Verifica se há empate de prioridades
            boolean empate = true;

            for(int j = 0; j < lista.size() - 1; j++) {
                if(!(lista.get(j).recuperaChave() == lista.get(j + 1).recuperaChave())) {
                    empate = false;
                    break;
                }
            }

            //Se há empate, chama quem chegou primeiro
            Paciente proximo = lista.getFirst(); //Primeiro paciente
            if(empate) {
                //Descobre quem chegou primeiro
                for (int j = 0; j < lista.size(); j++) {
                    String[] horarioAtual = lista.get(j).getHorarioChegada().split(":");
                    String[] horarioProximo = proximo.getHorarioChegada().split(":");

                    int minutosAtual = (Integer.parseInt(horarioAtual[0]) * 60) + Integer.parseInt(horarioAtual[1]);
                    int minutosProximo = (Integer.parseInt(horarioProximo[0]) * 60) + Integer.parseInt(horarioProximo[1]);

                    if(minutosAtual < minutosProximo) {
                        proximo = lista.get(j);
                    }
                }
            } else {
                //Encontra maior prioridade
                for(Paciente p : lista) {
                    if(p.compara(proximo) == -1) { //Se a prioridade de p é maior que a de proximo
                        proximo = p;
                    }
                }
            }

            System.out.println("Próximo paciente: " + proximo.getNome());
            lista.remove(proximo);

            if(i != tamanhoInicial - 1) {
                System.out.print("Chamar próximo paciente? ");
                scan.next();
            }
        }
    }

}
