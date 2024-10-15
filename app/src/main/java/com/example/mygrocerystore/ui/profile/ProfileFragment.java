package com.example.mygrocerystore.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.mygrocerystore.R;
import com.example.mygrocerystore.activities.LoginActivity;
import com.example.mygrocerystore.models.UserModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.appcheck.FirebaseAppCheckRegistrar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {

    CircleImageView profileImg;
    EditText firstname, email, number, address;
    Button update,delete;

    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseDatabase database;

    Uri profileUri;  // Declare Uri to store the image URI

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        profileImg = root.findViewById(R.id.profile_img);
        firstname = root.findViewById(R.id.profile_name);
        email = root.findViewById(R.id.profile_email);
        number = root.findViewById(R.id.profile_number);
        address = root.findViewById(R.id.profile_address);
        update = root.findViewById(R.id.update);
        delete = root.findViewById(R.id.profile_delete);

        // Load user data from Firebase database
        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel = snapshot.getValue(UserModel.class);
                        if (userModel != null) {
                            Glide.with(getContext()).load(userModel.getProfileImg()).into(profileImg);
                            firstname.setText(userModel.getFirstname());
                            email.setText(userModel.getEmail());
                            number.setText(userModel.getPhone());
                            address.setText(userModel.getAddress());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), "Failed to load profile data", Toast.LENGTH_SHORT).show();
                    }
                });

        // Profile image click listener to open image chooser
        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 33);
            }
        });

        // Update button click listener
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserProfile();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUserProfile();
            }
        });

        return root;
    }

    private void deleteUserProfile() {
        String userId = FirebaseAuth.getInstance().getUid();

        // Step 1: Delete user data from Firebase Realtime Database
        database.getReference().child("Users").child(userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel = snapshot.getValue(UserModel.class);
                        if (userModel != null) {
                            // Check if the user has a profile image to delete
                            String profileImageUrl = userModel.getProfileImg();
                            if (profileImageUrl != null && !profileImageUrl.isEmpty()) {
                                // Step 2: Delete the profile image from Firebase Storage
                                StorageReference profileImageRef = storage.getReferenceFromUrl(profileImageUrl);
                                profileImageRef.delete().addOnSuccessListener(unused -> {
                                    // Profile image deleted successfully
                                    Toast.makeText(getContext(), "Profile image deleted", Toast.LENGTH_SHORT).show();
                                }).addOnFailureListener(e -> {
                                    // Failed to delete profile image
                                    Toast.makeText(getContext(), "Failed to delete profile image", Toast.LENGTH_SHORT).show();
                                });
                            }

                            // Step 3: Delete the user data from Firebase Realtime Database
                            database.getReference().child("Users").child(userId).removeValue()
                                    .addOnSuccessListener(unused -> {
                                        // User data deleted successfully
                                        Toast.makeText(getContext(), "User data deleted", Toast.LENGTH_SHORT).show();

                                        // Step 4: Delete the user's authentication account
                                        FirebaseAuth.getInstance().getCurrentUser().delete()
                                                .addOnSuccessListener(unused1 -> {
                                                    // User account deleted successfully
                                                    Toast.makeText(getContext(), "User account deleted", Toast.LENGTH_SHORT).show();

                                                    // Optionally, redirect the user to a login screen after deletion
                                                    Intent intent = new Intent(getContext(), LoginActivity.class);
                                                    startActivity(intent);
                                                    getActivity().finish();
                                                })
                                                .addOnFailureListener(e -> {
                                                    // Failed to delete user authentication account
                                                    Toast.makeText(getContext(), "Failed to delete user account", Toast.LENGTH_SHORT).show();
                                                });
                                    })
                                    .addOnFailureListener(e -> {
                                        // Failed to delete user data
                                        Toast.makeText(getContext(), "Failed to delete user data", Toast.LENGTH_SHORT).show();
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), "Failed to delete profile data", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Handle image chooser result
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && data.getData() != null) {
            profileUri = data.getData();  // Store the selected image URI
            profileImg.setImageURI(profileUri);  // Display selected image

            final StorageReference reference = storage.getReference().child("profile_picture")
                    .child(FirebaseAuth.getInstance().getUid());

            reference.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "Profile Picture Uploaded", Toast.LENGTH_SHORT).show();

                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                                    .child("profileImg").setValue(uri.toString());
                            Toast.makeText(getContext(), "Profile Picture URL Saved", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }

    // Method to update the user's profile
    private void updateUserProfile() {
        // Get values from EditText fields
        String userName = firstname.getText().toString().trim();
        String userEmail = email.getText().toString().trim();
        String userNumber = number.getText().toString().trim();
        String userAddress = address.getText().toString().trim();

        // Ensure fields are not empty
        if (userName.isEmpty() || userEmail.isEmpty() || userNumber.isEmpty() || userAddress.isEmpty()) {
            Toast.makeText(getContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get user ID
        String userId = FirebaseAuth.getInstance().getUid();

        // Save updated user data to Firebase Realtime Database
        database.getReference().child("Users").child(userId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel = snapshot.getValue(UserModel.class);
                        if (userModel != null) {
                            userModel.setFirstname(userName);
                            userModel.setEmail(userEmail);
                            userModel.setPhone(userNumber);
                            userModel.setAddress(userAddress);

                            // If profile image was updated, set the new image URI
                            if (profileUri != null) {
                                userModel.setProfileImg(profileUri.toString());
                            }

                            // Update the user model in the database
                            database.getReference().child("Users").child(userId).setValue(userModel)
                                    .addOnSuccessListener(unused -> {
                                        Toast.makeText(getContext(), "Profile updated successfully!", Toast.LENGTH_SHORT).show();
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(getContext(), "Failed to update profile", Toast.LENGTH_SHORT).show();
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), "Failed to update profile", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}