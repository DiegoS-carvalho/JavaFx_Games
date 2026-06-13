package br.ds.senac.gamesfx.data.repository;

import br.ds.senac.gamesfx.data.ConexaoSQLite;
import br.ds.senac.gamesfx.model.Estudio;
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
}
