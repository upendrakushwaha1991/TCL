package com.cpm.Tcl.password;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.cpm.Tcl.AutoLoginActivity;
import com.cpm.Tcl.LoginActivity;
import com.cpm.Tcl.R;
import com.cpm.Tcl.blurlockview.BlurLockView;
import com.cpm.Tcl.blurlockview.Directions.HideType;
import com.cpm.Tcl.blurlockview.Directions.ShowType;
import com.cpm.Tcl.blurlockview.Eases.EaseType;
import com.cpm.Tcl.blurlockview.Password;
import com.cpm.Tcl.oneQad.OneQADActivity;
import com.cpm.Tcl.utilities.CommonString;

public class MPinActivity extends AppCompatActivity implements
        View.OnClickListener,
        BlurLockView.OnPasswordInputListener,
        BlurLockView.OnLeftButtonClickListener {

    BlurLockView blurLockView;
    ImageView imageView1;
    String pin = "";
    boolean IS_PASSWORD_CHECK;

    private SharedPreferences preferences = null;
    private SharedPreferences.Editor editor = null;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mpin);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        IS_PASSWORD_CHECK = getIntent().getBooleanExtra(CommonString.IS_PASSWORD_CHECK, false);

        imageView1 = (ImageView) findViewById(R.id.image_1);

        blurLockView = (BlurLockView) findViewById(R.id.blurlockview);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        // Set the view that need to be blurred
        blurLockView.setBlurredView(imageView1);

        // Set the password
        if (IS_PASSWORD_CHECK) {
            //preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String mpin = preferences.getString(CommonString.MPIN, null);
            blurLockView.setCorrectPassword(mpin);
            blurLockView.setLeftButton("Forgot MPin");
            blurLockView.setTitle("Please Enter Four Digit MPin");
        } else {
            blurLockView.setCorrectPassword("abcd");
            blurLockView.setLeftButton("Set MPin");
            blurLockView.setTitle("Please Set Four Digit MPin");
        }

        blurLockView.setIs_Password_Check_Mode(IS_PASSWORD_CHECK);



        blurLockView.setRightButton("Clear");
        blurLockView.setTypeface(getTypeface());
        blurLockView.setOnLeftButtonClickListener(this);
        blurLockView.setOnPasswordInputListener(this);

        //blurLockView.setType(getPasswordType(), false);
        //blurLockView.setPasswordLength(4);

       /* blurLockView.show(
                getIntent().getIntExtra("SHOW_DURATION", 1000),
                getShowType(getIntent().getIntExtra("SHOW_DIRECTION", 0)),
                getEaseType(getIntent().getIntExtra("SHOW_EASE_TYPE", 30)));*/
        blurLockView.setType(Password.NUMBER, true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private Password getPasswordType() {
        if ("PASSWORD_NUMBER".equals(getIntent().getStringExtra("PASSWORD_TYPE")))
            return Password.NUMBER;
        else if ("PASSWORD_NUMBER".equals(getIntent().getStringExtra("PASSWORD_TYPE")))
            return Password.TEXT;
        return Password.NUMBER;
    }

    private Typeface getTypeface() {
        if ("SAN".equals(getIntent().getStringExtra("TYPEFACE")))
            return Typeface.createFromAsset(getAssets(), "fonts/San Francisco Regular.ttf");
        else if ("DEFAULT".equals(getIntent().getStringExtra("TYPEFACE")))
            return Typeface.DEFAULT;
        return Typeface.DEFAULT;
    }

    @Override
    public void correct(String inputPassword) {

        if (IS_PASSWORD_CHECK) {
            Intent in = new Intent(getApplicationContext(), AutoLoginActivity.class);
            //Intent in = new Intent(getApplicationContext(), VideoActivity.class);
            startActivity(in);
            finish();
        } else {
            Toast.makeText(this,
                    "Correct",
                    Toast.LENGTH_SHORT).show();
        }


       /* blurLockView.hide(
                getIntent().getIntExtra("HIDE_DURATION", 1000),
                getHideType(getIntent().getIntExtra("HIDE_DIRECTION", 0)),
                getEaseType(getIntent().getIntExtra("HIDE_EASE_TYPE", 30)));*/
    }

    @Override
    public void incorrect(String inputPassword) {

        if (IS_PASSWORD_CHECK) {

            int incorrect_times = blurLockView.getIncorrectInputTimes();
            if (++incorrect_times >= 3) {
                /*Toast.makeText(this,
                        R.string.error_incorrect_password,
                        Toast.LENGTH_SHORT).show();*/

                Snackbar snackbar = Snackbar.make(blurLockView, "Incorrect MPin limit reached", Snackbar.LENGTH_INDEFINITE);
                View view = snackbar.getView();
                /*TextView tv = (TextView)view.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.RED);*/
                snackbar.setAction("Reset", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        editor.putString(CommonString.MPIN, null);

                        editor.commit();

                        finish();
                        Intent in = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(in);

                    }
                });
                snackbar.setActionTextColor(Color.GREEN);
                snackbar.show();
            } else {

                int count = 3 - incorrect_times;
                String attemt_str;
                if (count == 1) {
                    attemt_str = ". Attempt left - " + count;
                } else {
                    attemt_str = ". Attempts left - " + count;
                }

                String msg = getString(R.string.error_incorrect_pin) + attemt_str;
                //blurLockView.setIncorrectInputTimes(incorrect_times);
                Toast.makeText(this,
                        msg,
                        Toast.LENGTH_SHORT).show();
            }

        } else {
            pin = inputPassword;
        }

    }

    @Override
    public void input(String inputPassword) {
        pin = inputPassword;
    }

    @Override
    public void clear(String remainingPassword) {
        pin = remainingPassword;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_1:
               /* blurLockView.show(
                        getIntent().getIntExtra("SHOW_DURATION", 1000),
                        getShowType(getIntent().getIntExtra("SHOW_DIRECTION", 0)),
                        getEaseType(getIntent().getIntExtra("SHOW_EASE_TYPE", 30)));
                blurLockView.setType(Password.TEXT, true);*/
                break;
        }
    }

    @Override
    public void onClick() {

        if (IS_PASSWORD_CHECK) {

            Snackbar snackbar = Snackbar.make(blurLockView, "Need to reset MPin", Snackbar.LENGTH_INDEFINITE);
            View view = snackbar.getView();
                /*TextView tv = (TextView)view.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.RED);*/
            snackbar.setAction("Reset", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    editor.putString(CommonString.MPIN, null);

                    editor.commit();

                    finish();
                    Intent in = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(in);

                }
            });
            snackbar.setActionTextColor(Color.GREEN);
            snackbar.show();
        } else {
            if (pin.length() == 4) {

                editor.putString(CommonString.MPIN, pin);

                editor.commit();

                //Intent in = new Intent(getApplicationContext(), MainActivity.class);
                Intent in = new Intent(getApplicationContext(), OneQADActivity.class);

                startActivity(in);

                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Please fill four digit MPin", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private int downsamepleFactor;

    private ShowType getShowType(int p) {
        ShowType showType = ShowType.FROM_TOP_TO_BOTTOM;
        switch (p) {
            case 0:
                showType = ShowType.FROM_TOP_TO_BOTTOM;
                break;
            case 1:
                showType = ShowType.FROM_RIGHT_TO_LEFT;
                break;
            case 2:
                showType = ShowType.FROM_BOTTOM_TO_TOP;
                break;
            case 3:
                showType = ShowType.FROM_LEFT_TO_RIGHT;
                break;
            case 4:
                showType = ShowType.FADE_IN;
                break;
        }
        return showType;
    }

    private HideType getHideType(int p) {
        HideType hideType = HideType.FROM_TOP_TO_BOTTOM;
        switch (p) {
            case 0:
                hideType = HideType.FROM_TOP_TO_BOTTOM;
                break;
            case 1:
                hideType = HideType.FROM_RIGHT_TO_LEFT;
                break;
            case 2:
                hideType = HideType.FROM_BOTTOM_TO_TOP;
                break;
            case 3:
                hideType = HideType.FROM_LEFT_TO_RIGHT;
                break;
            case 4:
                hideType = HideType.FADE_OUT;
                break;
        }
        return hideType;
    }

    private EaseType getEaseType(int p) {
        EaseType easeType = EaseType.Linear;
        switch (p) {
            case 0:
                easeType = EaseType.EaseInSine;
                break;
            case 1:
                easeType = EaseType.EaseOutSine;
                break;
            case 2:
                easeType = EaseType.EaseInOutSine;
                break;
            case 3:
                easeType = EaseType.EaseInQuad;
                break;
            case 4:
                easeType = EaseType.EaseOutQuad;
                break;
            case 5:
                easeType = EaseType.EaseInOutQuad;
                break;
            case 6:
                easeType = EaseType.EaseInCubic;
                break;
            case 7:
                easeType = EaseType.EaseOutCubic;
                break;
            case 8:
                easeType = EaseType.EaseInOutCubic;
                break;
            case 9:
                easeType = EaseType.EaseInQuart;
                break;
            case 10:
                easeType = EaseType.EaseOutQuart;
                break;
            case 11:
                easeType = EaseType.EaseInOutQuart;
                break;
            case 12:
                easeType = EaseType.EaseInQuint;
                break;
            case 13:
                easeType = EaseType.EaseOutQuint;
                break;
            case 14:
                easeType = EaseType.EaseInOutQuint;
                break;
            case 15:
                easeType = EaseType.EaseInExpo;
                break;
            case 16:
                easeType = EaseType.EaseOutExpo;
                break;
            case 17:
                easeType = EaseType.EaseInOutExpo;
                break;
            case 18:
                easeType = EaseType.EaseInCirc;
                break;
            case 19:
                easeType = EaseType.EaseOutCirc;
                break;
            case 20:
                easeType = EaseType.EaseInOutCirc;
                break;
            case 21:
                easeType = EaseType.EaseInBack;
                break;
            case 22:
                easeType = EaseType.EaseOutBack;
                break;
            case 23:
                easeType = EaseType.EaseInOutBack;
                break;
            case 24:
                easeType = EaseType.EaseInElastic;
                break;
            case 25:
                easeType = EaseType.EaseOutElastic;
                break;
            case 26:
                easeType = EaseType.EaseInOutElastic;
                break;
            case 27:
                easeType = EaseType.EaseInBounce;
                break;
            case 28:
                easeType = EaseType.EaseOutBounce;
                break;
            case 29:
                easeType = EaseType.EaseInOutBounce;
                break;
            case 30:
                easeType = EaseType.Linear;
                break;
        }
        return easeType;
    }

    /*public void showComment(){

        dialog = new Dialog(MPinActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.comment_layout);
        TextView tv = (TextView) dialog.findViewById(R.id.tv_comment);

        tv.setText("");

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.TOP;
        //wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        dialog.show();

    }*/

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */

}
