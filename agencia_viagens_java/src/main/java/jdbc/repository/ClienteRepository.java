package jdbc.repository;

import jdbc.connection.ConnectionMySql;
import jdbc.dominio.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteRepository {
    public static void save(Cliente cliente) {

        try (Connection connection = ConnectionMySql.getConnection();
             PreparedStatement ps = createPrepareStatementcreateSave(connection, cliente)) {
            ps.execute();
            System.out.println("Cliente salvo com sucesso " + cliente.getNome());
        } catch (SQLException e) {
            throw new Error("Erro ao salvar cliente no banco de dados" + cliente.getNome(), e);
        }


    }

    private static PreparedStatement createPrepareStatementcreateSave(Connection connection, Cliente cliente) throws SQLException {
        String sql = "INSERT INTO `agencia_viagem_lazer_ferias`.`clientes` (`cpf`, `nome`, `Sobrenome`, `data_nascimento`, " +
                "`genero`, `cep`, `estado`, `cidade`, `endereco`, `numero`, `complemento`,`email`, `senha`) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, cliente.getCpf());
        ps.setString(2, cliente.getNome());
        ps.setString(3, cliente.getSobrenome());
        ps.setDate(4, Date.valueOf(cliente.getData_nascimento()));
        ps.setString(5, cliente.getGenero());
        ps.setString(6, cliente.getCep());
        ps.setString(7, cliente.getEstado());
        ps.setString(8, cliente.getCidade());
        ps.setString(9, cliente.getEndereco());
        ps.setInt(10, cliente.getNumero());
        ps.setString(11, cliente.getComplemento());
        ps.setString(12, cliente.getEmail());
        ps.setString(13, cliente.getSenha());

        return ps;
    }

    public static void update(Cliente cliente) {
        try (Connection connection = ConnectionMySql.getConnection();
             PreparedStatement ps = createPreparedStatementUpdate(connection, cliente)) {
            ps.execute();
            System.out.println("Cliente alterado com sucesso " + cliente.getNome());
        } catch (SQLException e) {
            throw new Error("Erro ao alterar cliente no banco de dados" + cliente.getId_Cliente(), e);
        }
    }

    private static PreparedStatement createPreparedStatementUpdate(Connection connection, Cliente cliente) throws SQLException {

        String sql = "UPDATE `agencia_viagem_lazer_ferias`.`clientes` SET `cpf` = ?, `nome` = ?, `Sobrenome` = ?," +
                "`data_nascimento` = ?, `genero` = ?, `cep` = ?, `estado` = ?, `cidade` = ?, `endereco` = ?, `numero` = ?, " +
                "`complemento` = ?, `email` = ?, `senha` = ?" +
                " WHERE (`id_cliente` = ?);";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, cliente.getCpf());
        ps.setString(2, cliente.getNome());
        ps.setString(3, cliente.getSobrenome());
        ps.setDate(4, Date.valueOf(cliente.getData_nascimento()));
        ps.setString(5, cliente.getGenero());
        ps.setString(6, cliente.getCep());
        ps.setString(7, cliente.getEstado());
        ps.setString(8, cliente.getCidade());
        ps.setString(9, cliente.getEndereco());
        ps.setInt(10, cliente.getNumero());
        ps.setString(11, cliente.getComplemento());
        ps.setString(12, cliente.getEmail());
        ps.setString(13, cliente.getSenha());
        ps.setInt(14, cliente.getId_Cliente());

        return ps;

    }

    public static Optional<Cliente> findById(Integer id) {

        try {
            try (Connection connection = ConnectionMySql.getConnection()) {
                try (PreparedStatement ps = createPrepareStatementFindById(connection, id)) {
                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) return Optional.empty();

                        return Optional.of(Cliente.builder()
                                .id_Cliente(rs.getInt("id_cliente"))
                                .cpf(rs.getString("cpf"))
                                .nome(rs.getNString("nome"))
                                .sobrenome(rs.getString("sobrenome"))
                                .data_nascimento(rs.getDate("data_nascimento").toLocalDate())
                                .genero(rs.getString("genero"))
                                .cep(rs.getString("cep"))
                                .estado(rs.getString("estado"))
                                .cidade(rs.getString("cidade"))
                                .endereco(rs.getString("endereco"))
                                .numero(rs.getInt("numero"))
                                .complemento(rs.getString("complemento"))
                                .email(rs.getString("email"))
                                .senha(rs.getString("senha"))
                                .build());

                    }
                }
            }
        } catch (SQLException e) {
            throw new Error("Erro ao buscar cliente no banco de dados" + id, e);
        }
    }

    private static PreparedStatement createPrepareStatementFindById(Connection connection, Integer id) throws SQLException {
        String sql = "SELECT * FROM agencia_viagem_lazer_ferias.clientes WHERE id_cliente = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    public static List<Cliente> findByCpf(String cpf) {
        List<Cliente> clientes = new ArrayList<>();

        try {
            Connection connection = ConnectionMySql.getConnection();
            try {
                PreparedStatement ps = createPrepareStatementFindByCpf(connection, cpf);
                try {
                    ResultSet rs = ps.executeQuery();
                    try {
                        while (rs.next()) {
                            Cliente cliente = Cliente.builder()
                                    .id_Cliente(rs.getInt("id_cliente"))
                                    .cpf(rs.getString("cpf"))
                                    .nome(rs.getString("nome"))
                                    .sobrenome(rs.getString("sobrenome"))
                                    .data_nascimento(rs.getDate("data_nascimento").toLocalDate())
                                    .genero(rs.getString("genero"))
                                    .cep(rs.getString("cep"))
                                    .estado(rs.getString("estado"))
                                    .cidade(rs.getString("cidade"))
                                    .endereco(rs.getString("endereco"))
                                    .numero(rs.getInt("numero"))
                                    .complemento(rs.getString("complemento"))
                                    .email(rs.getString("email"))
                                    .senha(rs.getString("senha"))
                                    .build();

                            clientes.add(cliente);
                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    ps.close();
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new Error("Cliente nao encontrada" + e);
        }
        return clientes;
    }

    private static PreparedStatement createPrepareStatementFindByCpf(Connection connection, String cpf) throws SQLException {
        String sql = "SELECT * FROM agencia_viagem_lazer_ferias.clientes where cpf like ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", cpf));
        return ps;
    }

    public static List<Cliente> findByEmail(String email) {
        List<Cliente> clientes = new ArrayList<>();

        try {
            Connection connection = ConnectionMySql.getConnection();
            try {
                PreparedStatement ps = createPrepareStatementFindByCpf(connection, email);
                try {
                    ResultSet rs = ps.executeQuery();
                    try {
                        while (rs.next()) {
                            Cliente cliente = Cliente.builder()
                                    .id_Cliente(rs.getInt("id_cliente"))
                                    .cpf(rs.getString("cpf"))
                                    .nome(rs.getString("nome"))
                                    .sobrenome(rs.getString("sobrenome"))
                                    .data_nascimento(rs.getDate("data_nascimento").toLocalDate())
                                    .genero(rs.getString("genero"))
                                    .cep(rs.getString("cep"))
                                    .estado(rs.getString("estado"))
                                    .cidade(rs.getString("cidade"))
                                    .endereco(rs.getString("endereco"))
                                    .numero(rs.getInt("numero"))
                                    .complemento(rs.getString("complemento"))
                                    .email(rs.getString("email"))
                                    .senha(rs.getString("senha"))
                                    .build();

                            clientes.add(cliente);
                        }
                    } finally {
                        rs.close();
                    }
                } finally {
                    ps.close();
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new Error("Cliente nao encontrada" + e);
        }
        return clientes;
    }

    private static PreparedStatement createPrepareStatementFindByEmail(Connection connection, String email) throws SQLException {
        String sql = "SELECT * FROM agencia_viagem_lazer_ferias.clientes where email = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, String.format(email));
        return ps;
    }


    public static List<Cliente> finalAll() {
        return findByCpf("");
    }

    public static void delete(Integer id) {
        try {
            Connection connection = ConnectionMySql.getConnection();
            try {
                PreparedStatement ps = createPreparedStatementDelete(connection, id);
                try {
                    ps.execute();
                    System.out.println("Cliente deletado com sucesso");
                } finally {
                    ps.close();
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new Error("Erro ao excluir cliente" + e);
        }
    }

    private static PreparedStatement createPreparedStatementDelete(Connection connection, Integer id) throws SQLException {
        String sql = "DELETE FROM `agencia_viagem_lazer_ferias`.`clientes` WHERE (`id_cliente` = ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);

        return ps;
    }


}
