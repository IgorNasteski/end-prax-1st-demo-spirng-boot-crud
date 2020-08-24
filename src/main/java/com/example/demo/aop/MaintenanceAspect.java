package com.example.demo.aop;

import com.example.demo.dao.MaintenanceRepository;
import com.example.demo.entity.Maintenance;
import com.example.demo.service.Method4ControllerMaintenance;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;

import java.util.List;

@EnableAspectJAutoProxy
@Aspect
@Component
public class MaintenanceAspect {

        @Autowired
        private Method4ControllerMaintenance method4ControllerMaintenance;

        @Autowired
        private MaintenanceRepository maintenanceRepository;

        @Pointcut("execution(* com.example.demo.controller.DemoController.showHome(..))")
        private void forDaoPackage() {}

        //ovaj @Before dodat samo radi testa
        @Before("forDaoPackage()")	//sada ovde samo napisemo naziv metode i to ce da ustvari ispise "execution(* springdemo.aop.dao.*.*(..))"
        public void beforeAddAccountAdvice() {			//nebitan naziv ove metode, ovaj blok koda se izvrsava pre metode iznad
                System.out.println("\n======>>> Executing @Before advice on showHome()");
        }


        //presrecemo metodu kontrolera "showHome()" kako bi proverili da li je vreme odrzavanja(ako jeste salji na tu stranicu), ako nije
        @Around("forDaoPackage()")                      //nastavi sa telom metode koju presrecemo u kontroleru (a tamo je po default-u namesteno da vraca "home"
        public Object aroundShowHome(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

                // print out method we are advising on
                String method = theProceedingJoinPoint.getSignature().toShortString();
                System.out.println("\n=====>>> Executing @Around on method: " + method);

                // begin
                List<Maintenance> listOfDaysForMaintenance = maintenanceRepository.findAll();

                //Object result;                        //proveri da li je maintenance ili nije
                if(method4ControllerMaintenance.maintenanceOrNotBoolean(listOfDaysForMaintenance)){
                        //return new ModelAndView("redirect:/maintenanceInfoPage");
                        return "maintenanceInfoPage";   //ako je true, onda salji na stranicu za odrzavanje
                }

                return theProceedingJoinPoint.proceed();        //ako nije true nastavi sa metodom kontrolera
                        //koju presecamo, a ona ce svakako vratiti home page, tj prvu stranicu
        }




}



//@Before("execution(public void springdemo.aop.dao.AccountDAO.addAccount())")	//sada smo naveli celu putanju klase iz koje zelimo da koristimo ovu metodu
//@Before("execution(public void add*())")	//odradice za sve void metode koje pocinju sa add