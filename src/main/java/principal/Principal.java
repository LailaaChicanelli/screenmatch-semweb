package principal;

import model.DadosEpisodios;
import model.DadosTemp;
import model.dadosSerie;
import service.ConsumoAPI;
import service.ConverterDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitor = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverterDados converter = new ConverterDados();

    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=7c8f5f4d";

    public void exibirMenu() {
        System.out.println("Digite o nome da série que deseja buscar:");
        var nomeSerie = leitor.nextLine();
        var json = consumoAPI.obterDados(
        ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);

        dadosSerie dados = converter.obterDados(json, dadosSerie.class);
        System.out.println(dados);

        List<DadosTemp> temps = new ArrayList<>();

        for (int i = 1; i<=dados.totalTemps(); i++) {
            json = consumoAPI.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i +  API_KEY);
            DadosTemp dadosTemp = converter.obterDados(json, DadosTemp.class);
            temps.add(dadosTemp);
        }
        temps.forEach(System.out::println);


//         for (int i = 0; i <dados.totalTemps(); i++) {
//             List<DadosEpisodios> epPorTemp = temps.get(i).episodios();
//             for (int j = 0; j < epPorTemp.size(); j++) {
//                 System.out.println(epPorTemp.get(j).tituloEp());
//             }
//         }

         temps.forEach(t -> t.episodios().forEach(e -> System.out.println(e.tituloEp())));
    }
}
