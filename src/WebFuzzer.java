import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

public class WebFuzzer {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso incorrecto.");
            System.out.println("Uso: java WebFuzzer <URL> <ARCHIVO_DICCIONARIO>");
            System.out.println("Ejemplo: java GemiFuzzer http://127.0.0.1/ mi_lista.txt");
            return;
        }

        String urlBase = args[0].endsWith("/") ? args[0] : args[0] + "/";
        Path rutaDiccionario = Path.of(args[1]);

        if (!Files.exists(rutaDiccionario)) {
            System.err.println(" Error: El archivo '" + args[1] + "' no existe.");
            return;
        }

        long inicio = System.currentTimeMillis();
        System.out.println("──────────────────────────────────────────");
        System.out.println("WEB-FUZZER v1.0 | Auditoría Web");
        System.out.println("Objetivo: " + urlBase);
        System.out.println("Diccionario: " + rutaDiccionario.toAbsolutePath());
        System.out.println("──────────────────────────────────────────");

        Semaphore semaphore = new Semaphore(50);

        System.out.println("\uD83D\uDD0D Escaneando: " + urlBase);

        try (var executor = Executors.newVirtualThreadPerTaskExecutor();
             Stream<String> lineas = Files.lines(rutaDiccionario)) {

            lineas.filter(l -> !l.isBlank() && !l.startsWith("#"))
                    .forEach(word -> {
                        executor.execute(() -> {
                            try {
                                semaphore.acquire();
                                new FuzzingTask(urlBase + word.trim()).run();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            } finally {
                                semaphore.release();
                            }
                        });
                    });
        }catch (Exception e){
            System.err.println("Error al leer el diccionario: " + e.getMessage());
        }
        long fin = System.currentTimeMillis();
        double segundos = (fin - inicio) / 1000.0;
        System.out.println("Escaneo finalizado en: " + segundos + " segundos.");
    }
}
