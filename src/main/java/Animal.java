import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
  private String name;
  private int id;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (name == null || name.length() == 0) {
      throw(new IllegalArgumentException("No valid animal name provided."));
    }
    this.name = name;
    System.out.println("setName name: " + name + " id: " + id);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
    System.out.println("setId name: " + name + " id: " + id);
  }

  public abstract boolean equals(Object o);

  public abstract void save();

  public void updateName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE animals SET name=:name WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", this.getId())
        .addParameter("name", name)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM animals WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", this.getId())
        .executeUpdate();
    }
  }

  public List<Sighting> getSightings() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE animal_id=:id;";
        List<Sighting> sightings = con.createQuery(sql)
          .addParameter("id", this.getId())
          .executeAndFetch(Sighting.class);
      return sightings;
    }
  }
}
