package interceptor;

import java.io.Serializable;

import com.opensymphony.xwork2.ActionInvocation;

public interface Interceptor  extends Serializable{
	 void init();
	 void destroy();
	 String intercept(ActionInvocation invocation) throws Exception;
}
