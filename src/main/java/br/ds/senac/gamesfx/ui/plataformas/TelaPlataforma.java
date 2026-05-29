package br.ds.senac.gamesfx.ui.plataformas;

import br.ds.senac.gamesfx.data.repository.JogoRepository;
import br.ds.senac.gamesfx.data.repository.PlataformaRepository;
import br.ds.senac.gamesfx.model.Plataforma;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Optional;

public class TelaPlataforma {


        private TextField tfId = new TextField();
        private TextField tfnome= new TextField();
        private ComboBox<String> comboFabricante = new ComboBox<String>();
        private DatePicker dpDataPlataforma = new DatePicker();
        private TextField tfpreco = new TextField();

        public TelaPlataforma(){}
        public TelaPlataforma(Plataforma plataforma){
            tfId.setText(String.valueOf(plataforma.getId()));
            tfnome.setText(plataforma.getNome());
            comboFabricante.setValue((plataforma.getFabricante()));
            dpDataPlataforma.setValue(plataforma.getDataPlataforma());
            tfpreco.setText(String.valueOf(plataforma.getPreco()));
        }
        public void criarTela(Stage stagePai){
            Stage stage = new Stage();
            stage.initOwner(stagePai);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setMaxWidth(500);
            stage.setHeight(500);
            stage.setTitle("Cadastro de Jogo");

            BorderPane raiz = new BorderPane();
            raiz.setTop(criarPainelTitulo());
            raiz.setCenter(criarFormulario());
            raiz.setBottom(criarRodape(stage));
            Scene cena = new Scene(raiz,500,550);

            stage.setHeight(550);
            stage.setWidth(500);
            stage.setResizable(false);
            stage.setScene(cena);
            stage.showAndWait();

        }
        public HBox criarPainelTitulo(){

            HBox painelTitulo = new HBox();

            painelTitulo.setPadding(new Insets(20,0,20,20));
            painelTitulo.setStyle("-fx-background-color:#2F3336; ");
//        painelTitulo.set
            painelTitulo.setAlignment(Pos.CENTER_LEFT);

            Image image = new Image(getClass().getResourceAsStream("/imagens/save.png"));
            ImageView imageView = new ImageView(image);

            imageView.setFitWidth(40);
            imageView.setFitHeight(40);

            Label lblTitulo = new Label("Cadastro de Jogos");

            lblTitulo.setStyle( "-fx-font-size: 28; " +
                    "-fx-font-weight: bold; " +
                    "-fx-text-fill:#C2B98A;");
            painelTitulo.getChildren().addAll(imageView,lblTitulo);

            return painelTitulo;
        }
        private VBox criarFormulario(){


            ObservableList<String> fabricante = FXCollections
                    .observableArrayList(
                            "Tt Games" ,"Rockstar Games", "Naughty Dog", "Ubisoft", "Electronic Arts", "Capcom", "Square Enix", "Bethesda", "Nintendo", "Insomniac Games", "FromSoftware"
                    );

            VBox formulario = new VBox();
            formulario.setPadding(new Insets(10));
//====================================================================================================

            GridPane gridFormulario = new GridPane();
            gridFormulario.setVgap(7);


            //Criar os componentes para a grid

            Label lblid = new Label("ID: ");
//        tfId = new TextField();
            tfId.setEditable(false);
            tfId.setDisable(true);

            Label lblNome = new Label("Título: ");
//        tfTitulo = new TextField();
            tfnome.setPromptText("Ex. Lego Batman II");


            Label lblFabricante = new Label("Estudio: ");
            comboFabricante.setItems(fabricante);

            Label lblPreco = new Label("Valor: ");
//        tfValor = new TextField();
            tfpreco.setPromptText("Ex. 9,99");

            Label lblDataPlataforma = new Label("Data de Lançamento: ");
//        dpDataLancamento = new DatePicker(LocalDate.now());



            //adicionar na grid
            gridFormulario.add(lblid,0,0);
            gridFormulario.add(tfId,1,0);
            gridFormulario.add(lblNome,0,1);
            gridFormulario.add(tfnome,1,1);
            gridFormulario.add(lblFabricante,0,2);
            gridFormulario.add(comboFabricante,1,2);
             gridFormulario.add(lblPreco,0,4);
            gridFormulario.add(tfpreco,1,4);
            gridFormulario.add(lblDataPlataforma,0,5);
            gridFormulario.add(dpDataPlataforma,1,5);

//====================================================================================================


            formulario.getChildren().addAll(gridFormulario);


            return  formulario;
        }
        private Button criarBotao(String textoBotao, String urlImg) {


            Image image = new Image(getClass().getResourceAsStream(urlImg));
            ImageView imageView = new ImageView(image);

            imageView.setFitWidth(40);
            imageView.setFitHeight(40);


            Button botao = new Button();
            botao.setText(textoBotao);
            botao.setGraphic(imageView);
            botao.setPrefHeight(40);
            botao.setPrefWidth(120);
            botao.setStyle(" -fx-background-color: #3B4045; -fx-text-fill: #D8C79A;-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #D8C79A; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 8 14;");


            return botao;
        }
        private HBox criarRodape(Stage stage){

            HBox rodape = new HBox();
            rodape.setPadding(new Insets (10,5,10,10));
            rodape.setStyle("-fx-background-color:#6B6D70 ; ");

            Button btnSalvar = criarBotao("salvar","/imagens/save.png");
            btnSalvar.setTooltip(new Tooltip("Salvar"));
            Button btnApagar = criarBotao("Apagar","/imagens/trash1.png");
            btnApagar.setTooltip(new Tooltip("Apagar"));
            rodape.setAlignment(Pos.BASELINE_RIGHT);

            rodape.setSpacing(10);
            btnSalvar.setTooltip(new Tooltip("Salvar dados do jogo"));

            btnSalvar.setOnAction(evento ->{
                Plataforma plataforma = new Plataforma();
                plataforma.setNome(tfnome.getText());
                plataforma.setFabricante(comboFabricante.getValue());
                plataforma.setDataPlataforma(dpDataPlataforma.getValue());
                plataforma.setPreco(Double.parseDouble(tfpreco.getText()));

                // Criar o repositório para enviar o jogo
                PlataformaRepository repository = new PlataformaRepository();
                if (tfId.getText().equals("")){
                    repository.salvar(plataforma);

                    Alert mensagemSalvar = new Alert(Alert.AlertType.CONFIRMATION);
                    mensagemSalvar.setTitle("cadastro de Plataforma");
                    mensagemSalvar.setHeaderText("a plataforma foi gravado com sucesso");
                    mensagemSalvar.setContentText("deseja cadastrar outra Plataforma?");

                    Optional<ButtonType> escolhaCadastra = mensagemSalvar.showAndWait();

                    if(escolhaCadastra.get() == ButtonType.OK){
                        limparCampos();

                    }else {
                        stage.close();
                    }


//                }else{
//                    plataforma.setId(Integer.parseInt(tfId.getText()));
//                    repository.editar(plataforma);
//
//                    Alert mensagemEditar = new Alert(Alert.AlertType.INFORMATION);
//                    mensagemEditar.setTitle("Editar Jogo");
//                    mensagemEditar.setHeaderText("o jogo foi editado com sucesso");
//                    mensagemEditar.showAndWait();
//
//                    stage.close();
//
                }
                limparCampos();
            });
            rodape.getChildren().addAll(btnSalvar, btnApagar);
            return rodape;
        }
        private void limparCampos() {

            tfnome.clear();
            tfpreco.clear();
            comboFabricante.setValue(" ");

            dpDataPlataforma.setValue(LocalDate.now());

        }
    }


