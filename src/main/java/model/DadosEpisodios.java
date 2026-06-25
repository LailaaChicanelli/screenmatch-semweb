package model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodios(@JsonAlias("Title") String tituloEp,
                             @JsonAlias("Episode") Integer numeroEp,
                             @JsonAlias("imdbRating") String avaliacaoEp,
                             @JsonAlias("Released") String dataLancamento,
                             @JsonAlias("Runtime") String tempoEp) {
}
