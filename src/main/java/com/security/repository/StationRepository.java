package com.security.repository;


import java.time.LocalDateTime;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.security.model.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    
    @Query("SELECT s FROM Station s ORDER BY s.id DESC")
    Page<Station> findAllWithFilters(Pageable pageable);
}