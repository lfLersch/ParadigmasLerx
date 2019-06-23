/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enadeufsmexplorer;

/**
 *
 * @author luizf
 */
public class Questao {
    private String ano;
    String prova;
    String tipoQuestao;
    String idQuestao;
    String objeto;
    String acertosCurso;
    String acertosRegiao;
    String acertosBrasil;
    String dif;

    public Questao(String[] vet) {
        this.ano = vet[0];
        this.prova = vet[1];
        this.tipoQuestao = vet[2];
        this.idQuestao = vet[3];
        this.objeto = vet[4];
        this.acertosCurso = vet[5];
        this.acertosRegiao = vet[6];
        this.acertosBrasil = vet[7];
        this.dif = vet[8];
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getProva() {
        return prova;
    }

    public void setProva(String prova) {
        this.prova = prova;
    }

    public String getTipoQuestao() {
        return tipoQuestao;
    }

    public void setTipoQuestao(String tipoQuestao) {
        this.tipoQuestao = tipoQuestao;
    }

    public String getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(String idQuestao) {
        this.idQuestao = idQuestao;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getAcertosCurso() {
        return acertosCurso;
    }

    public void setAcertosCurso(String acertosCurso) {
        this.acertosCurso = acertosCurso;
    }

    public String getAcertosRegiao() {
        return acertosRegiao;
    }

    public void setAcertosRegiao(String acertosRegiao) {
        this.acertosRegiao = acertosRegiao;
    }

    public String getAcertosBrasil() {
        return acertosBrasil;
    }

    public void setAcertosBrasil(String acertosBrasil) {
        this.acertosBrasil = acertosBrasil;
    }

    public String getDif() {
        return dif;
    }

    public void setDif(String dif) {
        this.dif = dif;
    }
    
    public void ImprimeQuestao(){
        System.out.println("Ano" + ano + "\nprova" + prova + "\ntipoQ" + tipoQuestao + "\nidQ" + idQuestao + "\nobj" + objeto + "\nacertosC" + acertosCurso + "\nacertosR" + acertosRegiao + "\nacertosB" + acertosBrasil + "\ndif" + dif);
    }

    
    
}

