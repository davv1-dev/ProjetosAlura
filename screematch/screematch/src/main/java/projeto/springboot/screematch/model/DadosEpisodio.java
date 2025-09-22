package projeto.springboot.screematch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(@JsonAlias("Title") String Titulo,@JsonAlias("Episode") Integer numero,@JsonAlias("imdbRating") String Avaliação, @JsonAlias("Released") String DataLançamento) {
}
