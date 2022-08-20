package multiThread;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

/**
 * TODO
 *
 * @version 2.0
 * @author lianp
 * @date 2022/4/13 14:38
 */
@Aspect
@Component
public class AOP {

    @Pointcut("execation(*com.inspur.dsp)")
    public void requestServer(){

    }


    @Before("requestServer()")
    public void doBefore(JoinPoint joinPoint){
        //方法前获取请求头
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //servletRequestAttributes.getRequest();
    }


    @Around("requestServer()")
    public void doAround(ProceedingJoinPoint proceedingJoinPoint){
        Object[] inputName = ((MethodSignature)proceedingJoinPoint.getSignature()).getParameterNames();
        //获取参数值
        String[] inputValues = (String[]) proceedingJoinPoint.getArgs();
    }

    @After("requestServer()")
    public void doAfter(JoinPoint joinPoint){

    }
}
