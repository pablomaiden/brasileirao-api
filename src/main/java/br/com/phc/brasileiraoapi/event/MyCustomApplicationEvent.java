package br.com.phc.brasileiraoapi.event;

import org.springframework.context.ApplicationEvent;

public class MyCustomApplicationEvent extends ApplicationEvent{	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5880164066090581596L;
	private String name;
	
	public MyCustomApplicationEvent(Object source, String name) {
		super(source);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

}
