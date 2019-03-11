//package hung.hoang.pagrp.features;
//
//import hung.hoang.pagrp.SpringBootVuejsApplication;
//import hung.hoang.pagrp.domain.ImportError;
//import hung.hoang.pagrp.domain.Shop;
//import hung.hoang.pagrp.domain.ShopResult;
//import hung.hoang.pagrp.repository.ShopRespository;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.IOException;
//import java.util.List;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = SpringBootVuejsApplication.class)
//public class ShopUsecaseTest {
//
//    @Autowired
//    ShopUsecase shopUsecase;
//
//    Resource csvFile;
//	@Before
//    public void init() {
//        csvFile = new ClassPathResource("files/shop.csv");
//    }
//
//    @Test
//    public void importCSV() throws IOException {
//	    List<Shop> shopList = shopUsecase.importCSV(csvFile.getInputStream());
//	    Assert.assertNotNull(shopList);
//	    Assert.assertEquals(7,shopList.size());
//    }
//
//    @Test
//    public void validateDuplicated() throws IOException {
//        csvFile = new ClassPathResource("files/duplicated_shop.csv");
//        List<Shop> shops = shopUsecase.importCSV(csvFile.getInputStream());
//        List<ShopResult> duplicated = shopUsecase.validateDuplicated(shops);
//        Assert.assertNotNull(duplicated);
//        Assert.assertEquals(3, duplicated.get(0).getShop().getId());
//        Assert.assertEquals(ImportError.DUPPLICATE_SHOP, duplicated.get(0).getImportError());
//    }
//
//    @Test
//    public void validateEndDate() throws IOException {
//        csvFile = new ClassPathResource("files/invalid_enddateshop.csv");
//        List<Shop> shops = shopUsecase.importCSV(csvFile.getInputStream());
//        List<ShopResult> invalidDate = shopUsecase.validateDate(shops);
//        Assert.assertNotNull(invalidDate);
//        Assert.assertEquals(4, invalidDate.size());
//        Assert.assertEquals(ImportError.ENDDATE_NOT_IN_FUTURE, invalidDate.get(0).getImportError());
//    }
//    @Test
//    public void validateStartDate() throws IOException {
//        csvFile = new ClassPathResource("files/invalid_startdate_shop.csv");
//        List<Shop> shops = shopUsecase.importCSV(csvFile.getInputStream());
//        List<ShopResult> invalidDate = shopUsecase.validateDate(shops);
//        Assert.assertNotNull(invalidDate);
//        Assert.assertEquals(1, invalidDate.size());
//        Assert.assertEquals(ImportError.STARTDATE_GREATER_ENDDATE, invalidDate.get(0).getImportError());
//    }
//    @Test
//    public void validateDate() throws IOException {
//        csvFile = new ClassPathResource("files/invalid_dateshop.csv");
//        List<Shop> shops = shopUsecase.importCSV(csvFile.getInputStream());
//        List<ShopResult> invalidDate = shopUsecase.validateDate(shops);
//        Assert.assertNotNull(invalidDate);
//        Assert.assertEquals(1, invalidDate.size());
//        Assert.assertEquals(ImportError.INVALID_DATE, invalidDate.get(0).getImportError());
//    }
//}
