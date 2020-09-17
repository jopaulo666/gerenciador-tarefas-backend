package br.com.jopaulo.tarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import br.com.jopaulo.tarefas.domain.task.Task;

@SpringBootApplication
public class GerenciadorTarefasBackendApplication implements RepositoryRestConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorTarefasBackendApplication.class, args);
	}
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Task.class);
	}

}
