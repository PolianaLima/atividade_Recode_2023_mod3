package jdbc.repository;

import jdbc.connection.ConnectionMySql;
import jdbc.dominio.Voo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VooRepository {

    public static void save(Voo voo){
        try (Connection connection = ConnectionMySql.getConnection();
             PreparedStatement ps = createPreparedStatementSave(connection, voo)) {
            ps.execute();
            System.out.println("Voo "+ voo.getNumero_voo()+" salvo com sucesso." );
        } catch (SQLException e) {
            throw new Error("Erro ao salvar voo no banco de dados" + voo.getNumero_voo(), e);
        }

    }

    private static PreparedStatement createPreparedStatementSave(Connection connection, Voo voo)throws SQLException{
        String sql = "INSERT INTO `agencia_viagem_lazer_ferias`.`voos` (`numero_voo`, `companhia_aerea`, `aeroporto_partida`, `aeroporto_chegada`, `data_partida`,`data_chegada`,`duracao_voo`,`numero_assentos`,`preco_voo`,`origem`,`destino`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, voo.getNumero_voo());
        ps.setString(2, voo.getCompanhia_aerea());
        ps.setInt(3, voo.getId_aero_saida());
        ps.setInt(4, voo.getId_aero_chegada());
        ps.setDate(5, Date.valueOf(voo.getData_partida()));
        ps.setDate(6, Date.valueOf(voo.getData_chegada()));
        ps.setTime(7, Time.valueOf(voo.getDuracao_voo()));
        ps.setInt(8,voo.getNumero_assentos());
        ps.setDouble(9, voo.getPreco_voo());
        ps.setString(10, voo.getOrigem());
        ps.setString(11, voo.getDestino());

        return ps;
    }

    public static void update(Voo voo){
        try (Connection connection = ConnectionMySql.getConnection();
             PreparedStatement ps = createPreparedStatementUpdate(connection, voo)) {
            ps.execute();
            System.out.println("Voo alterado com sucesso " + voo.getNumero_voo());
        } catch (SQLException e) {
            throw new Error("Erro ao alterar o voo no banco de dados " + voo.getNumero_voo(), e);
        }

    }

    private static PreparedStatement createPreparedStatementUpdate(Connection connection, Voo voo)throws SQLException{
        String sql = "UPDATE `agencia_viagem_lazer_ferias`.`voos` SET `numero_voo` = ?, `companhia_aerea` = ?, `aeroporto_partida` = ?, `aeroporto_chegada` = ?, `data_partida` = ?, `data_chegada` = ?, `duracao_voo` = ?, `numero_assentos` = ?, `preco_voo` = ?, `origem` = ?, `destino` = ? WHERE (`id_voo` = ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, voo.getNumero_voo());
        ps.setString(2, voo.getCompanhia_aerea());
        ps.setInt(3, voo.getId_aero_saida());
        ps.setInt(4, voo.getId_aero_chegada());
        ps.setDate(5, Date.valueOf(voo.getData_partida()));
        ps.setDate(6, Date.valueOf(voo.getData_chegada()));
        ps.setTime(7, Time.valueOf(voo.getDuracao_voo()));
        ps.setInt(8,voo.getNumero_assentos());
        ps.setDouble(9, voo.getPreco_voo());
        ps.setString(10, voo.getOrigem());
        ps.setString(11, voo.getDestino());
        ps.setInt(12, voo.getId_voo());

        return ps;
    }

    public static List<Voo> findByVoo(Integer numero_voo){
        List<Voo> voos = new ArrayList<>();
        try (Connection connection = ConnectionMySql.getConnection();
        PreparedStatement ps = createPreparedStatementFindByVoo(connection, numero_voo);
        ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Voo voo = Voo.builder()
                        .id_voo(rs.getInt("id_voo"))
                        .numero_voo(rs.getInt("numero_voo"))
                        .companhia_aerea(rs.getString("companhia_aerea"))
                        .id_aero_saida(rs.getInt("aeroporto_partida"))
                        .id_aero_chegada(rs.getInt("aeroporto_chegada"))
                        .data_partida(rs.getDate("data_partida").toLocalDate())
                        .data_chegada(rs.getDate("data_chegada").toLocalDate())
                        .duracao_voo(rs.getTime("duracao_voo").toLocalTime())
                        .numero_assentos(rs.getInt("numero_assentos"))
                        .preco_voo(rs.getDouble("preco_voo"))
                        .origem(rs.getString("origem"))
                        .destino(rs.getString("destino"))
                        .build();
                voos.add(voo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return voos;
    }

    private static PreparedStatement createPreparedStatementFindByVoo(Connection connection, Integer numero_voo) throws  SQLException{
        String sql = "SELECT * FROM agencia_viagem_lazer_ferias.voos where numero_voo = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, numero_voo );
        return ps;
    }

    public static List<Voo> findById(Integer id){
        List<Voo> voos = new ArrayList<>();
        try (Connection connection = ConnectionMySql.getConnection();
             PreparedStatement ps = createPreparedStatementFindById(connection, id);
             ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                Voo voo = Voo.builder()
                        .id_voo(rs.getInt("id_voo"))
                        .numero_voo(rs.getInt("numero_voo"))
                        .companhia_aerea(rs.getString("companhia_aerea"))
                        .id_aero_saida(rs.getInt("aeroporto_partida"))
                        .id_aero_chegada(rs.getInt("aeroporto_chegada"))
                        .data_partida(rs.getDate("data_partida").toLocalDate())
                        .data_chegada(rs.getDate("data_chegada").toLocalDate())
                        .duracao_voo(rs.getTime("duracao_voo").toLocalTime())
                        .numero_assentos(rs.getInt("numero_assentos"))
                        .preco_voo(rs.getDouble("preco_voo"))
                        .origem(rs.getString("origem"))
                        .destino(rs.getString("destino"))
                        .build();
                voos.add(voo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return voos;
    }

    private static PreparedStatement createPreparedStatementFindById(Connection connection, Integer id) throws  SQLException{
        String sql = "SELECT * FROM agencia_viagem_lazer_ferias.voos where id_voo = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id );
        return ps;
    }

    public static List<Voo> findAll(){
        List<Voo> voos = new ArrayList<>();
        try (Connection connection = ConnectionMySql.getConnection();
             PreparedStatement ps = createPreparedStatementFindAll(connection);
             ResultSet rs = ps.executeQuery();){
            while (rs.next()){
                Voo voo = Voo.builder()
                        .id_voo(rs.getInt("id_voo"))
                        .numero_voo(rs.getInt("numero_voo"))
                        .companhia_aerea(rs.getString("companhia_aerea"))
                        .id_aero_saida(rs.getInt("aeroporto_partida"))
                        .id_aero_chegada(rs.getInt("aeroporto_chegada"))
                        .data_partida(rs.getDate("data_partida").toLocalDate())
                        .data_chegada(rs.getDate("data_chegada").toLocalDate())
                        .duracao_voo(rs.getTime("duracao_voo").toLocalTime())
                        .numero_assentos(rs.getInt("numero_assentos"))
                        .preco_voo(rs.getDouble("preco_voo"))
                        .origem(rs.getString("origem"))
                        .destino(rs.getString("destino"))
                        .build();


                voos.add(voo);
            }

        } catch (SQLException e) {
            throw new Error("Lista na voos nao encontrada" + e);
        }
        return voos;
    }

    private static PreparedStatement createPreparedStatementFindAll(Connection connection)throws SQLException{
        String sql = "SELECT * FROM agencia_viagem_lazer_ferias.voos;";
        PreparedStatement ps = connection.prepareStatement(sql);
        return  ps;
    }
    public static List<Voo> findByOrigemDestino(String origem, String destino){
        List<Voo> voos = new ArrayList<>();
        try (Connection connection = ConnectionMySql.getConnection();
             PreparedStatement ps = createPreparedStatementOrigemDestino(connection, origem, destino);
             ResultSet rs = ps.executeQuery();){
            while (rs.next()){
                Voo voo = Voo.builder()
                        .id_voo(rs.getInt("id_voo"))
                        .numero_voo(rs.getInt("numero_voo"))
                        .companhia_aerea(rs.getString("companhia_aerea"))
                        .id_aero_saida(rs.getInt("aeroporto_partida"))
                        .id_aero_chegada(rs.getInt("aeroporto_chegada"))
                        .data_partida(rs.getDate("data_partida").toLocalDate())
                        .data_chegada(rs.getDate("data_chegada").toLocalDate())
                        .duracao_voo(rs.getTime("duracao_voo").toLocalTime())
                        .numero_assentos(rs.getInt("numero_assentos"))
                        .preco_voo(rs.getDouble("preco_voo"))
                        .origem(rs.getString("origem"))
                        .destino(rs.getString("destino"))
                        .build();


                voos.add(voo);
            }

        } catch (SQLException e) {
            throw new Error("Lista na voos nao encontrada" + e);
        }
        return voos;
    }

    private static PreparedStatement createPreparedStatementOrigemDestino(Connection connection, String origem, String destino)throws SQLException{
        String sql = "SELECT * FROM agencia_viagem_lazer_ferias.voos where origem like ? and destino like ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,String.format("%%%s%%",origem)  );
        ps.setString(2,String.format("%%%s%%",destino) );
        return  ps;
    }


    public static void  delete(Integer id){
        try {
            try (Connection connection = ConnectionMySql.getConnection()) {
                try (PreparedStatement ps = createPreparedStatementDelete(connection, id)) {
                    ps.execute();
                    System.out.println("Voo deletado com sucesso");
                }
            }
        } catch (SQLException e) {
            throw new Error("Erro ao deletar voo" + e);
        }
    }

    private static PreparedStatement createPreparedStatementDelete(Connection connection, Integer id)throws  SQLException{
        String sql = "DELETE FROM `agencia_viagem_lazer_ferias`.`voos` WHERE (`id_voo` = ?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,id);
        return ps;
    }

}
