package com.example.notas;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;




//librerías para el servicio.
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class MainActivity extends Activity implements OnClickListener{

	
	Button btnNotas;
	EditText etxNotas;
		
	// Metodo que queremos ejecutar en el servicio web
		//private static final String METODO = "consul";
	private static final String METODO = "GetGeoIPContext";
		// Namespace definido en el servicio web
		//private static final String NAMESPACE = "http://ws.apache.org/axis2";
	private static final String NAMESPACE = "http://www.webservicex.net/";
		// namespace + metodo
		//private static final String ACCIONSOAP = "urn:consul";
	private static final String ACCIONSOAP = "http://www.webservicex.net/GetGeoIPContext";
		// Fichero de definicion del servcio web
		//private static final String URL = "http://localhost:8080/GradesServer/services/nota";http://www.webservicex.net/geoipservice.asmx
	private static final String URL = "http://www.webservicex.net/geoipservice.asmx";
		
		//Declaracion de variables para consuymir el web service
		private SoapObject request=null;
		private SoapSerializationEnvelope envelope=null;
		private SoapPrimitive  resultsRequestSOAP=null;
		private HttpTransportSE transporte=null;
		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etxNotas = (EditText) findViewById(R.id.editText1);
		btnNotas = (Button) findViewById(R.id.button1);
		
		//agregamos el escuchador del botón.
		btnNotas.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		//etxNotas.setText("Roger");
		
		// Modelo el request
	    request = new SoapObject(NAMESPACE, METODO);
	    //request.addProperty("a", "aprovado"); // Paso parametros al WS
	   // request.addProperty("ToCurrency", "PEN"); // Paso parametros al WS
	 
	    // Modelo el Sobre
	    envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    envelope.dotNet = true;
	    envelope.setOutputSoapObject(request);
	 
	    // Modelo el transporte
	    transporte = new HttpTransportSE(URL);
	    try {
		    // Llamada
		    transporte.call(ACCIONSOAP, envelope);
		 // Resultado
		    resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
		    //etxNotas.setText(resultsRequestSOAP.toString());
		    etxNotas.setText("paso0");
	    } catch (IOException e){
	    	etxNotas.setText("paso1");
	    	e.printStackTrace();
	    } catch (XmlPullParserException e) {
	    	etxNotas.setText("paso2");
	    	e.printStackTrace();
	    }
		
	}

}
