package com.ies.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="IES_USERS")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String fullName;
    private String email;
    private String pwd;
    private Long mobileNo;
    private String gender;
    private LocalDate dob;
    private Long ssn;
    private String accStatus;
    private String activeSw;

    private Integer roleId;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<PlansEntity> plans;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Long getSsn() {
        return ssn;
    }

    public void setSsn(Long ssn) {
        this.ssn = ssn;
    }

    public String getAccStatus() {
        return accStatus;
    }

    public void setAccStatus(String accStatus) {
        this.accStatus = accStatus;
    }

    public String getActiveSw() {
        return activeSw;
    }

    public void setActiveSw(String activeSw) {
        this.activeSw = activeSw;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<PlansEntity> getPlans() {
        return plans;
    }

    public void setPlans(List<PlansEntity> plans) {
        this.plans = plans;
    }
}
