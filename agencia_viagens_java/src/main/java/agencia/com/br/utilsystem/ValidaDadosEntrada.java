package agencia.com.br.utilsystem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class ValidaDadosEntrada {

    static Scanner entrada = new Scanner(System.in);

    public static Integer readInteger(String prompt) {
        Integer value = null;
        while (true) {
            try {
                System.out.print(prompt);
                value = entrada.nextInt();
                entrada.nextLine();
                break; // Valor válido, sair do loop
            } catch (Exception e) {
                System.out.println("Digite um valor inteiro válido.");
                entrada.nextLine(); // Limpar entrada inválida
            }
        }
        return value;
    }

    public static String readString(String prompt) {
        System.out.print(prompt);
        return entrada.nextLine().toUpperCase();
    }


    public static LocalDate readLocalDate(String prompt) {
        LocalDate value = null;
        while (true) {
            try {
                System.out.print(prompt);
                value = LocalDate.parse(entrada.next());
                entrada.nextLine();
                break; // Valor válido, sair do loop
            } catch (Exception e) {
                System.out.println("Digite uma data válida no formato yyyy-MM-dd.");
                entrada.nextLine(); // Limpar entrada inválida
            }
        }
        return value;
    }

    public static LocalTime readLocalTime(String prompt) {
        LocalTime value = null;
        while (true) {
            try {
                System.out.print(prompt);
                value = LocalTime.parse(entrada.next());
                entrada.nextLine();
                break; // Valor válido, sair do loop
            } catch (Exception e) {
                System.out.println("Digite uma hora válida no formato HH:mm:ss.");
                entrada.nextLine(); // Limpar entrada inválida
            }
        }
        return value;
    }

    public static Double readDouble(String prompt) {
        Double value = null;
        while (true) {
            try {
                System.out.print(prompt);
                value = entrada.nextDouble();
                entrada.nextLine();
                break; // Valor válido, sair do loop
            } catch (Exception e) {
                System.out.println("Digite um valor numérico válido.");
                entrada.nextLine(); // Limpar entrada inválida
            }
        }
        return value;
    }
}
