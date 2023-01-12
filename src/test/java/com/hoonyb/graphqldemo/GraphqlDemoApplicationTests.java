package com.hoonyb.graphqldemo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import com.hoonyb.graphqldemo.entity.Food;
import com.hoonyb.graphqldemo.repository.FoodRepository;

@SpringBootTest
@AutoConfigureGraphQlTester
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GraphqlDemoApplicationTests {

	@Autowired
	private GraphQlTester graphQlTester;
	@Autowired
	private FoodRepository foodRepository;
	


	@Test
	@Order(1)
	@DisplayName("Food 객체 생성")
	void newObj() {
		Food food = Food.from("apple");
		assertNotNull(food);
	}

	// @Test
	@Test
	@Order(1)
	@DisplayName("데이터 생성")
	void insertBaseData() {
		System.out.println("beforeAll");
		foodRepository.save( Food.from("apple"));
		foodRepository.save( Food.from("banana"));
		foodRepository.save( Food.from("orange"));
		foodRepository.save( Food.from("lemon"));
		foodRepository.save( Food.from("grape"));
	}

	@Test
	@Order(2)
	@DisplayName("Food Insert")
	void insertFood() throws IOException {
		graphQlTester.documentName("save")  // 쿼리 지정
			.variable("name", "mango") // 인자 넘기기
			.execute()
			.path("save.id") // id 반환하지 않을 경우 예외
			.entity(Long.class)  // 반환값에 자료형은 자바 클래스
			.isEqualTo(6L)
			.path("save.name")
			.entity(String.class)
			.isEqualTo("mango");
	}

	@Test
	@Order(3)
	@DisplayName("Food Insert 후 조회")
	void insertAndSelect() throws Exception {
		foodRepository.save( Food.from("cake"));
		graphQlTester.documentName("getFood")
			.variable("name", "cake")
			.execute()
			.path("getFood.name")
			.entity(String.class)
			.isEqualTo("cake");
	}

	@Test
	@Order(4)
	@DisplayName("Food 조회")
	void selectFood() throws Exception {
		graphQlTester.documentName("getFood")
			.variable("name", "banana")
			.execute()
			.path("getFood.name")
			.entity(String.class)
			.isEqualTo("banana");
	}


}
