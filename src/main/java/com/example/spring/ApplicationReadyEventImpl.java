package com.example.spring;

import com.example.spring.controllers.UserController;
import com.example.spring.dtos.user.UserCreateDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationReadyEventImpl implements ApplicationListener<ApplicationReadyEvent> {

    private final UserController userController;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            initializeRolesAndAdminAccount();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeRolesAndAdminAccount() throws ParseException {
        userController.createUser(UserCreateDto.builder()
                .username("admin")
                .email("admin@localhost")
                .password("admin123")
                .dob(new SimpleDateFormat("dd/MM/yyy").parse("01/01/2022"))
                .location(new Point(10, 20))
                .build());
    }

}
