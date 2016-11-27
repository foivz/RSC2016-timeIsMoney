package com.rsc.rschackathon.activities;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.adapters.TeamListAdapter;
import com.rsc.rschackathon.api.NetworkService;
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
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServerTeamActivity extends AppCompatActivity
        implements NfcAdapter.CreateNdefMessageCallback, NfcAdapter.OnNdefPushCompleteCallback, TeamListAdapter.Listener {

    TeamListAdapter recyclerViewAdapter;

    private static List<String> teamMateovi;

    LinearLayoutManager linearLayoutManager;

    NfcAdapter nfcAdapter;

    @BindView(R.id.team_list)
    RecyclerView teamList;

  //  @BindView(R.id.team_mates_numbers)
   // TextView numbers;

    Thread thread;

    NetworkService networkService;

    private Handler handler = new Handler();

    public static Intent createIntent(final Context context) {
        return new Intent(context, ServerTeamActivity.class).putExtra("id", 100);
    }

    @OnClick(R.id.activity_server_team_play)
    public void startQActivity(){
        startActivity(new Intent(ServerTeamActivity.this, QuestionActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_team);
        networkService = new NetworkService();
        ButterKnife.bind(this);
        //numbers.setText("1/"+ brojIgraca + " players");

        thread = new Thread() {
            public void run() {

                Call<GetTeamMembersAPI> call = networkService.getAPI()
                        .getTeamMembers(CreateTeamActivity.TEAM_ID, MainActivity.API_KEY);
                call.enqueue(new Callback<GetTeamMembersAPI>() {
                    @Override
                    public void onResponse(Call<GetTeamMembersAPI> call, Response<GetTeamMembersAPI> response) {
                        if (response.body() != null) {

                            //Log.i("TAG", response.body().getData().get(0).getName());
                            /*teamMateovi.clear();
                            for (int i = 0; i < response.body().getData().size(); i++) {
                                teamMateovi.add(response.body().getData().get(i).getName());
                            }*/
                            recyclerViewAdapter.setData(response.body().getData());
                            //numbers.setText( response.body().getData().size()+"/"+ brojIgraca + " players");
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

        handler.removeCallbacks(thread);
        handler.postDelayed(thread, 0);
        Log.d("TAG", "onResume");

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getIntExtra("id", 0) == 0) {
                String action = intent.getAction();
                if (action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
                    Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
                    NdefMessage inNdefMessage = (NdefMessage) parcelables[0];
                    NdefRecord[] inNdefRecords = inNdefMessage.getRecords();
                    NdefRecord NdefRecord_0 = inNdefRecords[0];
                    String inMsg = new String(NdefRecord_0.getPayload());
                    //  teamMateovi.add(inMsg);
                    //  recyclerViewAdapter.setData(teamMateovi);
//                    textInfo.setText(inMsg);
                }
            }
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
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

        String stringOut = String.valueOf(CreateTeamActivity.TEAM_ID);
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
