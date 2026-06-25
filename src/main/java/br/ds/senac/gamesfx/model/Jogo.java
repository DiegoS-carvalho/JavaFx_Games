package br.ds.senac.gamesfx.model;

import java.time.LocalDate;

public class Jogo {
    private int id;
    private String titulo;
    private int plataforma;
    private String nomePlataforma;
    private int estudio;
    private String nomeEstudio;
    private double preco;
    private LocalDate DataLancamento;
    private boolean finalizado;

    public Jogo(int id, String titulo, int plataforma){
        this.id = id;
        this.titulo = titulo;
        this.plataforma = plataforma;
    }

    public Jogo() {
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

    public int getEstudio() {
        return estudio;
    }

    public void setEstudio(int estudio) {
        this.estudio = estudio;
    }
//===========================================================\\
    public int getPlataforma() {
        return plataforma;
    }
    public void setPlataforma(int plataforma) {
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
