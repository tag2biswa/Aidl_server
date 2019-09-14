package com.example.server;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.aidl.ITestCallback;
import com.example.aidl.ITestInterface;

import java.time.LocalTime;

import androidx.annotation.Nullable;

public class TestService extends Service {

    private String TAG = "Server_TestService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: called");
        return stub;
    }

    ITestCallback.Stub stub = new ITestCallback.Stub() {
        @Override
        public void register(ITestInterface iTestInterface) throws RemoteException {
            Log.d(TAG, "register: ");
            // Sending boolean value
            iTestInterface.testOne(true);

            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(1000);
                    // Sending continuous int value
                    iTestInterface.testTwo(i);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //Sending continuous time stamp
                iTestInterface.testThree(String.valueOf(LocalTime.now()));
            }
        }

        @Override
        public void unregister(ITestInterface iTestInterface) throws RemoteException {
            iTestInterface = null;
        }
    };
}
