package pe.edu.upc.proyectotsys.viewcontrollers.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

import pe.edu.upc.proyectotsys.R;
import android.app.PendingIntent;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.content.Intent;
import android.view.View;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Map;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import pe.edu.upc.proyectotsys.R;
import pe.edu.upc.proyectotsys.models.Advisor;
import pe.edu.upc.proyectotsys.viewcontrollers.Interface.AdvisorInterface;
import pe.edu.upc.proyectotsys.viewcontrollers.clases.PhotoUtils;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UpdateUserActivity extends AppCompatActivity implements View.OnClickListener {
    private AlertDialog _photoDialog;
    private Uri mImageUri;
    private static final int ACTIVITY_SELECT_IMAGE = 1020, ACTIVITY_SELECT_FROM_CAMERA = 1040, ACTIVITY_SHARE = 1030;
    private PhotoUtils photoUtils;
    private ImageView pictureImageView;
    private boolean fromShare = false;
    private ImageButton GoMapImageButton;
    private Button button_registrar;
    private TextInputLayout impdni, impphone, impname, implastname, impemail, imppassword, imppassword2, impadress;
    private EditText txtdni, txtphone, txtname, txtlastname, txtemail, txtpassword, txtpassword2, txtadress;
    private String txtlat, txtlon;
    private Toolbar toolbar;
    Location location;
    LocationManager locationManager;
    LocationListener locationListener;
    AlertDialog alert = null;
    private SharedPreferences pref_Session;
    private String strImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pref_Session = this.getSharedPreferences("SessionUser", Context.MODE_PRIVATE);

        GoMapImageButton = (ImageButton) findViewById(R.id.GoMapImageButton);
        button_registrar = (Button) findViewById(R.id.button_registrar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     UpdateUserActivity.this.onBackPressed();
                                                 }
                                             });

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        GoMapImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewIn) {
                try {
                    if (!GPSHabilitado()){
                        MostrarAlertaGPS();
                    }else{
                        Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                        startActivityForResult(i, 99);
                    }
                } catch (Exception except) {
                    Toast.makeText(UpdateUserActivity.this, except.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        button_registrar.setOnClickListener(this);
        CargarDatosUser();

        photoUtils = new PhotoUtils(this);
        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                fromShare = true;
            } else if (type.startsWith("image/")) {
                fromShare = true;
                mImageUri = (Uri) intent
                        .getParcelableExtra(Intent.EXTRA_STREAM);
                getImage(mImageUri);
            }
        }
        pictureImageView = (ImageView) findViewById(R.id.pictureImageView);
        getPhotoDialog();
        setPhotoButton();
    }

    private void setPhotoButton() {
        pictureImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!getPhotoDialog().isShowing() && !isFinishing())
                    getPhotoDialog().show();
            }
        });
    }

    private AlertDialog getPhotoDialog() {
        if (_photoDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(UpdateUserActivity.this);
            builder.setTitle(R.string.photo_source);
            builder.setPositiveButton(R.string.camera, new DialogInterface.OnClickListener() {
                //CAMARA
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(
                            "android.media.action.IMAGE_CAPTURE");
                    File photo = null;
                    try {
                        // place where to store camera taken picture
                        photo = PhotoUtils.createTemporaryFile("picture", ".jpg", UpdateUserActivity.this);
                        photo.delete();
                    } catch (Exception e) {
                        Log.v(getClass().getSimpleName(),
                                "Can't create file to take picture!");
                    }
                    mImageUri = Uri.fromFile(photo);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
                    startActivityForResult(intent, ACTIVITY_SELECT_FROM_CAMERA);
                }
            });
            builder.setNegativeButton(R.string.gallery, new DialogInterface.OnClickListener() {
                //GALERIA
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    galleryIntent.setType("image/*");
                    startActivityForResult(galleryIntent, ACTIVITY_SELECT_IMAGE);
                }

            });
            _photoDialog = builder.create();
        }
        return _photoDialog;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mImageUri != null)
            outState.putString("Uri", mImageUri.toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("Uri")) {
            mImageUri = Uri.parse(savedInstanceState.getString("Uri"));
        }
    }

    private void setImageBitmap(Bitmap bitmap) {
        pictureImageView.setImageBitmap(bitmap);
    }

    public void getImage(Uri uri) {
        Bitmap bounds = photoUtils.getImage(uri);
        if (bounds != null) {
            setImageBitmap(bounds);
        }
    }

    private void MostrarAlertaGPS(){
        new AlertDialog.Builder(UpdateUserActivity.this)
                .setTitle("Sin ubicación precisa")
                .setMessage("El servicio de localización esta desactivada. Por favor, vaya a la configuración y habilite el GPS y su ubicación de red")
                .setPositiveButton("IR CONFIGURACION", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("CANCEL", null)
                .show();
    }

    private boolean GPSHabilitado(){
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return false;
        }else{
            return true;
        }
    }

    public boolean ValidarCamposRegistroUsuario(){
        txtdni = (EditText) findViewById(R.id.dniTextView);
        txtphone = (EditText) findViewById(R.id.phoneTextView);
        txtname = (EditText) findViewById(R.id.nameTextView);
        txtlastname = (EditText) findViewById(R.id.lastnameTextView);
        txtemail = (EditText) findViewById(R.id.correoTextView);
        txtpassword = (EditText) findViewById(R.id.passwordTextView);
        txtadress = (EditText) findViewById(R.id.directionTextView);
        txtpassword2 = (EditText) findViewById(R.id.password2TextView);
        txtlat = "16.155211";
        txtlon = "12.155211";

        impdni = (TextInputLayout) findViewById(R.id.impDni);
        impphone = (TextInputLayout) findViewById(R.id.impPhone);
        impname = (TextInputLayout) findViewById(R.id.impName);
        implastname = (TextInputLayout) findViewById(R.id.impLastName);
        impemail = (TextInputLayout) findViewById(R.id.impCorreo);
        imppassword = (TextInputLayout) findViewById(R.id.impPassword);
        imppassword2 = (TextInputLayout) findViewById(R.id.impPassword2);
        impadress = (TextInputLayout) findViewById(R.id.impdirection);

        if (txtdni.getText().toString().isEmpty() || txtdni.length() < 8){
            impdni.setError("DNI invalido");
            return false;
        }else if (txtphone.getText().toString().isEmpty() || txtphone.length() < 9){
            impphone.setError("Telefono invalido");
            return false;
        }else if (txtname.getText().toString().isEmpty()){
            impname.setError("Nombre invalido");
            return false;
        }else if (txtlastname.getText().toString().isEmpty()){
            implastname.setError("Apellidos invalido");
            return false;
        }else if(Patterns.EMAIL_ADDRESS.matcher(txtemail.getText().toString()).matches()==false){
            impemail.setError("Correo invalido");
            return false;
        }else if (txtadress.getText().toString().isEmpty()){
            impadress.setError("Dirección invalido");
            return false;
        }else{
            return true;
        }
    }

    public void CargarDatosUser(){
        txtdni = (EditText) findViewById(R.id.dniTextView);
        txtphone = (EditText) findViewById(R.id.phoneTextView);
        txtname = (EditText) findViewById(R.id.nameTextView);
        txtlastname = (EditText) findViewById(R.id.lastnameTextView);
        txtemail = (EditText) findViewById(R.id.correoTextView);
        txtpassword = (EditText) findViewById(R.id.passwordTextView);
        txtadress = (EditText) findViewById(R.id.directionTextView);
        txtpassword2 = (EditText) findViewById(R.id.password2TextView);
        pictureImageView = (ImageView) findViewById(R.id.pictureImageView);

        txtdni.setText(pref_Session.getString("dni", ""));
        txtphone.setText(pref_Session.getString("phone", ""));
        txtname.setText(pref_Session.getString("name", ""));
        txtlastname.setText(pref_Session.getString("lastName", ""));
        txtemail.setText(pref_Session.getString("email", ""));
        txtadress.setText(pref_Session.getString("direction", ""));
        strImage = pref_Session.getString("picture", "");

        CargarImage(strImage);


        txtdni.setEnabled(false);
        txtdni.setFocusable(false);
        txtdni.setTextColor(Color.GRAY);

        txtemail.setEnabled(false);
        txtemail.setFocusable(false);
        txtemail.setTextColor(Color.GRAY);
    }

    private void UpdateUser(){
        Toast.makeText(this, txtlastname.getText().toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, txtname.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    private void CargarImage(String strImage){
        //decode base64 string to image====================================================================================
        byte[]  imageBytes = Base64.decode(strImage, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        pictureImageView.setImageBitmap(decodedImage);
    }

    @Override
    public void onClick(View viewIn) {
        if (ValidarCamposRegistroUsuario() == true) {
            UpdateUser();
        }
    }


}
