package web.model;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "([A-Za-z]+)")
    @Size(min = 2, max = 100, message
            = "Size from 2 to 100")
    private String name;

    @Column(name = "lastName")
    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "([A-Za-z]+)")
    @Size(min = 2, max = 100, message
            = "Size from 2 to 100")
    private String surname;


    public User() {}

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + name + '\'' +
                ", lastName='" + surname + '\'' +
                '}';
    }
}
