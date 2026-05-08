package br.ds.senac.gamesfx.model;

import java.time.LocalDate;

public class Jogo {
    private int id;
    private String titulo;
    private double preco;
    private LocalDate DataLancamento;
    private String estudio;
    private String plataforma;
    private boolean finalizado;

    public Jogo(int id, String titulo, String plataforma){
        this.id = id;
        this.titulo = titulo;
        this.plataforma = plataforma;
    }
//===========================================================\\

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
//===========================================================\\

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
//===========================================================\\

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
//===========================================================\\

    public LocalDate getDataLancamento() {
        return DataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        DataLancamento = dataLancamento;
    }
// ===========================================================\\

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }
//===========================================================\\
    public String getPlataforma() {
        return plataforma;
    }
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
//===========================================================\\

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
//===========================================================\\


}
