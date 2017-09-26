package org.test.web.dao;

import org.test.web.domain.Counter;
import org.test.web.domain.Counters;

import java.util.Optional;

public interface CounterDAO {

  Optional<Counter> get(String name);

  Counters getAll();

  void delete(String name);

  void increment(String name);
}
