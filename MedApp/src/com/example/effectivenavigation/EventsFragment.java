package com.example.effectivenavigation;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class EventsFragment extends Fragment implements OnClickListener{

    public static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        Button notify_button = (Button) rootView.findViewById(R.id.notify_button);
        notify_button.setOnClickListener(this);
        return rootView;
    }
    
    @Override
	public void onClick(View v) {
		send_notification();
	}
    
    public void send_notification(){
    	Activity activity = getActivity();
    	Resources resource = activity.getResources();
    	NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getActivity());
    	mBuilder.setSmallIcon(R.drawable.medapp_icon);
    	Bitmap btp = BitmapFactory.decodeResource(resource, R.drawable.medapp_icon_small);
    	mBuilder.setLargeIcon(btp);
        mBuilder.setContentTitle("My notification");
        mBuilder.setContentText("Hello World!");
    	
    	mBuilder.setAutoCancel(true);
    	Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    	mBuilder.setSound(defaultRingtoneUri);
    	

    	Intent intent = new Intent();
    	intent.setClass(activity, RecipeDetail.class);
    	intent.putExtra("position", 1);
    	Intent backIntent = new Intent(activity, MainActivity.class);
        backIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Intent[] intents = new Intent[] {backIntent, intent};

        PendingIntent resultPendingIntent = PendingIntent.getActivities(getActivity(), 0, intents, PendingIntent.FLAG_ONE_SHOT);
    	
    	mBuilder.setContentIntent(resultPendingIntent);
    	NotificationManager mNotificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
    	mNotificationManager.notify(0, mBuilder.build());
    }
}