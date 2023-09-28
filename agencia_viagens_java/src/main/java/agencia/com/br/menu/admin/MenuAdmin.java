package agencia.com.br.menu.admin;

import java.util.Scanner;

public class MenuAdmin {
    public static void menuAdmin() {

        Scanner entrada = new Scanner(System.in);
        Integer opcao;

        System.out.println("\n");
        System.out.println("+-------------------------------------------+");
        System.out.println("|        Menu Administrador                 |");
        System.out.println("+-------------------------------------------+");
        System.out.println("| 01 - Aeroporto                            |");
        System.out.println("| 02 - Voos                                 |");
        System.out.println("| 99 - Menu Principal                       |");
        System.out.println("+-------------------------------------------+");

        System.out.print("O que deseja fazer?: ");

        try {

            opcao = entrada.nextInt();
            while (opcao != 99) {

                switch (opcao) {
                    case 1 -> MenuAeroporto.menuAeroporto();
                    case 2 -> MenuVoo.menuVoo();
                    default -> System.out.println("Opção Invalida!");
                }

                System.out.println("\n");
                System.out.println("+-------------------------------------------+");
                System.out.println("|        Menu Administrador                 |");
                System.out.println("+-------------------------------------------+");
                System.out.println("| 01 - Aeroporto                            |");
                System.out.println("| 02 - Voos                                 |");
                System.out.println("| 99 - Menu Principal                       |");
                System.out.println("+-------------------------------------------+");

                System.out.print("O que deseja fazer?: ");
                opcao = entrada.nextInt();

            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Entrada inválida. Esperava-se um número inteiro.");
            menuAdmin();

        }
    }
}
