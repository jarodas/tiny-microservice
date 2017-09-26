package org.test.web.resources;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.test.web.dao.CounterDAO;
import org.test.web.domain.Counter;
import org.test.web.domain.Counters;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

public class CounterServiceImpl implements CounterService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CounterServiceImpl.class);

  private final CounterDAO counterDAO;

  public CounterServiceImpl(CounterDAO counterDAO) {
    this.counterDAO = counterDAO;
  }

  @Override
  public Counter get(String name) {
    if (StringUtils.isBlank(name)) {
      LOGGER.error("Blank counter name passed to service!");
      throw new BadRequestException("Counter name cannot be empty");
    } else {
      return counterDAO.get(name).orElseThrow(NotFoundException::new);
    }
  }

  @Override
  public Counters getAll() {
    return counterDAO.getAll();
  }

  @Override
  public void delete(String name) {
    LOGGER.debug("Deleting a counter : {}", name);
    if (StringUtils.isBlank(name)) {
      LOGGER.error("Cannot delete a counter of empty name!");
      throw new BadRequestException("Cannot delete a counter of empty name");
    } else {
      counterDAO.delete(name);
    }
  }

  @Override
  public void increment(String name) {
    if (StringUtils.isBlank(name)) {
      LOGGER.error("Cannot increment a counter of empty name!");
      throw new BadRequestException("Cannot increment a counter of empty name");
    } else {
      counterDAO.increment(name);
    }
  }
}
