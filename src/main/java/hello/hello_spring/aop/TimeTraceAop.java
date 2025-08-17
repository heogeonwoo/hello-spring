package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// AOP
@Aspect // 공통 관심 사항
@Component // 컨포넌트 스캔 or Been에 직접 등록
// 실행 시간 측정 Aspect, 대상 메서드가 호출될 때마다 시작 시각 로그 → 메서드 실행 → 종료 시각 로그 및 실행 시간 출력
public class TimeTraceAop {
    @Around("execution(* hello.hello_spring..*(..))") // Aspect 타겟팅
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable { // 현재 실행 중인 메서드에 대한 정보 제공
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println(("END: " + joinPoint.toString() + " " + timeMs + "ms"));
        }
    }
}
