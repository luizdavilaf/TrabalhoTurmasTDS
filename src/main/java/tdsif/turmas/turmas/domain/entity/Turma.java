package tdsif.turmas.turmas.domain.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "turmas")
@Entity


public class Turma {
 

    @Id
    @Column(unique = true)
    private String id;

    @Column(length = 3, nullable = false/* , unique = true */)
    private String sigla;

    @Column(nullable = false)
    private String nome;

    @Column(length = 4, nullable = false)
    private String ano;

    @Column(nullable = false)
    private Integer vagasMin;

    @Column(nullable = false)
    private Integer vagasMax;

    @Column(nullable = false)   
    private Integer semestre;

    @ManyToMany
    @JoinTable(
        name = "matricula",
        joinColumns = @JoinColumn(name = "turma_id"),
        inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<Aluno> alunos = new ArrayList<>();

    

}
