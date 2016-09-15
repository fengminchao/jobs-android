package muxi.studio.jobsapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import muxi.studio.jobsapp.App;
import muxi.studio.jobsapp.R;
import muxi.studio.jobsapp.data.User;
import muxi.studio.jobsapp.db.JobsDao;
import muxi.studio.jobsapp.ui.MainActivity;

/**
 * Created by ybao on 16/6/6.
 */
public class LoginActivity extends AppCompatActivity implements LoginView {


    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private Button mBtnLogin;
    private Button mBtnRegister;
    private LoginPresenter presenter;
    private TextInputLayout inputLayoutName;
    private TextInputLayout inputLayoutPwd;
    private JobsDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnRegister = (Button)findViewById(R.id.btn_register);

        inputLayoutName = (TextInputLayout) findViewById(R.id.layout_name);
        inputLayoutPwd = (TextInputLayout)findViewById(R.id.layout_pwd);
        username = (EditText) inputLayoutName.findViewById(R.id.edit_name);
        password = (EditText) inputLayoutPwd.findViewById(R.id.edit_pwd);
        inputLayoutName.setHint("用户名");
        inputLayoutPwd.setHint("密码");
        dao = new JobsDao();
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setName(username.getText().toString());
                user.setPwd(password.getText().toString());
                dao.insertUsers(user);
                Log.d("register",user.getName());
                Log.d("register",user.getPwd());
                Toast.makeText(App.getContext(),"注册成功",Toast.LENGTH_SHORT).show();
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<User> users = new ArrayList<User>();
                users = dao.loadUsers(username.getText().toString());
                if (users.size() > 0 ) {
                    Log.d("login",users.get(0).getName());
                    Log.d("login",users.get(0).getPwd());
                }
                if (users.size() > 0){
                    inputLayoutName.setErrorEnabled(false);
                    if ((users.get(0).getPwd()).equals(password.getText().toString())){
                        inputLayoutPwd.setErrorEnabled(false);
                        Toast.makeText(App.getContext(),"验证通过",Toast.LENGTH_SHORT);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("username",username.getText().toString());
                        startActivity(intent);
                        Log.d("tag","tag");
                    }else {
                        inputLayoutPwd.setErrorEnabled(true);
                        inputLayoutPwd.setError("密码错误");
                    }
                }else {
                    inputLayoutName.setErrorEnabled(true);
                    inputLayoutName.setError("用户名不存在");
                }
            }
        });
        presenter = new LoginPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestory();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        username.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override
    public void navigateToHome() {
        Toast.makeText(this, "login success", Toast.LENGTH_LONG).show();
    }

//    @Override
//    public void onClick(View v) {
//        presenter.validateCredentials(username.getText().toString(), password.getText().toString());
//
//    }
}
