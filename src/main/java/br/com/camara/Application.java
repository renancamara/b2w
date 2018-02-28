package br.com.camara;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

//	@Autowired
//	private PlanetaRepository planetaRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//
//		planetaRepository.deleteAll();
//		
//		planetaRepository.save(new Planeta("Terra", "Bom", "Aguatico"));
//
//		for (Planeta planeta : planetaRepository.findAll()) {
//			System.out.println(planeta);
//		}
//		System.out.println();

	}

}
