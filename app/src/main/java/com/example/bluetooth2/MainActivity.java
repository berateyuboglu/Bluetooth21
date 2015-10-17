
package com.example.bluetooth2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
 
public class MainActivity extends Activity {
  public static final String TAG = "bluetooth2";

  Button sicak, hareket, kapi1 , kapi2, led, kapi3, kapi4,sicaklik;
  TextView txt;
  Handler h;
  Switch sw1,sw2,sw3,sw4,sw5,sw6;
  Point p;

  public final int RECIEVE_MESSAGE = 1;		// Status  for Handler
  public static BluetoothAdapter btAdapter = null;
  public static BluetoothSocket btSocket = null;
  public static StringBuilder sb = new StringBuilder();

  public static  ConnectedThread mConnectedThread;


  public static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


  public static String address = "98:D3:31:90:06:4F"; //BU NOKTA DOLACAK

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    sicak = (Button) findViewById(R.id.show_popup);					// button LED ON
    hareket = (Button) findViewById(R.id.hareket);				// button LED OFF
    txt = (TextView) findViewById(R.id.textView1);
    kapi1 = (Button) findViewById(R.id.kapi1);					// button LED ON
    kapi2 = (Button) findViewById(R.id.kapi2);		// for display the received data from the Arduino
    //led = (Button) findViewById(R.id.led);
    kapi3 = (Button) findViewById(R.id.kapi3);
    kapi4 = (Button) findViewById(R.id.kapi4);
    sicaklik = (Button) findViewById(R.id.sicaklik);


    h = new Handler() {
    	public  void handleMessage(android.os.Message msg) {
    		switch (msg.what) {
            case RECIEVE_MESSAGE:													// if receive massage
            	byte[] readBuf = (byte[]) msg.obj;
            	String strIncom = new String(readBuf, 0, msg.arg1);					// create string from bytes array
            	sb.append(strIncom);												// append string
            	int endOfLineIndex = sb.indexOf("\r\n");							// determine the end-of-line
            	if (endOfLineIndex > 0) { 											// if end-of-line,
                txt.setText(sb);                                                    // update TextView
               //Toast.makeText(getApplicationContext(),""+sb , Toast.LENGTH_SHORT).show();

                }
            	//Log.d(TAG, "...String:"+ sb.toString() +  "Byte:" + msg.arg1 + "...");
            	break;
    		}
        };
	};

hareket.setOnClickListener(new OnClickListener() {
    public void onClick(View v) {
        mConnectedThread.write("5");
    }
});


      hareket.setOnTouchListener(new View.OnTouchListener() {

          public boolean onTouch(View v, MotionEvent event) {
              switch (event.getAction()) {
                  case MotionEvent.ACTION_UP: {
                      mConnectedThread.write("5");
                      Button view = (Button) v;
                      view.getBackground().clearColorFilter();
                      view.invalidate();
                      break;

                  }

                  case MotionEvent.ACTION_DOWN: {

                      Button view = (Button) v;
                      view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                      view.invalidate();
                      break;
                  }

                  case MotionEvent.ACTION_CANCEL: {
                      Button view = (Button) v;
                      view.getBackground().clearColorFilter();
                      view.invalidate();
                      break;

                  }

              }

              return true;
          }
      });

      kapi1.setOnTouchListener(new View.OnTouchListener() {

          public boolean onTouch(View v, MotionEvent event) {
              switch (event.getAction()) {
                  case MotionEvent.ACTION_UP: {
                      Button view = (Button) v;
                      mConnectedThread.write("3");
                      view.getBackground().clearColorFilter();
                      view.invalidate();
                      break;

                  }

                  case MotionEvent.ACTION_DOWN: {

                      Button view = (Button) v;
                      view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                      view.invalidate();
                      break;
                  }

                  case MotionEvent.ACTION_CANCEL: {
                      Button view = (Button) v;
                      view.getBackground().clearColorFilter();
                      view.invalidate();
                      break;

                  }

              }

              return true;
          }
      });
      kapi2.setOnTouchListener(new View.OnTouchListener() {

          public boolean onTouch(View v, MotionEvent event) {
              switch (event.getAction()) {
                  case MotionEvent.ACTION_UP: {
                      mConnectedThread.write("4");
                      Button view = (Button) v;
                      view.getBackground().clearColorFilter();
                      view.invalidate();
                      break;

                  }

                  case MotionEvent.ACTION_DOWN: {

                      Button view = (Button) v;
                      view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                      view.invalidate();
                      break;
                  }

                  case MotionEvent.ACTION_CANCEL: {
                      Button view = (Button) v;
                      view.getBackground().clearColorFilter();
                      view.invalidate();
                      break;

                  }

              }

              return true;
          }
      });
      kapi3.setOnTouchListener(new View.OnTouchListener() {

          public boolean onTouch(View v, MotionEvent event) {
              switch (event.getAction()) {
                  case MotionEvent.ACTION_UP: {
                      mConnectedThread.write("6");
                      Button view = (Button) v;
                      view.getBackground().clearColorFilter();
                      view.invalidate();
                      break;

                  }

                  case MotionEvent.ACTION_DOWN: {

                      Button view = (Button) v;
                      view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                      view.invalidate();
                      break;
                  }

                  case MotionEvent.ACTION_CANCEL: {
                      Button view = (Button) v;
                      view.getBackground().clearColorFilter();
                      view.invalidate();
                      break;

                  }

              }

              return true;
          }
      });
      kapi4.setOnTouchListener(new View.OnTouchListener() {

          public boolean onTouch(View v, MotionEvent event) {
              switch (event.getAction()) {
                  case MotionEvent.ACTION_UP: {
                      mConnectedThread.write("7");
                      Button view = (Button) v;
                      view.getBackground().clearColorFilter();
                      view.invalidate();
                      break;

                  }

                  case MotionEvent.ACTION_DOWN: {

                      Button view = (Button) v;
                      view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                      view.invalidate();
                      break;
                  }

                  case MotionEvent.ACTION_CANCEL: {
                      Button view = (Button) v;
                      view.getBackground().clearColorFilter();
                      view.invalidate();
                      break;

                  }

              }

              return true;
          }
      });
      sicaklik.setOnTouchListener(new View.OnTouchListener() {

          public boolean onTouch(View v, MotionEvent event) {
              switch (event.getAction()) {
                  case MotionEvent.ACTION_UP: {
                      Button view = (Button) v;
                      mConnectedThread.write("1");
                      view.getBackground().clearColorFilter();
                      view.invalidate();
                      break;

                  }

                  case MotionEvent.ACTION_DOWN: {

                      Button view = (Button) v;
                      view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                      view.invalidate();
                      break;
                  }

                  case MotionEvent.ACTION_CANCEL: {
                      Button view = (Button) v;
                      view.getBackground().clearColorFilter();
                      view.invalidate();
                      break;

                  }

              }

              return true;
          }
      });




                  sicak.setOnClickListener(new OnClickListener() {

                      public void onClick(View v) {
                          showPopup(MainActivity.this, p);

                      }


                  });

                  btAdapter = BluetoothAdapter.getDefaultAdapter();        // get Bluetooth adapter
                  checkBTState();


                  sicaklik.setOnClickListener(new OnClickListener() {
                      public void onClick(View v) {
                          mConnectedThread.write("1");
                      }
                  });


                  kapi1.setOnClickListener(new OnClickListener() {

                      public void onClick(View v) {
                          mConnectedThread.write("3");


                      }
                  });

                  kapi2.setOnClickListener(new OnClickListener() {

                      public void onClick(View v) {

                          mConnectedThread.write("4");
                      }
                  });


                  kapi3.setOnClickListener(new OnClickListener() {

                      public void onClick(View v) {
                          // TODO Auto-generated method stub
                          mConnectedThread.write("6");
                      }
                  });


                  kapi4.setOnClickListener(new OnClickListener() {

                      public void onClick(View v) {
                          // TODO Auto-generated method stub
                          mConnectedThread.write("7");

                      }
                  });


              }


  /*   *-*-*-*-*-*-*-*-*   FONKSIYON BOLUMU *-*-*-*--*-*-*-*- */
    // Dokunma


          public BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
              if (Build.VERSION.SDK_INT >= 10) {
                  try {
                      final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[]{UUID.class});
                      return (BluetoothSocket) m.invoke(device, MY_UUID);
                  } catch (Exception e) {
                      Log.e(TAG, "Could not create Insecure RFComm Connection", e);
                  }
              }
              return device.createRfcommSocketToServiceRecord(MY_UUID);
          }

          @Override
          public void onResume() {
              super.onResume();

              Log.d(TAG, "...onResume - try connect...");

              // Set up a pointer to the remote node using it's address.
              BluetoothDevice device = btAdapter.getRemoteDevice(address);

              // Two things are needed to make a connection:
              //   A MAC address, which we got above.
              //   A Service ID or UUID.  In this case we are using the
              //      UUID for SPP.

              try {
                  btSocket = createBluetoothSocket(device);
              } catch (IOException e) {
                  errorExit("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
              }

    /*try {
      btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
    } catch (IOException e) {
      errorExit("Fatal Error", "In onResume() and socket create failed: " + e.getMessage() + ".");
    }*/

              // Discovery is resource intensive.  Make sure it isn't going on
              // when you attempt to connect and pass your message.
              btAdapter.cancelDiscovery();

              // Establish the connection.  This will block until it connects.
              Log.d(TAG, "...Connecting...");
              try {
                  btSocket.connect();
                  Log.d(TAG, "....Connection ok...");
              } catch (IOException e) {
                  try {
                      btSocket.close();
                  } catch (IOException e2) {
                      errorExit("Fatal Error", "In onResume() and unable to close socket during connection failure" + e2.getMessage() + ".");
                  }
              }

              // Create a data stream so we can talk to server.
              Log.d(TAG, "...Create Socket...");

              mConnectedThread = new ConnectedThread(btSocket);
              mConnectedThread.start();
          }

          @Override
          public void onPause() {
              super.onPause();

              Log.d(TAG, "...In onPause()...");


              try {
                  btSocket.close();
              } catch (IOException e2) {
                  errorExit("Fatal Error", "In onPause() and failed to close socket." + e2.getMessage() + ".");
              }
          }

          public void checkBTState() {
              // Check for Bluetooth support and then check to make sure it is turned on
              // Emulator doesn't support Bluetooth and will return null
              if (btAdapter == null) {
                  errorExit("Fatal Error", "Bluetooth not support");
              } else {
                  if (btAdapter.isEnabled()) {
                      Log.d(TAG, "...Bluetooth ON...");
                  } else {
                      //Prompt user to turn on Bluetooth
                      Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                      startActivityForResult(enableBtIntent, 1);
                  }
              }
          }

          public void errorExit(String title, String message) {
              Toast.makeText(getBaseContext(), title + " - " + message, Toast.LENGTH_LONG).show();
              finish();
          }

          public class ConnectedThread extends Thread {
              public final InputStream mmInStream;
              public final OutputStream mmOutStream;

              public ConnectedThread(BluetoothSocket socket) {
                  InputStream tmpIn = null;
                  OutputStream tmpOut = null;

                  // Get the input and output streams, using temp objects because
                  // member streams are final
                  try {
                      tmpIn = socket.getInputStream();
                      tmpOut = socket.getOutputStream();
                  } catch (IOException e) {
                  }

                  mmInStream = tmpIn;
                  mmOutStream = tmpOut;
              }

              public void run() {
                  byte[] buffer = new byte[256];  // buffer store for the stream
                  int bytes; // bytes returned from read()

                  // Keep listening to the InputStream until an exception occurs
                  while (true) {
                      try {
                          // Read from the InputStream
                          bytes = mmInStream.read(buffer);        // Get number of bytes and message in "buffer"
                          h.obtainMessage(RECIEVE_MESSAGE, bytes, -1, buffer).sendToTarget();        // Send to message queue Handler
                      } catch (IOException e) {
                          break;
                      }
                  }
              }

              /* Call this from the main activity to send data to the remote device */
              public void write(String message) {
                  Log.d(TAG, "...Data to send: " + message + "...");
                  byte[] msgBuffer = message.getBytes();
                  try {
                      mmOutStream.write(msgBuffer);
                  } catch (IOException e) {
                      Log.d(TAG, "...Error data send: " + e.getMessage() + "...");
                  }
              }


          }


          public void onWindowFocusChanged(boolean hasFocus) {

              int[] location = new int[2];
              Button button = (Button) findViewById(R.id.show_popup);

              // Get the x, y location and store it in the location[] array
              // location[0] = x, location[1] = y.
              button.getLocationOnScreen(location);

              //Initialize the Point with x, and y positions
              p = new Point();
              p.x = location[0];
              p.y = location[1];
          }

          // The method that displays the popup.
          private void showPopup(final Activity context, Point p) {
              int popupWidth = 1100;
              int popupHeight = 1100;

              // Inflate the popup_layout.xml
              LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
              LayoutInflater layoutInflater = (LayoutInflater) context
                      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              View layout = layoutInflater.inflate(R.layout.popup_layout, viewGroup);

              // Creating the PopupWindow
              final PopupWindow popup = new PopupWindow(context);
              popup.setContentView(layout);
              popup.setWidth(popupWidth);
              popup.setHeight(popupHeight);
              popup.setFocusable(true);

              // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
              int OFFSET_X = 0;
              int OFFSET_Y = 0;

              // Clear the default translucent background
              popup.setBackgroundDrawable(new BitmapDrawable());

              // Displaying the popup at the specified location, + offsets.
              popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);

              sw1 = (Switch) layout.findViewById(R.id.switch1);
              sw2 = (Switch) layout.findViewById(R.id.switch2);
              sw3 = (Switch) layout.findViewById(R.id.switch3);
              sw4 = (Switch) layout.findViewById(R.id.switch4);
              sw5 = (Switch) layout.findViewById(R.id.switch5);
              sw6 = (Switch) layout.findViewById(R.id.switch6);


              sw1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                      if (isChecked == true) {
                          mConnectedThread.write("9");
                      } else {
                          mConnectedThread.write("x");
                      }
                  }
              });


              sw2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                      if (isChecked == true) {
                          mConnectedThread.write("0");
                      } else {
                          mConnectedThread.write("h");
                      }
                  }
              });


              sw3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                      if (isChecked == true) {


                          mConnectedThread.write("q");

                      } else {

                          mConnectedThread.write("w");

                      }

                  }
              });


              sw4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                      if (isChecked == true) {
                          mConnectedThread.write("e");
                      } else {
                          mConnectedThread.write("r");
                      }
                  }
              });


              sw5.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                      if (isChecked == true) {

                          mConnectedThread.write("y");
                      } else {
                          mConnectedThread.write("u");
                      }
                  }
              });


              sw6.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                      if (isChecked == true) {
                          mConnectedThread.write("y");
                          mConnectedThread.write("q");
                          mConnectedThread.write("e");
                          mConnectedThread.write("0");
                          mConnectedThread.write("9");

                      } else {
                          mConnectedThread.write("u");
                          mConnectedThread.write("w");
                          mConnectedThread.write("r");
                          mConnectedThread.write("h");
                          mConnectedThread.write("x");

                      }
                  }
              });


          }


      }