package com.example.studygramapp;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class TeacherDetails extends AppCompatActivity {
    private FloatingActionButton uploadBtn;
    private ImageView uploadImage;
    private Uri imageUri;
    private EditText experience;
    private Button RegisterBtn;
    private TextView loginBtn;

    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("images");
    final private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    com.google.firebase.database.DatabaseReference DatabaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mydatabase-b4707-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details);

        uploadImage = findViewById(R.id.uploadImage);
        experience = findViewById(R.id.editTextDegree);
        RegisterBtn = findViewById(R.id.T_Register);
        loginBtn = findViewById(R.id.T_Login_Now);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            imageUri = data.getData();
                            uploadImage.setImageURI(imageUri);
                        } else {
                            Toast.makeText(TeacherDetails.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch image selection intent
                Intent photoPicker = new Intent();
                photoPicker.setAction(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String experienceTxt = experience.getText().toString();
                if (experienceTxt.isEmpty()) {
                    Toast.makeText(TeacherDetails.this, "Enter details", Toast.LENGTH_SHORT).show();
                } else if (imageUri == null) {
                    Toast.makeText(TeacherDetails.this, "Please select an image", Toast.LENGTH_SHORT).show();
                } else {
                    // Include the image uploading code here
                    uploadToFirebase(imageUri, experienceTxt);
                    Toast.makeText(TeacherDetails.this, "Registered", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TeacherDetails.this, S_Events.class);
                    startActivity(intent);
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherDetails.this, LoginasTeacher.class));
            }
        });
    }

    // Method to upload image and details to Firebase
    private void uploadToFirebase(Uri uri, String experienceTxt) {
        if (uri != null) {
            final StorageReference imageReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
            imageReference.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    DataClass dataClass = new DataClass(uri.toString(), experienceTxt);
                                    String key = databaseReference.push().getKey();
                                    databaseReference.child(key).setValue(dataClass)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {

                                                    Intent intent = new Intent(TeacherDetails.this, S_Events.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(TeacherDetails.this, "Failed to Register", Toast.LENGTH_SHORT).show();
                                                    Log.e("FirebaseDatabase", "Registration failed", e);
                                                }
                                            });
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TeacherDetails.this, "Failed to Upload", Toast.LENGTH_SHORT).show();
                            Log.e("FirebaseStorage", "Upload failed", e);
                        }
                    });
        } else {
            Toast.makeText(TeacherDetails.this, "Please select an image", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileExtension(Uri fileUri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(fileUri));
    }
}
