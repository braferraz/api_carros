package com.braian.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.braian.dev.entity.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long>{

}
