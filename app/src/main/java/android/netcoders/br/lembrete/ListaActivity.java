package android.netcoders.br.lembrete;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListaActivity extends AppCompatActivity {
    private LembreteDomain domain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //tela que deve ser carregada
        setContentView(R.layout.activity_lista);
        //Configurando a toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        domain = new LembreteDomain(this);

        //Setamos a acao para quando for clicando no botao de adicionar
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //quando clicar em adionar deve ser aberto o dialog
                //para ser criado um novo lembrete e adicionado a lista
                abrirDialog(-1, null);
            }
        });

        //carregamos a lista na tela
        carregar();
    }

    //se precisar for tratado algo no menu existente no topo da app
    //deve ser realizado aqui
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
    }

    //criamos uma serie de ifs para cada botao do menu
    //para criar as acoes para quando for clicado
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //quando clicar no botao limpar
        //limpa a lista e recarrega a lista na tela
        if (id == R.id.action_settings) {
            domain.limpar();
            carregar();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //carregamos a lista na tela
    private void carregar(){
        //Obtemos a listView presente na tela
        ListView listView = (ListView)
                findViewById(R.id.listViewLembretes);

        //criamos uma instancia do adapter passando o context e a lista
        LembreteAdapter adapter = new LembreteAdapter(this, domain.get());

        //setamos o adapter no listview, para que o listview
        //use ele para carregar e exibir os lembretes na tela
        listView.setAdapter(adapter);

        //setamos o click de cada linha, para que
        //abra o dialog em modo de alteracao
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //adapter, linha, posicao, id
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Obtemos o lembrete da posicao i do adapter
                Lembrete lembrete = (Lembrete) adapterView.getItemAtPosition(i);
                //passamos para o abrirDialog a posicao e o lembrete
                abrirDialog(i, lembrete);
            }
        });
    }

    //responsavel por abrir o dialog em modo novo e alteracao
    private void abrirDialog(int posicao, Lembrete lembrete){
        Dialog dialog;

        if(lembrete != null){
            //criamos o dialog em modo alteracao
            dialog = new LembreteDialog(this, posicao, lembrete);
        }else {
            //criamos o dialog em modo de inclusao
            dialog = new LembreteDialog(this);
        }

        //criamos o evento para que seja recarregada a lista na tela
        //quando o dialog for fechado
        //porque normalmente sera clicado no botao salvar
        //para incluir ou alterar um lembre
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                carregar();
            }
        });
    }
}














