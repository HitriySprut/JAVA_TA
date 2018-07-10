package ru.stqa.pft.mantis.model;

/**
 * Created by admin on 11.07.2018.
 */
public class Project {
  public int getId() {
    return id;
  }

  public Project withID(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Project withName(String name) {
    this.name = name;
    return this;
  }

  private int id;
  private String name;

}
