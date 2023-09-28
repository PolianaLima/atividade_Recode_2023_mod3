package agencia.com.br.menu.admin;

import agencia.com.br.controller.AeroportoController;
import agencia.com.br.controller.VooController;
import agencia.com.br.menu.MenuSistema;

import java.util.Scanner;

public class MenuAeroporto {
    public static void menuAeroporto(){
        Scanner entrada = new Scanner(System.in);
        Integer opcao;

        System.out.println("\n");
        System.out.println("+-------------------------------------------+");
        System.out.println("| Menu Administrador - Menu Aeroporto       |");
        System.out.println("+-------------------------------------------+");
        System.out.println("| 01 - Novo                                 |");
        System.out.println("| 02 - Editar                               |");
        System.out.println("| 03 - Buscar                               |");
        System.out.println("| 04 - Listar todos os Aeroportos           |");
        System.out.println("| 99 - Menu Anterior                        |");
        System.out.println("+-------------------------------------------+");

        System.out.print("O que deseja fazer?: ");

        try {

            opcao = entrada.nextInt();
            while (opcao != 99) {

                switch (opcao) {
                    case 1 -> AeroportoController.save();
                    case 2 -> AeroportoController.update();
                    case 3 -> AeroportoController.findByAeroporto();
                    case 4 -> AeroportoController.findAll();
                    case 5 -> MenuAdmin.menuAdmin();
                    default -> System.out.println("Opção Invalida!");
                }

                System.out.println("\n");
                System.out.println("+-------------------------------------------+");
                System.out.println("| Menu Administrador - Cadastro Aeroporto   |");
                System.out.println("+-------------------------------------------+");
                System.out.println("| 01 - Novo                                 |");
                System.out.println("| 02 - Editar                               |");
                System.out.println("| 03 - Buscar                               |");
                System.out.println("| 04 - Listar todos os Aeroportos           |");
                System.out.println("| 99 - Menu Anterior                        |");
                System.out.println("+-------------------------------------------+");

                System.out.print("O que deseja fazer?: ");
                opcao = entrada.nextInt();

            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Entrada inválida. Esperava-se um número inteiro.");
            menuAeroporto();

        }
    }
}
