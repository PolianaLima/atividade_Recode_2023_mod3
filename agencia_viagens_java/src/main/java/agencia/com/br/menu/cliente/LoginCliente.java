package agencia.com.br.menu.cliente;

import agencia.com.br.controller.CadastroUsuarioController;
import agencia.com.br.controller.PassagemController;
import agencia.com.br.menu.MenuSistema;
import jdbc.dominio.Cliente;

import java.util.List;
import java.util.Scanner;

public class LoginCliente {


    public static void LoginCliente() {
        int opcao;
        Scanner entrada = new Scanner(System.in);
        Integer id_ClienteAutenticado = null;

        System.out.println("+------------------------------------------------------------+");
        System.out.println("|                           LOGIN                            |");
        System.out.println("+------------------------------------------------------------+");
        List<Cliente> usuario = CadastroUsuarioController.loginAutenticado();

        for (Cliente cliente : usuario) {
            System.out.println("\n");
            System.out.println("Bem Vindo(a), " + cliente.getNome());
            id_ClienteAutenticado = cliente.getId_Cliente();
        }

        System.out.println("+------------------------------------------------------------+");
        System.out.println("| 01 - Comprar Passagem                                      |");
        System.out.println("| 02 - Consultar Viagens                                     |");
        System.out.println("| 03 - Excuir Passagem                                       |");
        System.out.println("| 04 - Alterar Cadastro                                      |");
        System.out.println("| 99 - Menu anterior                                         |");
        System.out.println("+------------------------------------------------------------+");

        System.out.print("O que deseja fazer?: ");

        try {

            opcao = entrada.nextInt();
            while (opcao != 99) {

                switch (opcao) {
                    case 1 -> PassagemController.save(id_ClienteAutenticado);
                    case 2 -> PassagemController.findBYPassagem(id_ClienteAutenticado);
                    case 3 -> PassagemController.deletarReserva(id_ClienteAutenticado);
                    case 4 -> CadastroUsuarioController.update(id_ClienteAutenticado);

                    default -> System.out.println("Opção Invalida!");
                }

                for (Cliente cliente : usuario) {
                    System.out.println("\n");
                    System.out.println("Bem Vindo(a), " + cliente.getNome());
                }

                System.out.println("+------------------------------------------------------------+");
                System.out.println("| 01 - Comprar Passagem                                      |");
                System.out.println("| 02 - Consultar Viagens                                     |");
                System.out.println("| 03 - Excuir Passagem                                       |");
                System.out.println("| 04 - Alterar Cadastro                                      |");
                System.out.println("| 99 - Menu anterior                                         |");
                System.out.println("+------------------------------------------------------------+");

                System.out.print("O que deseja fazer?: ");
                opcao = entrada.nextInt();

            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Entrada inválida. Esperava-se um número inteiro.");
        }
    }

}
