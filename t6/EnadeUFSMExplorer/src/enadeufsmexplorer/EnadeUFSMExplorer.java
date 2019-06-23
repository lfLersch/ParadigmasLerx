/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enadeufsmexplorer;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
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

    MenuItem miOpen = new MenuItem("Open");
    MenuItem miExit = new MenuItem("Exit");
    MenuItem miAbout = new MenuItem("About");
    MenuBar menuBar = new MenuBar();
    FileReader arq;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws FileNotFoundException {
        mFile.getItems().addAll(miOpen, miExit);
        mHelp.getItems().add(miAbout);
        menuBar.getMenus().addAll(mFile, mHelp);
        arq = new FileReader("enade.csv");
        readArq(arq);
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
                about(stage);
            }
        });

        miExit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.exit(0);

            }
        });

        miOpen.setOnAction(new EventHandler<ActionEvent>() {
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
        System.out.println(questoes.get(4).acertosBrasil);
        tabela.getColumns().addAll(tcAno, tcProva, tcTQuestao, tcIDQuestao, tcObjeto, tcACurso, tcARegiao, tcABrasil, tcDif);
        VBox vbPrincipal = new VBox();
        vbPrincipal.setSpacing(10);
        vbPrincipal.setAlignment(Pos.TOP_CENTER);
        

        miAbout.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                about(stage);
            }
        });

        miExit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        vbPrincipal.getChildren().addAll(menuBar, tabela);
        stage.setScene(new Scene(vbPrincipal, 500, 500));
        stage.show();
    }

    public void readArq(FileReader arq) {
        try {
            BufferedReader lerArq = new BufferedReader(arq);
            char c = 'a';
            Questao questao;
            String[] vetS;
            String[] vetQ = new String[9];
            String linha;
            lerArq.readLine();
            int contV, contA;
            while (lerArq.ready()) {
                contV = 0;
                contA = 0;
                linha = "";
                while (contV < 17 && lerArq.ready()) {
                    c = (char) lerArq.read();
                    if (c == '"') {
                        contA++;
                    } else if (c == ',') {
                        if (contA % 2 == 1) {
                            c = '.';
                        } else {
                            contV++;
                        }
                    }
                    linha += c;
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
                 questao = new Questao(vetQ);
                 questao.ImprimeQuestao();
                 listaQuestoes.add(questao);

            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

    }
}
