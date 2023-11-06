package tdsif.turmas.turmas.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    public Turma (){

    }

    public Turma(String ano, String nome, String sigla, int vagasmax, int vagasmin, int semestre) {
        this.ano = ano;
        this.nome = nome;
        this.sigla = sigla;
        this.vagasMax = vagasmax;
        this.vagasMin = vagasmin;
        this.semestre = semestre;
        this.id = sigla+"-"+ano+"-"+semestre;
    }

    @Id
    @Column(unique = true)
    private String id;

    @Column(length = 3, nullable = false, unique = true)
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

    

}
