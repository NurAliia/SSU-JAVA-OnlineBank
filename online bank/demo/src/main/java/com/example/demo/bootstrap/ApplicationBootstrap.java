package com.example.demo.bootstrap;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.BankAccountOperationRepository;
import com.example.demo.repository.BankAccountRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by NurAliya on 29.04.2018.
 */
@Component
public class ApplicationBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BankAccountRepository bankAccountRepository;
    private BankAccountOperationRepository bankAccountOperationRepository;


    public ApplicationBootstrap(UserRepository userRepository, RoleRepository roleRepository, BankAccountRepository bankAccountRepository, BankAccountOperationRepository bankAccountOperationRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountOperationRepository = bankAccountOperationRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        User petr = new User();
        petr.setName("Petr");
        petr.setLogin("abc@mail.ru");
        petr.setAddress("Saratov");
        petr.setPhone("12323724");
        petr.setPassword("12345678");
        userRepository.save(petr);
    }
}
