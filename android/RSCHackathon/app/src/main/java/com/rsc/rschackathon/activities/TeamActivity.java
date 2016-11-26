package com.rsc.rschackathon.activities;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.adapters.TeamListAdapter;

import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamActivity extends AppCompatActivity
        implements NfcAdapter.CreateNdefMessageCallback, TeamListAdapter.Listener {


    TextView textInfo;

    EditText textOut;

    TeamListAdapter recyclerViewAdapter;

    private static List<String> teamMateovi;

    LinearLayoutManager linearLayoutManager;

    NfcAdapter nfcAdapter;

    @BindView(R.id.team_list)
    RecyclerView teamList;

    public static Intent createIntent(final Context context) {
        return new Intent(context, TeamActivity.class).putExtra("id", 100);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        ButterKnife.bind(this);

        if (teamMateovi == null) {
            teamMateovi = new ArrayList<>();
            Log.i("TAG", "ONCREATE");
        }
        textInfo = (TextView) findViewById(R.id.info);
        textOut = (EditText) findViewById(R.id.textout);
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
        }

        recyclerViewAdapter = new TeamListAdapter();
        linearLayoutManager = new LinearLayoutManager(this);

        teamList.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter.setListener(this);
        teamList.setAdapter(recyclerViewAdapter);
    }


    @Override
    public void getDeviceAtPosition(int position) {

    }

    @Override
    protected void onResume() {
        super.onResume();

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
                    teamMateovi.add(inMsg);
                    recyclerViewAdapter.setData(teamMateovi);
                    textInfo.setText(inMsg);
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

        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.disableForegroundDispatch(this);
    }


    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {

        String stringOut = textOut.getText().toString();
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
