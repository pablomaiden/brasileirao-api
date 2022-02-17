package br.com.phc.brasileiraoapi.config;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

import br.com.phc.brasileiraoapi.event.MyCustomApplicationEvent;
import br.com.phc.brasileiraoapi.util.DataUtil;

@Configuration
public class MyCustomApplicationListener {
	
	private static final String TIME_ZONE = "America/Sao_Paulo";
	private static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
	
	@EventListener
	@Order(1)	
	public void listener1(MyCustomApplicationEvent event) {				
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println("Listener1      : Source : "+event.getSource().getClass().getName() + ", Message : "+ event.getName());	
				
		event.setName(DataUtil.formataDateEmString(new Date(), DD_MM_YYYY_HH_MM_SS));
			
	}

	@EventListener
	@Order(2)
	public void listener2(MyCustomApplicationEvent event) {
		System.out.println("Listener2      : Source : "+event.getSource().getClass().getName() + ", Message : "+ event.getName());	
	}

	@EventListener
	@Order(3)
	public void listener3(MyCustomApplicationEvent event) {
		System.out.println("Listener3      : Source : "+event.getSource().getClass().getName() + ", Message : "+ event.getName());	
	}
	

}
