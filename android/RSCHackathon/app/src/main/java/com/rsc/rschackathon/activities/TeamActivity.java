package com.rsc.rschackathon.activities;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.adapters.TeamListAdapter;
import com.rsc.rschackathon.database.Book;

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
        implements NfcAdapter.CreateNdefMessageCallback, NfcAdapter.OnNdefPushCompleteCallback, TeamListAdapter.Listener {


    TextView textInfo;

    EditText textOut;

    TeamListAdapter recyclerViewAdapter;

    private static List<String> teamMateovi;

    LinearLayoutManager linearLayoutManager;

    NfcAdapter nfcAdapter;

    @BindView(R.id.team_list)
    RecyclerView teamList;

    public static Intent createIntent(final Context context) {
        return new Intent(context, TeamActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        ButterKnife.bind(this);


        if (teamMateovi== null) {
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
            nfcAdapter.setOnNdefPushCompleteCallback(this, this);
        }

        recyclerViewAdapter = new TeamListAdapter();
        linearLayoutManager = new LinearLayoutManager(this);

        teamList.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter.setListener(this);
        teamList.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void getDeviceAtPosition(int position) {
        if (position == 1) {
            Book book = new Book("51", "TINO", "4");
            book.save();

        } else if (position == 2) {
            Book book = Book.findById(Book.class, 6);
            Toast.makeText(this, book.getTitle(), Toast.LENGTH_SHORT).show();
            //List<Note> notes = Note.findWithQuery(Note.class, "Select * from Note where name = ?", "satya");

        } else if (position == 3) {
            Book book = Book.findById(Book.class, 6);
            book.setTitle("updated title heresad");
            book.setEdition("3rd edition");
            book.save();
        } else if (position == 4) {
            Book book = Book.findById(Book.class, 6);
            book.delete();
        } else if (position == 5) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String action = intent.getAction();
        if (action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
            Parcelable[] parcelables =
                    intent.getParcelableArrayExtra(
                            NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage inNdefMessage = (NdefMessage) parcelables[0];
            NdefRecord[] inNdefRecords = inNdefMessage.getRecords();
            NdefRecord NdefRecord_0 = inNdefRecords[0];
            String inMsg = new String(NdefRecord_0.getPayload());
            teamMateovi.add(inMsg);
            recyclerViewAdapter.setData(teamMateovi);
            textInfo.setText(inMsg);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
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
