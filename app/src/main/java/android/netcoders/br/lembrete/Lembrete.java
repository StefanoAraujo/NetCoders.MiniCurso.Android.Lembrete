package android.netcoders.br.lembrete;

/**
 * Created by jeanj on 07/08/2016.
 *
 * Classe que representa um lembrete
 */
public class Lembrete {
    private String titulo;
    private String quando;
    private String obs;

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setQuando(String quando){
        this.quando = quando;
    }

    public void setObs(String obs){
        this.obs = obs;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getQuando(){
        return quando;
    }

    public  String getObs(){
        return obs;
    }
}
