package hung.hoang.pagrp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "shops")
public class ShopEntity {
    @Id
    Integer id;
    @Column(name = "startDate")
    Date startDate;
    @Column(name = "endDate")
    Date endDate;
}
