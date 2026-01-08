package com.dgsw.winter;

import org.junit.jupiter.api.*;

import java.util.List;
import java.util.stream.Stream;

public class JUnitTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("=== BeforeAll: 전체 테스트 실행 전 단 1번 실행 ===");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("→ BeforeEach: 각 테스트 실행 전 실행");
    }

    @Test
    void test1() {
        System.out.println("★ Test1 실행");
    }

    @Test
    void test2() {
        System.out.println("★ Test2 실행");
    }

    @AfterEach
    void afterEach() {
        System.out.println("← AfterEach: 각 테스트 실행 후 실행");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("=== AfterAll: 모든 테스트 종료 후 단 1번 실행 ===");
    }

    @Test
    void main(){
        List<String> lst = List.of("1","1","1");
        Stream<String> stream = lst.stream();
        Stream<Integer> integerStream = stream.map(Integer::valueOf);
    }
}
