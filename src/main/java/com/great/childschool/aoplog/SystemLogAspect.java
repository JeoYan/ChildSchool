package com.great.childschool.aoplog;
import com.great.childschool.entity.TjzTbLog;
import com.great.childschool.service.TjzBackService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author zx
 * @desc 切点类
 */
@Aspect
@Component
public class SystemLogAspect
{
	@Resource
	private TjzBackService tjzBackService;
	@Resource
	private HttpServletRequest req;
	@Pointcut("execution (* com.great.childschool.controller..*.*(..))")
	public void controllerAspect()
	{
	}
	@After("controllerAspect()")
	public void after(JoinPoint joinPoint) throws Throwable
	{
		System.out.println("进入after方法");
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] params = joinPoint.getArgs();
		try
		{
			Class<?> clazz = Class.forName(className);
			Method[] methods = clazz.getMethods();
			String operationType = "";
			String operationName = "";
			Class<?>[] paramsType = null;
			boolean isSameMethod = true;
			for (Method method : methods)
			{
				if (method.getName().equals(methodName) && method.getParameterTypes().length == params.length)
				{
					paramsType = method.getParameterTypes();
					for (int i = 0; i < paramsType.length; i++)
					{
						if (params[i] instanceof MultipartFile && paramsType[i] == MultipartFile.class)
						{
							continue;
						}
						if (params[i] instanceof HttpServletRequest && paramsType[i] == HttpServletRequest.class)
						{
							continue;
						}
						if (params[i] instanceof HttpServletResponse && paramsType[i] == HttpServletResponse.class)
						{
							continue;
						}
						if (params[i] != null)
						{
							if (paramsType[i] == params[i].getClass())
							{
								continue;
							}
						} else
						{
							continue;
						}
						isSameMethod = false;
						break;
					}
					if (isSameMethod)
					{
						if(method.isAnnotationPresent(Log.class)){
							operationName = method.getAnnotation(Log.class).operationName();
							TjzTbLog log=new TjzTbLog();
							log.setlEvent(operationName);
							log.setWid(Integer.valueOf(req.getSession().getAttribute("wid").toString()));
							Date day=new Date();
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
							log.setlDate(dateFormat.format(day));
							log.setlTime(timeFormat.format(day));
							int flag = tjzBackService.addLog(log);
							if (flag > 0)
							{
								System.out.println("日志记录成功");
							} else
							{
								System.out.println("日志记录失败");
							}
						}
					}
				}
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}