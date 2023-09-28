package jdbc.service;

import jdbc.dominio.Cliente;
import jdbc.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

public class ClienteService {
    public static void save(Cliente cliente){
        ClienteRepository.save(cliente);
    }

    public static void update(Cliente cliente){
        ClienteRepository.update(cliente);
    }

    public static List<Cliente> findByCpf(String cpf){
        return ClienteRepository.findByCpf(cpf);
    }

    public static List<Cliente> findByEmail(String email){
        return ClienteRepository.findByEmail(email);
    }

    public static List<Cliente>findAll(){
        return ClienteRepository.finalAll();
    }

    public static void delete(Integer id){
        ClienteRepository.delete(id);
    }

    public static Optional<Cliente> findBYId(Integer id){
       return ClienteRepository.findById(id);
    }



}
