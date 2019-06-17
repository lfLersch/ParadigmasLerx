/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randompickergui;

import java.awt.Desktop;
import static java.awt.SystemColor.desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JButton;

// Exemplo em JavaFX com tratamento de evento associado a um objeto da classe Button
// Ver mais sobre classes anonimas em:
// https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html
public class RandomPickerGUI extends Application {

    private Desktop desktop = Desktop.getDesktop();
    Boolean fNext, fShuffle;
    Menu mFile = new Menu("File");
    Menu mHelp = new Menu("Help");

    MenuItem miOpen = new MenuItem("Open");
    MenuItem miExit = new MenuItem("Exit");
    MenuItem miAbout = new MenuItem("About");
    MenuBar menuBar = new MenuBar();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        mFile.getItems().addAll(miOpen, miExit);
        mHelp.getItems().add(miAbout);
        menuBar.getMenus().addAll(mFile, mHelp);
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
        fNext = false;
        fShuffle = true;
        RandomList random = new RandomList();
        TextArea textArea = new TextArea();
        Label lDraft = new Label("");
        Button bNext = new Button("Next");
        Button bShuffle = new Button("Shuffle");
        final ToggleGroup group = new ToggleGroup();
        RadioButton rbOn = new RadioButton("Online");
        rbOn.setToggleGroup(group);
        rbOn.setSelected(true);
        RadioButton rbOff = new RadioButton("Offline");
        rbOff.setToggleGroup(group);

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
                            textArea.appendText(linha + "\n");
                            linha = lerArq.readLine();
                        }
                        textArea.deletePreviousChar();
                        arq.close();
                    } catch (IOException e) {
                        System.err.printf("Erro na abertura do arquivo: %s.\n",
                                e.getMessage());
                    }
                }

            }
        });

        bNext.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (fNext) {
                    if (random.listaSize() > 0) {
                        lDraft.setText(random.getStrings());
                    } else {
                        fNext = false;
                        fShuffle = true;
                        lDraft.setText("");
                    }
                }
            }
        });

        bShuffle.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (fShuffle) {
                    String linha = textArea.getText();
                    String vLista[] = linha.split("\n");
                    for (int i = 0; i < vLista.length; i++) {
                        random.addString(vLista[i]);
                    }
                    if (random.listaSize() > 0 && !("".equals(textArea.getText()))) {
                        if (rbOn.isSelected()) {
                            random.shuffleOnline();
                        } else {
                            random.shuffleOffline();
                        }
                        fNext = true;
                        fShuffle = false;
                    }
                }
            }
        });

        vbPrincipal.getChildren().addAll(menuBar, textArea, lDraft, bNext, bShuffle, rbOn, rbOff);
        stage.setScene(new Scene(vbPrincipal, 500, 500));
        stage.show();
    }
}
