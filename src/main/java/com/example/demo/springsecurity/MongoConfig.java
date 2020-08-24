package com.example.demo.springsecurity;

import com.example.demo.dao.FeedbackRepository;
import com.example.demo.entityMongodb.Feedback;
import com.mongodb.MongoClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = FeedbackRepository.class)
public class MongoConfig /*extends AbstractMongoConfiguration*/{

    //INJECT NEW Feedback DOCUMENTS INTO MONGODB
    //JUST FOR TEST, JUST TO ADD SOME MORE FEEDBACKS INTO MY MONGODB
    /*@Bean
    CommandLineRunner commandLineRunner(FeedbackRepository feedbackRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                feedbackRepository.save(new Feedback("Neki Subject", "Poruka222", "Ime", "030300000","i@g", "08.04.2020"));
                feedbackRepository.save(new Feedback("Neki Subject22", "Poruka222111", "Ime11", "030300000","i@g11", "08.04.2020"));
            }
        };
    }*/

    /*@Override
    public MongoClient mongoClient() {
        return new MongoClient("127.0.0.1", 27017);
    }

    @Override
    protected String getDatabaseName() {
        return "FeedbackTest";
    }



    @Override
    protected String getMappingBasePackage() {
        return "com.example.demo";
    }*/

}
