package br.ds.senac.gamesfx.data.repository;

import br.ds.senac.gamesfx.data.ConexaoSQLite;
import br.ds.senac.gamesfx.model.Jogo;
import br.ds.senac.gamesfx.model.Plataforma;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlataformaRepository {
    public ObservableList<Plataforma> getPlataformas() {

        String sql = "SELECT * FROM tb_plataformas";

        ObservableList<Plataforma> listaPlataformas = FXCollections.observableArrayList();

        try {
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()){
                Plataforma plataforma = new Plataforma();
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String fabricante = rs.getString("fabricante");
                LocalDate dataPlataforma = LocalDate.parse(rs.getString("data_plataforma"));
                double preco = rs.getDouble("preco");

                // Popular o objeto jogo com os dados
                plataforma.setId(id);
                plataforma.setNome(nome);
                plataforma.setFabricante(fabricante);
                plataforma.setPreco(preco);
                plataforma.setDataPlataforma(dataPlataforma);

                listaPlataformas.add(plataforma);
            }

            return listaPlataformas;

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na leitura dos dados.");
            e.printStackTrace();
            return null;
        }

    }
    public void salvar(Plataforma plataforma ){

        //Instrução sql para cadastrar um novo jogo no db
        String sql = "INSERT INTO tb_plataformas (nome, fabricante, data_plataforma,preco) " +
                "VALUES(?,?,?,?)";
        //Preparar a instrução sql para o db através da conexão,
        try {

            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            stm.setString(1,plataforma.getNome());
            stm.setString(2,plataforma.getFabricante());
            stm.setString(3,plataforma.getDataPlataforma().toString());
            stm.setDouble(4,plataforma.getPreco());
            stm.executeUpdate();


            ConexaoSQLite.fecharConexao();
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na gravação");
            e.printStackTrace();
        }
    }

    public int excluirPlataforma(int id) {

            String sql = "DELETE FROM tb_plataformas WHERE id = ?";

            try {
                PreparedStatement stm = ConexaoSQLite
                        .getConexao().
                        prepareStatement(sql);
                stm.setInt(1,id);
                int resultado = stm.executeUpdate();

                ConexaoSQLite.fecharConexao();

                return resultado;
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        }


    public void editar(Plataforma plataforma) {
        String sql =
                "UPDATE tb_plataformas SET" +
                        " nome = ?," +
                        "fabricante = ?," +
                        "data_plataforma = ?," +
                        "preco = ?," +
                        " WHERE  id = ?";

        try {
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            stm.setString(1, plataforma.getNome());
            stm.setString(2, plataforma.getFabricante());
            stm.setString(3, plataforma.getDataPlataforma().toString());
            stm.setDouble(4, plataforma.getPreco());
            stm.setInt(5,plataforma.getId());
            stm.executeUpdate();
            ConexaoSQLite.fecharConexao();
        } catch (SQLException erro) {
            System.out.println("Ocorreu um erro na gravação.");
            erro.printStackTrace();
        }
    }
}