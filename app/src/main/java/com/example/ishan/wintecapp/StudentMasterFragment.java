package com.example.ishan.wintecapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import static android.app.Activity.RESULT_OK;


public class StudentMasterFragment extends Fragment implements Serializable, Parcelable, AdapterView.OnItemSelectedListener {


    private OnFragmentInteractionListener mListener;
    // ImageView ivGalImg;
    ImageButton btnChange;
    Bitmap bmp = null;
    private Spinner spinner;
    EditText name;
    EditText code;
    EditText sessionStart;
    EditText sessionEnd;
    DBHelper db;
    Button btnsave;
    private static final String[] paths = {"Software Engineer", "Database Architecture", "Networking", "Multi Media Web Development"};

    public StudentMasterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_student_master, container, false);
        db = new DBHelper(getActivity(), null, null, 1);
        View layout = inflater.inflate(R.layout.fragment_student_master, container, false);
        name = (EditText) layout.findViewById(R.id.editTextName);
        btnChange = (ImageButton) layout.findViewById(R.id.btnImgChange);
        code = (EditText) layout.findViewById(R.id.editTextStudCode);
        sessionStart = (EditText) layout.findViewById(R.id.editTextSessionStart);
        sessionEnd = (EditText) layout.findViewById(R.id.editTextSessionEnd);

        spinner = (Spinner) layout.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                openGallery();
            }
        });
btnsave =(Button) layout.findViewById(R.id.btnSave);
btnsave.setOnClickListener(new View.OnClickListener(){
    @Override
    public  void onClick(View view){
        SaveStudent();
    }

});
        Drawable buckysFace=getResources().getDrawable(R.drawable.app_logo1);
        bmp=((BitmapDrawable)buckysFace).getBitmap();
        return layout;
    }

    private void openGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultcode, Intent intent) {
        super.onActivityResult(requestCode, resultcode, intent);

        if (requestCode == 1) {
            if (intent != null && resultcode == RESULT_OK) {
                Uri selectedImage = intent.getData();


                ParcelFileDescriptor parcelFD = null;
                try {
                    parcelFD = getActivity().getContentResolver().openFileDescriptor(selectedImage, "r");
                    FileDescriptor imageSource = parcelFD.getFileDescriptor();

                    // Decode image size
                    BitmapFactory.Options o = new BitmapFactory.Options();
                    o.inJustDecodeBounds = true;
                    BitmapFactory.decodeFileDescriptor(imageSource, null, o);

                    // the new size we want to scale to
                    final int REQUIRED_SIZE = 1024;

                    // Find the correct scale value. It should be the power of 2.
                    int width_tmp = o.outWidth, height_tmp = o.outHeight;
                    int scale = 1;
                    while (true) {
                        if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE) {
                            break;
                        }
                        width_tmp /= 2;
                        height_tmp /= 2;
                        scale *= 2;
                    }

                    // decode with inSampleSize
                    BitmapFactory.Options o2 = new BitmapFactory.Options();
                    o2.inSampleSize = scale;
                    Bitmap bitmap = BitmapFactory.decodeFileDescriptor(imageSource, null, o2);
                    bmp = bitmap;
                    // ivGalImg.setImageBitmap(bitmap);
                    btnChange.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    // handle errors
                } catch (IOException e) {
                    // handle errors
                } finally {
                    if (parcelFD != null)
                        try {
                            parcelFD.close();
                        } catch (IOException e) {
                            // ignored
                        }
                }

            } else {
                Log.d("Status:", "Photopicker canceled");
            }
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            mListener.setActivityTitle("Add Student");
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    int pathway = 1;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                pathway = 1;
                break;
            case 1:
                pathway = 2;
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                pathway = 3;
                // Whatever you want to happen when the thrid item gets selected
                break;
            case 3:
                pathway = 4;
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void SaveStudent() {

        Users users = new Users(name.getText().toString(), name.getText().toString(), code.getText().toString(), false);
        long user_id = db.createUser(users);
        if (user_id > 0) {
            Student student = new Student(Integer.parseInt(code.getText().toString()), name.getText().toString(), pathway, sessionStart.getText().toString(), sessionEnd.getText().toString(), true, bmp, (int) user_id);
long result=  db.createStudent(student);
            if(result>0){
                Intent intent =new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Save Successfully",
                        Toast.LENGTH_SHORT).show();

            }
        }
    }

}
