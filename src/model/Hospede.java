package model;

public class Hospede extends Pessoa {
    private String email;
    private String endereco;
    private String dataNascimento;

    public Hospede(String nome, String cpf, String telefone, String email, String endereco, String dataNascimento) {
        super(nome, cpf, telefone);
        this.email = email;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
    }

    @Override
    public void exibirDados() {
        System.out.println("Hóspede: " + getNome() + " | CPF: " + getCpf() + " | Telefone: " + getTelefone() + " | Email: " + email);
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
}