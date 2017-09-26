package org.test.web.domain;

import com.google.common.collect.ImmutableList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "counters")
public class Counters {

  private final List<Counter> counters;

  public Counters(List<Counter> counters) {
    this.counters = ImmutableList.copyOf(counters);
  }

  @XmlElement(name = "counter")
  public List<Counter> getCounters() {
    return counters;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Counters{");
    sb.append("counters=").append(counters);
    sb.append('}');
    return sb.toString();
  }
}
