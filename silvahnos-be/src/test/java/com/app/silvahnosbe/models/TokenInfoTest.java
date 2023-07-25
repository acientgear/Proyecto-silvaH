package com.app.silvahnosbe.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenInfoTest {
    
    @Test
void testTokenInfoConstructor_SetsJwtToken() {
    // Given
    String jwtToken = "testToken";

    // When
    TokenInfo tokenInfo = new TokenInfo(jwtToken);

    // Then
    assertEquals(jwtToken, tokenInfo.getJwtToken());
}

@Test
void testTokenInfoGetJwtToken_ReturnsJwtToken() {
    // Given
    String jwtToken = "testToken";
    TokenInfo tokenInfo = new TokenInfo(jwtToken);

    // When
    String result = tokenInfo.getJwtToken();

    // Then
    assertEquals(jwtToken, result);
}

}
