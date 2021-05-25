package spring.finalProject.entities.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import spring.finalProject.entities.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "usr")
public class Users extends BaseEntity implements UserDetails {
    @Column
    private String username;

    @JsonIgnore
    @Column
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "programmer_id", referencedColumnName = "id", nullable = true)
    private ProgrammerEntity programmer;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public ProgrammerEntity getProgrammer() {
        return programmer;
    }

    public void setProgrammer(ProgrammerEntity programmer) {
        this.programmer = programmer;
    }

    public void addRole(Role role){
        if (roles == null) roles = new ArrayList<>();
        if (!roles.contains(role)) roles.add(role);
    }
}
