package model;

public class Funcionario extends Pessoa {
    private String cargo;
    private double salario;
    private String dataAdmissao;

    public Funcionario(String nome, String cpf, String telefone, String cargo, double salario, String dataAdmissao) {
        super(nome, cpf, telefone);
        this.cargo = cargo;
        this.salario = salario;
        this.dataAdmissao = dataAdmissao;
    }

    @Override
    public void exibirDados() {
        System.out.println("Funcionário: " + getNome() + " | Cargo: " + cargo);
    }

    public String getCargo() { return cargo; }
    public double getSalario() { return salario; }
    public String getDataAdmissao() { return dataAdmissao; }
}