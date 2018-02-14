package com.example.cfdemoservices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

@SpringBootApplication
public class CfDemoServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfDemoServicesApplication.class, args);
	}

	@RestController
	class SalutationsController{
		@GetMapping("/hello")
		public String hello(){
			return "Greetings and Salutations";
		}
	}

    @RestController
    class ScalingController{
        @GetMapping("/scale-me")
        public String slow(){
            try{
                sleep(4000);
            }
            finally {
                return "Need more power!";
            }
        }
    }
}

@Component
class SampleDataCLR implements CommandLineRunner{
    private final FootballTeamRepository footballTeamRepository;

    public SampleDataCLR(FootballTeamRepository footballTeamRepository) {
        this.footballTeamRepository = footballTeamRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Stream.of("Bears", "Vikings", "Packers", "Lions")
                .forEach( name -> footballTeamRepository.save(new FootballTeam(name)));
        footballTeamRepository.findAll().forEach(System.out::println);
    }
}

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class FootballTeam{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public FootballTeam(String name) {
        this.name = name;
    }
}

interface FootballTeamRepository extends CrudRepository<FootballTeam, Long>{}
