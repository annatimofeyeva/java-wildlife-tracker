import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class NonEndangeredAnimal extends Animal {
  public static final String type = "nonendangered";

  public NonEndangeredAnimal(String name) {
    this.setName(name);
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (name, type) VALUES (:name, :type);";
      int id = (int) con.createQuery(sql, true)
        .addParameter("name", this.getName())
        .addParameter("type", NonEndangeredAnimal.type)
        .executeUpdate()
        .getKey();
      setId(id);
    }
  }

  @Override
  public boolean equals(Object otherAnimal) {
    if(!(otherAnimal instanceof NonEndangeredAnimal)) {
      return false;
    } else {
      NonEndangeredAnimal newAnimal = (NonEndangeredAnimal) otherAnimal;
      return this.getName().equals(newAnimal.getName());
    }
  }

  public static List<NonEndangeredAnimal> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id, name FROM animals WHERE type=:type;";
      return con.createQuery(sql)
        .addParameter("type", NonEndangeredAnimal.type)
        .executeAndFetch(NonEndangeredAnimal.class);
    }
  }

  public static NonEndangeredAnimal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id, name FROM animals WHERE id=:id AND type=:type;";
      NonEndangeredAnimal animal = con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("type", NonEndangeredAnimal.type)
        .executeAndFetchFirst(NonEndangeredAnimal.class);
      return animal;
    }
  }
}
