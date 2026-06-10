package model;

public class QuartoSimples extends Quarto {

    public QuartoSimples(int numero, String status) {
        super(numero, status, 1);
    }

    @Override
    public double calcularDiaria() {
        return 150.00;
    }
}