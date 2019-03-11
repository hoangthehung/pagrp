package hung.hoang.pagrp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hung.hoang.pagrp.SpringBootVuejsApplication;
import hung.hoang.pagrp.controller.dto.SearchShopDto;
import hung.hoang.pagrp.controller.dto.ShopResultDto;
import io.restassured.internal.util.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(
		classes = SpringBootVuejsApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class ShopControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Before
    public void init() {
    }

	@Test
	public void test_upload_success() throws Exception {
		Resource csvFile = new ClassPathResource("files/shop.csv");
		MockMultipartFile multipartFile = new MockMultipartFile("file", "files/shop.csv",
				"text/csv", IOUtils.toByteArray(csvFile.getInputStream()));
		MvcResult result = this.mvc.perform(MockMvcRequestBuilders.fileUpload("/shop/upload")
				.file(multipartFile))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		ShopResultDto response = objectMapper.readValue(contentAsString, ShopResultDto.class);
		Assert.assertEquals(false, response.isImportSuccess());
		Assert.assertEquals(7, response.getInvalidDateShops().size());
	}
	@Test
	public void test_upload_success_duplicate_shop() throws Exception {
		Resource csvFile = new ClassPathResource("files/duplicated_shop.csv");
		MockMultipartFile multipartFile = new MockMultipartFile("file", "duplicated_shop.csv",
				"text/csv", IOUtils.toByteArray(csvFile.getInputStream()));
		MvcResult result = this.mvc.perform(MockMvcRequestBuilders.fileUpload("/shop/upload")
				.file(multipartFile))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		ShopResultDto response = objectMapper.readValue(contentAsString, ShopResultDto.class);
		Assert.assertEquals(false, response.isImportSuccess());
		Assert.assertEquals(1, response.getDuplicatedShops().size());
	}
	@Test
	public void test_upload_success_invaliddate_shop() throws Exception {
		Resource csvFile = new ClassPathResource("files/invalid_enddateshop.csv");
		MockMultipartFile multipartFile = new MockMultipartFile("file", "invalid_enddateshop.csv",
				"text/csv", IOUtils.toByteArray(csvFile.getInputStream()));
		MvcResult result = this.mvc.perform(MockMvcRequestBuilders.fileUpload("/shop/upload")
				.file(multipartFile))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		String contentAsString = result.getResponse().getContentAsString();

		ShopResultDto response = objectMapper.readValue(contentAsString, ShopResultDto.class);
		Assert.assertEquals(false, response.isImportSuccess());
		Assert.assertEquals(4, response.getInvalidDateShops().size());
	}
	@Test
	public void test_search() throws Exception {
		Resource csvFile = new ClassPathResource("files/search_shop.csv");
		MockMultipartFile multipartFile = new MockMultipartFile("file", "files/search_shop.csv",
				"text/csv", IOUtils.toByteArray(csvFile.getInputStream()));
		this.mvc.perform(MockMvcRequestBuilders.fileUpload("/shop/upload")
				.file(multipartFile))
				.andExpect(MockMvcResultMatchers.status().isOk());

		MvcResult rs = this.mvc.perform(MockMvcRequestBuilders.get("/shop/search").param("date", "20191019"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		String contentAsString = rs.getResponse().getContentAsString();

		SearchShopDto response = objectMapper.readValue(contentAsString, SearchShopDto.class);
		Assert.assertEquals(1, response.getData().size());
		Assert.assertEquals(4, response.getData().get(0).getId());
	}

//	@Test
//	public void test_search_all() throws Exception {
//		Resource csvFile = new ClassPathResource("files/search_shop.csv");
//		MockMultipartFile multipartFile = new MockMultipartFile("file", "files/search_shop.csv",
//				"text/csv", IOUtils.toByteArray(csvFile.getInputStream()));
//		this.mvc.perform(MockMvcRequestBuilders.fileUpload("/shop/upload")
//				.file(multipartFile))
//				.andExpect(MockMvcResultMatchers.status().isOk());
//
//		MvcResult rs = this.mvc.perform(MockMvcRequestBuilders.get("/shop/search").param("date", ""))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andReturn();
//		String contentAsString = rs.getResponse().getContentAsString();
//
//		SearchShopDto response = objectMapper.readValue(contentAsString, SearchShopDto.class);
//		Assert.assertEquals(5, response.getData().size());
//	}
}
