package io.github.fbiville.configuration.http;

import java.util.Objects;

public class ApiCredentials {

    private final String applicationId;
    private final String applicationKey;

    public ApiCredentials(String applicationId, String applicationKey) {
        this.applicationId = applicationId;
        this.applicationKey = applicationKey;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getApplicationKey() {
        return applicationKey;
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationId, applicationKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ApiCredentials other = (ApiCredentials) obj;
        return Objects.equals(this.applicationId, other.applicationId)
                && Objects.equals(this.applicationKey, other.applicationKey);
    }
}
