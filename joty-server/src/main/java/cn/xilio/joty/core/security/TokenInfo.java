package cn.xilio.joty.core.security;


public class TokenInfo {
    /**
     * token 名称
     */
    public String tokenName;

    /**
     * token 值
     */
    public String tokenValue;

    /**
     * 此 token 是否已经登录
     */
    public Boolean isLogin;

    /**
     * 此 token 对应的 LoginId，未登录时为 null
     */
    public Object loginId;

    /**
     * token 剩余有效期（单位: 秒）
     */
    public long tokenTimeout;

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public Boolean getLogin() {
        return isLogin;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
    }

    public Object getLoginId() {
        return loginId;
    }

    public void setLoginId(Object loginId) {
        this.loginId = loginId;
    }

    public long getTokenTimeout() {
        return tokenTimeout;
    }

    public void setTokenTimeout(long tokenTimeout) {
        this.tokenTimeout = tokenTimeout;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "tokenName='" + tokenName + '\'' +
                ", tokenValue='" + tokenValue + '\'' +
                ", isLogin=" + isLogin +
                ", loginId=" + loginId +
                ", tokenTimeout=" + tokenTimeout +
                '}';
    }
}
