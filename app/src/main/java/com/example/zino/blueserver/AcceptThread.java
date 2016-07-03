package com.example.zino.blueserver;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by zino on 2016-07-03.
 */
public class AcceptThread extends Thread{
    String TAG=this.getClass().getName();
    Context context;
    private final BluetoothServerSocket mmServerSocket;
    BluetoothAdapter bluetoothAdapter;
    String NAME="myDevice";
    java.util.UUID MY_UUID;

    public AcceptThread(Context context,BluetoothAdapter bluetoothAdapter) {
        this.bluetoothAdapter=bluetoothAdapter;
        // Use a temporary object that is later assigned to mmServerSocket,
        // because mmServerSocket is final
        BluetoothServerSocket tmp = null;
        try {
            // MY_UUID is the app's UUID string, also used by the client code
            MY_UUID=UUID.fromString("ad36fe09-9990-4dc2-9a90-9ff30e9d644a");

            tmp = bluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
            Log.d(TAG, "MY_UUID is "+MY_UUID.toString());
        } catch (IOException e) { }
        mmServerSocket = tmp;
    }

    public void run() {
        BluetoothSocket socket = null;
        // Keep listening until exception occurs or a socket is returned
        while (true) {
            try {
                socket = mmServerSocket.accept();
                Toast.makeText(context, "접속자 발견", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                break;
            }
            // If a connection was accepted
            if (socket != null) {
                // Do work to manage the connection (in a separate thread)
                //manageConnectedSocket(socket);
                try {
                    mmServerSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /** Will cancel the listening socket, and cause the thread to finish */
    public void cancel() {
        try {
            mmServerSocket.close();
        } catch (IOException e) { }
    }
}
