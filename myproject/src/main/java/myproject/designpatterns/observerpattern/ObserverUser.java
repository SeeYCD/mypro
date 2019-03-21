package myproject.designpatterns.observerpattern;
/**
 * 观察具体对象
 * @author crh 2019年3月20日
 *
 */
public class ObserverUser implements Observer{
	private String nickName;
	public ObserverUser(String nickName){
		this.nickName=nickName;
	}
	@Override
	public void update(String mess) {
 		System.out.println(nickName+"收到消息："+mess);
	}

}
