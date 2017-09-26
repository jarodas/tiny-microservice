package org.test.web.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.test.web.domain.Counter;
import org.test.web.domain.Counters;

import java.util.List;
import java.util.Optional;

@Transactional
public class CounterDAOImpl extends HibernateDaoSupport implements CounterDAO {

  private static final Logger LOGGER = LoggerFactory.getLogger(CounterDAOImpl.class);

  @Override
  public Optional<Counter> get(String name) {
    LOGGER.debug("Getting counter for name : {}", name);
    final List found = getHibernateTemplate().findByNamedParam("from Counter c where c.name=:counter", "counter", name);
    if (found.isEmpty()) {
      return Optional.empty();
    } else {
      return Optional.of((Counter.class.cast(found.get(0))));
    }
  }

  @Override
  public Counters getAll() {
    return new Counters(getHibernateTemplate().find("from Counter"));
  }

  @Override
  public void delete(String name) {
    LOGGER.debug("Removing counter : {}", name);
    final Optional<Counter> counter = get(name);
    if (counter.isPresent()) {
      getHibernateTemplate().delete(counter.get());
    } else {
      LOGGER.debug("Counter {} doesn't exist anymore", name);
    }
  }

  @Override
  public void increment(String name) {
    LOGGER.trace("Incrementing counter : {}", name);
    final Optional<Counter> counterOpt = get(name);
    final Counter counter;
    if (counterOpt.isPresent()) {
      counter = counterOpt.get().increment();
    } else {
      counter = new Counter().setName(name).increment();
    }
    getHibernateTemplate().saveOrUpdate(counter);
    LOGGER.trace("New counter ({}) value : {}", name, counter.getValue());
  }
}
