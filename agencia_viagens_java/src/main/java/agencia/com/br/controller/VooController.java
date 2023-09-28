package agencia.com.br.controller;

import agencia.com.br.utilsystem.GeradorNumeroAleatorio;
import jdbc.dominio.Aeroporto;
import jdbc.dominio.Voo;
import jdbc.service.AeroportoService;
import jdbc.service.VooService;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

import static agencia.com.br.utilsystem.ValidaDadosEntrada.*;


public class VooController {
    static private String companhia_aerea;
    static private Integer id_aero_partida;
    static private Integer id_aero_chegada;
    static private LocalDate data_partida;
    static private LocalDate data_chegada;
    static private LocalTime duracao_voo;
    static private Integer numero_assentos;
    static private Double preco_voo;


    public static void save() {

        Integer numero_voo = GeradorNumeroAleatorio.gerarNumeroAleatorio(1, 1500);
        companhia_aerea = readString("Empresa: ");

        System.out.println("Aeroportos disponíveis: ");
        voosDisponiveis();

        id_aero_partida = readInteger("Aeroporto de partida (Digite o codigo para selecionar) : ");
        id_aero_chegada = readInteger("Aeroporto de chegada (Digite o codigo para selecionar) : ");
        data_partida = readLocalDate("Data de partida (yyyy-MM-dd): ");
        data_chegada = readLocalDate("Data de chegada (yyyy-MM-dd): ");
        duracao_voo = readLocalTime("Duracao (HH:mm:ss): ");
        numero_assentos = readInteger("Qtd de assentos: ");
        preco_voo = readDouble("Valor: ");

        VooService.save(Voo.builder()
                .numero_voo(numero_voo)
                .companhia_aerea(companhia_aerea)
                .id_aero_saida(id_aero_partida)
                .id_aero_chegada(id_aero_chegada)
                .data_partida(data_partida)
                .data_chegada(data_chegada)
                .duracao_voo(duracao_voo)
                .numero_assentos(numero_assentos)
                .preco_voo(preco_voo)
                .origem(AeroportoService.findByIdNomeCidade(id_aero_partida))
                .destino(AeroportoService.findByIdNomeCidade(id_aero_chegada))
                .build());

    }

    public static void findByVoo() {
        List<Voo> voo = getVoo();
        voo.forEach(elemento -> {
                    System.out.println("Informações sobre o voo: " + elemento.getNumero_voo() + "\n");

                    String precoFormatado = getPrecoFormatado(elemento);

                    System.out.println("Numero voo: " + elemento.getNumero_voo());
                    System.out.println("Empresa: " + elemento.getCompanhia_aerea());
                    System.out.println("Aeroporto Saida: " + AeroportoService.findByIdNomeAeroporto(elemento.getId_aero_saida()));
                    System.out.println("Aeroporto Chegada: " + AeroportoService.findByIdNomeAeroporto(elemento.getId_aero_chegada()));
                    System.out.println("Ida: " + elemento.getData_partida());
                    System.out.println("Volta: " + elemento.getData_chegada());
                    System.out.println("Duracao: " + elemento.getDuracao_voo());
                    System.out.println("Assentos: " + elemento.getNumero_assentos());
                    System.out.println("Valor: " + precoFormatado);
                    System.out.println("Origem: " + elemento.getOrigem());
                    System.out.println("Destino: " + elemento.getDestino());

                }
        );

    }

    public static void findByVoosByOrigemDestino() {
        String origem = readString("Origem: ");
        String destino = readString("Destino: ");

        List<Voo> voosFiltrados = VooService.findByOrigemDestino(origem, destino);

        if (!voosFiltrados.isEmpty()) {
            for (Voo elemento : voosFiltrados) {
                String precoFormatado = getPrecoFormatado(elemento);
                System.out.println("+------------------------------------------------------------+");
                System.out.println("|Selecionamos as melhores opções para voce                   |");
                System.out.println("+------------------------------------------------------------+");
                System.out.println("Voo: " + elemento.getNumero_voo());
                System.out.println("Empresa: " + elemento.getCompanhia_aerea());
                System.out.println("Origem: " + elemento.getOrigem());
                System.out.println("Destino: " + elemento.getDestino());
                System.out.println("Ida: " + elemento.getData_partida());
                System.out.println("Volta: " + elemento.getData_chegada());
                System.out.println("Duracao: " + elemento.getDuracao_voo());
                System.out.println("Valor: " + precoFormatado);
            }
        } else {
            System.out.println("Ops! Não encontramos nenhum Voo disponivel! \n" +
                    " Tente novamente com outras opções");
            findByVoosByOrigemDestino();
        }
    }


    public static void update() {
        List<Voo> voo = getVoo();

        for (Voo value : voo) {
            System.out.println("Editando voo " + value.getNumero_voo());
            companhia_aerea = readString("Empresa: ");

            System.out.println("Aeroportos disponíveis: ");
            voosDisponiveis();
            id_aero_partida = readInteger("Aeroporto de partida (Digite o codigo para selecionar) : ");

            id_aero_chegada = readInteger("Aeroporto de chegada (Digite o codigo para selecionar) : ");

            data_partida = readLocalDate("Data de partida (yyyy-MM-dd): ");
            data_chegada = readLocalDate("Data de chegada (yyyy-MM-dd): ");
            duracao_voo = readLocalTime("Duracao (HH:mm:ss): ");
            numero_assentos = readInteger("Qtd de assentos: ");
            preco_voo = readDouble("Valor: ");
            VooService.update(Voo.builder()
                    .id_voo(value.getId_voo())
                    .numero_voo(value.getNumero_voo())
                    .companhia_aerea(companhia_aerea)
                    .id_aero_saida(id_aero_partida)
                    .id_aero_chegada(id_aero_chegada)
                    .data_partida(data_partida)
                    .data_chegada(data_chegada)
                    .duracao_voo(duracao_voo)
                    .numero_assentos(numero_assentos)
                    .preco_voo(preco_voo)
                    .origem(AeroportoService.findByIdNomeCidade(id_aero_partida))
                    .destino(AeroportoService.findByIdNomeCidade(id_aero_chegada))
                    .build());

        }
    }

    private static void voosDisponiveis() {
        List<Aeroporto> aeroportos = AeroportoService.findAll();
        for (Aeroporto aeroporto : aeroportos) {
            System.out.print("Codigo do aeroporto: " + aeroporto.getId_aeroporto());
            System.out.print(" Aeroporto: " + aeroporto.getNome_aeroporto().toUpperCase());
            System.out.print(" - Cidade/Estado: " + aeroporto.getCidade().toUpperCase() + " - " + aeroporto.getEstado().toUpperCase() + "\n");
        }
    }


    public static List<Voo> getVoo() {

        Integer numero_voo = readInteger("Digite o numero do voo: ");

        List<Voo> voo = VooService.findByVoo(numero_voo);
        if (voo.isEmpty()) System.out.println("Voo não encontrado, verifique o numero e tente novamente!");

        return voo;
    }


    private static String getPrecoFormatado(Voo elemento) {
        Locale brasil;
        brasil = new Locale("pt", "BR");
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(brasil);
        return formatoMoeda.format(elemento.getPreco_voo());
    }

}
