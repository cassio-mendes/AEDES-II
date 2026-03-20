package listas.lista01;

import aulaHeaps.Item;

public class Paciente implements Item {

    private String nome;
    private String horarioChegada;
    private int prioridade;

    public Paciente(String nome, String horarioChegada, int prioridade) {
        this.nome = nome;
        this.horarioChegada = horarioChegada;
        this.prioridade = prioridade;
    }

    @Override
    public int compara(Item i) {
        Paciente paciente = (Paciente) i;
        return Integer.compare(this.prioridade, paciente.prioridade);
    }

    @Override
    public void alteraChave(Object chave) {
        this.prioridade = (Integer)chave;
    }

    @Override
    public Object recuperaChave() {
        return this.prioridade;
    }

    public String getNome() {
        return nome;
    }

    public String getHorarioChegada() {
        return horarioChegada;
    }

}
