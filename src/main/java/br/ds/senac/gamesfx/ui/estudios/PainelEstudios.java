package br.ds.senac.gamesfx.ui.estudios;

import br.ds.senac.gamesfx.data.repository.EstudioRepository;
import br.ds.senac.gamesfx.model.Estudio;
import br.ds.senac.gamesfx.model.Jogo;
import br.ds.senac.gamesfx.ui.jogos.TelaJogo;
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
        colunaNomeE.setCellValueFactory(new PropertyValueFactory<>("Nome do Estudio"));
        colunaNomeE.setStyle("-fx-text-fill:#D8C79A;");
        colunaNomeE.setPrefWidth(400);
        colunaNomeE.setMaxWidth(400);
        colunaNomeE.setResizable(false);

        TableColumn<Estudio, String> colunaFundador = new TableColumn("FUNDADOR");
        colunaFundador.setCellValueFactory(new PropertyValueFactory<>("Nome do Fundador"));
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

        });



        Button btnView =  criarBotao("Ver", "/imagens/view.png");



        Button btnEdit =  criarBotao("Editar", "/imagens/edit.png");
        btnEdit.setOnAction(e ->{




        });


        Button btnApagar =  criarBotao("Deletar", "/imagens/trash1.png");
        btnApagar.setOnAction(e-> {


            });


        botoes.getChildren().addAll(btnAdd,btnView,btnEdit,btnApagar);
        //========================================================================================\\
        painelEstudios.getChildren().addAll(lblTitulo,new Separator(), tabelaEstudios,botoes);
        return painelEstudios;
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

























