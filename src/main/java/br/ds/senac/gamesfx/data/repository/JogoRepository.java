package br.ds.senac.gamesfx.data.repository;

import br.ds.senac.gamesfx.data.ConexaoSQLite;
import br.ds.senac.gamesfx.model.Jogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
//mock fonte dados fake

public class JogoRepository {
    public ObservableList<Jogo> getJogos() {

        String sql = "SELECT * FROM tb_games";

        ObservableList<Jogo> listaJogos = FXCollections.observableArrayList();

        try {
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()){
                Jogo jogo = new Jogo();
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String categoria = rs.getString("categoria");
                String plataforma = rs.getString("plataforma");
                String estudio = rs.getString("estudio");
                double preco = rs.getDouble("preco");
                LocalDate dataLancamento = LocalDate.parse(rs.getString("data_lancamento"));
                boolean isFinalizado = rs.getInt("finalizado") == 1 ? true : false;

                // Popular o objeto jogo com os dados
                jogo.setId(id);
                jogo.setTitulo(titulo);
                jogo.setPlataforma(plataforma);
                jogo.setCategoria(categoria);
                jogo.setPreco(preco);
                jogo.setFinalizado(isFinalizado);
                jogo.setEstudio(estudio);
                jogo.setDataLancamento(dataLancamento);

                listaJogos.add(jogo);
            }

            return listaJogos;

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na leitura dos dados.");
            e.printStackTrace();
            return null;
        }

    }
    public void salvar(Jogo jogo ){

        //Instrução sql para cadastrar um novo jogo no db
        String sql = "INSERT INTO tb_games (titulo, plataforma,estudio, categoria, preco, data_lancamento," +
                " finalizado)" +
                "VALUES(?,?,?,?,?,?,?)";
        //Preparar a instrução sql para o db através da conexão,
        try {

        PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
        stm.setString(1,jogo.getTitulo());
            stm.setString(2,jogo.getPlataforma());
            stm.setString(3,jogo.getEstudio());
            stm.setString(4,jogo.getCategoria());
            stm.setDouble(5,jogo.getPreco());
            stm.setString(6,jogo.getDataLancamento().toString());
            stm.setInt(7,jogo.isFinalizado() ? 1 : 0);
            stm.executeUpdate();


            ConexaoSQLite.fecharConexao();
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na gravação");
            e.printStackTrace();
        }
    }

    public int excluir(int id) {
        return 0;
    }

    public void editar(Jogo jogo) {
        String sql =
                "UPDATE tb_games SET" +
                        " titulo = ?," +
                        "plataforma = ?," +
                        "estudio = ?," +
                        "categoria = ?," +
                        "preco = ?," +
                        "data_lancamento = ?," +
                        "finalizado = ?" +
                        " WHERE  id = ?;";

        try {
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            stm.setString(1, jogo.getTitulo());
            stm.setString(2, jogo.getPlataforma());
            stm.setString(3, jogo.getEstudio());
            stm.setString(4, jogo.getCategoria());
            stm.setDouble(5, jogo.getPreco());
            stm.setString(6, jogo.getDataLancamento().toString());
            stm.setInt(7, jogo.isFinalizado() ? 1 : 0);
            stm.setInt(8,jogo.getId());
            stm.executeUpdate();
            ConexaoSQLite.fecharConexao();
        } catch (SQLException erro) {
            System.out.println("Ocorreu um erro na gravação.");
            erro.printStackTrace();
        }
    }
}

