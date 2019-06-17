/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randompickergui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author luizf
 */
public class RandomList {

    private List<String> lista;

    public List<String> getLista() {
        return lista;
    }

    public void setLista(List<String> lista) {
        this.lista = lista;
    }

    public void readArq(FileReader arq) {
        try {
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            while (linha != null) {
                this.lista.add(linha);
                linha = lerArq.readLine();
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

    }

    public void shuffleOffline() {
        Collections.shuffle(this.lista);
    }

    public void shuffleOnline() {
        try {
            String urlstr = "https://www.random.org/lists/?mode=advanced";
            URL url = new URL(urlstr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setDoOutput(true);
            String data = null;
            data = arrangeData();
            
            con.getOutputStream().write(data.getBytes("UTF-8"));

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            String responseLine;
            StringBuffer response = new StringBuffer();
            this.lista.clear();
            while ((responseLine = in.readLine()) != null) {
                response.append(responseLine + "\n");
                this.lista.add(responseLine);
            }
            System.out.println(response);

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            shuffleOffline();
        }
    }

    public int listaSize() {
        return this.lista.size();
    }

    public String getStrings() {
        String s = new String();
        s = this.lista.get(0);
        this.lista.remove(0);
        return s;
    }

    public void addString(String s) {
        this.lista.add(s);
    }

    public RandomList() {
        this.lista = new ArrayList<>();
    }
    
    private String arrangeData(){
        //= "list=Fulano%0D%0ABeltrano%0D%0ASicrano&format=plain&rnd=new";
        int i = 0;
        String data = "list=" + this.lista.get(i);
        i++;
        while(this.lista.size() > i){
            data += "%0D%0A" + this.lista.get(i);
            i++;
        }
        data += "&format=plain&rnd=new";
        System.out.println(data);
        return data;
    }
}
