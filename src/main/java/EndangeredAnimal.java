import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class EndangeredAnimal extends Animal {
  private String health;
  private String age;
  public static final String type = "endangered";

  public EndangeredAnimal(String name, String health, String age) {
    // Name is set in the Constructor of super class
    super(name);
    this.health = health;
    this.age = age;
  }

  public EndangeredAnimal(String name, int id, String health, String age) {
    // Name is set in the Constructor of super class
    super(name, id);
    this.health = health;
    this.age = age;
  }

  public String getHealth() {
    return health;
  }

  public String getAge() {
    return age;
  }

  @Override
  public boolean equals(Object otherEndangeredAnimal) {
    if(!(otherEndangeredAnimal instanceof EndangeredAnimal)) {
      return false;
    } else {
      EndangeredAnimal newEndangeredAnimal = (EndangeredAnimal) otherEndangeredAnimal;
      return this.getName().equals(newEndangeredAnimal.getName()) && this.getHealth().equals(newEndangeredAnimal.getHealth()) && this.getAge().equals(newEndangeredAnimal.getAge());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (name, health, age, type) VALUES (:name, :health, :age, :type);";
      int id = (int) con.createQuery(sql, true)
        .addParameter("name", this.getName())
        .addParameter("health", this.getHealth())
        .addParameter("age", this.getAge())
        .addParameter("type", EndangeredAnimal.type)
        .executeUpdate()
        .getKey();
      setId(id);
    }
  }

  public static List<EndangeredAnimal> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id, name, health, age FROM animals WHERE type=:type;";
      return con.createQuery(sql)
        .addParameter("type", EndangeredAnimal.type)
        .executeAndFetch(EndangeredAnimal.class);
    }
  }

  public static EndangeredAnimal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id, name, health, age FROM animals WHERE id=:id AND type=:type;";
      EndangeredAnimal animal = con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("type", EndangeredAnimal.type)
        .executeAndFetchFirst(EndangeredAnimal.class);
      return animal;
    }
  }

  public void updateHealth(String health) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE animals SET health=:health WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", this.getId())
        .addParameter("health", health)
        .executeUpdate();
    }
  }

  public void updateAge(String age) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE animals SET age=:age WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("age", age)
        .addParameter("id", this.getId())
        .executeUpdate();
    }
  }

  public String toString() {
    return "id: " + this.getId() + " name: " + this.getName() + " health: " + this.getHealth() + " age: " + this.getAge();
  }
}
