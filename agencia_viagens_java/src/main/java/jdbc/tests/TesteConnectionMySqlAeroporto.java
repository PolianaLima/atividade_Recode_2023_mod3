package jdbc.tests;

import jdbc.dominio.Aeroporto;
import jdbc.service.AeroportoService;

import java.util.List;

public class TesteConnectionMySqlAeroporto {

    public static void main(String[] args) {
        Aeroporto aeroporto = Aeroporto.builder()
                .nome_aeroporto("Santos Dummont")
                .cidade("Rio de Janeiro")
                .estado("RJ")
                .pais("Brasil")
                .build();

        Aeroporto aeroportoUpdate = Aeroporto.builder()
                .id_aeroporto(1)
                .nome_aeroporto("GALEAO")
                .cidade("Rio de Janeiro")
                .estado("RJ")
                .pais("Brasil")
                .build();
        //AeroportoService.save(aeroporto);
        //AeroportoService.update(aeroportoUpdate);


        List<Aeroporto> aeroportos = AeroportoService.findAll();
        System.out.println(aeroportos);
    }




    }

