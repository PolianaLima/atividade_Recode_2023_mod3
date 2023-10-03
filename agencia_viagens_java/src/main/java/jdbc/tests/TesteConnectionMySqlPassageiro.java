package jdbc.tests;

import jdbc.dominio.Passageiro;
import jdbc.service.PassageiroService;

import java.time.LocalDate;

public class TesteConnectionMySqlPassageiro {
    public static void main(String[] args) {
        Passageiro passageiro = Passageiro.builder()
                .id_Cliente(1)
                .nome("Andre")
                .sobrenome("Barboza")
                .data_nascimento(LocalDate.of(1994,2,26))
                .cpf("15614823743")
                .nacionalidade("Brasil")
                .build();

        Passageiro passageiroUpdate = Passageiro.builder()
                .id_passageiro(1)
                .nome("Marina")
                .sobrenome("Lima")
                .data_nascimento(LocalDate.of(2023,8,23))
                .cpf("04625541395")
                .nacionalidade("Brasil")
                .build();

        PassageiroService.save(passageiro);
        //PassageiroService.update(passsageiroUpdate);

       // List<Passsageiro> passsageiros = PassageiroService.findByCpf("04625541395");
        //System.out.println(passsageiros);
    }

}
