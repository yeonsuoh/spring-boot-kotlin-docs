package com.kotlin.yeonsu

import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

// 웹 컨트롤러 관련 코드 테스트할 때 사용 - 웹 애플리케이션 일부를 모의(Mock) 환경에서 테스트
// 웹 요청 및 응답을 시뮬레이션하고, 웹 컨트롤러의 동작을 검증
@WebMvcTest
class HttpControllersTests(@Autowired val mockMvc: MockMvc) {

    @MockBean
    lateinit var userRepository: UserRepository
    // lateinit: 프로퍼티를 나중에 초기화할 수 있도록 허용하는 기능 (의존성 주입에 사용)

    @MockBean
    lateinit var articleRepository: ArticleRepository

    @Test
    fun `List articles`() {
        val johnDoe = User("johnDoe", "John", "Doe")
        val lorem5Article = Article("Lorem", "Lorem", "dolor sit amet", johnDoe)
        val ipsumArticle = Article("Ipsum", "Ipsum", "dolor sit amet", johnDoe)
        every { articleRepository.findAllByOrderByAddedAtDesc() } returns
            listOf(lorem5Article, ipsumArticle)
        // every - Mockk 라이브러리에서 제공하는 함수
        // 목 객체의 특정 메서드 호출( articleRepository.findAllByOrderByAddedAtDesc())에 대해
        // 어떤 경우에도 지정된 값(listOf(lorem5Article, ipsumArticle))을 반환하도록 설정

        mockMvc.perform(get("/api/article/").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.[0].author.login").value(johnDoe.login))
            .andExpect(jsonPath("\$.[0].slug").value(lorem5Article.slug))
            .andExpect(jsonPath("\$.[1].author.login").value(johnDoe.login))
            .andExpect(jsonPath("\$.[1].slug").value(ipsumArticle.slug))
        // $는 JSONpath 표현식에서 사용되는 특수 기호로 JSON 데이터의 경로를 나타낸다
        // $는 JSON 경로의 시작을 나타내며, JSON 데이터의 루트를 나타낸다.
        // $는 문자열 보간에 이용되므로 앞에 \를 붙여서 escape해야 한다.
    }
}
