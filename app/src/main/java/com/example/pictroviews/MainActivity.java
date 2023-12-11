package com.example.pictroviews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private Button exitButton;
    private Button FBbutton;
    private Button publishButton; // Agrega una referencia al botón de publicación
    private MqttManager mqttManager;
    private MqttCallbackHandler mqttCallbackHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startButton);
        exitButton = findViewById(R.id.exitButton);
        FBbutton = findViewById(R.id.FBbutton);
        publishButton = findViewById(R.id.publishButton);

        String brokerUrl = "tcp://valkar.cloud.shiftr.io:1883";
        String clientId = "valkar";
        mqttManager = new MqttManager(brokerUrl, clientId);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("valkar");
        options.setPassword("aRWdBA2Nxzh2y9UJ".toCharArray());

        mqttCallbackHandler = new MqttCallbackHandler();

        mqttManager.connect(options, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                // La conexión fue exitosa
                subscribeToMqttTopic(); // Suscribirse al tema MQTT que desees
            }
            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                // La conexión falló
                exception.printStackTrace();
            }
        });


        // Agregar un listener para el clic del botón de publicación
        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mqttTopic = "Alerta";
                String mqttMessage = "mascota encontrada";
                int qos=1;

                mqttManager.publish(mqttTopic, new MqttMessage(mqttMessage.getBytes()));
            }
        });
    }

    public void closeApplication(View view) {
        finishAffinity();
    }

    public void openSecondActivity(View view) {
        Intent intent = new Intent(this, MenuApp.class);
        startActivity(intent);
    }

    public void openMainActivityBD(View view) {
        Intent intent = new Intent(this, MainActivityBD.class);
        startActivity(intent);
    }

    private void subscribeToMqttTopic() {
        // Suscribirse al tema MQTT que desees
        String mqttTopic = "Alerta";
        int qos = 1; // Calidad de servicio, puedes ajustarla según tus necesidades
        mqttManager.subscribe(mqttTopic, qos, mqttCallbackHandler);
    }
}
