package io.gitlab.mihajlonesic.numistagraphql.model;


import io.gitlab.mihajlonesic.numistagraphql.entity.domain.ApiKeyStatus;
import io.gitlab.mihajlonesic.numistagraphql.entity.domain.Role;

public class UpdateApiKeyRequest {

    private Long id;
    private String username;
    private ApiKeyStatus status;
    private Role role;
    private Integer quota;
    private Boolean regenerateAuthKey;

    public UpdateApiKeyRequest() {
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

    public Boolean getRegenerateAuthKey() {
        return regenerateAuthKey;
    }

    public void setRegenerateAuthKey(Boolean regenerateAuthKey) {
        this.regenerateAuthKey = regenerateAuthKey;
    }
}
