package tdsif.turmas.turmas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import tdsif.turmas.turmas.domain.entity.Turma;
import tdsif.turmas.turmas.domain.repository.TurmaRepository;

@SpringBootApplication
public class TurmasApplication {

	@Autowired
	TurmaRepository turmaRepository;

	public static void main(String[] args) {
		SpringApplication.run(TurmasApplication.class, args);
	}

	@PreDestroy
	void limpar(){
		
	}

	@PostConstruct
	void popularBanco(){
		System.out.println("Rodando o Post Construct");



		Turma a = new Turma("2023","Pogramacao orietado a objetos","POO", 50, 2 ,1);
		// a.setAno("2023");
		// a.setNome("Pogramacao orietado a objetos");
		// a.setSigla("POO");		
		// a.setVagasMax(50);
		// a.setVagasMin(2);
		// a.setSemestre(1);
		
		//a.setId(a.getSigla()+a.getAno()+a.getSemestre());
		

		turmaRepository.save(a);

		

		// System.out.println("Matrícula:" + a.getMatricula());

		// System.out.println(TurmaRepository.findAll());
	}
}
