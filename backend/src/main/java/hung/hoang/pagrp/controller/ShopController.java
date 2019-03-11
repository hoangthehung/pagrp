package hung.hoang.pagrp.controller;

import hung.hoang.pagrp.controller.dto.SearchShopDto;
import hung.hoang.pagrp.controller.dto.ShopResultDto;
import hung.hoang.pagrp.domain.Shop;
import hung.hoang.pagrp.domain.ShopResult;
import hung.hoang.pagrp.exception.FileCSVNotFoundException;
import hung.hoang.pagrp.exception.InvalidInputException;
import hung.hoang.pagrp.features.ShopUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController()
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ShopUsecase shopUsecase;
    @PostMapping(value = "/upload")
    public ShopResultDto uploadMultipart(@RequestParam ("file") MultipartFile file) {
        try {
            List<Shop> shops = shopUsecase.importCSV(file.getInputStream());
            List<ShopResult> duplicate = shopUsecase.validateDuplicated(shops);
            List<ShopResult> invalid = shopUsecase.validateDate(shops);
            return new ShopResultDto(duplicate.size() == 0 && invalid.size() == 0, duplicate, invalid);
        } catch (IOException e) {
            throw new FileCSVNotFoundException("File CSV not found", e);
        }
    }
    @GetMapping(value = "/search")
    public SearchShopDto search(@RequestParam("date") String date) {
        List<Shop> shops = shopUsecase.findByDate(date);
        return new SearchShopDto(shops);
    }
}
