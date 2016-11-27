package com.rsc.rschackathon.activities;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.adapters.TeamListAdapter;
import com.rsc.rschackathon.api.NetworkService;
import com.rsc.rschackathon.api.models.AddTeamMemberApi;
import com.rsc.rschackathon.api.models.GetTeamMembersAPI;

import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamActivity extends AppCompatActivity
        implements NfcAdapter.CreateNdefMessageCallback, TeamListAdapter.Listener, NfcAdapter.OnNdefPushCompleteCallback {


//    TextView textInfo;
//
//    EditText textOut;

    TeamListAdapter recyclerViewAdapter;

    private static List<String> teamMateovi;

    LinearLayoutManager linearLayoutManager;

    NfcAdapter nfcAdapter;

    @BindView(R.id.team_list)
    RecyclerView teamList;

    Thread thread;

    NetworkService networkService;

    private Handler handler = new Handler();

    public static Intent createIntent(final Context context) {
        return new Intent(context, TeamActivity.class).putExtra("id", 100);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        ButterKnife.bind(this);
        networkService = new NetworkService();
        if (teamMateovi == null) {
            teamMateovi = new ArrayList<>();
            Log.i("TAG", "ONCREATE");
        }
//        textInfo = (TextView) findViewById(R.id.info);
//        textOut = (EditText) findViewById(R.id.textout);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Toast.makeText(this,
                    "nfcAdapter==null, no NFC adapter exists",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,
                    "Set Callback(s)",
                    Toast.LENGTH_LONG).show();
            nfcAdapter.setNdefPushMessageCallback(this, this);
            nfcAdapter.setOnNdefPushCompleteCallback(this, this);
        }

        recyclerViewAdapter = new TeamListAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this);

        teamList.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter.setListener(this);
        teamList.setAdapter(recyclerViewAdapter);

        getSupportActionBar().setTitle("Team members");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    @Override
    public void getDeviceAtPosition(int position) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TAG", "resumeaano");
        Intent intent = getIntent();
        if (intent != null) {
            Log.i("TAG", "NOTIFIKACIJA ne 200");
            if (intent.getIntExtra("id", 0) == 200) {

                Log.i("TAG", "NOTIFIKACIJA");
                String action = intent.getAction();
                if (action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
                    Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
                    NdefMessage inNdefMessage = (NdefMessage) parcelables[0];
                    NdefRecord[] inNdefRecords = inNdefMessage.getRecords();
                    NdefRecord NdefRecord_0 = inNdefRecords[0];
                    final String inMsg = new String(NdefRecord_0.getPayload());
                    Log.i("TAG", inMsg);
                    CreateTeamActivity.TEAM_ID = Integer.parseInt(inMsg);
                    //teamMateovi.add(inMsg);
                    //recyclerViewAdapter.setData(teamMateovi);
                    //textInfo.setText(inMsg);

                    Call<AddTeamMemberApi> call = networkService.getAPI()
                            .addTeamMembers(MainActivity.API_KEY, Integer.parseInt(inMsg), MainActivity.ID);
                    call.enqueue(new Callback<AddTeamMemberApi>() {
                        @Override
                        public void onResponse(Call<AddTeamMemberApi> call, Response<AddTeamMemberApi> response) {
                            Log.i("TAGs", "response");
                            if (response.body() != null) {
                                Log.i("TAGs", String.valueOf(response.body().isSuccess()));
                                if (response.body().isSuccess()) {
                                    Log.i("TAGs", "success adding ");
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<AddTeamMemberApi> call, Throwable t) {
                            Log.i("TAGs", t.getMessage());
                        }
                    });

                    thread = new Thread() {

                        public void run() {
                      //      Log.i("TAG", "thread");

                            Call<GetTeamMembersAPI> call = networkService.getAPI()
                                    .getTeamMembers(Integer.parseInt(inMsg), MainActivity.API_KEY);
                            call.enqueue(new Callback<GetTeamMembersAPI>() {
                                @Override
                                public void onResponse(Call<GetTeamMembersAPI> call, Response<GetTeamMembersAPI> response) {
              //                      Log.i("TAG", "response");
                  //                  Log.i("TAG", response.body().getData().get(0).getName());
                                    if (response.body() != null) {
                                        teamMateovi.clear();
                                        for (int i = 0; i < response.body().getData().size(); i++) {
                                            teamMateovi.add(response.body().getData().get(i).getName());
                                        }
                                        recyclerViewAdapter.setData(response.body().getData());
                                    }
                                }

                                @Override
                                public void onFailure(Call<GetTeamMembersAPI> call, Throwable t) {
                                    Log.i("TAG", t.getMessage());
                                }
                            });
                            handler.postDelayed(this, 1000);
                        }
                    };
                    handler.removeCallbacks(thread);
                    handler.postDelayed(thread, 0);
                }
            }
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        intent.putExtra("id", 200);
        setIntent(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(thread);
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    public void onNdefPushComplete(NfcEvent event) {

        final String eventString = "onNdefPushComplete\n" + event.toString();
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),
                        eventString,
                        Toast.LENGTH_LONG).show();


            }
        });

    }


    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {

//        String stringOut = textOut.getText().toString();
        String stringOut = "aaa";
        byte[] bytesOut = stringOut.getBytes();

        NdefRecord ndefRecordOut = new NdefRecord(
                NdefRecord.TNF_MIME_MEDIA,
                "text/plain".getBytes(),
                new byte[]{},
                bytesOut);

        NdefMessage ndefMessageout = new NdefMessage(ndefRecordOut);
        return ndefMessageout;
    }


}
