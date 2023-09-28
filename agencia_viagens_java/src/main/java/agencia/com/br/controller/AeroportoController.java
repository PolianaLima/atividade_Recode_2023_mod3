package agencia.com.br.controller;

import jdbc.dominio.Aeroporto;
import jdbc.service.AeroportoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static agencia.com.br.utilsystem.ValidaDadosEntrada.readInteger;
import static agencia.com.br.utilsystem.ValidaDadosEntrada.readString;

public class AeroportoController {
    static private String nome;
    static private String cidade;
    static private String estado;
    static private String pais;

    public static void save() {
        System.out.println("\n\n");
        System.out.println("Cadastro de Aeroporto");
        nome = readString("Nome: ");
        cidade = readString("Cidade: ");
        estado = readString("Estado(XX): ");
        pais = readString("Pais: ");

        AeroportoService.save(Aeroporto.builder()
                .nome_aeroporto(nome)
                .cidade(cidade)
                .estado(estado)
                .pais(pais)
                .build());
    }

    public static void update() {
        Integer codigo_aeroporto;
        List<Aeroporto> aeroporto = getAeroporto();
        for (Aeroporto value : aeroporto) {
            System.out.println("\nCodigo: " + value.getId_aeroporto());
            System.out.println("Nome: " + value.getNome_aeroporto());
            System.out.println("Cidade: " + value.getCidade());
            System.out.println("Estado: " + value.getEstado());
            System.out.println("Pais: " + value.getPais());

        }

        System.out.println("\n ");
        codigo_aeroporto = readInteger("Digite o codigo do aeroporto que deseja alterar: ");
        List<Aeroporto> aeroportoSelecionado = AeroportoService.findById(codigo_aeroporto);

        if(aeroportoSelecionado.isEmpty()){
            System.out.println("O codigo do aeroporto selecionaodo " + codigo_aeroporto + " nao consta na lista.");
            codigo_aeroporto  = readInteger("Digite um codigo válido: ");
            aeroportoSelecionado = AeroportoService.findById(codigo_aeroporto);
        }

        for (int i = 0; i < aeroportoSelecionado.size(); i++) {
            System.out.println("Alterar dados do Aeroporto " + aeroporto.get(i).getNome_aeroporto());
            nome = readString("Nome: ");
            if (Objects.equals(nome, "")) {
                nome = aeroporto.get(i).getNome_aeroporto();
            }
            cidade = readString("Cidade: ");
            if (Objects.equals(cidade,"")){
                cidade = aeroporto.get(i).getCidade();
            }
            estado = readString("Estado(XX): ");
            if (Objects.equals(estado,"")){
                estado = aeroporto.get(i).getEstado();
            }
            pais = readString("Pais: ");
            if (Objects.equals(pais,"")){
                estado = aeroporto.get(i).getPais();
            }

            AeroportoService.update(Aeroporto.builder()
                    .id_aeroporto(aeroporto.get(i).getId_aeroporto())
                    .nome_aeroporto(nome)
                    .cidade(cidade)
                    .estado(estado)
                    .pais(pais)
                    .build());
        }
    }

    public static void findByAeroporto() {
        List<Aeroporto> aeroporto = getAeroporto();

        aeroporto.forEach(elemento -> {
            System.out.println("Dados do Aeroporto: " + elemento.getNome_aeroporto() + "\n");
            System.out.println("Nome: " + elemento.getNome_aeroporto());
            System.out.println("Cidade: " + elemento.getCidade());
            System.out.println("Estado: " + elemento.getEstado());
            System.out.println("Pais: " + elemento.getPais());

        });
    }

    public static void findAll() {
        List<Aeroporto> aeroporto = AeroportoService.findAll();
        System.out.println("Lista de Aeroportos cadastrados");
        System.out.println("-------------------------------\n");
        aeroporto.forEach(elemento -> {
            System.out.println("Nome: " + elemento.getNome_aeroporto());
            System.out.println("Cidade: " + elemento.getCidade());
            System.out.println("Estado: " + elemento.getEstado());
            System.out.println("Pais: " + elemento.getPais());
        });
    }

    private static List<Aeroporto> getAeroporto() {
        List<Aeroporto> aeroporto = new ArrayList<>();
        String nome = readString("Digite o nome do aeroporto: ");

        if (Objects.equals(nome, "")) {
            System.out.println("Aeroporto não encontrado, verifique o nome e tente novamente!");
        } else {
            aeroporto = AeroportoService.findByNome(nome);
            if (aeroporto.isEmpty())
                System.out.println("Aeroporto não encontrado, verifique o nome e tente novamente!");
        }
        return aeroporto;
    }

}
