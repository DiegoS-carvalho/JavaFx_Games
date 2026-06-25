package br.ds.senac.gamesfx.ui.estudios;

import br.ds.senac.gamesfx.data.repository.EstudioRepository;
import br.ds.senac.gamesfx.data.repository.JogoRepository;
import br.ds.senac.gamesfx.model.Estudio;
import br.ds.senac.gamesfx.model.Jogo;
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

public class TelaEstudios {


        private TextField tfId = new TextField();
        private TextField tfnome= new TextField();
        private TextField tfnomeFundador = new TextField();
        private TextField tfpaisOrigem= new TextField();
        private DatePicker dpanoFundacao = new DatePicker();
        private String operacao;


        public  TelaEstudios(){}
        public TelaEstudios(Estudio estudio, String operacao){
            this.operacao = operacao;
            tfId.setText(String.valueOf(estudio.getId()));
            tfnome.setText(estudio.getNome());
            tfnomeFundador.setText(estudio.getNomeFundador());
            dpanoFundacao.setValue(estudio.getAnoFundacao());
            tfpaisOrigem.setText(estudio.getPaisOrigem());
        }
    public TelaEstudios(String operacao){this.operacao = operacao;}
        public void criarTela(Stage stagePai){
            Stage stage = new Stage();
            stage.initOwner(stagePai);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setMaxWidth(500);
            stage.setHeight(500);
            if(operacao.equals("cadastrar")){
                stage.setTitle("Cadastro de Estúdio");}
            else if (operacao.equals("editar")) {
                stage.setTitle("Edição de Estúdio");
            } else if (operacao.equals("visualizar")) {
                stage.setTitle("Visualização de Estúdio");
            }

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
            ImageView imageView = null;
            Label lblTitulo = null;
            switch (operacao) {
                case "cadastrar":
                    imageView = new ImageView(new Image(getClass().getResourceAsStream("/imagens/save.png")));
                    lblTitulo = new Label("Cadastro de Estúdio");
                    imageView.setFitWidth(40);
                    imageView.setFitHeight(40);
                    lblTitulo.setStyle( "-fx-font-size: 28; " +
                            "-fx-font-weight: bold; " +
                            "-fx-text-fill:#C2B98A;");
                    break;

                case "editar":
                    imageView = new ImageView(new Image(getClass().getResourceAsStream("/imagens/edit.png")));
                    lblTitulo = new Label("Edição de Estúdio");
                    imageView.setFitWidth(40);
                    imageView.setFitHeight(40);
                    lblTitulo.setStyle( "-fx-font-size: 28; " +
                            "-fx-font-weight: bold; " +
                            "-fx-text-fill:#C2B98A;");
                    break;

                case "visualizar":
                    imageView = new ImageView(new Image(getClass().getResourceAsStream("/imagens/view.png")));
                    lblTitulo = new Label("Visualização de Estúdio");
                    imageView.setFitWidth(40);
                    imageView.setFitHeight(40);
                    lblTitulo.setStyle( "-fx-font-size: 28; " +
                            "-fx-font-weight: bold; " +
                            "-fx-text-fill:#C2B98A;");
                    break;
            }


            painelTitulo.getChildren().addAll(imageView,lblTitulo);

            return painelTitulo;
        }
        private VBox criarFormulario(){




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

            Label lblNome = new Label("Nome: ");
//        tfNome = new TextField();
            tfnome.setPromptText("Ex. Lego Batman II");

            Label lblNomeFundador = new Label("Fundador: ");
//        tfTitulo = new TextField();
            tfnomeFundador.setPromptText("Ex. Lego Batman II");


            Label lblAnoFundacao = new Label("Ano de Fundação: ");
//        dpDataLancamento = new DatePicker(LocalDate.now());

            Label lblPaisOrigem = new Label("País de Origem: ");
//        tfTitulo = new TextField();
            tfpaisOrigem.setPromptText("Ex. Lego Batman II");


            //adicionar na grid
            gridFormulario.add(lblid,0,0);
            gridFormulario.add(tfId,1,0);
            gridFormulario.add(lblNome,0,1);
            gridFormulario.add(tfnome,1,1);
            gridFormulario.add(lblNomeFundador,0,2);
            gridFormulario.add(tfnomeFundador,1,2);
            gridFormulario.add(lblAnoFundacao,0,3);
            gridFormulario.add(dpanoFundacao,1,3);
            gridFormulario.add(lblPaisOrigem,0,4);
            gridFormulario.add(tfpaisOrigem,1,4);

//====================================================================================================
            if (operacao.equals("visualizar")) {
                tfId.setDisable(true);
                tfnome.setDisable(true);
                tfnomeFundador.setDisable(true);
                dpanoFundacao.setDisable(true);
                tfpaisOrigem.setDisable(true);


                formulario.getChildren().addAll(gridFormulario);
                return  formulario;
            } else if (operacao.equals("") || operacao == null) {
                formulario.getChildren().addAll(gridFormulario);


                return  formulario;
            }

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
                Estudio estudio = new Estudio();
                estudio.setNome(tfnome.getText());
                estudio.setNomeFundador(tfnomeFundador.getText());
                estudio.setPaisOrigem(tfpaisOrigem.getText());
                estudio.setAnoFundacao(dpanoFundacao.getValue());

                // Criar o repositório para enviar o jogo
                EstudioRepository repository = new EstudioRepository();
                if (tfId.getText().equals("")){
                    repository.salvar(estudio);

                    Alert mensagemSalvar = new Alert(Alert.AlertType.CONFIRMATION);
                    mensagemSalvar.setTitle("cadastro de Estudios");
                    mensagemSalvar.setHeaderText("o Estudio foi gravado com sucesso");
                    mensagemSalvar.setContentText("deseja cadastrar outro Estudio?");

                    Optional<ButtonType> escolhaCadastra = mensagemSalvar.showAndWait();

                    if(escolhaCadastra.get() == ButtonType.OK){
                        limparCampos();

                    }else {
                        stage.close();
                    }


                }else{
                    estudio.setId(Integer.parseInt(tfId.getText()));
                    repository.editar(estudio);

                    Alert mensagemEditar = new Alert(Alert.AlertType.INFORMATION);
                    mensagemEditar.setTitle("Editar estudio");
                    mensagemEditar.setHeaderText("o estudio foi editado com sucesso");
                    mensagemEditar.showAndWait();

                    stage.close();

                }
                limparCampos();
            });
            if (operacao.equals("visualizar")) {

                rodape.setPrefHeight(70); // ocupa altura
                rodape.setPrefWidth(200);  // ocupa largura

                return rodape;
            } else if (operacao.equals("") || operacao == null) {


                rodape.getChildren().addAll(btnSalvar, btnApagar);
                return rodape;
            }
            rodape.getChildren().addAll(btnSalvar, btnApagar);
            return rodape;
        }
        private void limparCampos() {

            tfnome.clear();
            tfnomeFundador.clear();
            tfpaisOrigem.clear();
            dpanoFundacao.setValue(LocalDate.now());
            tfnome.requestFocus();
        }
    }

