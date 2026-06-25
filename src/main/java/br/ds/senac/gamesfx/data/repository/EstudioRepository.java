package br.ds.senac.gamesfx.data.repository;

import br.ds.senac.gamesfx.data.ConexaoSQLite;
import br.ds.senac.gamesfx.model.Estudio;
import br.ds.senac.gamesfx.model.Plataforma;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class EstudioRepository {
    public ObservableList<Estudio> getEstudios() {

        String sql = "SELECT * FROM tb_estudio";

        ObservableList<Estudio> listaEstudios = FXCollections.observableArrayList();

        try {
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()){
                Estudio estudio = new Estudio();
                int id = rs.getInt("id");
                String nomeEstudio = rs.getString("nome");
                String nomeFundador = rs.getString("nomeFundador");
                String paisOrigem = rs.getString("origem");
                LocalDate anoFundacao = LocalDate.parse(rs.getString("data_lancamento"));

                // Popular o objeto jogo com os dados
                estudio.setId(id);
                estudio.setNome(nomeEstudio);
                estudio.setNomeFundador(nomeFundador);
                estudio.setPaisOrigem(paisOrigem);
                estudio.setAnoFundacao(anoFundacao);

                listaEstudios.add(estudio);
            }

            return listaEstudios;

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na leitura dos dados.");
            e.printStackTrace();
            return null;
        }

    }

    public void salvar(Estudio estudio  ){

        //Instrução sql para cadastrar um novo jogo no db
        String sql = "INSERT INTO tb_estudio (nome, nomeFundador,data_lancamento, origem) " +
                "VALUES(?,?,?,?)";
        //Preparar a instrução sql para o db através da conexão,
        try {

            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            stm.setString(1,estudio.getNome());
            stm.setString(2,estudio.getNomeFundador());
            stm.setString(3,estudio.getAnoFundacao().toString());
            stm.setString(4,estudio.getPaisOrigem());
            stm.executeUpdate();


            ConexaoSQLite.fecharConexao();
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro na gravação");
            e.printStackTrace();
        }
    }

    public int excluirEstudio(int id) {

            String sql = "DELETE FROM tb_estudio WHERE id = ?";

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


    public void editar(Estudio estudio ) {
        String sql =
                "UPDATE tb_estudio SET" +
                        " nome = ?," +
                        "nomeFundador = ?," +
                        "origem = ?," +
                        "data_lancamento = ?," +
                        " WHERE  id = ?;";

        try {
            PreparedStatement stm = ConexaoSQLite.getConexao().prepareStatement(sql);
            stm.setString(1, estudio.getNome());
            stm.setString(2, estudio.getNomeFundador());
            stm.setString(3, estudio.getAnoFundacao().toString());
            stm.setString(4, estudio.getPaisOrigem());
            stm.setInt(5,estudio.getId());
            stm.executeUpdate();
            ConexaoSQLite.fecharConexao();
        } catch (SQLException erro) {
            System.out.println("Ocorreu um erro na gravação.");
            erro.printStackTrace();
        }
    }
}

