package android.netcoders.br.lembrete;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jeanj on 07/08/2016.
 *
 * Um ListView necessita de um adapter
 * para que seja criadas as linhas na tela
 */
public class LembreteAdapter
        extends ArrayAdapter<Lembrete> {

    //Classe responsavel por gerar interfaces
    //no caso usamos para criar nossa linha
    private LayoutInflater inflater;

    public LembreteAdapter(Context context,
                   ArrayList<Lembrete> lembretes){

        //setamos o layout que deve ser utilizado e o array de itens
        super(context, R.layout.lembrete_linha, lembretes);

        //Reponsavel por gerar nossa linha/interface
        inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //Toda vez que uma linha e gerada o ListView utiliza o getView para
    //exibir as linhas
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView e a nossa linha ja criada, porem se for primeira vez
        //estara como null e entao devemos renderizar a interface
        if(convertView == null){
            convertView = inflater.inflate(R.layout.lembrete_linha, null);
        }

        //capturamos o Lembre do array
        Lembrete lembrete = (Lembrete) getItem(position);

        //Capturamos o textViewTitulo da tela
        TextView textViewTitulo = (TextView)
                convertView.findViewById(R.id.textViewTitulo);

        //setamos o titulo no textViewTitulo
        textViewTitulo.setText(lembrete.getTitulo());

        //retornamos a linha
        return convertView;
    }
}
