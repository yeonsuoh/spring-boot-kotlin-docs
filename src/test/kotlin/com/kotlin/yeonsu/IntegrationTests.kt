package com.kotlin.yeonsu

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(
    webEnvironment =
    SpringBootTest.WebEnvironment.RANDOM_PORT,
    // 임의의 포트를 사용하여 웹 서버를 실행하도록 지정하고 테스트가 실제 HTTP 요청을 보내도록 설정
)
class IntegrationTests(@Autowired val restTemplate: TestRestTemplate) {
    // restTemplate 객체를 주입하도록 지정
    // restTemplate는 HTTP 요청을 수행하는 데 사용

    @BeforeAll
    fun setup() {
        println(">> Setup")
    }

    // 백틱 함수 이름 : 백틱으로 함수 이름을 둘러싼 후 공백과 특수 문자를 사용 가능
    // 테스트 코드에서 테스트하는 내용을 더 알기 쉽게 나타내기 위해 사용한다.
    @Test
    fun `Assert blog page title, content and status code`() {
        println(">> Assert blog page title, content and status code")
        val entity = restTemplate.getForEntity<String>("/")
        // restTemplate을 사용하여 '/' 경로로 GET 요청을 보내고 응답을 문자열로 수신
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("<h1>Blog</h1>", "Lorem")
    }

    @Test
    fun `Assert article page title, content and status code`() {
        println(">> Assert article page title, content and status code")
        val title = "Lorem"
        val entity = restTemplate.getForEntity<String>("/article/${title.toSlug()}")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains(title, "Lorem", "dolor sit amet")
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }
}
