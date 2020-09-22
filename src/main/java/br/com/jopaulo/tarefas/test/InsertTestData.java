package br.com.jopaulo.tarefas.test;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
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
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		AppUser appUser1 = new AppUser("joao", encoder.encode("123"), "João Paulo Mendes");
		appUserRepository.save(appUser1);
		
		AppUser appUser2 = new AppUser("bruna", encoder.encode("abc"), "Bruna Suellen");
		appUserRepository.save(appUser2);
		
		LocalDate baseDate = LocalDate.parse("2020-12-01");
		
		for (int i = 1; i <= 5; i++) {
			Task task = new Task(String.format("Usuário: %s #%d", appUser1.getUsername(), i), baseDate.plusDays(i), false);
			task.setAppUser(appUser1);
			taskRepository.save(task);
		}
		
		for (int i = 1; i <= 5; i++) {
			Task task = new Task(String.format("Usuário: %s #%d", appUser2.getUsername(), i), baseDate.plusDays(i), false);
			task.setAppUser(appUser2);
			taskRepository.save(task);
		}
	}
	
}
