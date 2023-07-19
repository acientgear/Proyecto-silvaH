package com.app.silvahnosbe.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserInfoTest {
    @Test
    public void testGetUsuario() {
        // Given
        String usuario = "testuser";
        UserInfo userInfo = new UserInfo();
        userInfo.setUsuario(usuario);
    
        // When
        String result = userInfo.getUsuario();
    
        // Then
        assertEquals(usuario, result);
    }

    @Test
    public void testSetUsuario() {
        // Given
        String usuario = "testuser";
        UserInfo userInfo = new UserInfo();

        // When
        userInfo.setUsuario(usuario);

        // Then
        assertEquals(usuario, userInfo.getUsuario());
    }

    @Test
    public void testGetPassword() {
        // Given
        String password = "testpassword";
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(password);

        // When
        String result = userInfo.getPassword();

        // Then
        assertEquals(password, result);
    }

    @Test
    public void testSetPassword() {
        // Given
        String password = "testpassword";
        UserInfo userInfo = new UserInfo();

        // When
        userInfo.setPassword(password);

        // Then
        assertEquals(password, userInfo.getPassword());
    }
}
