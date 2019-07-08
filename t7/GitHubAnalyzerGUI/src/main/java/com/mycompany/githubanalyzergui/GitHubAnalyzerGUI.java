/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.githubanalyzergui;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeLineJoin;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author luizf
 */
public class GitHubAnalyzerGUI extends Application {

    private Desktop desktop = Desktop.getDesktop();
    Menu mFile = new Menu("File");
    Menu mHelp = new Menu("Help");
    Menu mTools = new Menu("Tools");

    MenuItem miOpen = new MenuItem("Open");
    MenuItem miExit = new MenuItem("Exit");
    MenuItem miAbout = new MenuItem("About");
    MenuItem miCommitAnalyzer = new MenuItem("Commit analyzer");
    MenuBar menuBar = new MenuBar();
    ObservableList<Repositorio> listaRepositorio = FXCollections.<Repositorio>observableArrayList();
    // ObservableList<String> lRepositorio = FXCollections.<String>observableArrayList();

    ListView<Repositorio> lvRepositorio = new ListView<>();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        mFile.getItems().addAll(miOpen, miExit);
        mHelp.getItems().add(miAbout);
        mTools.getItems().add(miCommitAnalyzer);
        menuBar.getMenus().addAll(mFile, mHelp, mTools);
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

    private void about(final Stage stage) {
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

    private void principal(final Stage stage) {
        //Label lNCommits = new Label("Numero Commits: ");
        //Label lMedia = new Label("Media mensagens: ");
        TableView<Repositorio> tvRepositorios = new TableView<>();
        TableColumn tcNome = new TableColumn("Nome");
        TableColumn tcNCommit = new TableColumn("Numero de commits");
        TableColumn tcMCommit = new TableColumn("Media tamanho mensagens");
        List<Repositorio> lr = new ArrayList<>();
        tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcNCommit.setCellValueFactory(new PropertyValueFactory<>("numeroCommit"));
        tcMCommit.setCellValueFactory(new PropertyValueFactory<>("mediaTamanho"));
        tvRepositorios.setItems(FXCollections.observableArrayList(listaRepositorio));
        tvRepositorios.getColumns().addAll(tcNome, tcNCommit, tcMCommit);
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
        bc.setTitle("Media Tamanho: ");
        XYChart.Series series1 = new XYChart.Series();
        for (Repositorio lista : listaRepositorio) {
            if(!lista.getMediaTamanho().equals("/")){
                series1.getData().add(new XYChart.Data("", Float.parseFloat(lista.getMediaTamanho())));
            }
        }

        bc.getData().addAll(series1);

        bc.setMaxSize(200, 200);

        VBox vbPrincipal = new VBox();
        vbPrincipal.setSpacing(10);
        vbPrincipal.setAlignment(Pos.TOP_CENTER);

        miCommitAnalyzer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("OS DADOS SERÃO PROCESSADOS EM BACKGROUND");
                alert.setTitle("INFO");
                alert.show();
                for (Repositorio lista : listaRepositorio) {
                    lista.start();
                }
            }
        });

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
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(stage);

                if (file != null) {
                    try {
                        FileReader arq = new FileReader(file.getAbsolutePath());
                        BufferedReader lerArq = new BufferedReader(arq);
                        String linha = lerArq.readLine();
                        while (linha != null) {
                            Repositorio r = new Repositorio(linha);
                            //r.run();
                            listaRepositorio.add(r);
                            linha = lerArq.readLine();

                        }
                        lvRepositorio.setItems(listaRepositorio);
                        arq.close();
                        principal(stage);
                    } catch (IOException e) {
                        System.err.printf("Erro na abertura do arquivo: %s.\n",
                                e.getMessage());
                    }
                }

            }
        });

        vbPrincipal.getChildren().addAll(menuBar, tvRepositorios);
        vbPrincipal.getChildren().add(bc);

        stage.setScene(new Scene(vbPrincipal, 500, 500));
        stage.show();

    }

}
//deverão ser exibidos, para cada repositório, o número de commits e o tamanho médio das mensagens desses commits 
