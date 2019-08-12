package sec04.ex02;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class LoginImpl
 *
 */
@WebListener
public class LoginImpl implements HttpSessionListener {
	String user_id;
	String user_pw;
	static int total_user=0;
	
    /**
     * Default constructor. 
     */
    public LoginImpl() {
        // TODO Auto-generated constructor stub
    }
    
    public LoginImpl(String user_id, String user_pw) {
        // TODO Auto-generated constructor stub
    	this.user_id = user_id;
    	this.user_pw = user_pw;
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { //技记 积己 矫 捞亥飘 贸府
         System.out.println("技记 积己");
         ++total_user; //技记 积己 矫 立加磊 荐甫 1 刘啊
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { //技记 家戈 矫 捞亥飘 贸府
         System.out.println("技记 家戈");
         --total_user; //技记 家戈 矫 立加磊 荐甫 1 皑家
    }
	
}
