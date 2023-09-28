package jdbc.service;

import jdbc.dominio.Aeroporto;
import jdbc.repository.AeroportoRepository;

import java.util.List;

public class AeroportoService {
    public static void save(Aeroporto aeroporto){
        AeroportoRepository.save(aeroporto);
    }

    public static void update(Aeroporto aeroporto){
        AeroportoRepository.update(aeroporto);
    }

    public static List<Aeroporto> findByNome(String nome){
      return   AeroportoRepository.findByNome(nome);
    }

    public static List<Aeroporto> findAll(){
        return   AeroportoRepository.findAll();
    }


    public static List<Aeroporto> findById(Integer id) {
       return  AeroportoRepository.findById(id);
    }
    public static String findByIdNomeCidade(Integer id) {
       return  AeroportoRepository.findByIdNomeCidade(id);
    }

    public static String findByIdNomeAeroporto(Integer id) {
       return  AeroportoRepository.findByIdNomeAeroporto(id);
    }


}
