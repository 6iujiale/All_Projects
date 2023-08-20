package com.ubtrobot.cruzr.user;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ubtrobot.Robot;
import com.ubtrobot.callingsystemservices.R;
import com.ubtrobot.skill.Directive;
import com.ubtrobot.skill.SkillManager;
import com.user.userimpl.User;
import com.user.userimpl.UserListenrAbstract;
import com.user.userimpl.UserManager;
import com.user.userimpl.UserStatus;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    private UserManager userManager;
    private String mFaceId;
    private String mPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        userManager = UserManager.getInstance(this);
    }

    /**
     * Gets all local user information
     */
    public void getUsers(View view) {
        userManager.getUsers(new UserListenrAbstract(){
            @Override
            public void getUsers(List<User> users) {
                super.getUsers(users);
                Log.d("UserActivity","users:"+users);
                for (User user : users) {
                    Log.d("UserActivity","user:"+user);
                }
            }
        });
    }

    /**
     * Query user information by FaceId
     */
    public void getUserByFaceId(View view) {
        if(TextUtils.isEmpty(mFaceId)){
            Log.e("UserActivity","mFaceId is empty");
            return ;
        }
        userManager.getUserByFaceId(mFaceId,new UserListenrAbstract(){
            @Override
            public void getUserByFaceId(User user) {
                super.getUserByFaceId(user);
                Log.d("UserActivity","---user:"+user);
            }
        });
    }

    /**
     * Querying user management status
     */
    public void getStatus(View view) {
        userManager.getStatus(new UserListenrAbstract(){
            @Override
            public void getStatus(UserStatus status) {
                super.getStatus(status);
                Log.d("UserActivity","status:"+status);
            }
        });
    }

    public void register(View view){

        String name=((EditText)findViewById(R.id.name)).getText().toString();
        String path=((EditText)findViewById(R.id.path)).getText().toString();
        String title=((EditText)findViewById(R.id.title)).getText().toString();


        Log.d("MainActivity","name:"+name);
        Log.d("MainActivity","path:"+path);
        Log.d("MainActivity","title:"+title);

        User user = new User.UserBuilder(name, path)
                .gender(0)
                .title(title)
                .build();
        userManager.registerUser(user,new UserListenrAbstract(){
            @Override
            public void register(int code, String msg) {
                super.register(code, msg);
                Log.d("MainActivity","code:"+code+"====msg:"+msg);
            }
        });
    }


    /**
     * File decryption
     */
    public void decryptFile(View view) {

        if(TextUtils.isEmpty(mPath)){
            Log.e("UserActivity","mPath is empty");
            return ;
        }
        try {
            InputStream decryptInputStream = userManager.decryptInputStream(mPath);

            Bitmap bitmap = BitmapFactory.decodeStream(decryptInputStream);
            ImageView imageView = findViewById(R.id.img);
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
