package com.security.dto;

import java.util.Date;

public class UserDTO {

	  private Long id;
	    
	    private String userId;
	    private String completeName;
	    private String username;
	    private String password;
	    private String email;
	    private String profileImageUrl;
	    private Date lastLoginDate;
	    private Date lastLoginDateDisplay;
	    private Date joinDate;
	    private String role;
	    private String[] authorities;
	    private boolean isActive;
	    private boolean isNotLocked;
	    private boolean isChangePassword;
	    private String languages;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompleteName() {
		return completeName;
	}
	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLanguages() {
		return languages;
	}
	public void setLanguages(String languages) {
		this.languages = languages;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public Date getLastLoginDateDisplay() {
		return lastLoginDateDisplay;
	}
	public void setLastLoginDateDisplay(Date lastLoginDateDisplay) {
		this.lastLoginDateDisplay = lastLoginDateDisplay;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String[] getAuthorities() {
		return authorities;
	}
	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public boolean isChangePassword() {
		return isChangePassword;
	}
	public void setChangePassword(boolean isChangePassword) {
		this.isChangePassword = isChangePassword;
	}
	public boolean isNotLocked() {
		return isNotLocked;
	}
	public void setNotLocked(boolean isNotLocked) {
		this.isNotLocked = isNotLocked;
	}
	
	
	
	/*public UserDTO(Long id, String completeName, String email, String languages, String userId, String username,
			String password, String profileImageUrl, Date lastLoginDate, Date lastLoginDateDisplay, Date joinDate,
			String role, String[] authorities, boolean isActive, boolean isNotLocked, boolean isChangePassword) {
		super();
		this.id = id;
		this.completeName = completeName;
		this.email = email;
		this.languages = languages;
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.profileImageUrl = profileImageUrl;
		this.lastLoginDate = lastLoginDate;
		this.lastLoginDateDisplay = lastLoginDateDisplay;
		this.joinDate = joinDate;
		this.role = role;
		this.authorities = authorities;
		this.isActive = isActive;
		this.isNotLocked = isNotLocked;
		this.isChangePassword = isChangePassword;
	}*/
	
	
	
	
	
}