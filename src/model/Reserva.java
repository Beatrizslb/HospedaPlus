package model;

import interfaces.Reservavel;
import java.util.ArrayList;
import java.util.List;

public class Reserva implements Reservavel {

    private int codigo;
    private Hospede hospede;
    private Quarto quarto;
    private int quantidadeDias;
    private String status;
    private List<Servico> servicos;
    private Pagamento pagamento;

    public Reserva(int codigo, Hospede hospede, Quarto quarto, int quantidadeDias) {
        this.codigo = codigo;
        this.hospede = hospede;
        this.quarto = quarto;
        this.quantidadeDias = quantidadeDias;
        this.status = "Pendente";
        this.servicos = new ArrayList<>();
    }

    @Override
    public void confirmarReserva() {
        this.status = "Confirmada";
        this.quarto.setStatus("Ocupado");
        System.out.println("Reserva confirmada.");
    }

    @Override
    public void cancelarReserva() {
        this.status = "Cancelada";
        this.quarto.setStatus("Disponível");
        System.out.println("Reserva cancelada.");
    }

    public void adicionarServico(Servico servico) {
        servicos.add(servico);
    }

    public double calcularValorTotal() {
        double total = quarto.calcularDiaria() * quantidadeDias;

        for (Servico servico : servicos) {
            total += servico.getValor();
        }

        return total;
    }

    public void gerarPagamento(String formaPagamento) {
        this.pagamento = new Pagamento(calcularValorTotal(), formaPagamento);
        this.pagamento.registrarPagamento();
    }

    public void exibirReserva() {
        System.out.println("\n--- RESERVA ---");
        System.out.println("Código: " + codigo);
        System.out.println("Hóspede: " + hospede.getNome());
        System.out.println("CPF: " + hospede.getCpf());
        System.out.println("Telefone: " + hospede.getTelefone());
        System.out.println("Email: " + hospede.getEmail());
        System.out.println("Quarto: " + quarto.getNumero());
        System.out.println("Dias: " + quantidadeDias);
        System.out.println("Status: " + status);
        System.out.println("Valor total: R$ " + calcularValorTotal());
    }

    public int getCodigo() {
        return codigo;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public int getQuantidadeDias() {
        return quantidadeDias;
    }

    public String getStatus() {
        return status;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }
}