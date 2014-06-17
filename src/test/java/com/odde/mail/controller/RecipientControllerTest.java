package com.odde.mail.controller;

import com.odde.mail.model.Recipient;
import com.odde.mail.model.Result;
import com.odde.mail.service.RecipientService;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

@RunWith(JMockit.class)
public class RecipientControllerTest {

    @Tested
    private RecipientController recipientController;

    @Injectable
    private RecipientService recipientService;

    @Test
    public void should_return_status_success_when_add_recipient_success() throws Exception {
        verifyAddRecipientByResult("成功");
    }

    @Test
    public void should_return_status_failed_when_add_recipient_failed() throws Exception {
        verifyAddRecipientByResult("失败");
    }

    @Test
    public void should_return_recipients_when_list_success() throws Exception {
        final Recipient tom = new Recipient("Tom", "test@test.com");
        new NonStrictExpectations() {{
            recipientService.list();
            result = asList(tom);
        }};

        String result = recipientController.list();
        assertThat(result, containsString("\"username\":\"Tom\",\"email\":\"test@test.com\""));
    }

    private void verifyAddRecipientByResult(final String serviceResult) throws Exception {
        new NonStrictExpectations() {{
            recipientService.add("Tom", "test@test.com");
            result = new Result(serviceResult);
        }};

        String result = recipientController.add("Tom", "test@test.com");
        assertThat(result, is("{\"status\":\"" + serviceResult + "\"}"));
    }

}