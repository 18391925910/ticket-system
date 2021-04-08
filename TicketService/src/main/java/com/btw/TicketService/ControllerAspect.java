package com.btw.TicketService;
import com.btw.TicketService.utils.ResultJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import com.btw.TicketService.utils.TokenVerifier;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: 权限验证
 * @author: elvis.yue@i9i8.com
 * @create: 2021-04-02 09:32
 */
@Aspect
@Slf4j
@Component
public class ControllerAspect {
    @Pointcut("execution(* com.btw.TicketService.controller.*.*(..)) ")
    public void pointCut(){
    }

    @Around("pointCut()")
    public Object permissionsValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info("正在调用："+methodSignature.getName()+"()");
        HttpServletRequest request=(HttpServletRequest) joinPoint.getArgs()[0];
        String user_id=request.getHeader("user_id");
        String token=request.getHeader("token");
        if (TokenVerifier.verifyToken(user_id,token))
            return joinPoint.proceed();
        else return ResultJsonUtil.getInstance()
                .addParam(ResultJsonUtil.RESULT_STR,ResultJsonUtil.RESULT_FAIL)
                .addParam(ResultJsonUtil.INFO_STR,"没有权限")
                .getResult();
    }
}