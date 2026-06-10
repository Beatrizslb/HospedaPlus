package view;

import exception.QuartoIndisponivelException;
import model.*;
import repository.HotelRepository;
import service.HotelService;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private Hotel hotel;
    private HotelService service;
    private HotelRepository repository;
    private int proximoCodigoReserva = 1;

    public TelaPrincipal() {
        hotel = new Hotel("Hospeda+");
        service = new HotelService();
        repository = new HotelRepository();

        configurarQuartos();
        configurarTela();
    }

    private void configurarQuartos() {
        for (int i = 1; i <= 10; i++) {
            hotel.adicionarQuarto(new QuartoSimples(i, "Disponível"));
        }

        for (int i = 20; i <= 30; i++) {
            hotel.adicionarQuarto(new QuartoDuplo(i, "Disponível"));
        }

        for (int i = 40; i <= 45; i++) {
            hotel.adicionarQuarto(new Suite(i, "Disponível"));
        }
    }

    private void configurarTela() {
        setTitle("Hospeda+ - Sistema de Gestão Hoteleira");
        setSize(520, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
        painelPrincipal.setBackground(new Color(245, 247, 250));

        JPanel painelTitulo = new JPanel(new GridLayout(2, 1));
        painelTitulo.setBackground(new Color(245, 247, 250));

        JLabel titulo = new JLabel("HOSPEDA+", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(new Color(30, 50, 80));

        JLabel subtitulo = new JLabel("Sistema de Gestão Hoteleira", SwingConstants.CENTER);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitulo.setForeground(new Color(90, 90, 90));

        painelTitulo.add(titulo);
        painelTitulo.add(subtitulo);

        JPanel painelBotoes = new JPanel(new GridLayout(7, 1, 12, 12));
        painelBotoes.setBackground(new Color(245, 247, 250));

        JButton btnCadastrarHospede = criarBotao("Cadastrar Hóspede");
        JButton btnListarHospedes = criarBotao("Listar Hóspedes");
        JButton btnListarQuartos = criarBotao("Listar Quartos");
        JButton btnCriarReserva = criarBotao("Criar Reserva");
        JButton btnListarReservas = criarBotao("Listar Reservas");
        JButton btnCheckOut = criarBotao("Realizar Check-out");
        JButton btnSair = criarBotao("Sair");

        painelBotoes.add(btnCadastrarHospede);
        painelBotoes.add(btnListarHospedes);
        painelBotoes.add(btnListarQuartos);
        painelBotoes.add(btnCriarReserva);
        painelBotoes.add(btnListarReservas);
        painelBotoes.add(btnCheckOut);
        painelBotoes.add(btnSair);

        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);
        painelPrincipal.add(painelBotoes, BorderLayout.CENTER);

        add(painelPrincipal);

        btnCadastrarHospede.addActionListener(e -> cadastrarHospede());
        btnListarHospedes.addActionListener(e -> listarHospedes());
        btnListarQuartos.addActionListener(e -> listarQuartos());
        btnCriarReserva.addActionListener(e -> criarReserva());
        btnListarReservas.addActionListener(e -> listarReservas());
        btnCheckOut.addActionListener(e -> realizarCheckOut());
        btnSair.addActionListener(e -> System.exit(0));
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 15));
        botao.setFocusPainted(false);
        botao.setBackground(new Color(40, 85, 140));
        botao.setForeground(Color.WHITE);
        botao.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return botao;
    }

    private String solicitarCampoObrigatorio(String mensagem) {
        while (true) {
            String valor = JOptionPane.showInputDialog(this, mensagem);

            if (valor == null) {
                return null;
            }

            valor = valor.trim();

            if (!valor.isEmpty()) {
                return valor;
            }

            JOptionPane.showMessageDialog(this, "Este campo é obrigatório. Preencha para continuar.");
        }
    }

    private void cadastrarHospede() {
        String nome = solicitarCampoObrigatorio("Nome:");
        if (nome == null) {
            JOptionPane.showMessageDialog(this, "Cadastro cancelado.");
            return;
        }

        String cpf = solicitarCampoObrigatorio("CPF:");
        if (cpf == null) {
            JOptionPane.showMessageDialog(this, "Cadastro cancelado.");
            return;
        }

        String telefone = solicitarCampoObrigatorio("Telefone:");
        if (telefone == null) {
            JOptionPane.showMessageDialog(this, "Cadastro cancelado.");
            return;
        }

        String email = solicitarCampoObrigatorio("Email:");
        if (email == null) {
            JOptionPane.showMessageDialog(this, "Cadastro cancelado.");
            return;
        }

        String endereco = solicitarCampoObrigatorio("Endereço:");
        if (endereco == null) {
            JOptionPane.showMessageDialog(this, "Cadastro cancelado.");
            return;
        }

        String dataNascimento = solicitarCampoObrigatorio("Data de nascimento:");
        if (dataNascimento == null) {
            JOptionPane.showMessageDialog(this, "Cadastro cancelado.");
            return;
        }

        Hospede hospede = new Hospede(nome, cpf, telefone, email, endereco, dataNascimento);
        hotel.adicionarHospede(hospede);

        JOptionPane.showMessageDialog(this, "Hóspede cadastrado com sucesso!");
    }

    private void listarHospedes() {
        StringBuilder texto = new StringBuilder();

        if (hotel.getHospedes().isEmpty()) {
            texto.append("Nenhum hóspede cadastrado.");
        } else {
            for (Hospede h : hotel.getHospedes()) {
                texto.append("Nome: ").append(h.getNome())
                        .append("\nCPF: ").append(h.getCpf())
                        .append("\nTelefone: ").append(h.getTelefone())
                        .append("\nEmail: ").append(h.getEmail())
                        .append("\n-------------------------\n");
            }
        }

        JOptionPane.showMessageDialog(this, texto.toString());
    }

    private void listarQuartos() {
        int simplesDisponiveis = 0;
        int duplosDisponiveis = 0;
        int suitesDisponiveis = 0;

        StringBuilder texto = new StringBuilder();
        texto.append("QUARTOS DO HOTEL\n\n");

        for (Quarto q : hotel.getQuartos()) {
            texto.append("Quarto ")
                    .append(q.getNumero())
                    .append(" - ")
                    .append(identificarTipoQuarto(q))
                    .append(" | Status: ")
                    .append(q.getStatus())
                    .append(" | Diária: R$ ")
                    .append(q.calcularDiaria())
                    .append("\n");

            if (q.getStatus().equalsIgnoreCase("Disponível")) {
                if (q instanceof QuartoSimples) {
                    simplesDisponiveis++;
                } else if (q instanceof QuartoDuplo) {
                    duplosDisponiveis++;
                } else if (q instanceof Suite) {
                    suitesDisponiveis++;
                }
            }
        }

        texto.append("\nRESUMO DE DISPONIBILIDADE\n");
        texto.append("Quartos Simples disponíveis: ").append(simplesDisponiveis).append("\n");
        texto.append("Quartos Duplos disponíveis: ").append(duplosDisponiveis).append("\n");
        texto.append("Suítes disponíveis: ").append(suitesDisponiveis).append("\n");

        JOptionPane.showMessageDialog(this, texto.toString());
    }

    private void criarReserva() {
        if (hotel.getHospedes().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cadastre um hóspede primeiro.");
            return;
        }

        String cpfBusca = solicitarCampoObrigatorio("Digite o CPF do hóspede:");
        if (cpfBusca == null) {
            JOptionPane.showMessageDialog(this, "Reserva cancelada.");
            return;
        }

        Hospede hospedeEscolhido = buscarHospedePorCpf(cpfBusca);

        if (hospedeEscolhido == null) {
            JOptionPane.showMessageDialog(this, "Hóspede não encontrado.");
            return;
        }

        String numeroTexto = solicitarCampoObrigatorio(montarListaQuartosDisponiveis());
        if (numeroTexto == null) {
            JOptionPane.showMessageDialog(this, "Reserva cancelada.");
            return;
        }

        try {
            int numeroQuarto = Integer.parseInt(numeroTexto);
            Quarto quartoEscolhido = buscarQuartoDisponivelPorNumero(numeroQuarto);

            if (quartoEscolhido == null) {
                JOptionPane.showMessageDialog(this, "Quarto não encontrado ou indisponível.");
                return;
            }

            String diasTexto = solicitarCampoObrigatorio("Quantidade de dias:");
            if (diasTexto == null) {
                JOptionPane.showMessageDialog(this, "Reserva cancelada.");
                return;
            }

            int dias = Integer.parseInt(diasTexto);

            if (dias <= 0) {
                JOptionPane.showMessageDialog(this, "A quantidade de dias deve ser maior que zero.");
                return;
            }

            Reserva reserva = service.criarReserva(
                    proximoCodigoReserva,
                    hospedeEscolhido,
                    quartoEscolhido,
                    dias
            );

            reserva.gerarPagamento("Demonstrativo");

            repository.salvarReserva(reserva);
            hotel.adicionarReserva(reserva);

            proximoCodigoReserva++;

            JOptionPane.showMessageDialog(
                    this,
                    "Reserva criada com sucesso!\n\n" +
                            "Hóspede: " + hospedeEscolhido.getNome() + "\n" +
                            "CPF: " + hospedeEscolhido.getCpf() + "\n" +
                            "Telefone: " + hospedeEscolhido.getTelefone() + "\n" +
                            "Email: " + hospedeEscolhido.getEmail() + "\n" +
                            "Quarto: " + quartoEscolhido.getNumero() + "\n" +
                            "Tipo: " + identificarTipoQuarto(quartoEscolhido) + "\n" +
                            "Valor total: R$ " + reserva.calcularValorTotal()
            );

        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Digite apenas números nos campos de quarto e dias.");
        } catch (QuartoIndisponivelException erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage());
        }
    }

    private void listarReservas() {
        StringBuilder texto = new StringBuilder();

        if (hotel.getReservas().isEmpty()) {
            texto.append("Nenhuma reserva cadastrada.");
        } else {
            texto.append("RESERVAS CADASTRADAS\n\n");

            for (Reserva reserva : hotel.getReservas()) {
                Hospede h = reserva.getHospede();
                Quarto q = reserva.getQuarto();

                texto.append("Reserva nº ").append(reserva.getCodigo()).append("\n")
                        .append("Hóspede: ").append(h.getNome()).append("\n")
                        .append("CPF: ").append(h.getCpf()).append("\n")
                        .append("Telefone: ").append(h.getTelefone()).append("\n")
                        .append("Email: ").append(h.getEmail()).append("\n")
                        .append("Quarto: ").append(q.getNumero()).append(" - ").append(identificarTipoQuarto(q)).append("\n")
                        .append("Status do quarto: ").append(q.getStatus()).append("\n")
                        .append("Valor total: R$ ").append(reserva.calcularValorTotal()).append("\n")
                        .append("-----------------------------\n");
            }
        }

        JOptionPane.showMessageDialog(this, texto.toString());
    }

    private void realizarCheckOut() {
        if (hotel.getReservas().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma reserva encontrada.");
            return;
        }

        String codigoTexto = solicitarCampoObrigatorio("Digite o código da reserva para check-out:");
        if (codigoTexto == null) {
            JOptionPane.showMessageDialog(this, "Check-out cancelado.");
            return;
        }

        try {
            int codigo = Integer.parseInt(codigoTexto);

            Reserva reservaEscolhida = null;

            for (Reserva reserva : hotel.getReservas()) {
                if (reserva.getCodigo() == codigo) {
                    reservaEscolhida = reserva;
                    break;
                }
            }

            if (reservaEscolhida == null) {
                JOptionPane.showMessageDialog(this, "Reserva não encontrada.");
                return;
            }

            service.realizarCheckOut(reservaEscolhida);

            JOptionPane.showMessageDialog(
                    this,
                    "Check-out realizado com sucesso!\n\n" +
                            "Hóspede: " + reservaEscolhida.getHospede().getNome() + "\n" +
                            "Quarto liberado: " + reservaEscolhida.getQuarto().getNumero()
            );

        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Digite um código válido.");
        }
    }

    private Hospede buscarHospedePorCpf(String cpf) {
        for (Hospede h : hotel.getHospedes()) {
            if (h.getCpf().equals(cpf)) {
                return h;
            }
        }
        return null;
    }

    private Quarto buscarQuartoDisponivelPorNumero(int numero) {
        for (Quarto q : hotel.getQuartos()) {
            if (q.getNumero() == numero && q.getStatus().equalsIgnoreCase("Disponível")) {
                return q;
            }
        }
        return null;
    }

    private String montarListaQuartosDisponiveis() {
        StringBuilder texto = new StringBuilder();

        texto.append("Digite o número do quarto desejado:\n\n");
        texto.append("QUARTOS DISPONÍVEIS\n\n");

        for (Quarto q : hotel.getQuartos()) {
            if (q.getStatus().equalsIgnoreCase("Disponível")) {
                texto.append("Quarto ")
                        .append(q.getNumero())
                        .append(" - ")
                        .append(identificarTipoQuarto(q))
                        .append(" | Diária: R$ ")
                        .append(q.calcularDiaria())
                        .append("\n");
            }
        }

        return texto.toString();
    }

    private String identificarTipoQuarto(Quarto quarto) {
        if (quarto instanceof QuartoSimples) {
            return "Quarto Simples";
        } else if (quarto instanceof QuartoDuplo) {
            return "Quarto Duplo";
        } else if (quarto instanceof Suite) {
            return "Suíte";
        } else {
            return "Quarto";
        }
    }
}