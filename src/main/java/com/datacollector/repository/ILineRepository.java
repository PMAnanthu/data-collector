package com.datacollector.repository;

import com.datacollector.dtos.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILineRepository extends JpaRepository<Line, Long> {

    List<Line> findByFormatContaining(String name);
}