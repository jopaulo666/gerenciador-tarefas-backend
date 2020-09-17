package br.com.jopaulo.tarefas.domain.task;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.jopaulo.tarefas.domain.user.AppUser;

@Entity
public class Task {

	@Id
	@GeneratedValue
	private Integer id;
	
	@NotEmpty(message = "Descrição é obrigatória")
	@Length(min = 3, max = 40, message = "Tamanho da tarefa inválido")
	private String description;
	
	@NotNull(message = "Data da tarefa é obrigatória")
	@FutureOrPresent(message = "A data da tarefa não deve ser uma data passada")
	private LocalDate  whenToDo;
	
	private Boolean done = false;
	
	@ManyToOne
	@JoinColumn(name = "app_user_id")
//	@NotNull(message = "Usuário da tarefa é obrigatória")
	private AppUser appUser;
	
	public Task() {
	}
	
	public Task(String description, LocalDate whenToDo, Boolean done) {
		this.description = description;
		this.whenToDo = whenToDo;
		this.done = done;
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getWhenToDo() {
		return whenToDo;
	}

	public void setWhenToDo(LocalDate whenToDo) {
		this.whenToDo = whenToDo;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
}
