package agencia.com.br.controller;

import jdbc.dominio.Cliente;
import jdbc.service.ClienteService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static agencia.com.br.utilsystem.ValidaDadosEntrada.*;


public class CadastroUsuarioController {

    static Scanner entrada = new Scanner(System.in);
    static private String cpf;
    static private String nome;
    static private String sobrenome;
    static private LocalDate data_nascimento;
    static private String genero;
    static private String cep;
    static private String estado;
    static private String cidade;
    static private String endereco;
    static private Integer numero;
    static private String complemento;
    static private String email;
    static private String senha;

    public static void save() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n");
        System.out.println("+---------------------------------------------------+");
        System.out.println("| Cadastro de Usuário                               |");
        System.out.println("+---------------------------------------------------+");

        nome = readString("Nome: ");
        sobrenome = readString("Sobrenome: ");
        cpf = readString("Cpf: ");
        data_nascimento = readLocalDate("Data de nascimento (yyyy-MM-dd):");
        genero = readString("Genero [F] [M] [O]: ");
        cep = readString("Cep: ");
        endereco = readString("Endereço: ");
        numero = readInteger("Numero: ");
        cidade = readString("Cidade: ");
        estado = readString("Estado [XX]: ");
        complemento = readString("Complemento: ");
        email = readString("Email: ");
        senha = readString("Senha: ");

        ClienteService.save(Cliente.builder()
                .cpf(cpf)
                .nome(nome)
                .sobrenome(sobrenome)
                .data_nascimento(data_nascimento)
                .genero(genero)
                .cep(cep)
                .estado(estado)
                .cidade(cidade)
                .endereco(endereco)
                .numero(numero)
                .complemento(complemento)
                .email(email)
                .senha(senha)
                .build());
    }

    public static void update(Integer id_autenticado) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n");
        System.out.println("+---------------------------------------------------+");
        System.out.println("| Editar Usuário                                    |");
        System.out.println("+---------------------------------------------------+");


        nome = readString("Nome: ");
        sobrenome = readString("Sobrenome: ");
        cpf = readString("Cpf: ");
        data_nascimento = readLocalDate("Data de nascimento (yyyy-MM-dd):");
        genero = readString("Genero [F] [M] [O]: ");
        cep = readString("Cep: ");
        endereco = readString("Endereço: ");
        numero = readInteger("Numero: ");
        cidade = readString("Cidade: ");
        estado = readString("Estado [XX]: ");
        complemento = readString("Complemento: ");
        email = readString("Email: ");
        senha = readString("Senha: ");

        ClienteService.update(Cliente.builder()
                .id_Cliente(id_autenticado)
                .cpf(cpf)
                .nome(nome)
                .sobrenome(sobrenome)
                .data_nascimento(data_nascimento)
                .genero(genero)
                .cep(cep)
                .estado(estado)
                .cidade(cidade)
                .endereco(endereco)
                .numero(numero)
                .complemento(complemento)
                .email(email)
                .senha(senha)
                .build());
    }
    public static List<Cliente> loginAutenticado(){
        email = readString("Usuário: ");
        senha = readString("Senha: ");

        List<Cliente> usuarios = ClienteService.findAll();

        List<Cliente> usuarioAutenticado = usuarios.stream()
                .filter(cliente -> cliente.getEmail().equals(email)&& cliente.getSenha().equals(senha))
                .collect(Collectors.toList());





        while (usuarioAutenticado.isEmpty()){
            System.out.println("Usuario ou senha inválidos! \n \n");

            System.out.println("Digite um usuario e senha valido para efetuar LOGIN!");
            email = readString("Usuário: ");
            senha = readString("Senha: ");

          usuarioAutenticado =   usuarios.stream()
                    .filter(cliente -> cliente.getEmail().equals(email)&& cliente.getSenha().equals(senha))
                    .collect(Collectors.toList());

        }


        return usuarioAutenticado;
    }
}
