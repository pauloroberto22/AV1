package com.example.ibge.repository;

import org.springframework.stereotype.Repository;

import com.example.ibge.model.IbgeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Repository
public interface IbgeRepository extends MongoRepository<IbgeEntity,String> {

}
