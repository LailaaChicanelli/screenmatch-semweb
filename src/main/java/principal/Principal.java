package principal;

import model.DadosEpisodios;
import model.DadosTemp;
import model.dadosSerie;
import model.episodios;
import org.springframework.format.annotation.DateTimeFormat;
import service.ConsumoAPI;
import service.ConverterDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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


//         List<String> nomes = Arrays.asList("Nathaniel", "Adam", "Dylan", "Peter");

//         nomes.stream()
//                 .sorted()
//                 .limit(3)
//                 .filter(n -> n.startsWith("N"))
//                 .map(n -> n.toUpperCase())
//                 .forEach(System.out::println);


            List<DadosEpisodios> ddsEps = temps.stream()
                    .flatMap(t -> t.episodios().stream())
                    .collect(Collectors.toList());

            System.out.println("\nTop 5 melhores eps: ");

            ddsEps.stream()
                    .filter(e -> !e.avaliacaoEp().equalsIgnoreCase("N/A"))
                    .peek(e -> System.out.println("Primeiro filtro "))
                    .sorted(Comparator.comparing(DadosEpisodios::avaliacaoEp).reversed())
                    .peek(e -> System.out.println("Ordenação " + e))
                    .limit(5)
                    .peek(e -> System.out.println("Limite " + e))
                    .map(e -> e.tituloEp().toUpperCase())
                    .peek(e -> System.out.println("Mapeamento  " + e))
                    .forEach(System.out::println);


        System.out.println();

            List<episodios> eps = temps.stream()
                    .flatMap(t -> t.episodios().stream()
                            .map(d -> new episodios(t.numeroTemp(), d))
                    ).collect(Collectors.toList());


            eps.forEach(System.out::println);

        System.out.println();
//            System.out.println("Digite um trecho do titulo para busca: ");
//            var trechoTitulo = leitor.nextLine();
//
//
//        Optional<episodios> buscadorEps = eps.stream()
//                .filter(e -> e.getTituloEp().toUpperCase().contains(trechoTitulo.toUpperCase()))
//                .findFirst();
//        if (buscadorEps.isPresent()) {
//            System.out.println("Episódio encontrado com sucesso!" );
//            System.out.println("Temporada: " + buscadorEps.get().getTemp());
//        } else {
//            System.out.println("Episódio não encontrado!");
//        }


//        System.out.println("\nA partir de que ano que deseja buscar os episódios?\n ");
//        var ano = leitor.nextLine();
//        leitor.nextLine();
//
//
//        LocalDate dataBusca = LocalDate.of(Integer.parseInt(ano), 1, 1);
//
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
//
//        eps.stream()
//                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println(
//                        "Temporada " + e.getTemp() +
//                                " Episódio " + e.getTituloEp() +
//                                " Data lançamento " + e.getDataLancamento().format(formatador)
//                 ));


        Map<Integer, Double> avaliacaoPorTemp = eps.stream()
                .filter(e -> e.getAvaliacaoEp() > 0.0)
                .collect(Collectors.groupingBy(episodios::getTemp,
                        Collectors.averagingDouble(episodios::getAvaliacaoEp)));
        System.out.println(avaliacaoPorTemp);


        System.out.println();

        DoubleSummaryStatistics est = eps.stream()
                .filter(e -> e.getAvaliacaoEp() > 0.0)
                .collect(Collectors.summarizingDouble(episodios::getAvaliacaoEp));
        System.out.println("Média: " +est.getAverage());
        System.out.println("Melhor episódio: " +est.getMax());
        System.out.println("Pior episódio: " +est.getMin());
        System.out.println("Total de episódios: " +est.getCount());

    }

}

