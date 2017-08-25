package deandreis.contacts;

import android.Manifest;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        checkPermissions();
    }

    private void checkPermissions() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            getAllContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getAllContacts();
            } else {
            }
        }

    }

    void getAllContacts() {
        long startnow;
        long endnow;

        startnow = android.os.SystemClock.uptimeMillis();
        ArrayList<Contact> contacts = new ArrayList<>();
        int size = 0;

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String selection = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Cursor cursor = getContentResolver().query(uri, new String[]
                        {ContactsContract.CommonDataKinds.Phone.NUMBER,
                                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                                ContactsContract.CommonDataKinds.Phone._ID, ContactsContract.Data.CONTACT_ID},
                selection, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            size++;


            String contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            int phoneContactID = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
            int contactID = cursor.getInt(cursor.getColumnIndex(ContactsContract.Data.CONTACT_ID));


            Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactID);
            Uri displayPhotoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.DISPLAY_PHOTO);

            Contact contact = new Contact(contactName, contactNumber);
            contact.setPhotoUri(displayPhotoUri.toString());

            contacts.add(contact);

            Log.d("END", "position:" + size + "name " + contactName + " " + " PhoeContactID " + phoneContactID + "  ContactID " + contactID);

            cursor.moveToNext();
        }

        cursor.close();
        cursor = null;


        AdapterContact contactAdapter = new AdapterContact(contacts);
        recycler.setAdapter(contactAdapter);

        endnow = android.os.SystemClock.uptimeMillis();
        Log.d("END", "TimeForContacts " + (endnow - startnow) + " ms");
        Log.d("END", "Contacts Size " + size);
    }


}
