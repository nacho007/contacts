package deandreis.contacts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ignaciodeandreisdenis on 25/8/17.
 */

public class AdapterContact extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    static String TAG = "AdapterContacts";

    private ArrayList<Contact> contactList;


    public AdapterContact(ArrayList<Contact> contactList) {
        this.setContactList(contactList);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_contact_row, parent, false);
        return new ContactViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ContactViewHolder) holder).setItem(getContactList().get(position));
        ((ContactViewHolder) holder).bind(getContactList().get(position));
    }


    @Override
    public int getItemCount() {
        return getContactList().size();
    }

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(ArrayList<Contact> contactList) {
        this.contactList = contactList;
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {

        Context context;

        @BindView(R.id.adapter_contact_row_linearlayout)
        LinearLayout adapter_contact_row_linearlayout;

        @BindView(R.id.adapter_contact_row_circleimageview)
        ImageView adapter_contact_row_circleimageview;

        @BindView(R.id.adapter_contact_row_textview_name)
        TextView adapter_contact_row_textview_name;

        @BindView(R.id.adapter_contact_row_textview_number)
        TextView adapter_contact_row_textview_number;


        public ContactViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void setItem(Contact contact) {

            adapter_contact_row_textview_name.setText(contact.getName());
            adapter_contact_row_textview_number.setText(contact.getNumber());

            if (contact.getPhotoUri() != null) {

                Glide.with(context).load(contact.getPhotoUri()).placeholder(R.mipmap.ic_launcher).listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        adapter_contact_row_circleimageview.setImageResource(R.mipmap.ic_launcher);
                        return true;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                }).into(adapter_contact_row_circleimageview);
            }

            if (contact.getName() != null && !contact.getName().equals("")) {
                adapter_contact_row_textview_name.setVisibility(View.VISIBLE);
            } else {
                adapter_contact_row_textview_name.setVisibility(View.GONE);
            }

        }

        public void bind(final Contact contact) {

        }

    }

}

