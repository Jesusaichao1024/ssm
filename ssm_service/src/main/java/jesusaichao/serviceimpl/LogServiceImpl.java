package jesusaichao.serviceimpl;

import jesusaichao.dao.LogDao;
import jesusaichao.domain.Logs;
import jesusaichao.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description:
 * @Date: 18.10.24
 * @Time: 17:56
 * @Project_name: ssm01
 */
@Service
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED,readOnly = false)
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;
    @Override
    public void saveLog(Logs logs) {
        logDao.saveLog(logs);
    }
}
