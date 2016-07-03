package com.example.zino.blueserver;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter;
    AcceptThread acceptThread;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setDiscoverable();
    }

    public void setDiscoverable(){
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        acceptThread=new AcceptThread(this, bluetoothAdapter);
        acceptThread.start();
    }

}
