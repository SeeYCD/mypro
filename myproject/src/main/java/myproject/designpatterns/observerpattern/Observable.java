package myproject.designpatterns.observerpattern;
/**
 * 被观察者
 * @author crh 2019年3月20日
 *
 */
public interface Observable {
 	public void removeObserver(Observer o);
 	public void registObserver(Observer o);
 	public void pushNews(String mess);
  }
