package com.example.demo.dao;

import com.example.demo.entityMongodb.Feedback;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {

    public List<Feedback> findByEmail(String email);

}
