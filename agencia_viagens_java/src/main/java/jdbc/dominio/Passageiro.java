package jdbc.dominio;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Passageiro {
    private Integer id_passageiro;
    private Integer id_Cliente;
    private String nome;
    private String sobrenome;
    private LocalDate data_nascimento;
    private String cpf;
    private String nacionalidade;
    private Integer numero_bilhete;

}
