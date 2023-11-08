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

    @Test
    fun `Assert blog page title, content and status code`() {
        val entity = restTemplate.getForEntity<String>("/")
        // restTemplate을 사용하여 '/' 경로로 GET 요청을 보내고 응답을 문자열로 수신
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("<h1>Blog</h1>")
    }

    @Test
    fun `Assert article page title, content and status code`() {
        println(">> TODO")
    }

    @AfterAll
    fun teardown() {
        println(">> Tear down")
    }
}
