package me.kamili.rachid.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.kamili.rachid.server.IMyService;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter mAdapter;
    private List<LegendGroup> mListGroup = new ArrayList<>();

    IMyService myService;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myService = IMyService.Stub.asInterface(iBinder);

            try {
                addLegends(myService.getAll());
            } catch (RemoteException e) {
                //nothing
            }

            Toast.makeText(MainActivity.this, "remote service connected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            myService = null;
            Toast.makeText(MainActivity.this, "remote service disconnected", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        startServerConnection();
    }

    private void startServerConnection() {
        ComponentName aidlComponent = new ComponentName("me.kamili.rachid.server", "me.kamili.rachid.server.MyService");
        Intent remoteIntent = new Intent();
        remoteIntent.setComponent(aidlComponent);
        bindService(remoteIntent, serviceConnection, BIND_AUTO_CREATE);
    }

    private void bindViews() {
        RecyclerView mRecyclerView = findViewById(R.id.rvLegendGroup);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new LegendGroupAdapter(mListGroup);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void addLegends(List<LegendGroup> legendGroups) {
        mListGroup.clear();
        mListGroup.addAll(legendGroups);
        mAdapter.notifyDataSetChanged();
    }
}
