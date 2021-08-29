package web23.web18.controller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import web23.web18.model.MapperAuth;
import web23.web18.model.User;
import web23.web18.model.UserRole;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AspectAuth {

    private final MapperAuth mapperAuth;

    public static void log(String format, Object... args) {
        System.out.println(String.format(format, args));
    }
    ControllerHelper controllerHelper;
    AspectAuth(MapperAuth mapperAuth) {
        this.mapperAuth = mapperAuth;
    }

    public ModelAndView loginRequired(ProceedingJoinPoint joinPoint) {
        System.out.println("最简单的单方法匹配");
        // return new ModelAndView("redirect:/index");
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(
                RequestAttributes.REFERENCE_REQUEST
        );
        User currentUser = controllerHelper.currentUser(request,this.mapperAuth);
        if (currentUser.role != UserRole.guest) {
            try {
                return (ModelAndView) joinPoint.proceed();
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        } else {
            return new ModelAndView("redirect:/login/view");
        }

    }
    // @Around("execution(* web23.web18.controller.ControllerWeibo.*(..))")
    // public ModelAndView weiboRequired(ProceedingJoinPoint joinPoint) {
    //     return loginRequired(joinPoint);
    // }
    //
    // @Around("execution(* web23.web18.controller.ControllerTodo.*(..))")
    // public ModelAndView TodoRequired(ProceedingJoinPoint joinPoint) {
    //     return loginRequired(joinPoint);
    // }
}
