package agencia.com.br.menu;

import agencia.com.br.menu.admin.MenuAdmin;
import agencia.com.br.menu.cliente.MenuCliente;

import java.util.Scanner;

public class MenuSistema {
    public static void exibirMenu() {


        Scanner entrada = new Scanner(System.in);
        Integer opcao;

        System.out.println("\n");
        System.out.println("+------------------------------------------------------------+");
        System.out.println("|                BEM VINDO AO LAZER E FERIAS                 |");
        System.out.println("+------------------------------------------------------------+");
        System.out.println("|                                                            |");
        System.out.println("|            ESCOLHA UMA OPÇÃO DE ACESSO                     |");
        System.out.println("|            [ 1 ] ADMNISTADOR SISTEMA                       |");
        System.out.println("|            [ 2 ] USUARIO                                   |");
        System.out.println("|            [ 3 ] FECHAR                                    |");
        System.out.println("|                                                            |");
        System.out.println("|                                                            |");
        System.out.println("+------------------------------------------------------------+");
        System.out.print("Digite a opção escolhida: ");


        try {
            opcao = entrada.nextInt();

            while (opcao != 3) {

                switch (opcao) {
                    case 1 -> MenuAdmin.menuAdmin();
                    case 2 -> MenuCliente.menuCliente();
                    default -> System.out.println("Opção invalida!");
                }

                System.out.println("\n");
                System.out.println("+------------------------------------------------------------+");
                System.out.println("|                BEM VINDO AO LAZER E FERIAS                 |");
                System.out.println("+------------------------------------------------------------+");
                System.out.println("|                                                            |");
                System.out.println("|            ESCOLHA UMA OPÇÃO DE ACESSO                     |");
                System.out.println("|            [ 1 ] ADMNISTADOR SISTEMA                       |");
                System.out.println("|            [ 2 ] USUARIO                                   |");
                System.out.println("|            [ 3 ] FECHAR                                    |");
                System.out.println("|                                                            |");
                System.out.println("|                                                            |");
                System.out.println("+------------------------------------------------------------+");
                System.out.print("Digite a opção escolhida: ");
                opcao = entrada.nextInt();

            }

            System.out.println("Finalizando Sistema!");
        } catch (java.util.InputMismatchException e) {
            System.out.println("Entrada inválida. Esperava-se um número inteiro.");
            exibirMenu();
        }


    }
}
