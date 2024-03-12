dependencies {
    implementation(project(":common"))

    /** spring starter */
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("org.springframework.boot:spring-boot-starter-data-redis-reactive")

    /** querydsl */
    implementation("com.querydsl:querydsl-jpa:${DependencyVersion.QUERYDSL}:jakarta")
    kapt("com.querydsl:querydsl-apt:${DependencyVersion.QUERYDSL}:jakarta")

    /** database */
    runtimeOnly("com.mysql:mysql-connector-j")
}

object DependencyVersion {
    /** querydsl */
    const val QUERYDSL = "5.0.0"
}
