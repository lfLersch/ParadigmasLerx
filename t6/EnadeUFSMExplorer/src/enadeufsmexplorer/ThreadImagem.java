/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enadeufsmexplorer;

import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author luizf
 */
public class ThreadImagem extends Thread {

    String url;
    VBox vbModal;
    boolean hasImage;

    public ThreadImagem(VBox vbModal, String url, boolean hImage) {
        this.vbModal = vbModal;
        this.url = url;
        this.hasImage = hImage;
    }

    @Override
    public void run() {
        if (hasImage) {
            try {
                ImageView ivImage = new ImageView(new Image(this.url, 300, 300, false, true));
                vbModal.getChildren().add(ivImage);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("IMAGE NOT FOUND");
                alert.setTitle("ERROR");
                alert.show();
            }
        }

    }
}
