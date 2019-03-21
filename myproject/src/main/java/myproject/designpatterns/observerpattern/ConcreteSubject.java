package myproject.designpatterns.observerpattern;

import java.util.LinkedHashSet;
import java.util.Set;

public class ConcreteSubject implements Observable{
	private Set<Observer> set=new LinkedHashSet<>();

	@Override
	public void removeObserver(Observer o) {
		set.remove(o);
	}

	@Override
	public void registObserver(Observer o) {
 		set.add(o);
	}

	@Override
	public void pushNews(String mess) {
 		for(Observer o:set){
 			o.update(mess);
 		}
	}

}
