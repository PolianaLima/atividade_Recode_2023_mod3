package jdbc.repository;

import jdbc.connection.ConnectionMySql;
import jdbc.dominio.Passageiro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PassageiroRepository {
    public static void save(Passageiro passageiro) {
        try {
            try (Connection connection = ConnectionMySql.getConnection()) {
                try (PreparedStatement ps = createPreparedStatementSave(connection, passageiro)) {
                    ps.execute();
                    System.out.println("Passageiro salvo com sucesso " + passageiro.getNome());
                }
            }
        } catch (SQLException e) {
            throw new Error("Erro ao salvar passageiro no banco de dados" + passageiro.getNome(), e);
        }

    }

    private static PreparedStatement createPreparedStatementSave(Connection connection, Passageiro passageiro) throws SQLException {
        String sql = "INSERT INTO `agencia_viagem_lazer_ferias`.`passageiros` (`id_cliente`, `nome`, `sobrenome`, `data_nascimento`, `cpf`, `nacionalidade`) " +
                "VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, passageiro.getId_Cliente());
        ps.setString(2, passageiro.getNome());
        ps.setString(3, passageiro.getSobrenome());
        ps.setDate(4, Date.valueOf(passageiro.getData_nascimento()));
        ps.setString(5, passageiro.getCpf());
        ps.setString(6, passageiro.getNacionalidade());
        return ps;
    }

    public static void update(Passageiro passageiro) {
        try {
            Connection connection = ConnectionMySql.getConnection();
            try {
                PreparedStatement ps = createPreparedStatementUpdate(connection, passageiro);
                try {
                    ps.execute();
                    System.out.println("Passageiro alterado com sucesso " + passageiro.getNome());
                } finally {
                    ps.close();
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new Error("Erro ao alterar passageiro no banco de dados" + passageiro.getNome(), e);
        }

    }

    private static PreparedStatement createPreparedStatementUpdate(Connection connection, Passageiro passageiro) throws SQLException {
        String sql = "UPDATE `agencia_viagem_lazer_ferias`.`passageiros` SET `nome` = ?, `sobrenome` = ?, `data_nascimento` = ?, `cpf` = ?, `nacionalidade` = ?" +
                " WHERE (`id_passageiro` = ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, passageiro.getNome());
        ps.setString(2, passageiro.getSobrenome());
        ps.setDate(3, Date.valueOf(passageiro.getData_nascimento()));
        ps.setString(4, passageiro.getCpf());
        ps.setString(5, passageiro.getNacionalidade());
        ps.setInt(6, passageiro.getId_passageiro());
        return ps;
    }

    public static List<Passageiro> findByCpf(String cpf) {
        List<Passageiro> passageiros = new ArrayList<>();

        try (Connection connection = ConnectionMySql.getConnection()) {
            try (PreparedStatement ps = createPreparedStatementFindByCpf(connection, cpf)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Passageiro passageiro = Passageiro.builder()
                                .id_passageiro(rs.getInt("id_passageiro"))
                                .id_Cliente(rs.getInt("id_cliente"))
                                .nome(rs.getString("nome"))
                                .sobrenome(rs.getString("sobrenome"))
                                .data_nascimento(rs.getDate("data_nascimento").toLocalDate())
                                .cpf(rs.getString("cpf"))
                                .nacionalidade(rs.getString("nacionalidade"))
                                .build();

                        passageiros.add(passageiro);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Error("CPf nao encontrado" + e);
        }
        return passageiros;
    }

    private static PreparedStatement createPreparedStatementFindByCpf(Connection connection, String cpf) throws SQLException {
        String sql = "SELECT * FROM agencia_viagem_lazer_ferias.passageiros where cpf like ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", cpf));
        return ps;
    }

    public static List<Passageiro> findAll() {
        return findByCpf("");
    }

    public static void delete(Integer id) {
        try {
            Connection connection = ConnectionMySql.getConnection();
            try {
                PreparedStatement ps = createPreparedStatementDelete(connection, id);
                try {
                    ps.execute();
                    System.out.println("Passageiro deletado com sucesso");
                } finally {
                    ps.close();
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new Error("Erro ao deletar passageiro" + e);
        }

    }

    private static PreparedStatement createPreparedStatementDelete(Connection connection, Integer id) throws SQLException {
        String sql = "DELETE FROM `agencia_viagem_lazer_ferias`.`passageiros` WHERE (`id_passagiro` = ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }


}
