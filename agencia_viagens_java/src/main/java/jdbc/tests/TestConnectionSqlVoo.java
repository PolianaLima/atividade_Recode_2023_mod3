package jdbc.tests;

import jdbc.dominio.Voo;
import jdbc.service.VooService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TestConnectionSqlVoo {

    public static void main(String[] args) {
        Voo voo = Voo.builder()
                .numero_voo(1020)
                .companhia_aerea("Latam")
                .id_aero_saida(1)
                .id_aero_chegada(2)
                .data_partida( LocalDate.of(2023,8,23))
                .data_chegada( LocalDate.of(2023,8,23))
                .duracao_voo(LocalTime.of(2,5,0))
                .numero_assentos(100)
                .preco_voo(2500.00)
                .build();

        Voo vooUpdate = Voo.builder()
                .id_voo(1)
                .numero_voo(1015)
                .companhia_aerea("Gol")
                .id_aero_saida(1)
                .id_aero_chegada(2)
                .data_partida( LocalDate.of(2023,8,23))
                .data_chegada( LocalDate.of(2023,8,23))
                .duracao_voo(LocalTime.of(2,5,0))
                .numero_assentos(100)
                .preco_voo(2500.00)
                .build();

       // VooService.save(voo);

     //   VooService.delete(1);

        List<Voo> voos = VooService.findByVoo(1011);
        System.out.println(voos);
    }

}
