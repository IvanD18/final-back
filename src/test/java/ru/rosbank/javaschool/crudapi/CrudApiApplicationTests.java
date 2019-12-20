package ru.rosbank.javaschool.crudapi;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.context.WebApplicationContext;
import ru.rosbank.javaschool.crudapi.dto.PostSaveRequestDto;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CrudApiApplicationTests {
  @Autowired
  private WebApplicationContext context;

  @BeforeEach
  void setUp() {
    RestAssuredMockMvc.webAppContextSetup(context);
  }

  @Test
  void postsShouldBeAvailableWithoutAuth() {

    given().auth().with(httpBasic("login", "password"))
    .when()
      .get("/api/posts")
    .then()
      .statusCode(200)
        // org.hamcrest
      .body("$", not(emptyArray()))
      .body("$", hasSize(3));
  }

  @Test
  void shouldBeAbleCreatePostWithAuth() {
    // given - предусловия
    // when - когда (что-то делаем)
    // then - тогда (что-то проверяем)
    given()
        .auth().with(httpBasic("login", "password"))
        .contentType(ContentType.JSON)
        .body(new PostSaveRequestDto(0, "Last", null))
    .when()
        .post("/api/posts")
    .then()
        .apply(print())
        .statusCode(200)
        // org.hamcrest
        .body("id", not(equalTo(0)));
  }

  @Test
  void shouldBeAbleSeeFreshPosts() {
    // given - предусловия
    // when - когда (что-то делаем)
    // then - тогда (что-то проверяем)
    given()
        .when()
        .get("/api/posts")
        .then()
        .statusCode(200)
        // org.hamcrest
        .body("$", not(emptyArray()))
        .body("$", hasSize(3));
  }
}
