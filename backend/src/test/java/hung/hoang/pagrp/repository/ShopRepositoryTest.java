package hung.hoang.pagrp.repository;

import hung.hoang.pagrp.domain.ShopEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ShopRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShopRespository shopRespository;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private ShopEntity s1;

    @Before
    public void fillSomeDataIntoOurDb() throws ParseException {
        // Add new Users to Database
        s1 = new ShopEntity(123, sdf.parse("20190301"), sdf.parse("20190311") );
        entityManager.persist(s1);
        entityManager.persist(new ShopEntity(124, sdf.parse("20190401"), sdf.parse("20190411")));
    }

    @Test
    public void testFindByDate() throws Exception {
        // Search for specific User in Database according to lastname
        List<ShopEntity> shopEntities = shopRespository.findByDate(sdf.parse("20190309"));
        assertThat(shopEntities, contains(s1));
    }

}
