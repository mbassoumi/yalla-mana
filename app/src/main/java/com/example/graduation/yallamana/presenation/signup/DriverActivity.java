package com.example.graduation.yallamana.presenation.signup;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduation.yallamana.R;
import com.example.graduation.yallamana.util.network.api.Attributes;
import com.example.graduation.yallamana.util.network.api.Car;
import com.example.graduation.yallamana.util.network.api.Example;
import com.example.graduation.yallamana.util.network.api.NewUser;
import com.example.graduation.yallamana.util.network.retrofit.ApiClient;
import com.example.graduation.yallamana.util.network.retrofit.RetrofitInterface;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DriverActivity extends AppCompatActivity {

    Toolbar mToolBar;
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
    private PreferenceManager prefManager;
    private Spinner bank, year, seats, carModel;
    private EditText carModeel;
    private Button yalla_Button;
    private Car car;
    private Attributes attributess;
    private CheckBox androidUser, age;
    private String check1, check2;
    private String bankAccount, carYear, seatsNumber, carType;
    private ImageView carImage, carLic, driverLic, driverId;

    private NewUser driverUser;
    RetrofitInterface retrofitInterface;
    View view1, view2;
    String carModell;
    private String imagePath;
    private Bitmap bitmap;
    private  String type;
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        retrofitInterface = ApiClient.getClient().create(RetrofitInterface.class);

        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mToolBar.setTitle(R.string.driver_toolbar);
        mToolBar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(mToolBar);
        Intent i = getIntent();
        driverUser = (NewUser) i.getSerializableExtra("driverUser");

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DriverActivity.this, SignupActivity.class);
                intent.putExtra("userPhone", "");
                startActivity(intent);
                finish();
            }
        });
//        addItemsOnSpinner2();
//              addListenerOnButton();
//               addListenerOnSpinnerItemSelection();
        // Checking for first time launch - before calling setContentView()
        prefManager = new PreferenceManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }


        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);


        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.fragment_driver,
                R.layout.fragment_driver2,
        };

        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

//        btnSkip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               // launchHomeScreen();
//                Toast.makeText(AllTripsActivity.this, "This is my Toast message!",
//                        Toast.LENGTH_LONG).show();
//            }
//        });


    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        // startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
        //finish();
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            layout1();
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (position == 0) {
//                addItemsOnSpinner2();

//                addListenerOnButton();
//                addListenerOnSpinnerItemSelection();

                view1 = layoutInflater.inflate(R.layout.fragment_driver, container, false);

                container.addView(view1);

                return view1;
            } else {
                view2 = layoutInflater.inflate(R.layout.fragment_driver2, container, false);


                container.addView(view2);

                return view2;
            }
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }


    private void layout1() {


        bank = (Spinner) findViewById(R.id.spinnerBank);
        androidUser = (CheckBox) findViewById(R.id.checkBox);
        age = (CheckBox) findViewById(R.id.checkBox2);
        driverId=(ImageView)findViewById(R.id.idImage);
        driverLic=(ImageView)findViewById(R.id.licenseImage);
        carImage=(ImageView)findViewById(R.id.CarImage);
        carLic=(ImageView)findViewById(R.id.licenseImagecar);
        driverId=(ImageView)findViewById(R.id.idImage);
        bankAccount = bank.getSelectedItem().toString();

        if (androidUser.isChecked()) {
            check1 = "true";
        } else {
            check1 = "false";
        }
        if (age.isChecked()) {
            check2 = "true";
        } else {
            check2 = "false";
        }
        year = (Spinner) findViewById(R.id.spinner_year);
        seats = (Spinner) findViewById(R.id.seats);
        carModel = (Spinner) findViewById(R.id.car_model);
        carModeel = (EditText) findViewById(R.id.my_car_model);

        yalla_Button = (Button) findViewById(R.id.yalla_Button);

        carLic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="carl";
                getImage();
            }
        });
        carImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="carIM";

                getImage();
            }
        });

        driverLic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="drivl";

                getImage();
            }
        });
        driverId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type="drivID";

                getImage();
            }
        });
        yalla_Button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                NetworkInfo wifiCheck;

                carType = carModel.getSelectedItem().toString();
                carYear = year.getSelectedItem().toString();
                seatsNumber = seats.getSelectedItem().toString();
                int seatsNum = Integer.parseInt(seatsNumber);
                String modeeel = carModeel.getText().toString();
                if (modeeel.equals(null)){
                   modeeel="no model";
                }
                car = new Car("photo url", carYear, modeeel, seatsNum, attributess);
                ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                wifiCheck = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


                // Do whatever here
                NewUser driverUser1 = new NewUser(driverUser.getName(), driverUser.getPhone(), driverUser.getEmail(), driverUser.getGender(),
                        driverUser.getDriverLicence(), driverUser.getType(), car);
                skip(driverUser1);


            }

        });
        //  addListenerOnSpinnerItemSelection();
        attributess = new Attributes(carType, bankAccount, check2, check1);

        ImageView LicenseImage = (ImageView) findViewById(R.id.licenseImage);

        LicenseImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(DriverActivity.this,
                        "OnClickListener : ",

                        Toast.LENGTH_SHORT).show();
            }

        });
        ImageView idImage = (ImageView) findViewById(R.id.idImage);

        idImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(DriverActivity.this,
                        "OnClickListener : ",

                        Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void getImage() {

        // TODO Auto-generated method stub
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         if (resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            imagePath = getRealPathFromURIPath(targetUri, DriverActivity.this);
             if (type.equals("carl")){
                 carLic.setImageURI(targetUri);

             } else if (type.equals("carIM")){
                 carImage.setImageURI(targetUri);

             }else  if (type.equals("drivl")){
                 driverLic.setImageURI(targetUri);

             } else if (type.equals("drivID")){
                driverId.setImageURI(targetUri);

             }
            //  imagePath = targetUri.getPath().toString();
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                if (type.equals("carl")){
                    carLic.setImageBitmap(bitmap);
                    Picasso.with(getApplicationContext())
                            .load(filePath)
                            .resize(600, 150)
                            .centerInside()
                            .into(carLic);

                } else if (type.equals("carIM")){
                    carImage.setImageBitmap(bitmap);
                    Picasso.with(getApplicationContext())
                            .load(filePath)
                            .resize(600, 150)
                            .centerInside()
                            .into(carImage);

                }else  if (type.equals("drivl")){
                    driverLic.setImageBitmap(bitmap);
                    Picasso.with(getApplicationContext())
                            .load(filePath)
                            .resize(600, 150)
                            .centerInside()
                            .into(driverLic);

                } else if (type.equals("drivID")){
                    driverId.setImageBitmap(bitmap);
                    Picasso.with(getApplicationContext())
                            .load(filePath)
                            .resize(600, 150)
                            .centerInside()
                            .into(driverLic);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private String getRealPathFromURIPath(Uri targetUri, DriverActivity signupActivity) {
        Cursor cursor = signupActivity.getContentResolver().query(targetUri, null, null, null, null);
        if (cursor == null) {
            return targetUri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }


  private void skip(NewUser driverUser) {

        Call<Example> call2 = retrofitInterface.setUserInfo(driverUser);
        // Toast.makeText(getApplicationContext(), "imagee"+encodedImage, Toast.LENGTH_SHORT).show();

        call2.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                if (response.code() == 403) {
                Toast.makeText(getApplicationContext(), "Thank you , we will contact you soon :)", Toast.LENGTH_SHORT).show();
                moveToMyProfile();
            }


                else {
                    Toast.makeText(getApplicationContext(), response.message().toString(), Toast.LENGTH_LONG).show();

                }
            }


            @Override
            public void onFailure(Call<Example> call, Throwable t) {

                call2.cancel();
            }
        });


    }

    private void moveToMyProfile() {

        Intent i = new Intent(DriverActivity.this, SignupActivity.class);
        i.putExtra("userPhone","");
        startActivity(i);


        finish();
    }
}
