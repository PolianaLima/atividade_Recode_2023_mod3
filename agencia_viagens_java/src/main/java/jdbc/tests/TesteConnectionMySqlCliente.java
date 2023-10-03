package jdbc.tests;

import jdbc.dominio.Cliente;
import jdbc.repository.ClienteRepository;
import jdbc.service.ClienteService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class TesteConnectionMySqlCliente {

    public static void main(String[] args) {
        Cliente cliente  = Cliente.builder()
                .cpf("04625544499")
                .nome("Helena")
                .sobrenome("Barboza")
                .data_nascimento( LocalDate.of(2021,10,13))
                .genero("F")
                .cep("21510640")
                .estado("RJ")
                .cidade("Rio de Janeiro")
                .endereco("Rua Sergio de Siqueira de Macedo")
                .numero(31)
                .complemento("Vila")
                .build();

          //ClienteService.save(cliente);


        Cliente clienteUpdate  = Cliente.builder()
                .id_Cliente(2)
                .cpf("04625541199")
                .nome("Andre")
                .sobrenome("Barboza")
                .data_nascimento( LocalDate.of(2023,8,23))
                .genero("m")
                .cep("21510640")
                .estado("RJ")
                .cidade("Rio de Janeiro")
                .endereco("Rua Sergio de Siqueira de Macedo")
                .numero(31)
                .complemento("Vila")
                .build();

      //  ClienteService.update(clienteUpdate);
      //  ClienteService.delete(2);

        List<Cliente> clientes = ClienteService.findByCpf("0462554");
        System.out.println(clientes);

    }

}
