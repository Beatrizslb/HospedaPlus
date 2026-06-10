package model;

public class QuartoDuplo extends Quarto {

    public QuartoDuplo(int numero, String status) {
        super(numero, status, 2);
    }

    @Override
    public double calcularDiaria() {
        return 250.00;
    }
}