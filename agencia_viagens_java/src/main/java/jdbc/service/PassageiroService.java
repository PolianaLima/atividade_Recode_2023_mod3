package jdbc.service;

import jdbc.dominio.Passageiro;
import jdbc.repository.PassageiroRepository;

import java.util.List;

public class PassageiroService {
    public static void save(Passageiro passageiro){
        PassageiroRepository.save(passageiro);
    }

    public static void update(Passageiro passageiro){
        PassageiroRepository.update(passageiro);
    }

    public static List<Passageiro>findByCpf(String cpf){
        return PassageiroRepository.findByCpf(cpf);
    }

    public static List<Passageiro> findAll(){
        return PassageiroRepository.findAll();
    }
}
