package com.soul.jpastudy.repository;

import com.soul.jpastudy.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    Customer findByCustName(String custName);

    @Query("select c from Customer c")
    List<Customer> findAll();

    @Transactional
    @Modifying
    @Query("update Customer c set c.custName = ?1 where c.custId = ?2")
    int updateCustomer(@Param("custName") String custName, @Param("custId") Long custId);

    @Transactional
    @Modifying
    @Query("delete from Customer c where c.custId = :custId and c.custIndustry = :custIndustry")
    int deleteCustomer(@Param("custId") Long custId, @Param("custIndustry") String custIndustry);

}
