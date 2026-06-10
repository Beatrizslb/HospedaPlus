package model;

public abstract class Quarto {
    private int numero;
    private String status;
    private int capacidade;

    public Quarto(int numero, String status, int capacidade) {
        this.numero = numero;
        this.status = status;
        this.capacidade = capacidade;
    }

    public abstract double calcularDiaria();

    public void exibirDados() {
        System.out.println("Quarto " + numero + " | Status: " + status + " | Capacidade: " + capacidade);
        System.out.println("Valor da diária: R$ " + calcularDiaria());
    }

    public int getNumero() {
        return numero;
    }

    public String getStatus() {
        return status;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}