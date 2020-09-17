package br.com.jopaulo.tarefas.test;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.jopaulo.tarefas.domain.task.Task;
import br.com.jopaulo.tarefas.domain.task.TaskRepository;
import br.com.jopaulo.tarefas.domain.user.AppUser;
import br.com.jopaulo.tarefas.domain.user.AppUserRepository;

@Component
public class InsertTestData {

	private TaskRepository taskRepository;
	private AppUserRepository appUserRepository;
	
	@Autowired
	public InsertTestData(TaskRepository taskRepository, AppUserRepository appUserRepository) {
		this.taskRepository = taskRepository;
		this.appUserRepository = appUserRepository;
	}
	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//TODO: cirptografar senha
		AppUser appUser = new AppUser("joao", "123", "João Paulo Mendes");
		appUserRepository.save(appUser);
		
		LocalDate baseDate = LocalDate.parse("2020-12-01");
		
		for (int i = 1; i <= 10; i++) {
			Task task = new Task("Tarefa #" + i, baseDate.plusDays(i), false);
			task.setAppUser(appUser);
			taskRepository.save(task);
		}
	}
	
}
