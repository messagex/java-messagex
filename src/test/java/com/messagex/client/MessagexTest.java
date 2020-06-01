package com.messagex.client;

import com.messagex.api.request.Contact;
import com.messagex.api.request.Content;
import com.messagex.api.request.Mail;
import com.messagex.api.response.AuthoriseResponse;
import com.messagex.api.response.MailSendResponse;
import com.messagex.config.MessagexOptions;
import com.messagex.handler.AuthoriseFailHandler;
import com.messagex.handler.AuthoriseSucessHandler;
import com.messagex.handler.MailSendSuccessHandler;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.jupiter.api.Assertions.*;

public class MessagexTest {

    @Test
    public void testGetMessagexOptions() {
        MessagexOptions options = new MessagexOptions("http://localhost:9999",
                "testApiKey1234",
                "testApiSecret1234");
        Messagex client = new Messagex(options);
        assertEquals(options.getBaseUrl(), client.getMessagexOptions().getBaseUrl(), "BaseURL should be the same as passed in.");
    }

    @Test
    public void testAuthenticateSuccess() {
        try {
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 6999), 0);
            server.createContext("/api/authorise", new AuthoriseSucessHandler());
            server.setExecutor(threadPoolExecutor);
            server.start();
            MessagexOptions options = new MessagexOptions("http://localhost:6999",
                    "testApiKey1234",
                    "testApiSecret1234");
            Messagex client = new Messagex(options);
            AuthoriseResponse response = client.authenticate();
            assertNotNull(response.getBearerToken());
            server.stop(0);
            threadPoolExecutor.shutdownNow();
        } catch (Exception ex) {
            // Forcefully fail the test
            assertNull(ex);
        }

    }

    @Test
    public void testAuthenticateFailure() {
        try {
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 7999), 0);
            server.createContext("/api/authorise", new AuthoriseFailHandler());
            server.setExecutor(threadPoolExecutor);
            server.start();
            MessagexOptions options = new MessagexOptions("http://localhost:7999",
                    "testApiKey1234",
                    "testApiSecret1234");
            Messagex client = new Messagex(options);
            AuthoriseResponse response = client.authenticate();
            assertNull(response);
            server.stop(0);
            threadPoolExecutor.shutdownNow();
        } catch (Exception ex) {
            // Forcefully fail the test
            assertNotNull(ex);
            assertEquals("MessageX Authentication Failed", ex.getMessage());
        }
    }

    @Test
    public void testMailSendSucess() {
        try {
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8999), 0);
            server.createContext("/api/mail/send", new MailSendSuccessHandler());
            server.setExecutor(threadPoolExecutor);
            server.start();
            MessagexOptions options = new MessagexOptions("http://localhost:8999",
                    "testApiKey1234",
                    "testApiSecret1234");
            Messagex client = new Messagex(options);
            Mail mail = new Mail();
            Contact from = new Contact();
            from.setAddress("saurabhraje1124@gmail.com");
            mail.setFrom(from);
            Contact toAddr = new Contact();
            toAddr.setAddress("saurabh.raje@smsglobal.com");
            Contact[] to = {toAddr};
            mail.setTo(to);
            mail.setSubject("Test Email from Java SDK");
            Content htmlContent = new Content();
            htmlContent.setType("text/html");
            htmlContent.setBody("<html><head><title>Test HTML email body</title></head><body><p>Test HTML Email body</p></body></html>");
            Content plainContent = new Content();
            plainContent.setType("text/plain");
            plainContent.setBody("Test Plain Email Body");
            mail.setContent(new Content[]{htmlContent, plainContent});
            Contact replyTo = new Contact();
            replyTo.setAddress("saurabhraje1124@gmail.com");
            mail.setReplyTo(replyTo);
            MailSendResponse response = client.sendMail("testBearerToken1234", mail);
            assertNotNull(response);
            assertTrue(response.getSuccess());
            assertEquals("Email was sent successfully.", response.getMessage());
            server.stop(0);
            threadPoolExecutor.shutdownNow();
        } catch (Exception ex) {
            ex.printStackTrace();
            assertNull(ex);
        }
    }

    @Test
    public void testMailFromContactFailure() {
        try {
            MessagexOptions options = new MessagexOptions("http://localhost:8999",
                    "testApiKey1234",
                    "testApiSecret1234");
            Messagex client = new Messagex(options);
            Mail mail = new Mail();
            Contact toAddr = new Contact();
            toAddr.setAddress("saurabh.raje@smsglobal.com");
            Contact[] to = {toAddr};
            mail.setTo(to);
            mail.setSubject("Test Email from Java SDK");
            Content htmlContent = new Content();
            htmlContent.setType("text/html");
            htmlContent.setBody("<html><head><title>Test HTML email body</title></head><body><p>Test HTML Email body</p></body></html>");
            Content plainContent = new Content();
            plainContent.setType("text/plain");
            plainContent.setBody("Test Plain Email Body");
            mail.setContent(new Content[]{htmlContent, plainContent});
            Contact replyTo = new Contact();
            replyTo.setAddress("saurabhraje1124@gmail.com");
            mail.setReplyTo(replyTo);
            MailSendResponse response = client.sendMail("testBearerToken1234", mail);
            assertNull(response);
        } catch (Exception ex) {
            assertNotNull(ex);
            assertEquals("The `from` email is invalid", ex.getMessage());
        }
    }

    @Test
    public void testMailToContactFailure() {
        try {
            MessagexOptions options = new MessagexOptions("http://localhost:10000",
                "testApiKey1234",
                "testApiSecret1234");
            Messagex client = new Messagex(options);
            Mail mail = new Mail();
            Contact from = new Contact();
            from.setAddress("test@test.com");
            mail.setFrom(from);
            Contact toAddr = new Contact();
            toAddr.setName("saurabh.raje@smsglobal.com");
            Contact[] to = {toAddr};
            mail.setTo(to);
            mail.setSubject("Test Email from Java SDK");
            Content htmlContent = new Content();
            htmlContent.setType("text/html");
            htmlContent.setBody("<html><head><title>Test HTML email body</title></head><body><p>Test HTML Email body</p></body></html>");
            Content plainContent = new Content();
            plainContent.setType("text/plain");
            plainContent.setBody("Test Plain Email Body");
            mail.setContent(new Content[]{htmlContent, plainContent});
            Contact replyTo = new Contact();
            replyTo.setAddress("saurabhraje1124@gmail.com");
            mail.setReplyTo(replyTo);
            MailSendResponse response = client.sendMail("testBearerToken1234", mail);
            assertNull(response);
        } catch (Exception ex) {
            assertNotNull(ex);
            assertEquals("One or more `to` addresses are invalid", ex.getMessage());
        }
    }

}