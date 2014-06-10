package com.odde.mail.service;

import com.odde.mail.model.Recipient;
import com.odde.mail.repo.RecipientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class RecipientServiceTest {

    @Mock
    private RecipientRepository recipientRepository;

    @InjectMocks
    private RecipientService recipientService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_return_success_when_add_recipient_not_exist() throws Exception {
        when(recipientRepository.findByEmail(anyString())).thenReturn(null);
        when(recipientRepository.save(any(Recipient.class))).thenReturn(null);

        assertThat(recipientService.add("Tom", "test@test.com").getStatus(), is("成功"));
        verify(recipientRepository).findByEmail(anyString());
        verify(recipientRepository).save(any(Recipient.class));
    }

    @Test
    public void should_return_failed_when_add_recipient_exist() throws Exception {
        when(recipientRepository.findByEmail(anyString())).thenReturn(new Recipient("Tom", "test@test.com"));

        assertThat(recipientService.add("Tom", "test@test.com").getStatus(), is("失败"));
        verify(recipientRepository).findByEmail(anyString());
    }
    
    @Test
    public void should_result_recipients_when_list_recipients() throws Exception {
        Recipient tom = new Recipient("Tom", "test@test.com");
        when(recipientRepository.findAll()).thenReturn(asList(tom));

        List<Recipient> recipients = recipientService.list();

        assertThat(recipients.size(), is(1));
        assertThat(recipients.get(0), is(tom));
        verify(recipientRepository).findAll();
    }

}