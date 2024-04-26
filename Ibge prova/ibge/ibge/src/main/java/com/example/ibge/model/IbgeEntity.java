package com.example.ibge.model;

import com.mongodb.client.model.Collation;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;



@Document(collection = "ibge")
public class IbgeEntity {

    @Id
    private String codigo;
    private String nome;

}

