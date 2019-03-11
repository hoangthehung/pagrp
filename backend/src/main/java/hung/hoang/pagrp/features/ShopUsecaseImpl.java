package hung.hoang.pagrp.features;

import com.google.common.collect.Lists;
import hung.hoang.pagrp.domain.ImportError;
import hung.hoang.pagrp.domain.Shop;
import hung.hoang.pagrp.domain.ShopEntity;
import hung.hoang.pagrp.domain.ShopResult;
import hung.hoang.pagrp.repository.ShopRespository;
import hung.hoang.pagrp.util.CsvUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShopUsecaseImpl implements ShopUsecase {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    @Autowired
    ShopRespository shopRespository;
    @Override
    public List<Shop> importCSV(InputStream inputStream) throws IOException {
        List<Shop> shops = CsvUtils.read(Shop.class, inputStream);
        shopRespository.deleteAll();
        for (Shop shop : shops) {
            ShopEntity entity = null;
            try {
                entity = shopToEntity(shop);
            } catch (ParseException e) {
            }
            shopRespository.save(entity);
        }
        return shops;
    }


    @Override
    public List<ShopResult> validateDate(List<Shop> shops) {
        List<ShopResult> results = new ArrayList<>();
        for (Shop shop : shops) {
            log.info("{}: {} - {}", shop.getId(), shop.getStartDate(), shop.getEndDate());
            try {
                if (sdf.parse(shop.getEndDate()).before(new Date()))
                    results.add(new ShopResult(shop, ImportError.ENDDATE_NOT_IN_FUTURE));
                else if (sdf.parse(shop.getEndDate()).before(sdf.parse(shop.getStartDate())))
                    results.add(new ShopResult(shop, ImportError.STARTDATE_GREATER_ENDDATE));
            } catch (ParseException e) {
                results.add(new ShopResult(shop, ImportError.INVALID_DATE));
            }
        }
        return results;
    }
    @Override
    public List<ShopResult> validateDuplicated(List<Shop> shops) {
        List<ShopResult> results = new ArrayList<>();
        Set<Integer> shopIds = new HashSet<>();
        for (Shop shop : shops) {
            if (!shopIds.add(shop.getId())){
                ShopResult shopResult = new ShopResult(shop, ImportError.DUPPLICATE_SHOP);
                results.add(shopResult);
            }
        }
        return results;
    }

    @Override
    public List<Shop> findByDate(String dateStr) {
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
        }
        List<ShopEntity> entities = new ArrayList<>();
        if (date != null)
            entities = shopRespository.findByDate(date);
        else
            entities = Lists.newArrayList( shopRespository.findAll());
        List<Shop> result = entities.stream().map(entity -> entityToShop(entity)).collect(Collectors.toList());
        return result;
    }

    private Shop entityToShop(ShopEntity entity) {
        return new Shop(entity.getId().intValue(), sdf.format(entity.getStartDate()), sdf.format(entity.getEndDate()));
    }

    private ShopEntity shopToEntity(Shop shop) throws ParseException {
        return new ShopEntity(shop.getId(), sdf.parse(shop.getStartDate()), sdf.parse(shop.getEndDate()));
    }
}
