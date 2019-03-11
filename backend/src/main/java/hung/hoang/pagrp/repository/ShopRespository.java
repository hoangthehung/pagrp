package hung.hoang.pagrp.repository;

import hung.hoang.pagrp.domain.ShopEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ShopRespository extends CrudRepository<ShopEntity, Integer> {
    @Query("SELECT s FROM ShopEntity s WHERE :date BETWEEN s.startDate AND s.endDate")
    List<ShopEntity> findByDate(@Param("date") Date date);
}
