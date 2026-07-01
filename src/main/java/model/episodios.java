package model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class episodios {
    private Integer temp;
    private String tituloEp;
    private Integer numeroEp;
    private double avaliacaoEp;
    private LocalDate dataLancamento;
    private  String tempoEp;


    public episodios(Integer numeroTemp, DadosEpisodios dadosEpisodios) {
        this.temp = numeroTemp;
        this.tituloEp = dadosEpisodios.tituloEp();
        this.numeroEp = dadosEpisodios.numeroEp();
        try {
            this.avaliacaoEp = Double.valueOf(dadosEpisodios.avaliacaoEp());
        }  catch (NumberFormatException e) {
            this.avaliacaoEp = 0.0;
        }

        try {
            this.dataLancamento = LocalDate.parse(dadosEpisodios.dataLancamento());
        } catch (DateTimeParseException e) {
            this.dataLancamento = null;
        }


    }


    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public String getTituloEp() {
        return tituloEp;
    }

    public void setTituloEp(String tituloEp) {
        this.tituloEp = tituloEp;
    }

    public Integer getNumeroEp() {
        return numeroEp;
    }

    public void setNumeroEp(Integer numeroEp) {
        this.numeroEp = numeroEp;
    }

    public double getAvaliacaoEp() {
        return avaliacaoEp;
    }

    public void setAvaliacaoEp(double avaliacaoEp) {
        this.avaliacaoEp = avaliacaoEp;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getTempoEp() {
        return tempoEp;
    }

    public void setTempoEp(String tempoEp) {
        this.tempoEp = tempoEp;
    }

    @Override
    public String toString() {
        return "temp=" + temp +
                ", tituloEp='" + tituloEp + '\'' +
                ", numeroEp=" + numeroEp +
                ", avaliacaoEp=" + avaliacaoEp +
                ", dataLancamento=" + dataLancamento +
                ", tempoEp='" + tempoEp + '\'';
    }
}
