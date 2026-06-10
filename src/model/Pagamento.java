package model;

import interfaces.Pagavel;

public class Pagamento implements Pagavel {
    private double valorTotal;
    private String formaPagamento;
    private String status;

    public Pagamento(double valorTotal, String formaPagamento) {
        this.valorTotal = valorTotal;
        this.formaPagamento = formaPagamento;
        this.status = "Pendente";
    }

    @Override
    public void registrarPagamento() {
        this.status = "Registrado";
        System.out.println("Pagamento demonstrativo registrado.");
        System.out.println("Valor: R$ " + valorTotal + " | Forma: " + formaPagamento);
    }

    public String getStatus() {
        return status;
    }
}