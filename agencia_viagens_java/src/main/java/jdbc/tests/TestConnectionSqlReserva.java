package jdbc.tests;

import jdbc.dominio.Reserva;
import jdbc.service.ReservaService;

import java.util.List;

public class TestConnectionSqlReserva {
    public static void main(String[] args) {
        Reserva reserva = Reserva.builder()
                .id_cliente(1)
                .id_voo(5)
                .numero_passageiro(1)
                .status("RESERVADO")
                .build();

        Reserva reservaUpdate = Reserva.builder()
                .id_reserva(1)
                .status("CANCELADO")
                .build();

       //ReservaService.save(reserva);

       // ReservaService.update(reservaUpdate);

        List<Reserva> reservas = ReservaService.findAll();
        System.out.println(reservas);
    }
    }


