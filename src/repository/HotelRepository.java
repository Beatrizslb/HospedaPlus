package repository;

import exception.ReservaNaoEncontradaException;
import model.Reserva;

import java.util.ArrayList;
import java.util.List;

public class HotelRepository {
    private List<Reserva> reservas = new ArrayList<>();

    public void salvarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public Reserva buscarReservaPorCodigo(int codigo) throws ReservaNaoEncontradaException {
        for (Reserva reserva : reservas) {
            if (reserva.getCodigo() == codigo) {
                return reserva;
            }
        }

        throw new ReservaNaoEncontradaException("Reserva não encontrada.");
    }

    public List<Reserva> listarReservas() {
        return reservas;
    }
}