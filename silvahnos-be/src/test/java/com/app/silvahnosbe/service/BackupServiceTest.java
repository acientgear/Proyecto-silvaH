/*package com.app.silvahnosbe.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.app.silvahnosbe.services.BackupService;

@ExtendWith(SpringExtension.class)
public class BackupServiceTest {
    
    @Mock
    private Runtime runtimeMock;

    @InjectMocks
    private BackupService backupService;

    @Test
    public void testMakeBackup() throws ClassNotFoundException, IOException, InterruptedException {
        // Given
        MockitoAnnotations.openMocks(this);

        // Stubbing the Runtime.getRuntime() method
        when(runtimeMock.exec(anyString())).thenReturn(processMock);
        when(processMock.waitFor()).thenReturn(0); // Set the process return value to 0 (success)

        // Set the values for the @Value annotated fields in BackupService
        backupService.setDatabaseUsername("your_username");
        backupService.setDatabasePassword("your_password");
        backupService.setDatabaseName("your_database_name");
        backupService.setBackupPath("your_backup_path");

        // When
        backupService.makeBackup();

        // Then
        // Add your assertions here, or verify that certain methods were called
    }
}
*/