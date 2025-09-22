package projeto.springboot.screematch.model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static java.lang.Double.parseDouble;

public class Episodio {
    private Integer Temporada;
    private String Titulo;
    private Integer numero;
    private Double Avaliação;
    private LocalDate DataLançamento;

    public Episodio(Integer Temporada, DadosEpisodio dados){
        try{
            this.Temporada = Temporada;
            this.Titulo = dados.Titulo();
            this.numero = dados.numero();
            this.Avaliação = parseDouble(dados.Avaliação());
            this.DataLançamento = LocalDate.parse(dados.DataLançamento());
        }catch(NumberFormatException e){
            this.Avaliação = 0.0;
        }catch (DateTimeParseException e){
            this.DataLançamento = null;
        }


    }


    public Integer getTemporada() {
        return Temporada;
    }

    public void setTemporada(Integer temporada) {
        Temporada = temporada;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Double getAvaliação() {
        return Avaliação;
    }

    public void setAvaliação(Double avaliação) {
        Avaliação = avaliação;
    }

    public LocalDate getDataLançamento() {
        return DataLançamento;
    }

    public void setDataLançamento(LocalDate dataLançamento) {
        DataLançamento = dataLançamento;
    }

    @Override
    public String toString() {
        return "Episodio{" +
                "Temporada=" + Temporada +
                ", Titulo='" + Titulo + '\'' +
                ", numero=" + numero +
                ", Avaliação=" + Avaliação +
                ", DataLançamento=" + DataLançamento +
                '}';
    }
}
