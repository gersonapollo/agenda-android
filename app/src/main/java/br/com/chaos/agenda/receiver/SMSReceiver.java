package br.com.chaos.agenda.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.io.Serializable;

import br.com.chaos.agenda.R;
import br.com.chaos.agenda.dao.AlunoDao;

/**
 * Created by gersonapollo on 15/06/17.
 */

public class SMSReceiver extends BroadcastReceiver{
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[]) pdus[0];
        String format = (String) intent.getSerializableExtra("format");

        SmsMessage sms = SmsMessage.createFromPdu(pdu, format);
        String telefone = sms.getDisplayOriginatingAddress();

        AlunoDao dao = new AlunoDao(context);
        if(dao.isAluno(telefone)){
            Toast.makeText(context, "Mensagem de Aluno Recebida!", Toast.LENGTH_SHORT).show();
            MediaPlayer media = MediaPlayer.create(context, R.raw.msg);
            media.start();
        }

    }
}
