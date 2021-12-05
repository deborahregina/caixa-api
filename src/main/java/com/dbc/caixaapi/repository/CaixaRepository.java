package com.dbc.caixaapi.repository;

import com.dbc.caixaapi.entity.CaixaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CaixaRepository extends MongoRepository<CaixaEntity, String> {


}
