package com.kotlin.yeonsu

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(BlogProperties::class)
// BlogProperties 클래스에 적용된 @ConfigurationProperties("blog") 어노테이션을 활성화
// blog 프리픽스를 가진 설정 정보를 로드하고 BlogProperties 클래스를 Spring 빈으로 등록
class YeonsuApplication

fun main(args: Array<String>) {
    runApplication<YeonsuApplication>(*args)
}
