package io.gitlab.mihajlonesic.numistagraphql.entity;

import io.gitlab.mihajlonesic.numistagraphql.entity.domain.ApiKeyStatus;
import io.gitlab.mihajlonesic.numistagraphql.entity.domain.Role;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String authKey;

    @Enumerated(EnumType.STRING)
    private ApiKeyStatus status;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Integer quota;
    private Integer used;

    @Deprecated
    public ApiKey() {
    }

    public static ApiKey create(String username, Role role, Integer quota) {
        ApiKey apiKey = new ApiKey();
        apiKey.setUsername(username);
        apiKey.setAuthKey(UUID.randomUUID().toString());
        apiKey.setStatus(ApiKeyStatus.ACTIVE);
        apiKey.setRole(role);
        apiKey.setQuota(quota);
        apiKey.setUsed(quota != null ? 0 : null);
        return apiKey;
    }

    public ApiKey update(String username, ApiKeyStatus status, Role role, Integer quota, Boolean regenerateAuthKey) {
        if (username != null) {
            setUsername(username);
        }
        if (status != null) {
            setStatus(status);
        }
        if (quota != null) {
            setQuota(quota);
        }
        if (role != null) {
            setRole(role);

            if (role.equals(Role.ADMIN)) {
                setQuota(null);
                setUsed(null);
            }
        }
        if (regenerateAuthKey != null) {
            if (regenerateAuthKey) {
                setAuthKey(UUID.randomUUID().toString());
            }
        }
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public ApiKeyStatus getStatus() {
        return status;
    }

    public void setStatus(ApiKeyStatus status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public boolean quotaUsed() {
        if (this.quota == null) {
            return false;
        }
        return this.used.equals(this.quota);
    }

    public void use() {
        if (this.quota != null && this.used != null) {
            this.used += 1;
        }
    }

    public void resetUsage() {
        if (this.used != null) {
            this.used = 0;
        }
    }

    public String getPassword() {
        return username+":"+authKey;
    }
}
