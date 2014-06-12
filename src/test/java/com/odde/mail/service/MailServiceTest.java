package com.odde.mail.service;

import com.odde.mail.model.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void should_return_success_when_send_mail_success() throws Exception {
        Result result = mailService.send("test@163.com", "test", "test");
        assertThat(result.getStatus(), is("成功"));
    }

    @Test
    public void should_return_success_when_send_multiple_mails_success() throws Exception {
        Result result = mailService.send("test@163.com,test@sina.com", "test", "test");
        assertThat(result.getStatus(), is("成功"));
    }

    @Test
    public void should_return_failed_when_send_mail_failed() throws Exception {
        Result result = mailService.send("wrong", "test", "test");
        assertThat(result.getStatus(), is("失败"));
    }
}