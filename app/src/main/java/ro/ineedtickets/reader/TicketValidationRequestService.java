package ro.ineedtickets.reader;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import com.google.gson.Gson;

import java.util.HashMap;

public class TicketValidationRequestService {

    private Context context;
    private String ApiUrl = "http://192.168.100.13:58381/api/validate/";
    private ValidationResponseModel responseModel;

    public TicketValidationRequestService(Context context){
        this.context = context;
    }

    public ValidationResponseModel SendRequest(String ticketCode){

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("ticketCode", ticketCode);
        JSONObject jsonParams = new JSONObject(params);

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, ApiUrl, jsonParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        responseModel = gson.fromJson(response.toString(), ValidationResponseModel.class);
                        ((IVolleyCallback)context).VolleyResponse(responseModel);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null) {
                            error.printStackTrace();
                            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

        requestQueue.add(jsonRequest);

        return responseModel;
    }
}
