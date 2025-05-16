package aoi.com.creditos_api.singleton;
import org.springframework.stereotype.Component;

@Component
public class AuditoriaSingleton {

    private static AuditoriaSingleton instance;
    private int totalConsultas;

    private AuditoriaSingleton() {
        this.totalConsultas = 0;
    }

    public static synchronized AuditoriaSingleton getInstance() {
        if (instance == null) {
            instance = new AuditoriaSingleton();
        }
        return instance;
    }

    public void registrarConsulta(String tipo, String valor) {
        totalConsultas++;
        System.out.println("Auditoria - Consulta #" + totalConsultas +
                ": Tipo=" + tipo + ", Valor=" + valor);
    }

    public int getTotalConsultas() {
        return totalConsultas;
    }
}

