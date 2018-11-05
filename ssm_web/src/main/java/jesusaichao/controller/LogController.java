package jesusaichao.controller;

import jesusaichao.domain.Logs;
import jesusaichao.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author: Jesusaichao
 * @Created With By IDEA
 * @Description:
 * @Date: 18.10.24
 * @Time: 17:57
 * @Project_name: ssm01
 */
@Aspect
@Repository
public class LogController {
    @Autowired
    private LogService logService;
    private Logs logs;
    @Autowired
    private HttpServletRequest request;

    /**
     * 切入点定义拦截的规则
     */
    @Pointcut("execution(* jesusaichao.controller.*.*(..))")
    public void pointCut() {
    }

    /**
     * 前置通知
     * @param joinPoint
     */
    @Before("pointCut()")
    public void executeBefore(JoinPoint joinPoint) {
        logs = new Logs();
        //当前的访问用户名
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        logs.setUsername(user.getUsername());
        //获取正在访问的类名
        Class targetClass = joinPoint.getTarget().getClass();
        String className = targetClass.getSimpleName();
        //获取方法名称
        String methodName = joinPoint.getSignature().getName();
        logs.setMethod(className + "=" + methodName);
        //ip地址
        logs.setIp(request.getRemoteAddr());
        logs.setVisitTime(new Date());
    }
    @AfterReturning("pointCut()")
    public void executeAfter(){
        //获取访问时间间隔
        logs.setExecuteTime(System.currentTimeMillis() - logs.getVisitTime().getTime());
        //访问结果
        logs.setExecuteResult("success");
        //返回结果
        logs.setExecuteMsg("success");
        logService.saveLog(logs);
    }

    @AfterThrowing(pointcut = "pointCut()",throwing = "e")
    public void executeException(Exception e){
        //设置访问间隔时间
        logs.setExecuteTime(System.currentTimeMillis() - logs.getVisitTime().getTime());
        //异常通知
        logs.setExecuteResult("exception");
        //访问结果
        logs.setExecuteMsg(e.getMessage());
        //保存日志
        logService.saveLog(logs);
    }
}
