package bitcamp.util;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionProxyBuilder {

  TransactionTemplate txTemplate;

  public TransactionProxyBuilder(PlatformTransactionManager txManager) {
    this.txTemplate = new TransactionTemplate(txManager);
  }

  public Object build(Object originalWorker) {
    // 1) 인터페이스 알아내기
    Class<?> clazz = originalWorker.getClass().getInterfaces()[0];

    // 2) 인터페이스 구현체를 만들어 리턴한다.
    return Proxy.newProxyInstance(
            this.getClass().getClassLoader(),
            new Class[]{clazz},
            new InvocationHandler() {
              @Override
              public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 프록시 객체의 메서드를 호출할 때,
                // @Transactional 이 붙은 메서드를 호출할 때는 TransactionTemplate 으로 처리하고
                // @Transactional 이 안붙은 메서드는 그냥 오리지널 객체의 메서드를 그대로 호출한다.

                //System.out.println(originalWorker.getClass().getName());

                // 1) 프록시 객체의 메서드와 일치하는 오리지널 작업 객체의 메서드를 가져온다.
                Method originalMethod = getOriginalMethod(originalWorker, method);

                // 2) 오리지널 객체의 메서드에서 @Transaction 애노테이션을 추출한다.
                Transactional trasactional = originalMethod.getAnnotation(Transactional.class);

                if (trasactional != null) {
                  // @Transactional 애노테이션이 붙은 메서드라면 TransactionTemplate을 통해 실행하고
                  return txTemplate.execute(status -> {
                    System.out.printf("%s() - 트랜잭션 경유!\n", originalMethod.getName());
                    try {
                      return originalMethod.invoke(originalWorker, args);
                    } catch (Exception e) {
                      throw new RuntimeException(e);
                    }
                  });
                } else {
                  // @Transactional 애노테이션이 안 붙은 메서드라면 그냥 호출한다.
                  System.out.printf("%s() - 직접 호출!\n", originalMethod.getName());
                  return originalMethod.invoke(originalWorker, args);
                }
              }
            });
  }

  public Method getOriginalMethod(Object originalWorker, Method method) throws Exception {
    Method[] methods = originalWorker.getClass().getDeclaredMethods();
    for (Method originalMethod : methods) {
      if (originalMethod.getName().equals(method.getName()) && originalMethod.getParameterCount() == method.getParameterCount()) {
        return originalMethod;
      }
    }
    return null;
  }
}
