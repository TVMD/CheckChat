package com.app.checkchat.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.checkchat.R;
import com.app.checkchat.models.Demo;
import com.app.checkchat.models.User;
import com.app.checkchat.services.authentication.AuthenticationServiceInterface;
import com.app.checkchat.services.demo.DemoServiceInterface;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bolts.Continuation;
import bolts.Task;

import static java.security.AccessController.getContext;


@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewById(R.id.activity_main_lv_question)
    ListView lvQuestion;

    QuickAdapter<User> adapter;

    List<User> myArray;

    @Inject
    AuthenticationServiceInterface authenticationService;

    @Inject
    DemoServiceInterface demoService;

    @Override
    public void init() {
        application.getApplicationComponent().inject(this);

        adapter = new QuickAdapter<User>(this, R.layout.layout_item_lv) {
            @Override
            protected void convert(BaseAdapterHelper helper, User item) {
                helper.setText(R.id.layout_item_lv_tv_title, item.getDisplayName());
                helper.setText(R.id.layout_item_lv_tv_content, item.getEmail());
            }
        };

    }

    @Background
    protected void loading() {
        demoService.getStack("android").continueWith(new Continuation<List<User>, Object>() {
            @Override
            public Object then(Task<List<User>> task) throws Exception {
                hideHUD();
                if (task.isFaulted() || task.isCancelled()) {

                    applicationService.handleError(MainActivity.this, task.getError());
                } else {
                    myArray = task.getResult();
                    adapter.clear();
                    adapter.addAll(myArray);
                    lvQuestion.setAdapter(adapter);
                }

                return null;

            }
        }, Task.UI_THREAD_EXECUTOR);
    }

    @Click(R.id.activity_main_btn_load)
    void onClickLoad(){
        showHUD("loading");
        loading();
    }
}
