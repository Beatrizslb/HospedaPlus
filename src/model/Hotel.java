package model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private String nome;
    private List<Hospede> hospedes;
    private List<Funcionario> funcionarios;
    private List<Quarto> quartos;
    private List<Reserva> reservas;

    public Hotel(String nome) {
        this.nome = nome;
        this.hospedes = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
        this.quartos = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public void adicionarHospede(Hospede hospede) {
        hospedes.add(hospede);
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void adicionarQuarto(Quarto quarto) {
        quartos.add(quarto);
    }

    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public String getNome() {
        return nome;
    }

    public List<Hospede> getHospedes() {
        return hospedes;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public List<Quarto> getQuartos() {
        return quartos;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
}