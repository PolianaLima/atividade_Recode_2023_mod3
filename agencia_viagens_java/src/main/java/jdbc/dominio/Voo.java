package jdbc.dominio;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Voo {
    private Integer id_voo;
    private Integer numero_voo;
    private String companhia_aerea;
    private Integer id_aero_saida;
    private Integer id_aero_chegada;
    private LocalDate data_partida;
    private LocalDate data_chegada;
    private LocalTime duracao_voo;
    private Integer numero_assentos;
    private double preco_voo;
    private String origem;
    private String destino;
}
