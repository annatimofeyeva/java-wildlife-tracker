import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
  private String name;
  private int id;

  public Animal(String name) {
    this.name = name;
  }

  public Animal(String name, int id){
    this(name);
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id){
    this.id = id;
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
