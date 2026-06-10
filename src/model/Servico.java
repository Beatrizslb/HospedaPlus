package model;

public class Servico {
    private String nome;
    private double valor;

    public Servico(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public void exibirServico() {
        System.out.println(nome + " - R$ " + valor);
    }

    public double getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }
}