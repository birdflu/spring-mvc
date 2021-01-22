package ru.birdflu.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.birdflu.springcourse.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
  private static int PEOPLE_COUNT;

  private static final String URL = "jdbc:postgresql://localhost:5434/first_db";
  private static final String USERNAME = "postgres";
  private static final String PASSWORD = "postgres";

  private static Connection connection;

  static {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    try {
      connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }


/*  private List<Person> people;

  {
    people = new ArrayList<>();

    people.add(new Person(++PEOPLE_COUNT, "Tom", 24, "tom@mail.ru"));
    people.add(new Person(++PEOPLE_COUNT, "Bob", 52, "bob@mail.ru"));
    people.add(new Person(++PEOPLE_COUNT, "Mike", 18, "mike@yahoo.com"));
    people.add(new Person(++PEOPLE_COUNT, "Katy", 34, "katy@gmail.com"));

  }*/

  public List<Person> index() {
    List<Person> people = new ArrayList<>();
    try {
      Statement statement = connection.createStatement();
      String SQL = "SELECT * FROM Person";
      ResultSet resultSet = statement.executeQuery(SQL);

      while (resultSet.next()) {
        Person person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setEmail(resultSet.getString("email"));
        person.setAge(resultSet.getInt("age"));

        people.add(person);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return people;
  }

  public Person show(int id) {
    return null;
//    return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
  }

  public void save(Person person) {
    person.setId(++PEOPLE_COUNT);

    try {
      Statement statement = connection.createStatement();
      String SQL = "INSERT INTO Person VALUES (" +
              1 + ",'" +
              person.getName() + "'," +
              person.getAge() + ",'" +
              person.getEmail() +"')";

      statement.executeUpdate(SQL);

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    //    people.add(person);
  }

  public void update(int id, Person updatedPerson) {
    Person personToBeUpdated = show(id);
    personToBeUpdated.setName(updatedPerson.getName());
    personToBeUpdated.setAge(updatedPerson.getAge());
    personToBeUpdated.setEmail(updatedPerson.getEmail());
  }

  public void delete(int id) {
//    people.removeIf(person -> person.getId() == id);
  }
}
