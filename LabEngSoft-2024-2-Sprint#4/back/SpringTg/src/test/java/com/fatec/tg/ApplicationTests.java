package com.fatec.tg;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")  // Certifique-se de usar o perfil de teste
class ApplicationTests {
    @Test
    void contextLoads() {
    }
}
