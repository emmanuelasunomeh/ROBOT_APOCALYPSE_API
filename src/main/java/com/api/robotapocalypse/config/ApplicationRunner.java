package com.api.robotapocalypse.config;
import com.api.robotapocalypse.entity.Survival;
import com.api.robotapocalypse.repo.SurvivalRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Data
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    private SurvivalRepository survivalRepository;

    @Override
    public void run(String... args) throws Exception {
        this.addSurvivalList();
        System.out.println("----APPLICATION-RUNNER CALLED----");

    }

    public void addSurvivalList(){
        List<Survival> stocks;
        stocks= Stream.of(
                new Survival( "Mark Steve",20L,"MALE", null, Boolean.FALSE, null),
        new Survival( "Job Bell",30L,"FEMALE", null, Boolean.FALSE,null),
                        new Survival( "Adam Stone",40L,"MALE", null, Boolean.FALSE, null)
                )
                .collect(Collectors.toList());
        survivalRepository.saveAll(stocks);

    }
}