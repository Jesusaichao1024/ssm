package jesusaichao.domain;

import java.util.Date;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description:
 * @Date: 18.10.24
 * @Time: 10:00
 * @Project_name: ssm01
 */
public class Logs {
    private Integer id;
    private Date visitTime;
    private String username;
    private String ip;
    private String method;
    private Long executeTime;

    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }

    private String executeResult;
    private String executeMsg;

    @Override
    public String toString() {
        return "Logs{" +
                "id=" + id +
                ", visitTime=" + visitTime +
                ", username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                ", method='" + method + '\'' +
                ", executeTime=" + executeTime +
                ", executeResult='" + executeResult + '\'' +
                ", executeMsg='" + executeMsg + '\'' +
                '}';
    }

    public Long getExecuteTime() {
        return executeTime;
    }

    public String getExecuteResult() {
        return executeResult;
    }

    public void setExecuteResult(String executeResult) {
        this.executeResult = executeResult;
    }

    public String getExecuteMsg() {
        return executeMsg;
    }

    public void setExecuteMsg(String executeMsg) {
        this.executeMsg = executeMsg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
