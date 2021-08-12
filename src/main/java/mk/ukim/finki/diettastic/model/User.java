package mk.ukim.finki.diettastic.model;

import lombok.Data;

import mk.ukim.finki.diettastic.model.enums.DietType;
import mk.ukim.finki.diettastic.model.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String birthdayString;
    private String avatarURL;
    // TODO: Age is automatically calculated
    private Integer age;
    private Float height;
    private Float weight;
    private boolean isNutritionist;

    @Enumerated(value = EnumType.STRING)
    private DietType dietType;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany
    private List<Goal> personalGoals;

    @OneToMany
    private List<Post> userPosts;

    @ManyToOne
    private User nutritionist;

    @ManyToMany
    private List<User> friendsList;

    public User() {
    }

    public User(String username, String password, String email, String name, String surname, String birthdayString,
                String avatarURL) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.birthdayString = birthdayString;
        this.avatarURL = avatarURL;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }


    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired =  true;
    private boolean isEnabled = true;

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
