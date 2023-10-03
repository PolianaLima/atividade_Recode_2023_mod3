package agencia.com.br.controller;

import agencia.com.br.utilsystem.GeradorNumeroAleatorio;
import jdbc.dominio.Cliente;
import jdbc.dominio.Passageiro;
import jdbc.dominio.Reserva;
import jdbc.dominio.Voo;
import jdbc.service.ClienteService;
import jdbc.service.PassageiroService;
import jdbc.service.ReservaService;
import jdbc.service.VooService;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

import static agencia.com.br.utilsystem.ValidaDadosEntrada.*;

public class PassagemController {
    static private Integer id_voo;
    static private Integer numeroPassageiro;
    static private String status;

    public static void save(Integer id_ClienteAutenticado) {

        int numero_bilhete = GeradorNumeroAleatorio.gerarNumeroAleatorio(1, 1500);


        Integer vooEscolhido;
        Integer opcao;

        String nome;
        String sobrenome;
        LocalDate data_nascimento;
        String cpf;
        String nacionalidade;

        System.out.println("Pesquisar passagem ");

        VooController.findByVoosByOrigemDestino();
        vooEscolhido = readInteger("Escolha o voo da ideal para você e digite o numero para selecionar: ");

        List<Voo> vooSelecionado = VooService.findByVoo(vooEscolhido);
        if(vooSelecionado.isEmpty()){
            System.out.println("O voo " + vooEscolhido + "não consta na lista! \n Digite um voo valido!");
            vooEscolhido = readInteger("Escolha o voo da ideal para você e digite o numero para selecionar: ");
            vooSelecionado = VooService.findByVoo(vooEscolhido);

        }

        for (Voo voo : vooSelecionado) {
            id_voo = voo.getId_voo();
        }

        numeroPassageiro = readInteger("Quantidade de Passageiros: ");
        status = "RESERVADO";

        ReservaService.save(Reserva.builder()
                .id_cliente(id_ClienteAutenticado)
                .id_voo(id_voo)
                .numero_passageiro(numeroPassageiro)
                .status(status)
                .build());

        System.out.println("+------------------------------------------------------------+");
        System.out.println("| Cadastre os Passageiros                                    |");
        System.out.println("+------------------------------------------------------------+");
        for (int i = 1; i <= numeroPassageiro; i++) {
           nome= readString("Nome:");
            sobrenome = readString("Sobrenome:");
            data_nascimento = readLocalDate("Data de nascimento (yyyy-MM-dd):");
           cpf =  readString("Cpf:");
           nacionalidade =  readString("Nacionalidade:");
            System.out.println("\n");
            PassageiroService.save(Passageiro.builder()
                    .id_Cliente(id_ClienteAutenticado)
                    .nome(nome)
                    .sobrenome(sobrenome)
                    .data_nascimento(data_nascimento)
                    .cpf(cpf)
                    .nacionalidade(nacionalidade)
                    .build());
        }
    }

    public static void findBYPassagem(Integer id_ClienteAutenticado) {
        System.out.println("+------------------------------------------------------------+");
        System.out.println("| Minhas Viagens                                             |");
        System.out.println("+------------------------------------------------------------+");
        List<Reserva> reservaList = ReservaService.findAll();


        for (Reserva reserva : reservaList) {
            if (reserva.getId_cliente() == id_ClienteAutenticado){
                List<Voo> getVoo = VooService.findById(reserva.getId_voo());
                for (Voo voo : getVoo) {
                    System.out.println("Codigo Reserva: " + voo.getId_voo());
                    System.out.println("Voo: " + voo.getNumero_voo());
                    System.out.println("Origem: " + voo.getOrigem() + " | Destino: " + voo.getDestino());
                    System.out.println("Ida: " + voo.getData_partida() + " | Volta: " + voo.getData_chegada());
                }
                System.out.println("Total Passageiros: " + reserva.getNumero_passageiro());
                System.out.println("Status: " + reserva.getStatus());
                System.out.println("\n");
            }
        }
    }

    public static void deletarReserva(Integer id_ClienteAutenticado){
        Integer codigoPassagem = null;
       findBYPassagem(id_ClienteAutenticado);

     codigoPassagem =  readInteger("DIgite o codigo da reserva que deseja excluir:");
      ReservaService.delete(codigoPassagem);


    }

    private static String getPrecoFormatado(Voo elemento) {
        Locale brasil =  Locale.of("pt", "BR");
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(brasil);
        return formatoMoeda.format(elemento.getPreco_voo());

    }


}


