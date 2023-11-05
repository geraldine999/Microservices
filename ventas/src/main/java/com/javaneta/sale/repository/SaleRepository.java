package com.javaneta.sale.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaneta.sale.entity.SaleEntity;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Serializable> {

    Optional<SaleEntity> findById(String id);
    
    @Query(value = "SELECT * FROM sales WHERE idcustomer = ?1", nativeQuery = true)
    List<SaleEntity> searchByIdCustomer(String id);
}
