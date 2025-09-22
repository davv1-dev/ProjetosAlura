package projeto.springboot.screematch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String Titulo, @JsonAlias("totalSeasons") Integer totaltemporadas,@JsonAlias("imdbRating") String avalição) {
    @Override
    public String Titulo() {
        return Titulo;
    }

    @Override
    public Integer totaltemporadas() {
        return totaltemporadas;
    }

    @Override
    public String avalição() {
        return avalição;
    }
}
