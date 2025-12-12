package com.jason.quicktix.repository;

import com.jason.quicktix.entity.GoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodRepository extends JpaRepository<GoodEntity, Long> {}
