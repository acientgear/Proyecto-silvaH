package com.app.silvahnosbe.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import com.app.silvahnosbe.repositories.CorreoRepository;
import com.app.silvahnosbe.repositories.EmailConfigRepository;
import com.app.silvahnosbe.repositories.FacturaRepository;
import com.app.silvahnosbe.repositories.ParametroRepository;
import com.app.silvahnosbe.util.EmailService;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {
    @Mock
    private EmailConfigRepository emailConfigRepository;

    @Mock
    private FacturaRepository facturaRepository;

    @Mock
    private ParametroRepository parametroRepository;

    @Mock
    private CorreoRepository correoRepository;

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    /*@Test
    public void testSendEmail() {
        // Configurar el comportamiento del mock del repositorio
        EmailConfig emailConfig = new EmailConfig();
        emailConfig.setUsername("correo@ejemplo.com");
        when(emailConfigRepository.findById(1L)).thenReturn(Optional.of(emailConfig));

        // Llamar al método que se está probando
        emailService.sendEmail();

        // Verificar que el método send del mailSender fue llamado con los parámetros
        // correctos
        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(messageCaptor.capture());
        SimpleMailMessage message = messageCaptor.getValue();

        assertEquals("correo@ejemplo.com", message.getFrom());
        assertEquals("luis.toro.f@usach.cl", message.getTo()[0]);
        assertEquals("Notificación factura", message.getSubject());
        assertEquals("Factura 131 está por vencer", message.getText());
    }*/

    /*@Test
    void testCronEmail() {
        // Configurar datos de prueba
        int dias = 5;
        String destino = "destino@example.com";
        String textoEmailConfig = "Texto de ejemplo";
        int numeroFactura = 123;
        String fechaActual = "20-06-23";

        FacturaEntity factura = new FacturaEntity();
        factura.setNumero_factura(numeroFactura);
        String numFac = String.valueOf(factura.getNumero_factura());

        List<FacturaEntity> facturas = new ArrayList<>();
        facturas.add(factura);

        ParametroEntity parametro = new ParametroEntity();
        parametro.setValor(String.valueOf(dias));

        CorreoEntity correo = new CorreoEntity();
        correo.setDireccion(destino);

        EmailConfig emailConfig = new EmailConfig();
        emailConfig.setTexto(textoEmailConfig);

        // Configurar comportamiento simulado de los repositorios y mailSender
        when(parametroRepository.findById(anyLong())).thenReturn(Optional.of(parametro));
        when(correoRepository.findById(anyLong())).thenReturn(Optional.of(correo));
        when(facturaRepository.facturaV(dias)).thenReturn(facturas);
        when(emailConfigRepository.findById(anyLong())).thenReturn(Optional.of(emailConfig));

        // Ejecutar el método a probar
        emailService.cronEmail();

        // Verificar que se envió el correo con los datos esperados
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));

        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setFrom(emailConfig.getUsername());
        expectedMessage.setSubject("facturas proximas a vencer:  " + "  " + fechaActual);
        expectedMessage.setTo(destino);
        expectedMessage.setText(textoEmailConfig + "\n" + "n° :" + numFac + "\n");

        verify(mailSender).send(expectedMessage);
    }*/
}
