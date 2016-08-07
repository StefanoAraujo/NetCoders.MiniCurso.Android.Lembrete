package android.netcoders.br.lembrete;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by jeanj on 07/08/2016.
 *
 * Dialog que usamos para salvar e atualizar
 * um Lembrete
 */
public class LembreteDialog extends Dialog {
    private LembreteDomain domain;

    //Quando for para atualizar, posicao sera diferente de -1
    private int posicao;

    //Contrustor chamado quando for um novo lembrete
    public LembreteDialog(Context context){
        super(context);
        posicao = -1;
        carregar(context, null);

    }

    //Construtor chamado quando for a edição de lembre
    public LembreteDialog(Context context, int posicao, Lembrete lembrete){
        super(context);

       //setamos a posicao do Lembrete no array para posterior atualizacao
        this.posicao = posicao;
        carregar(context, lembrete);
    }

    private void carregar(Context context, Lembrete lembrete){
        //informamos qual tela deve ser utilizada
        setContentView(R.layout.dialog_lembrete);

        //criamos uma instancia da LembreteDomain
        domain = new LembreteDomain(context);

        //Capturamos o botao de salvar da tela e configuramos o evento
        //de click dele
        Button buttonSalvar = (Button) findViewById(R.id.buttonSalvar);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        //Se o lembrete for diferente de null
        //carregamos a tela com os dados
        //capturando os campos da tela e setando os textos
        if(lembrete != null){
            EditText editTextTitulo = (EditText)
                    findViewById(R.id.editTextTitulo);

            EditText editTextQuando = (EditText)
                    findViewById(R.id.editTextQuando);

            EditText editTextObs = (EditText)
                    findViewById(R.id.editTextObservacao);

            editTextTitulo.setText(lembrete.getTitulo());
            editTextQuando.setText(lembrete.getQuando());
            editTextObs.setText(lembrete.getObs());
        }

        //exibimos o dialog
        show();
    }

    private void salvar(){
        EditText editTextTitulo = (EditText)
                findViewById(R.id.editTextTitulo);

        EditText editTextQuando = (EditText)
                findViewById(R.id.editTextQuando);

        EditText editTextObs = (EditText)
                findViewById(R.id.editTextObservacao);

        //usamos o setError para informar que ha um erro no campo
        //para o usuario
        if(editTextTitulo.getText().toString().isEmpty()){
            editTextTitulo
                    .setError("O que vc quer lembrar?");

            editTextTitulo.requestFocus();
            return;
        }

        if(editTextQuando.getText().toString().isEmpty()){
            editTextQuando.setError("Quando?");
            editTextQuando.requestFocus();
            return;
        }

        //criamos um novo lembrete e preenchemos com os dados da tela
        Lembrete lembrete = new Lembrete();
        lembrete.setTitulo
                (editTextTitulo.getText().toString());

        lembrete.setQuando
                (editTextQuando.getText().toString());

        lembrete.setObs
                (editTextObs.getText().toString());

        //se a posicao for diferente de -1 significa que trata-se
        //de uma atualizacao
        if(posicao != -1){
            domain.atualizar(posicao, lembrete);
        }else {
            domain.salvar(lembrete);
        }

        //fechar dialog
        dismiss();
    }















}
