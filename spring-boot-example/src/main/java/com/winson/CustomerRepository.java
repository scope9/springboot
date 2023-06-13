package com.winson;

import org.springframework.data.jpa.repository.JpaRepository;

//allows crud operation without any sql thanks to the import
public interface CustomerRepository
        extends JpaRepository<Customer, Integer> {
}
