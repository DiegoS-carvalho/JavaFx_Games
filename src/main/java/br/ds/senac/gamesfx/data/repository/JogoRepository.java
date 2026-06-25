package br.ds.senac.gamesfx.data.repository;

import br.ds.senac.gamesfx.data.ConexaoSQLite;
import br.ds.senac.gamesfx.model.Estudio;
import br.ds.senac.gamesfx.model.Jogo;
import br.ds.senac.gamesfx.model.Plataforma;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
//mock fonte dados fake

public class JogoRepository {
Plataforma plataformaRepo = new Plataforma();
EstudioRepository estudioRepository = new EstudioRepository();
    public ObservableList<Jogo> getJogos() {


        String sql =
                "SELECT " +
                        "g.id, " +
                        "g.titulo, " +
                        "p.nome AS nome_plataforma, " +
                        "e.nome AS estudio, " +
                        "g.preco, " +
                        "g.data_lancamento, " +
                        "g.finalizado " +
                        "FROM tb_games AS g " +
                        "INNER JOIN tb_plataformas AS p ON g.plataforma = p.id " +
                        "INNER JOIN tb_estudio AS e ON g.estudio = e.id;";


        ObservableList<Jogo> listaJogos = FXCollections.observableArrayList();

        try {
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()){
                Jogo jogo = new Jogo();
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");

                int plataforma = rs.getInt("nome_plataforma");
                int estudio = rs.getInt("estudio");

                double preco = rs.getDouble("preco");
                LocalDate dataLancamento = LocalDate.parse(rs.getString("data_lancamento"));
                boolean isFinalizado = rs.getInt("finalizado") == 1 ? true : false;

                // Popular o objeto jogo com os dados
                jogo.setId(id);
                jogo.setTitulo(titulo);
                jogo.setPlataforma(plataforma);

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
        String sql = "INSERT INTO tb_games (titulo, plataforma,estudio, preco, data_lancamento," +
                " finalizado) " +
                "VALUES(?,?,?,?,?,?)";
        //Preparar a instrução sql para o db através da conexão,
        try {

        PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
        stm.setString(1,jogo.getTitulo());
            stm.setInt(2, jogo.getPlataforma());
            stm.setInt(3,jogo.getEstudio());
            stm.setDouble(4,jogo.getPreco());
            stm.setString(5,jogo.getDataLancamento().toString());
            stm.setInt(6,jogo.isFinalizado() ? 1 : 0);
            stm.executeUpdate();


            ConexaoSQLite.fecharConexao();
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na gravação");
            e.printStackTrace();
        }
    }

    public int excluir (int id){
        String sql = "DELETE FROM tb_games WHERE id = ?";

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
            stm.setInt(2, jogo.getPlataforma());
            stm.setInt(3, jogo.getEstudio());

            stm.setDouble(4, jogo.getPreco());
            stm.setString(5, jogo.getDataLancamento().toString());
            stm.setInt(6, jogo.isFinalizado() ? 1 : 0);
            stm.setInt(7,jogo.getId());
            stm.executeUpdate();
            ConexaoSQLite.fecharConexao();
        } catch (SQLException erro) {
            System.out.println("Ocorreu um erro na gravação.");
            erro.printStackTrace();
        }
    }
}

