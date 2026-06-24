package acreenMatch;

import model.dadosSerie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.ConsumoAPI;
import service.ConverterDados;

@SpringBootApplication
public class ScreenMatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenMatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsumoAPI consumoAPI = new ConsumoAPI();
		var jason = consumoAPI.obterDados("http://www.omdbapi.com/?t=gilmore+girls&apikey=7c8f5f4d");
		System.out.println(jason);
		ConverterDados converter = new ConverterDados();
		dadosSerie dados = converter.obterDados(jason, dadosSerie.class);
		System.out.println(dados);
	}
}
