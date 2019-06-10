/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randompickercmd;
import static java.awt.SystemColor.desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import javafx.stage.Stage;


// Exemplo em JavaFX com tratamento de evento associado a um objeto da classe Button
// Ver mais sobre classes anonimas em:
// https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html


public class RandomPickerCmd {
   public static void main(String[] args) throws IOException {
      List<String> lista = new ArrayList<>();
       
       try {
      FileReader arq = new FileReader("texto.txt");
      BufferedReader lerArq = new BufferedReader(arq);
      String linha = lerArq.readLine();
      while(linha != null){
        lista.add(linha);
        linha = lerArq.readLine();
      }
      arq.close();
    } catch (IOException e) {
        System.err.printf("Erro na abertura do arquivo: %s.\n",
          e.getMessage());
    }
      Collections.shuffle(lista);
      Random r = new Random();
            while(lista.size() > 0){
                int i = r.nextInt(lista.size());
                System.out.println(lista.get(i));
                lista.remove(i);
                System.in.read();
             }
            
                System.out.println("Empty List");
   }
}
