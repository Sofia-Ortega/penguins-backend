package com.example.penguins.configurations;

import com.example.penguins.entities.User;
import com.example.penguins.repositories.PenguinRepository;
import com.example.penguins.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Component
public class OnStartupAndShutdown {
    private final UserRepository userRepository;
    private final PenguinRepository penguinRepository;

    User superUser;

    OnStartupAndShutdown(PenguinRepository penguinRepository, UserRepository userRepository) {
        this.penguinRepository = penguinRepository;
        this.userRepository = userRepository;
        this.superUser = null;
    }


    @PostConstruct
    private void getHungerDifference() {

        // Note: penguin table at 8/12/24 10:39 PM
        //  id | hunger | love | name |  species
        //----+--------+------+------+------------
        //  1 |    104 |   20 | joe  | ROCKHOPPER
        //  2 |     14 |   30 | sam  | EMPEROR
        //  3 |      7 |   10 | bob  | ADELIE


        Optional<User> superUserOptional = userRepository.findById(Constants.SUPER_USER_ID);

        if (superUserOptional.isEmpty()) {
            throw new RuntimeException("super user not found!");
        }

        superUser = superUserOptional.get();
        if (superUser.getLastSeen() == null) superUser.setLastSeen(Instant.now().minusSeconds(3600));

        Duration timeSinceLastLogin = Duration.between(superUser.getLastSeen(), Instant.now());
        int hoursSinceLastLogin = (int) (timeSinceLastLogin.getSeconds() / 3600);
        Integer hungerGained = hoursSinceLastLogin * Constants.HUNGER_PER_HOUR;

        penguinRepository.findAll().forEach(penguin -> {
            Integer newHunger = Math.min(penguin.getHunger() + hungerGained, Constants.MAX_HUNGER);
            penguin.setHunger(newHunger);
            penguinRepository.save(penguin);
        });

        updateLastSeen();

    }

    @PreDestroy
    private void updateLastSeen() {
        superUser.setLastSeen(Instant.now());
        userRepository.save(superUser);
    }
}
