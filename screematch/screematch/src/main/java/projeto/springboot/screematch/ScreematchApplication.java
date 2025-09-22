package projeto.springboot.screematch;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import projeto.springboot.screematch.model.DadosEpisodio;
import projeto.springboot.screematch.model.DadosSerie;
import projeto.springboot.screematch.model.DadosTemporada;
import projeto.springboot.screematch.principal.Principal;
import projeto.springboot.screematch.service.ConsumoApi;
import projeto.springboot.screematch.service.CoverteDados;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreematchApplication implements CommandLineRunner {

	public static void main(String[] args) {


		SpringApplication.run(ScreematchApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
	}
}
