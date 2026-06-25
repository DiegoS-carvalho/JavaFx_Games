package br.ds.senac.gamesfx.ui.plataformas;

import br.ds.senac.gamesfx.data.repository.PlataformaRepository;
import br.ds.senac.gamesfx.model.Estudio;
import br.ds.senac.gamesfx.model.Jogo;
import br.ds.senac.gamesfx.model.Plataforma;
import br.ds.senac.gamesfx.ui.estudios.TelaEstudios;
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


public class PainelPlataformas {


    private Stage stage;
    
    public PainelPlataformas(Stage stage){this.stage = stage;}
    
    public VBox criarPainelPlataformas(){
        VBox painelPlataformas = new VBox();
        painelPlataformas.setPadding(new Insets(15,20,20,20));
        painelPlataformas.setStyle("-fx-background-color:  #2F3336;");
        Label lblTitulo = new Label("Listagem de Plataformas");
        lblTitulo.setStyle("-fx-font-size: 36; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill:#C2B98A;");
       //===============Tabela Plataformas=======================
        TableView<Plataforma> tabelaPlataformas = new TableView<>();
        //Arrumando o espaço da tabela.
        VBox.setVgrow(tabelaPlataformas, Priority.ALWAYS);
        
        
        // Colunas da tabela

        TableColumn<Plataforma, Integer> colunaId = new TableColumn("ID");
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaId.setStyle("-fx-text-fill:#D8C79A;");
        colunaId.setPrefWidth(50);
        colunaId.setMaxWidth(50);
        colunaId.setResizable(false);

        TableColumn<Plataforma,String> colunaTitulo = new TableColumn<>("PLATAFORMA");
        colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaTitulo.setStyle("-fx-text-fill:#D8C79A;");
        colunaTitulo.setPrefWidth(400);
        colunaTitulo.setMaxWidth(400);
        colunaTitulo.setResizable(false);



        TableColumn<Plataforma, String> colunaFabricante = new TableColumn("FABRICANTE");
        colunaFabricante.setCellValueFactory(new PropertyValueFactory<>("Fabricante"));
        colunaFabricante.setStyle("-fx-text-fill:#D8C79A;");
        colunaFabricante.setPrefWidth(200);
        colunaFabricante.setMaxWidth(200);
        colunaFabricante.setResizable(false);


        PlataformaRepository repository = new PlataformaRepository();
        tabelaPlataformas.setItems(repository.getPlataformas());

        tabelaPlataformas.getColumns().addAll(colunaId,colunaTitulo,colunaFabricante);
        tabelaPlataformas.setStyle(
                "-fx-background-color: #23272A;" +
                        "-fx-control-inner-background: #23272A;" +
                        "-fx-table-cell-border-color: transparent;"

        );




        HBox botoes = new HBox(30);
        botoes.setPadding(new Insets(20,0,0,20));
        botoes.setAlignment(Pos.BASELINE_RIGHT);

        Button btnAdd =  criarBotao("Adicionar", "/imagens/save.png");
        btnAdd.setOnAction(e->{
            TelaPlataforma telaPlataforma = new TelaPlataforma("cadastrar");
            telaPlataforma.criarTela(stage);
            tabelaPlataformas.setItems(repository.getPlataformas());
        });

        Button btnView =  criarBotao("Ver", "/imagens/view.png");
        btnView.setOnAction(e ->{
            Plataforma visualizarPlataforma  = tabelaPlataformas.getSelectionModel().getSelectedItem();
            alerta(visualizarPlataforma);
            TelaPlataforma telaPlataforma1 = new TelaPlataforma(visualizarPlataforma,"visualizar");

            telaPlataforma1.criarTela(stage);
            tabelaPlataformas.setItems(repository.getPlataformas());
        });

        Button btnEdit =  criarBotao("Editar", "/imagens/edit.png");
        btnEdit.setOnAction(e ->{

            Plataforma editarPlataforma  = tabelaPlataformas.getSelectionModel().getSelectedItem();
            alerta(editarPlataforma);
            TelaPlataforma telaPlataforma = new TelaPlataforma(editarPlataforma,"editar");
            telaPlataforma.criarTela(stage);
            tabelaPlataformas.setItems(repository.getPlataformas());


        });


        Button btnApagar =  criarBotao("Deletar", "/imagens/trash1.png");
        btnApagar.setOnAction(e-> {
            Plataforma PlataformaExcluir = tabelaPlataformas.getSelectionModel().getSelectedItem();


            if(PlataformaExcluir == null){
                Alert alertaPlataformaNulo = new Alert(Alert.AlertType.WARNING);

                alertaPlataformaNulo.setTitle("Exclusão de Plataforma");
                alertaPlataformaNulo.setHeaderText("selecione uma Plataforma para realizar a exclusão");
                alertaPlataformaNulo.showAndWait();
                return;
            }


            Alert confirmaExclusao = new Alert(Alert.AlertType.CONFIRMATION);
            confirmaExclusao.setTitle("Exclusão de Plataforma");
            confirmaExclusao.setHeaderText("Você está excluindo uma Plataforma");
            confirmaExclusao.setContentText("deseja continuar?");

            Optional<ButtonType> resposta = confirmaExclusao.showAndWait();
            ButtonType botaoSelecionado = resposta.get();


            if(botaoSelecionado == ButtonType.OK){

                repository.excluirPlataforma(PlataformaExcluir.getId());
                tabelaPlataformas.setItems(repository.getPlataformas());
            }
//                    if (resultado > 0) {
//            JOptionPane.showMessageDialog(null,"Jogo excluido com sucesso");
//                }
        });




        botoes.getChildren().addAll(btnAdd,btnView,btnEdit,btnApagar);
        painelPlataformas.getChildren().addAll(lblTitulo,new Separator(), tabelaPlataformas,botoes);
        return  painelPlataformas;
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
    public Alert alerta(Plataforma plataforma){
        if (plataforma == null){
            Alert alertaJogoNulo = new Alert(Alert.AlertType.WARNING);

            alertaJogoNulo.setTitle("Aviso");
            alertaJogoNulo.setHeaderText("Nenhuma plataforma selecionado");
            alertaJogoNulo.showAndWait();
            return alertaJogoNulo;
        }
        else return null;
    }
}
