package com.example.demo

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.onSuccess
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class OneServiceTest {
    private lateinit var oneService: OneService

    @BeforeEach
    fun setUp() {
        oneService = OneService()
    }

    @Nested
    inner class DoSomething {
        @ParameterizedTest
        @MethodSource("com.example.demo.OneServiceTest#argumentsOfMyTest")
        fun `test name`(
                firstResult: Result<List<Customer>, CustomerError>,
                secondResult: Result<Customer, CustomerError>
        ) {
            // given
            // ...

            // when
            oneService.doSomething()

            // then
            firstResult.onSuccess {
                println("Check something is first result is Ok")
            }
        }
    }

    private companion object {
        @JvmStatic
        fun argumentsOfMyTest() = Stream.of(
                Arguments.of(
                        Ok(listOf(Customer("Foo BAR"))),
                        Err(CustomerError(""))
                ),
                Arguments.of(
                        Err(CustomerError("")),
                        Ok(Customer("Foo BAR"))
                )
        )
    }
}
