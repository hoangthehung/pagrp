package hung.hoang.pagrp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopResult {
    Shop shop;
    ImportError importError;
}
