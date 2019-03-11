package hung.hoang.pagrp.controller.dto;

import hung.hoang.pagrp.domain.Shop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchShopDto {
    List<Shop> data;
}
