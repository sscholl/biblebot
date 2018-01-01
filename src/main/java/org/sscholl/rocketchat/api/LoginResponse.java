package org.sscholl.rocketchat.api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginResponse {
    private String status;
    private LoginResponseData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LoginResponseData getData() {
        return data;
    }

    public void setData(LoginResponseData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    public class LoginResponseData {

        private String authToken;
        private String userId;

        public String getAuthToken() {
            return authToken;
        }

        public void setAuthToken(String authToken) {
            this.authToken = authToken;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "LoginResponseData{" +
                    "authToken='" + authToken + '\'' +
                    ", userId='" + userId + '\'' +
                    '}';
        }
    }
}
