package jesusaichao.service;

import jesusaichao.domain.Logs;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description:
 * @Date: 18.10.24
 * @Time: 17:55
 * @Project_name: ssm01
 */
public interface LogService {
    /**
     * 保存日志
     * @param logs
     */
    void saveLog(Logs logs);
}
