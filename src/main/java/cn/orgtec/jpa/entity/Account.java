package cn.orgtec.jpa.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "account")
@ToString
/**
 *  @JsonInclude(JsonInclude.Include.NON_NULL)表示,如果值为null,则不返回
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    private String bankNumber;
    private Double balance;
    private String level;


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Account() {

    }

    public Account(Integer id, String bankNumber, String level) {
        this.id = id;
        this.bankNumber = bankNumber;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}