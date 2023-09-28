package jdbc.dominio;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Reserva {
    private Integer id_reserva;
    private Integer id_cliente;
    private Integer id_voo;
    private Integer numero_passageiro;
    private String status;
}
