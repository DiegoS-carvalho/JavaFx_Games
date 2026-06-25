package br.ds.senac.gamesfx.ui.estudios;

import br.ds.senac.gamesfx.data.repository.EstudioRepository;
import br.ds.senac.gamesfx.model.Estudio;
import br.ds.senac.gamesfx.model.Jogo;
import br.ds.senac.gamesfx.model.Plataforma;
import br.ds.senac.gamesfx.ui.jogos.TelaJogo;
import br.ds.senac.gamesfx.ui.plataformas.TelaPlataforma;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class PainelEstudios {


    private Stage stage;
public PainelEstudios(Stage stage) {this.stage = stage;}

    public VBox criarPainelEstudios(){
    VBox painelEstudios = new VBox();

    painelEstudios.setPadding(new Insets(15,20,20,20));
        painelEstudios.setStyle("-fx-background-color:  #2F3336;");

        //cabeçalho
        Label lblTitulo = new Label("Listagem de Estúdios");
        lblTitulo.setStyle( "-fx-font-size: 36; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill:#C2B98A;");



        //agora entra a tabela de estudios
        TableView<Estudio> tabelaEstudios = new TableView<>();
        VBox.setVgrow(tabelaEstudios, Priority.ALWAYS);

        //as colunas da tabela agora

        TableColumn<Estudio, Integer> colunaId = new TableColumn("ID");
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaId.setStyle("-fx-text-fill:#D8C79A;");
        colunaId.setPrefWidth(50);
        colunaId.setMaxWidth(50);
        colunaId.setResizable(false);

        TableColumn<Estudio, String> colunaNomeE = new TableColumn("ESTUDIO");
        colunaNomeE.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaNomeE.setStyle("-fx-text-fill:#D8C79A;");
        colunaNomeE.setPrefWidth(400);
        colunaNomeE.setMaxWidth(400);
        colunaNomeE.setResizable(false);

        TableColumn<Estudio, String> colunaFundador = new TableColumn("FUNDADOR");
        colunaFundador.setCellValueFactory(new PropertyValueFactory<>("nomeFundador"));
        colunaFundador.setStyle("-fx-text-fill:#D8C79A;");
        colunaFundador.setPrefWidth(200);
        colunaFundador.setMaxWidth(200);
        colunaFundador.setResizable(false);

        //importar os dados do da tabela
        EstudioRepository repository = new EstudioRepository();
        tabelaEstudios.setItems(repository.getEstudios());

        tabelaEstudios.getColumns().addAll(colunaId,colunaNomeE, colunaFundador);
        tabelaEstudios.setStyle(

                "-fx-background-color: #23272A;" +
                        "-fx-control-inner-background: #23272A;" +
                        "-fx-table-cell-border-color: transparent;"

        );

        HBox botoes = new HBox(30);
        botoes.setPadding(new Insets(20,0,0,20));
        botoes.setAlignment(Pos.BASELINE_RIGHT);

        Button btnAdd =  criarBotao("Adicionar", "/imagens/save.png");

        btnAdd.setOnAction(e->{
            TelaEstudios telaEstudios = new TelaEstudios("cadastrar");
            telaEstudios.criarTela(stage);
            tabelaEstudios.setItems(repository.getEstudios());
        });

        Button btnView =  criarBotao("Ver", "/imagens/view.png");
        btnView.setOnAction(e ->{
            Estudio visualizarEstudio  = tabelaEstudios.getSelectionModel().getSelectedItem();
            alerta(visualizarEstudio);
            TelaEstudios telaEstudios1 = new TelaEstudios(visualizarEstudio,"visualizar");

            telaEstudios1.criarTela(stage);
            tabelaEstudios.setItems(repository.getEstudios());
        });


        Button btnEdit =  criarBotao("Editar", "/imagens/edit.png");
        btnEdit.setOnAction(e ->{


            Estudio EditarEstudios  = tabelaEstudios.getSelectionModel().getSelectedItem();
            TelaEstudios telaEstudios = new TelaEstudios(EditarEstudios,"cadastrar");
            alerta(EditarEstudios);
            telaEstudios.criarTela(stage);
            tabelaEstudios.setItems(repository.getEstudios());


        });


        Button btnApagar =  criarBotao("Deletar", "/imagens/trash1.png");
        btnApagar.setOnAction(e-> {
            Estudio EstudioExcluir = tabelaEstudios.getSelectionModel().getSelectedItem();


            if(EstudioExcluir == null){
                Alert alertaEstudioNulo = new Alert(Alert.AlertType.WARNING);

                alertaEstudioNulo.setTitle("Exclusão de Estúdio");
                alertaEstudioNulo.setHeaderText("selecione um Estúdio para realizar a exclusão");
                alertaEstudioNulo.showAndWait();
                return;
            }


            Alert confirmaExclusao = new Alert(Alert.AlertType.CONFIRMATION);
            confirmaExclusao.setTitle("Exclusão de Estúdio");
            confirmaExclusao.setHeaderText("Você está excluindo uma Estúdio");
            confirmaExclusao.setContentText("deseja continuar?");

            Optional<ButtonType> resposta = confirmaExclusao.showAndWait();
            ButtonType botaoSelecionado = resposta.get();


            if(botaoSelecionado == ButtonType.OK){

                repository.excluirEstudio(EstudioExcluir.getId());
                tabelaEstudios.setItems(repository.getEstudios());
            }
//                    if (resultado > 0) {
//            JOptionPane.showMessageDialog(null,"Jogo excluido com sucesso");
//                }
        });

        botoes.getChildren().addAll(btnAdd,btnView,btnEdit,btnApagar);
        //========================================================================================\\
        painelEstudios.getChildren().addAll(lblTitulo,new Separator(), tabelaEstudios,botoes);
        return painelEstudios;
    }


        public Alert alerta(Estudio estudio){
            if (estudio == null){
                Alert alertaJogoNulo = new Alert(Alert.AlertType.WARNING);

                alertaJogoNulo.setTitle("Aviso");
                alertaJogoNulo.setHeaderText("Nenhum estudio selecionado");
                alertaJogoNulo.showAndWait();
                return alertaJogoNulo;
            }
            else return null;
}

    private Button criarBotao(String textoBotao, String urlImg){
        Image image = new Image(getClass().getResourceAsStream(urlImg));
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(40);
        imageView.setFitHeight(40);

        Button botao = new Button();
        botao.setText(textoBotao);
        botao.setGraphic(imageView);
        botao.setPrefHeight(80);
        botao.setPrefWidth(140);
        botao.setStyle(" -fx-background-color: #3B4045; -fx-text-fill: #D8C79A;-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #D8C79A; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 8 14;");
        botao.setContentDisplay(ContentDisplay.TOP);

        return botao;
    }

}



