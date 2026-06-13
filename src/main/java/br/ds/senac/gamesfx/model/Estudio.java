package br.ds.senac.gamesfx.model;

import java.time.LocalDate;

public class Estudio {

    private int id;
    private String nome;
    private String nomeFundador;
    private LocalDate anoFundacao;
    private String paisOrigem;


    public Estudio(){};




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFundador() {
        return nomeFundador;
    }

    public void setNomeFundador(String nomeFundador) {
        this.nomeFundador = nomeFundador;
    }

    public LocalDate getAnoFundacao() {
        return anoFundacao;
    }

    public void setAnoFundacao(LocalDate anoFundacao) {
        this.anoFundacao = anoFundacao;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }
}

