package jdbc.repository;

import jdbc.connection.ConnectionMySql;
import jdbc.dominio.Reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaRepository {
    public static void save(Reserva reserva){
        try {
            try (Connection connection = ConnectionMySql.getConnection()) {
                try (PreparedStatement ps = createPreparedStatementSave(connection, reserva)) {
                    ps.execute();
                    System.out.println("Reserva realizada com sucesso!");
                }
            }
        } catch (SQLException e) {
            throw new Error("Erro ao salvar Reserva" + e);
        }

    }

    private static PreparedStatement createPreparedStatementSave(Connection connection, Reserva reserva)throws SQLException{
        String sql = "INSERT INTO `agencia_viagem_lazer_ferias`.`reservas` (`id_cliente`, `id_voo`, `numero_passageiro`, `status`)" +
                " VALUES (?, ?, ?, ?);";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, reserva.getId_cliente());
        ps.setInt(2,reserva.getId_voo());
        ps.setInt(3, reserva.getNumero_passageiro());
        ps.setString(4,reserva.getStatus());
        return  ps;
    }

    public static void update(Reserva reserva){
        try {
            try (Connection connection = ConnectionMySql.getConnection()) {
                try (PreparedStatement ps = createPreparedStatementUpdate(connection, reserva)) {
                    ps.execute();
                    System.out.println("Reserva cancelada com sucesso!");
                }
            }
        } catch (SQLException e) {
            throw new Error("Erro ao cancelar Reserva" + e);
        }

    }

    private static PreparedStatement createPreparedStatementUpdate(Connection connection, Reserva reserva)throws SQLException{
        String sql = "UPDATE `agencia_viagem_lazer_ferias`.`reservas` SET `status` = ? " +
                "WHERE (`id_reserva` = ?);";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,reserva.getStatus());
        ps.setInt(2,reserva.getId_reserva());
        return  ps;
    }

    public static List<Reserva> findAll(){
        List<Reserva> reservas = new ArrayList<>();
        try {
            try (Connection connection = ConnectionMySql.getConnection()) {
                try (PreparedStatement ps = createPreparedStatementFindAll(connection)) {
                    try (ResultSet rs = ps.executeQuery()) {
                        while (rs.next()) {
                            Reserva reserva = Reserva.builder()
                                    .id_reserva(rs.getInt("id_reserva"))
                                    .id_cliente(rs.getInt("id_cliente"))
                                    .id_voo(rs.getInt("id_voo"))
                                    .numero_passageiro(rs.getInt("numero_passageiro"))
                                    .status(rs.getString("status"))
                                    .build();

                            reservas.add(reserva);
                        }

                    }
                }
            }
        } catch (SQLException e) {
            throw new Error("Lista na passagens nao encontrada" + e);
        }
        return reservas;
    }

    private static PreparedStatement createPreparedStatementFindAll(Connection connection)throws SQLException{
        String sql = "SELECT * FROM agencia_viagem_lazer_ferias.reservas;";
        PreparedStatement ps = connection.prepareStatement(sql);
        return  ps;
    }

    public static void deletarReservaId(Integer id){
        try {
            try (Connection connection = ConnectionMySql.getConnection()) {
                try (PreparedStatement ps = createPreparedStatementDelete(connection, id)) {
                    ps.execute();
                    System.out.println("Reserva deletado com sucesso");
                }
            }
        } catch (SQLException e) {
            throw new Error("Erro ao deletar reserva" + e);
        }

    }

    private static PreparedStatement createPreparedStatementDelete(Connection connection, Integer id) throws SQLException{
        String sql = "DELETE FROM `agencia_viagem_lazer_ferias`.`reservas` WHERE (`id_reserva` = ? );";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }
}
