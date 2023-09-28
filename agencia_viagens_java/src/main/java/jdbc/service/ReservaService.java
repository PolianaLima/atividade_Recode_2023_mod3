package jdbc.service;

import jdbc.dominio.Reserva;
import jdbc.repository.ReservaRepository;

import java.util.List;

public class ReservaService {
    public static void save(Reserva reserva){
        ReservaRepository.save(reserva);
    }

    public static void update(Reserva reserva){
        ReservaRepository.update(reserva);
    }

    public static List<Reserva> findAll(){
       return ReservaRepository.findAll();
    }

    public static void delete(Integer id){
        ReservaRepository.deletarReservaId(id);
    }
}
