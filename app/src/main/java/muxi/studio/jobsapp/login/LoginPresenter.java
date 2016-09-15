package muxi.studio.jobsapp.login;

/**
 * Created by ybao on 16/6/6.
 */
public interface LoginPresenter {

    void validateCredentials(String name,String pwd);

    void onDestory();
}
