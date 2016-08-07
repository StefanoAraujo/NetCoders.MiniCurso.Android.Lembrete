package android.netcoders.br.lembrete;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by jeanj on 07/08/2016.
 */
public class LembreteDomain {
    //Contexto da aplicacao
    private Context context;
    //Armazenamento de preferencias da aplicacao
    private SharedPreferences preferences;
    //Classe responsavel por tratar objetos Json
    private Gson gson;

    private static String preferenceLista = "lembretes";
    private static String nomeLista = "lista";

    public  LembreteDomain(Context context){
        this.context = context;

        gson = new Gson();

        //Carregamos as preferencias de nome "lembretes"
        //em modo privado, para que so essa app consiga acessar
        preferences = context
                .getSharedPreferences(preferenceLista,
                        Context.MODE_PRIVATE);

    }

    //retornamos a lista de lembretes
    public ArrayList<Lembrete> get(){
        //obtemos o json salvo em preferences com o nome "lista"
        String json = preferences.
                getString(nomeLista, null);

        //se nao foi salvo ainda, retornamos uma nova lista vazia
        if(json == null){
            return new ArrayList<>();
        }

        //Criamos o tipo ArrayList<Lembrete>
        //para que o Gson consiga criar nossa colecao
        //que estava em json
        Type type = new TypeToken
                <ArrayList<Lembrete>>(){}
                .getType();

        //convertemos para array e retornamos
        return  gson.fromJson(json, type);
    }

    public void salvar(Lembrete lembrete){
        ArrayList<Lembrete> lista = get();
        lista.add(lembrete);

        //apos adicionar o lembrete  a lista
        //convertemos a lista para json e
        //salvamos com o editor
        SharedPreferences.Editor editor =
                preferences.edit();
        String json = gson.toJson(lista);

        editor.putString(nomeLista, json);
        //commit para que as alteracoes sejam salvas
        editor.commit();
    }

    public void atualizar(int posicao, Lembrete lembrete){
        ArrayList<Lembrete> lista = get();
        //atraves da posicao passada por parametro, substituimos
        //o item no array
        lista.set(posicao, lembrete);

        //apos adicionar atualizar a lista
        //convertemos a lista para json e
        //salvamos com o editor
        SharedPreferences.Editor editor =
                preferences.edit();
        String json = gson.toJson(lista);

        editor.putString(nomeLista, json);
        //commit para que as alteracoes sejam salvas
        editor.commit();
    }

    public void limpar(){
        ArrayList<Lembrete> lista = new ArrayList<>();

        //Apos criada uma lista vazia, atraves do editor
        //salvamos a lista em json para substituir a que existia antes
        SharedPreferences.Editor editor =
                preferences.edit();
        String json = gson.toJson(lista);

        editor.putString(nomeLista, json);
        editor.commit();
    }
}
