package jdbc.dominio;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Aeroporto {
  private Integer id_aeroporto;
  private String nome_aeroporto;
  private String cidade;
  private String estado;
  private String pais;
}
