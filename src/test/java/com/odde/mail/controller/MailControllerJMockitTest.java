package com.odde.mail.controller;

import com.odde.mail.model.Result;
import com.odde.mail.service.MailService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JMockit.class)
public class MailControllerJMockitTest {
    @Tested
    MailController mailController;

    @Injectable
    private MailService mailService;

    @Test
    public void should_return_status_success_when_send_mail_success() throws Exception {
        verifySendMailByResult("成功");
    }

    @Test
    public void should_return_status_failed_when_send_mail_failed() throws Exception {
        verifySendMailByResult("失败");
    }

    private void verifySendMailByResult(final String serviceResult) throws Exception {
        new Expectations() {{
            mailService.send("test@test.com", "test", "test");
            result = new Result(serviceResult);
        }};

        String result = mailController.send("test@test.com", "test", "test");

        assertThat(result, is("{\"status\":\"" + serviceResult + "\"}"));
    }

}