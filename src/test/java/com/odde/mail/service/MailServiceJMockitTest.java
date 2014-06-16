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
import javax.mail.Transport;

import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(JMockit.class)
public class MailServiceJMockitTest {

    @Tested
    private MailService mailService;

    @Mocked
    private Transport unused = null;

    @Mocked
    private Properties properties;

    @Test
    public void should_return_success_when_send_mail_success() throws Exception {
        new Expectations() {{
            Transport.send((Message) any);
        }};

        Result result = mailService.send("test@163.com", "test", "test");
        assertThat(result.getStatus(), is("成功"));
    }

    @Test
    public void should_return_success_when_send_multiple_mails_success() throws Exception {
        new Expectations() {{
            Transport.send((Message) any);
        }};

        Result result = mailService.send("test@163.com,test@sina.com", "test", "test");
        assertThat(result.getStatus(), is("成功"));
    }

    @Test
    public void should_return_failed_when_send_mail_failed() throws Exception {
        new Expectations() {{
            Transport.send((Message) any);
            result = new MessagingException();
        }};

        Result result = mailService.send("wrong", "test", "test");
        assertThat(result.getStatus(), is("失败"));
    }
}