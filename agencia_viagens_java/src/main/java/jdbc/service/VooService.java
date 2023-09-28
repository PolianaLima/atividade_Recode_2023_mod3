package jdbc.service;

import jdbc.dominio.Voo;
import jdbc.repository.VooRepository;

import java.util.List;

public class VooService {
    public static void save(Voo voo){
        VooRepository.save(voo);
    }

    public static void update(Voo voo){
        VooRepository.update(voo);
    }

    public static List<Voo> findByVoo(Integer numero_voo){
        return VooRepository.findByVoo(numero_voo);
    }

    public static List<Voo> findById(Integer id){
        return VooRepository.findById(id);
    }
    public static List<Voo> findByOrigemDestino(String origem, String destino){
        return VooRepository.findByOrigemDestino(origem, destino);
    }

    public static List<Voo> findAll(){
        return VooRepository.findAll();
    }



    public static void delete(Integer id){
        VooRepository.delete(id);
    }

}
