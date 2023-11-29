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



		Turma turma = new Turma();
		turma.setAno("2023");
		turma.setNome("Pogramacao orietado a objetos");
		turma.setSigla("POO");		
		turma.setVagasMax(50);
		turma.setVagasMin(2);
		turma.setSemestre(1);		
		turma.setId(turma.getSigla() + "-" + turma.getAno() + "-" + turma.getSemestre());
		

		turmaRepository.save(turma);

		

		// System.out.println("Matrícula:" + a.getMatricula());

		// System.out.println(TurmaRepository.findAll());
	}
}
