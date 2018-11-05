package jesusaichao.dao;

import jesusaichao.domain.Logs;
import org.apache.ibatis.annotations.Insert;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description:
 * @Date: 18.10.24
 * @Time: 17:53
 * @Project_name: ssm01
 */
public interface LogDao {
    /**
     * 保存日志
     * @param logs
     */
    @Insert("insert into sys_log values(common_sequence.nextval,#{visitTime},#{username},#{ip},#{method},#{executeTime},#{executeResult},#{executeMsg})")
    void saveLog(Logs logs);
}
