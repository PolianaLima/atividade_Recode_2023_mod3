package agencia.com.br.menu.cliente;

import agencia.com.br.controller.CadastroUsuarioController;
import agencia.com.br.controller.PassagemController;
import agencia.com.br.menu.MenuSistema;
import agencia.com.br.menu.admin.MenuAeroporto;
import agencia.com.br.menu.admin.MenuVoo;

import java.util.Scanner;

public class MenuCliente {
    public static void menuCliente(){
        Scanner entrada = new Scanner(System.in);
        Integer opcao;

        System.out.println("\n");
        System.out.println("+------------------------------------------------------------+");
        System.out.println("| Menu usuário                                               |");
        System.out.println("+------------------------------------------------------------+");
        System.out.println("| 01 - Cadastre-se                                           |");
        System.out.println("| 02 - Login                                                 |");
        System.out.println("| 99 - Menu Principal                                        |");
        System.out.println("+------------------------------------------------------------+");

        System.out.print("O que deseja fazer?: ");

        try {

            opcao = entrada.nextInt();
            while (opcao != 99) {

                switch (opcao) {
                    case 1 -> CadastroUsuarioController.save();
                    case 2 -> LoginCliente.LoginCliente();
                    default -> System.out.println("Opção Invalida!");
                }

                System.out.println("\n");
                System.out.println("+------------------------------------------------------------+");
                System.out.println("| Menu usuário                                               |");
                System.out.println("+------------------------------------------------------------+");
                System.out.println("| 01 - Cadastre-se                                           |");
                System.out.println("| 02 - Login                                                 |");
                System.out.println("| 99 - Menu Principal                                        |");
                System.out.println("+------------------------------------------------------------+");


                System.out.print("O que deseja fazer?: ");
                opcao = entrada.nextInt();

            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Entrada inválida. Esperava-se um número inteiro.");
            menuCliente();

        }
    }
}
