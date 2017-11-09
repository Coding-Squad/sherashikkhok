package com.sherashikkhok.repository;

import com.sherashikkhok.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    
    List<Image> findByMimetype(String mimetype);
}