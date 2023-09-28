package jdbc.repository;

import jdbc.connection.ConnectionMySql;
import jdbc.dominio.Aeroporto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AeroportoRepository {
    public static void save(Aeroporto aeroporto) {
        try {
            Connection connection = ConnectionMySql.getConnection();
            try {
                PreparedStatement ps = createPrepareStatementcreateSave(connection, aeroporto);
                try {
                    ps.execute();
                    System.out.println("Aeroporto salvo com sucesso " + aeroporto.getNome_aeroporto());
                } finally {
                    ps.close();
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new Error("Erro ao salvar aeroporto no banco de dados" + aeroporto.getNome_aeroporto(), e);
        }
    }

    private static PreparedStatement createPrepareStatementcreateSave(Connection connection, Aeroporto aeroporto) throws SQLException {
        String sql = "INSERT INTO `agencia_viagem_lazer_ferias`.`aeroportos` (`nome_aeroporto`, `cidade`, `estado`, `pais`) " +
                "VALUES (?,?,?, ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, aeroporto.getNome_aeroporto());
        ps.setString(2, aeroporto.getCidade());
        ps.setString(3, aeroporto.getEstado());
        ps.setString(4, aeroporto.getPais());
        return ps;
    }

    public static void update(Aeroporto aeroporto) {
        try {
            Connection connection = ConnectionMySql.getConnection();
            try {
                PreparedStatement ps = createPrepareStatementcreateUpdate(connection, aeroporto);
                try {
                    ps.execute();
                    System.out.println("Aeroporto alterado com sucesso " + aeroporto.getNome_aeroporto());
                } finally {
                    ps.close();
                }
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw new Error("Erro ao alterar aeroporto no banco de dados" + aeroporto.getNome_aeroporto(), e);
        }
    }

    private static PreparedStatement createPrepareStatementcreateUpdate(Connection connection, Aeroporto aeroporto) throws SQLException {
        String sql = "UPDATE `agencia_viagem_lazer_ferias`.`aeroportos` SET `nome_aeroporto` = ?, `cidade` = ?, `estado` = ?, `pais` = ? " +
                "WHERE (`id_aeroporto` = ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, aeroporto.getNome_aeroporto());
        ps.setString(2, aeroporto.getCidade());
        ps.setString(3, aeroporto.getEstado());
        ps.setString(4, aeroporto.getPais());
        ps.setInt(5, aeroporto.getId_aeroporto());
        return ps;
    }

    public static List<Aeroporto> findByNome(String nome) {
        List<Aeroporto> aeroportos = new ArrayList<>();

        try {
            Connection connection = ConnectionMySql.getConnection();
            try {
                PreparedStatement ps = createPrepareStatementFindByNome(connection, nome);
                try {
                    ResultSet rs = ps.executeQuery();
                    try {
                        while (rs.next()) {
                            Aeroporto aeroporto = Aeroporto.builder()
                                    .id_aeroporto(rs.getInt("id_aeroporto"))
                                    .nome_aeroporto(rs.getString("nome_aeroporto"))
                                    .cidade(rs.getString("cidade"))
                                    .estado(rs.getString("estado"))
                                    .pais(rs.getString("pais"))
                                    .build();

                            aeroportos.add(aeroporto);
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
            throw new Error("Aeroporto nao encontrada" + e);
        }
        return aeroportos;
    }

    private static PreparedStatement createPrepareStatementFindByNome(Connection connection, String nome) throws SQLException {
        String sql = "SELECT * FROM agencia_viagem_lazer_ferias.aeroportos where nome_aeroporto like ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", nome));
        return ps;
    }

    public static List<Aeroporto> findAll() {
        return findByNome("");
    }

    public static String findByIdNomeCidade(Integer id) {
        List aeroportos = new ArrayList<>();

        String cidade = "";

        try (Connection connection = ConnectionMySql.getConnection();
             PreparedStatement ps = createPrepareStatementFindById(connection, id);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Aeroporto aeroporto = Aeroporto.builder()
                        .id_aeroporto(rs.getInt("id_aeroporto"))
                        .nome_aeroporto(rs.getString("nome_aeroporto"))
                        .cidade(rs.getString("cidade"))
                        .estado(rs.getString("estado"))
                        .pais(rs.getString("pais"))
                        .build();

                aeroportos.add(aeroporto);

                cidade = aeroporto.getCidade();
            }

        } catch (SQLException e) {
            throw new Error("Erro ao buscar aeroporto" + e);
        }
        return cidade;
    }

    public static String findByIdNomeAeroporto(Integer id) {
        List aeroportos = new ArrayList<>();

        String aeroportoNome = "";

        try (Connection connection = ConnectionMySql.getConnection();
             PreparedStatement ps = createPrepareStatementFindById(connection, id);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Aeroporto aeroporto = Aeroporto.builder()
                        .id_aeroporto(rs.getInt("id_aeroporto"))
                        .nome_aeroporto(rs.getString("nome_aeroporto"))
                        .cidade(rs.getString("cidade"))
                        .estado(rs.getString("estado"))
                        .pais(rs.getString("pais"))
                        .build();

                aeroportos.add(aeroporto);

                aeroportoNome = aeroporto.getNome_aeroporto();
            }

        } catch (SQLException e) {
            throw new Error("Erro ao buscar aeroporto" + e);
        }
        return aeroportoNome;
    }

    public static List<Aeroporto> findById(Integer id) {
        List aeroportos = new ArrayList<>();


        try (Connection connection = ConnectionMySql.getConnection();
             PreparedStatement ps = createPrepareStatementFindById(connection, id);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Aeroporto aeroporto = Aeroporto.builder()
                        .id_aeroporto(rs.getInt("id_aeroporto"))
                        .nome_aeroporto(rs.getString("nome_aeroporto"))
                        .cidade(rs.getString("cidade"))
                        .estado(rs.getString("estado"))
                        .pais(rs.getString("pais"))
                        .build();

                aeroportos.add(aeroporto);

            }
            return aeroportos;


        } catch (SQLException e) {
            throw new Error("Erro ao buscar aeroporto" + e);
        }
    }

    private static PreparedStatement createPrepareStatementFindById(Connection connection, Integer id) throws SQLException {
        String sql = "SELECT * FROM agencia_viagem_lazer_ferias.aeroportos where id_aeroporto = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

}
