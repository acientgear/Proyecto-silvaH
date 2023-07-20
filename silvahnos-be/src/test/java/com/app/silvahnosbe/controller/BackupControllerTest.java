package com.app.silvahnosbe.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.silvahnosbe.controllers.BackupController;
import com.app.silvahnosbe.services.BackupService;

@ExtendWith(SpringExtension.class)
public class BackupControllerTest {


    @Mock
    private BackupService backupServiceMock;

    @InjectMocks
    private BackupController backupController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoBackup() throws ClassNotFoundException, IOException, SQLException {
        // Given
        // Stubbing the void method with doNothing()
        doNothing().when(backupServiceMock).makeBackup();

        // When
        ResponseEntity<String> responseEntity = backupController.doBackup();

        // Then
        // Verificar que el método makeBackup() se haya llamado
        verify(backupServiceMock).makeBackup();
        // Agregar más aserciones según tus necesidades sobre el objeto ResponseEntity
    }
}
