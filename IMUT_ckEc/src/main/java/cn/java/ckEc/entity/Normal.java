package cn.java.ckEc.entity;

import java.util.Date;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class Normal {
    private Integer id;
    
    @NotNull(message = "用户名格式不合法!")
	@Pattern(regexp = ".{8,16}", message = "用户名格式不合法!")
    @Pattern(regexp = "^[^ ]+$", message = "用户名不得含有空格!")
    private String username;
    
	@NotBlank(message = "不是一个合法的电子邮件地址!")
    @NotNull(message = "不是一个合法的电子邮件地址!")
    @Email
    private String email;
    
    @NotNull(message = "密码格式不合法!")
    @Pattern(regexp = "\\w{8,32}", message = "密码格式不合法!")
	@Pattern(regexp = "^[^ ]+$", message = "密码不得含有空格!")
    private String password;

    @Override
	public String toString() {
		return "Normal [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", timeCreated=" + timeCreated + ", cellphone=" + cellphone + ", sex=" + sex + ", headSculpture="
				+ headSculpture + ", permissionType1=" + permissionType1 + ", permissionType2=" + permissionType2
				+ ", permissionType3=" + permissionType3 + ", permissionType4=" + permissionType4 + ", permissionType5="
				+ permissionType5 + "]";
	}

	private Date timeCreated;
    
	@NotBlank(message = "不是一个合法的手机号!")
    @NotNull(message = "不是一个合法的手机号!")
    @Pattern(regexp = "1[356789]\\d{9}", message = "不是一个合法的手机号!")
    private String cellphone;
    
    private Boolean sex;
    
    @Pattern(regexp = "^[^ ]+$", message = "头像地址中不得含有空格!")
    private String headSculpture;

    private Boolean permissionType1;

    private Boolean permissionType2;

    private Boolean permissionType3;

    private Boolean permissionType4;

    private Boolean permissionType5;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getHeadSculpture() {
        return headSculpture;
    }

    public void setHeadSculpture(String headSculpture) {
        this.headSculpture = headSculpture;
    }

    public Boolean getPermissionType1() {
        return permissionType1;
    }

    public void setPermissionType1(Boolean permissionType1) {
        this.permissionType1 = permissionType1;
    }

    public Boolean getPermissionType2() {
        return permissionType2;
    }

    public void setPermissionType2(Boolean permissionType2) {
        this.permissionType2 = permissionType2;
    }

    public Boolean getPermissionType3() {
        return permissionType3;
    }

    public void setPermissionType3(Boolean permissionType3) {
        this.permissionType3 = permissionType3;
    }

    public Boolean getPermissionType4() {
        return permissionType4;
    }

    public void setPermissionType4(Boolean permissionType4) {
        this.permissionType4 = permissionType4;
    }

    public Boolean getPermissionType5() {
        return permissionType5;
    }

    public void setPermissionType5(Boolean permissionType5) {
        this.permissionType5 = permissionType5;
    }
}