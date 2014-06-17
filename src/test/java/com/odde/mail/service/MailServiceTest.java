package com.odde.mail.service;

import com.odde.mail.model.Result;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(JMockit.class)
public class MailServiceTest {

    @Tested
    private MailService mailService;

    @Mocked
    private Transport unused = null;

    @Mocked
    private Properties properties;

    @Mocked
    private Session session;

    @Mocked
    private MimeMessage mimeMessage;

    @Mocked
    private InternetAddress address;

    @Test
    public void should_return_success_when_send_mail_success() throws Exception {
        mockSend(null);

        Result result = mailService.send("test@163.com", "test", "test");

        assertThat(result.getStatus(), is("成功"));
    }

    @Test
    public void should_return_success_when_send_multiple_mails_success() throws Exception {
        mockSend(null);

        Result result = mailService.send("test@163.com,test@sina.com", "test", "test");

        assertThat(result.getStatus(), is("成功"));
    }

    @Test
    public void should_return_failed_when_send_mail_failed() throws Exception {
        mockSend(new MessagingException());

        Result result = mailService.send("wrong", "test", "test");

        assertThat(result.getStatus(), is("失败"));
    }

    private void mockSend(final MessagingException exception) throws MessagingException {
        new Expectations() {{
            Transport.send((Message) any);
            result = exception;
        }};
    }
}