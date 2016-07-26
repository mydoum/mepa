package fr.epita.sigl.mepa.core.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "AppUser.findById", query = "FROM AppUser o WHERE o.id=:id"),
        @NamedQuery(name = "AppUser.findAll", query = "FROM AppUser o"),
        @NamedQuery(name = "AppUser.findByFirstName", query = "FROM AppUser o WHERE o.firstName=:firstName"),
        @NamedQuery(name = "AppUser.findByLastName", query = "FROM AppUser o WHERE o.lastName=:lastName"),
        @NamedQuery(name = "AppUser.findByLogin", query = "FROM AppUser o WHERE o.login=:login")
})
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "login", nullable = false, unique=true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

//    @Temporal(TemporalType.DATE)
    @Column(name = "birthDate")
    private Date birthDate;

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
