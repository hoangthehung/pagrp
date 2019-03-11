package hung.hoang.pagrp.controller.dto;

import hung.hoang.pagrp.domain.ShopResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopResultDto {
    boolean isImportSuccess;
    List<ShopResult> duplicatedShops;
    List<ShopResult> invalidDateShops;
}
