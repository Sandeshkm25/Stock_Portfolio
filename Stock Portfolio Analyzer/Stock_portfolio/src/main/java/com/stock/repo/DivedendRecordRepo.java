package com.stock.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.entity.DivendendRecord;

@Repository
public interface DivedendRecordRepo extends JpaRepository<DivendendRecord, Long>{

}
