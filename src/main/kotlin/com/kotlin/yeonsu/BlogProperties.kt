package com.kotlin.yeonsu

import org.springframework.boot.context.properties.ConfigurationProperties

// 코틀린에서 추천하는 애플리케이션 속성을 관리하는 방법은 읽기전용 속성을 사용하는 것이다.

@ConfigurationProperties("blog")
// application.properties (또는 application.yml) 설정 파일에서 'blog' 프리픽스를 가진 설정정보 로드

data class BlogProperties(var title: String, val banner: Banner) {
    data class Banner(val title: String? = null, val content: String)
}

// 이 클래스는 @ConfigurationProperties를 사용하여 설정 정보를 로드하므로,
// application.properties에서 다음과 같이 설정 정보를 정의할 수 있다.

// blog.title: My Blog
// blog.banner.title: Welcome to My Blog
// blog.banner.content: This is a sample blog.

// 그런 다음 Spring Boot 애플리케이션에서 Blog Properties 클래스의 객체를 주입하고 설정 정보를 사용할 수 있다
// 이렇게 설정 정보를 중앙에서 관리하면 Spring Boot 애플리케이션의 여러 부분에서 동일한 설정을 사용할 수 있고
// 런타임에 동적으로 변경할 수 있다.

// 이후 최상위 Application level에서 사용 가능
