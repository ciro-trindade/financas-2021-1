package br.financas.fatec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;



@Entity
@Table(name = "tb_categoria")
public class Categoria extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nm_nome", length = 50)
	@NotBlank
	@Length(min = 4, max = 50)
	private String nome;

	public Categoria() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
