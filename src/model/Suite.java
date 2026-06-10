package model;

public class Suite extends Quarto {

    public Suite(int numero, String status) {
        super(numero, status, 4);
    }

    @Override
    public double calcularDiaria() {
        return 400.00;
    }
}