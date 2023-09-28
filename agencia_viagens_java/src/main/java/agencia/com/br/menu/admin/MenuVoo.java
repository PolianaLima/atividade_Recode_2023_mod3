package agencia.com.br.menu.admin;

import agencia.com.br.menu.MenuSistema;
import agencia.com.br.controller.VooController;

import java.util.Scanner;



public class MenuVoo {
    public static void menuVoo(){
        Scanner entrada = new Scanner(System.in);
        Integer opcao;

        System.out.println("\n");
        System.out.println("+-------------------------------------------+");
        System.out.println("| Menu Administrador - Cadastro Voo         |");
        System.out.println("+-------------------------------------------+");
        System.out.println("| 01 - Novo                                 |");
        System.out.println("| 02 - Buscar                               |");
        System.out.println("| 03 - Editar                               |");
        System.out.println("| 99 - Menu Anterior                        |");
        System.out.println("+-------------------------------------------+");

        System.out.print("O que deseja fazer?: ");

        try {

            opcao = entrada.nextInt();
            while (opcao != 99) {

                switch (opcao) {
                    case 1 -> VooController.save();
                    case 2 -> VooController.findByVoo();
                    case 3 -> VooController.update();
                    case 5 -> MenuAdmin.menuAdmin();
                    default -> System.out.println("Opção Invalida!");
                }

                System.out.println("\n");
                System.out.println("+-------------------------------------------+");
                System.out.println("| Menu Administrador - Cadastro             |");
                System.out.println("+-------------------------------------------+");
                System.out.println("| 01 - Novo                                 |");
                System.out.println("| 02 - Buscar                               |");
                System.out.println("| 03 - Editar                               |");
                System.out.println("| 99 - Menu Anterior                        |");
                System.out.println("+-------------------------------------------+");

                System.out.print("O que deseja fazer?: ");
                opcao = entrada.nextInt();

            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Entrada inválida. Esperava-se um número inteiro.");
            menuVoo();

        }
    }
}
