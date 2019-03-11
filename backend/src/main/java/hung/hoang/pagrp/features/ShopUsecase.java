package hung.hoang.pagrp.features;

import hung.hoang.pagrp.domain.Shop;
import hung.hoang.pagrp.domain.ShopResult;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

public interface ShopUsecase {
    List<Shop> importCSV(InputStream inputStream)  throws IOException;
    List<ShopResult> validateDate(List<Shop> shops);
    List<ShopResult> validateDuplicated(List<Shop> shops);
    List<Shop> findByDate(String date);
}
