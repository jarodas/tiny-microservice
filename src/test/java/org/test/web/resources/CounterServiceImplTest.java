package org.test.web.resources;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.web.dao.CounterDAO;
import org.test.web.domain.Counter;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CounterServiceImplTest {

  @Mock
  private CounterDAO counterDAOMock;

  @InjectMocks
  private CounterServiceImpl service;

  @Test(expected = NotFoundException.class)
  public void get_notFound() throws Exception {
    final String counterName = "ab";
    when(counterDAOMock.get(counterName)).thenReturn(Optional.empty());

    service.get(counterName);
  }

  @Test(expected = BadRequestException.class)
  public void get_emptyRequest() throws Exception {
    final String counterName = " "; //possible with %20

    service.get(counterName);
  }

  @Test
  public void get_success() throws Exception {
    final String counterName = "metric"; //possible with %20
    final int value = 128;
    final Counter counter = new Counter().setName(counterName).setValue(value);
    when(counterDAOMock.get(counterName)).thenReturn(Optional.of(counter));

    final Counter result = service.get(counterName);

    Assert.assertEquals(value, result.getValue());
    verify(counterDAOMock).get(counterName);
  }

  @Test
  public void getAll() throws Exception {
  }

  @Test
  public void delete() throws Exception {
  }

  @Test
  public void increment() throws Exception {
  }

}