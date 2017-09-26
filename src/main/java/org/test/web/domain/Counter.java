package org.test.web.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Objects;

@XmlRootElement(name = "counter")
@Entity
@Table(name = "counter")
public class Counter {
  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "id")
  private Long id;

  @Column(unique = true)
  private String name;

  private int value;

  @Version
  @XmlTransient
  private Long version;

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getValue() {
    return value;
  }

  public Counter setId(Long id) {
    this.id = id;
    return this;
  }

  public Counter setName(String name) {
    this.name = name;
    return this;
  }

  public Counter setValue(int value) {
    this.value = value;
    return this;
  }

  public Counter increment() {
    this.value++;
    return this;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Counter{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append(", value=").append(value);
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Counter counter = (Counter) o;
    return Objects.equals(name, counter.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
