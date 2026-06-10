package service;

import exception.QuartoIndisponivelException;
import model.Hospede;
import model.Quarto;
import model.Reserva;

public class HotelService {

    public Reserva criarReserva(int codigo, Hospede hospede, Quarto quarto, int quantidadeDias)
            throws QuartoIndisponivelException {

        if (!quarto.getStatus().equalsIgnoreCase("Disponível")) {
            throw new QuartoIndisponivelException("O quarto não está disponível para reserva.");
        }

        Reserva reserva = new Reserva(codigo, hospede, quarto, quantidadeDias);
        reserva.confirmarReserva();

        return reserva;
    }

    public void realizarCheckOut(Reserva reserva) {
        reserva.getQuarto().setStatus("Disponível");
        System.out.println("Check-out realizado. Quarto liberado.");
    }
}