/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enadeufsmexplorer;

import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.DataFormat.URL;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jdk.nashorn.internal.parser.TokenType;

// Exemplo em JavaFX com tratamento de evento associado a um objeto da classe Button
// Ver mais sobre classes anonimas em:
// https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html
public class EnadeUFSMExplorer extends Application {

    private Desktop desktop = Desktop.getDesktop();
    Boolean fNext, fShuffle;
    Menu mFile = new Menu("File");
    Menu mHelp = new Menu("Help");
    List<Questao> listaQuestoes = new ArrayList<>();

    MenuItem miReload = new MenuItem("Reload");
    MenuItem miSource = new MenuItem("Source");
    MenuItem miExit = new MenuItem("Exit");
    MenuItem miAbout = new MenuItem("About");
    MenuBar menuBar = new MenuBar();
    boolean fDown;
    String url = "https://docs.google.com/spreadsheets/d/e/2PACX-1vTO06Jdr3J1kPYoTPRkdUaq8XuslvSD5--FPMht-ilVBT1gExJXDPTiX0P3FsrxV5VKUZJrIUtH1wvN/pub?gid=0&single=true&output=csv";

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws FileNotFoundException {
        mFile.getItems().addAll(miReload, miSource, miExit);
        mHelp.getItems().add(miAbout);
        menuBar.getMenus().addAll(mFile, mHelp);
        readArq();
        principal(stage);
    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(FileChooser.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }

    private void about(Stage stage) {
        VBox vbAbout = new VBox();
        vbAbout.setSpacing(10);
        vbAbout.setAlignment(Pos.TOP_CENTER);
        Button bBack = new Button("Back");
        Label lInfo = new Label("Luiz Felipe Lehmen Lersch");

        miAbout.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //about(stage);
            }
        });

        miExit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.exit(0);

            }
        });

        miReload.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                principal(stage);
            }
        });

        miSource.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                principal(stage);
            }
        });

        bBack.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                principal(stage);
            }
        });

        vbAbout.getChildren().addAll(menuBar, lInfo, bBack);
        stage.setScene(new Scene(vbAbout, 500, 500));
        stage.show();
    }

    private void principal(Stage stage) {
        //TableView tv = new TableView();
        TableView tabela = new TableView<>();

        TableColumn tcAno = new TableColumn("Ano");
        TableColumn tcProva = new TableColumn("Prova");
        TableColumn tcTQuestao = new TableColumn("Tipo Questão");
        TableColumn tcIDQuestao = new TableColumn("ID Questão");
        TableColumn tcObjeto = new TableColumn("Objeto");
        TableColumn tcACurso = new TableColumn("Acertos Curso");
        TableColumn tcARegiao = new TableColumn("Acertos Região");
        TableColumn tcABrasil = new TableColumn("Acertos Brasil");
        TableColumn tcDif = new TableColumn("Dif. (Curso-Brasil)");

        tcAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        tcProva.setCellValueFactory(new PropertyValueFactory<>("prova"));
        tcTQuestao.setCellValueFactory(new PropertyValueFactory<>("tipoQuestao"));
        tcIDQuestao.setCellValueFactory(new PropertyValueFactory<>("idQuestao"));
        tcObjeto.setCellValueFactory(new PropertyValueFactory<>("objeto"));
        tcACurso.setCellValueFactory(new PropertyValueFactory<>("acertosCurso"));
        tcARegiao.setCellValueFactory(new PropertyValueFactory<>("acertosRegiao"));
        tcABrasil.setCellValueFactory(new PropertyValueFactory<>("acertosBrasil"));
        tcDif.setCellValueFactory(new PropertyValueFactory<>("dif"));

        List<Questao> questoes = new ArrayList<>();
        questoes = listaQuestoes;

        tabela.setItems(FXCollections.observableArrayList(questoes));
        tabela.getColumns().addAll(tcAno, tcProva, tcTQuestao, tcIDQuestao, tcObjeto, tcACurso, tcARegiao, tcABrasil, tcDif);
        VBox vbPrincipal = new VBox();
        vbPrincipal.setSpacing(10);
        vbPrincipal.setAlignment(Pos.TOP_CENTER);

        tabela.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Stage stage = new Stage();
                VBox vbModal = new VBox();
                Questao q = listaQuestoes.get(tabela.getSelectionModel().getSelectedIndex());
                boolean hi = false;
                if (q.urlImage != null) {
                    hi = true;
                }
                ThreadImagem ti = new ThreadImagem(vbModal, q.urlImage, hi);
                ti.start();
                Label lAno = new Label("Ano: " + q.ano);
                Label lProva = new Label("Prova" + q.prova);
                Label lTQuestao = new Label("Tipo Questao: " + q.tipoQuestao);
                Label lIDquestao = new Label("ID Questao: " + q.idQuestao);
                Label lObjeto = new Label("Objeto" + q.objeto);
                Label lACurso = new Label("Acertos Curso: " + q.acertosCurso);
                Label lARegiao = new Label("Acertos Regiao:" + q.acertosRegiao);
                Label lABrasil = new Label("Acertos Brasil: " + q.acertosBrasil);
                Label lDif = new Label("Dif: " + q.dif);
                Label lGabarito = new Label("GABARITO: " + q.gabarito);
                vbModal.getChildren().addAll(lAno, lProva, lTQuestao, lIDquestao, lObjeto, lACurso, lARegiao, lABrasil, lDif, lGabarito);

                //}catch(IOException e){
                //vbModal.getChildren().addAll(lAno, lProva, lTQuestao, lIDquestao, lObjeto, lACurso, lARegiao, lABrasil, lDif, lGabarito);
                /*try {
                 stage.getScene().wait();
                 } catch (InterruptedException ex) {
                 Logger.getLogger(EnadeUFSMExplorer.class.getName()).log(Level.SEVERE, null, ex);
                 }*/
                CategoryAxis xAxis = new CategoryAxis();
                NumberAxis yAxis = new NumberAxis();
                BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
                bc.setTitle("Quadro de Acertos");
                xAxis.setLabel("Categoria");
                yAxis.setLabel("Acertos");

                XYChart.Series series1 = new XYChart.Series();
                try {
                    series1.getData().add(new XYChart.Data("Curso", Float.parseFloat(q.getAcertosCurso())));
                } catch (Exception e) {

                }
                try {
                    series1.getData().add(new XYChart.Data("Regiao", Float.parseFloat(q.getAcertosRegiao())));
                } catch (Exception e) {

                }
                try {
                    series1.getData().add(new XYChart.Data("Brasil", Float.parseFloat(q.getAcertosBrasil())));
                } catch (Exception e) {

                }

                bc.getData().addAll(series1);
                vbModal.getChildren().add(bc);
                bc.setMaxSize(200, 200);

                stage.setScene(new Scene(vbModal));

                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                if (q.urlImage != null) {
                    try {
                        ti.join();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(EnadeUFSMExplorer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                stage.show();

            }
        });

        miReload.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    readArq();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(EnadeUFSMExplorer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        miSource.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog();

                dialog.setTitle("Source");
                dialog.setHeaderText("Enter new URL:");

                url = dialog.showAndWait().toString();

            }

            private TextInputDialog TextInputDialog() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        miAbout.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                about(stage);
            }
        }
        );

        miExit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        vbPrincipal.getChildren().addAll(menuBar, tabela);
        stage.setScene(new Scene(vbPrincipal, 500, 500));
        stage.show();
    }

    public void readArq() throws FileNotFoundException {

        fDown = true;
        BufferedReader lerArq = BufferUrl();
        if (fDown) {
            try {
                char c = 'a';
                Questao questao;
                String[] vetS;
                String[] vetQ = new String[11];
                String linha;
                lerArq.readLine();
                int contV, contA;
                while (lerArq.ready()) {
                    contV = 0;
                    contA = 0;
                    boolean image = false;
                    linha = "";
                    while (contV < 17 && lerArq.ready()) {
                        c = (char) lerArq.read();
                        if (c == '"') {
                            contA++;
                            continue;
                        } else if (c == ',') {
                            if (contA % 2 == 1) {
                                c = '.';
                            } else {
                                contV++;
                            }
                        }
                        linha += c;
                    }
                    c = (char) lerArq.read();
                    while (c != '\r' && lerArq.ready()) {
                        image = true;
                        linha += c;
                        c = (char) lerArq.read();
                    }
                    vetS = linha.split(",");
                    vetQ[0] = vetS[1];
                    vetQ[1] = vetS[2];
                    vetQ[2] = vetS[3];
                    vetQ[3] = vetS[4];
                    vetQ[4] = vetS[5];
                    vetQ[5] = vetS[8];
                    vetQ[6] = vetS[9];
                    vetQ[7] = vetS[10];
                    vetQ[8] = vetS[11];
                    vetQ[9] = vetS[7];
                    questao = new Questao(vetQ);

                    if (image) {
                        questao.setUrlImage(vetS[17]);
                        System.out.println(questao.urlImage);
                    }

                    listaQuestoes.add(questao);

                }
            } catch (IOException e) {
                System.err.printf("Erro na abertura do arquivo: %s.\n",
                        e.getMessage());
            }

        }

    }

    private BufferedReader BufferUrl() throws FileNotFoundException {
        try {
            BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
            FileOutputStream fOut = new FileOutputStream("enade.csv");
            byte buffer[] = new byte[1024];
            int rBytes;
            while ((rBytes = in.read(buffer, 0, 1024)) != -1) {
                fOut.write(buffer, 0, rBytes);
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("FAIL IN DOWNLOAD NEW FILE");
            alert.setTitle("ERROR");
            fDown = false;
            alert.show();
        }
        FileReader fReader = new FileReader("enade.csv");
        BufferedReader bReader = new BufferedReader(fReader);
        return bReader;
    }
}
