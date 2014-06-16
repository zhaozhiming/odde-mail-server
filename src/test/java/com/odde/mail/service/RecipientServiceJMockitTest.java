package com.odde.mail.service;

import com.odde.mail.model.Recipient;
import com.odde.mail.model.Result;
import com.odde.mail.repo.RecipientRepository;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(JMockit.class)
public class RecipientServiceJMockitTest {

    @Tested
    private RecipientService recipientService;

    @Injectable
    private RecipientRepository recipientRepository;

    @Test
    public void should_return_success_when_add_recipient_not_exist() throws Exception {
        Result result = recipientService.add("Tom", "test@test.com");
        assertThat(result.getStatus(), is("成功"));
    }

    @Test
    public void should_return_failed_when_add_recipient_exist() throws Exception {
        new NonStrictExpectations() {{
            recipientRepository.findByEmail("test@test.com");
            result = new Recipient("Tom", "test@test.com");
        }};

        Result result = recipientService.add("Tom", "test@test.com");
        assertThat(result.getStatus(), is("失败"));
    }

    @Test
    public void should_result_recipients_when_list_recipients() throws Exception {
        final Recipient tom = new Recipient("Tom", "test@test.com");
        new NonStrictExpectations() {{
            recipientRepository.findAll();
            result = asList(tom);
        }};

        List<Recipient> recipients = recipientService.list();

        assertThat(recipients.size(), is(1));
        assertThat(recipients.get(0), is(tom));
    }

}