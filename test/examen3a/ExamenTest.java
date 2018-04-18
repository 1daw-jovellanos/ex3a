package examen3a;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * The test class ExamenTest.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ExamenTest {

    static double nota;
    Ficus f1, f2;
    Maceta m1;
    
    static {
        nota = 0;
    }
    
    /**
     * Default constructor for test class ExamenTest
     */
    public ExamenTest() {
    }

    
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
         f1 = new Ficus(100);
         f2 = new Ficus(200);
         m1 = new Maceta(100 * 0.15, 10);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
    }

    @BeforeClass
    public static void before() {
        nota = 0;
    }
    @AfterClass
    public static void after() {
        System.out.format("TOTAL: %.2f%n", nota);
    }
    
    @Test
    public void Ejercicio1() {
        double notaLocal = 0;
        Examen examen1 = new Examen();
        List<IAlquilable> l = new ArrayList<>();
        examen1.miLista = l;

        l.add(new Maceta(11.1, 22.2));
        l.add(new Martillo(33.3, 44.4));
        l.add(new Jarron(55.5, 66.6));
        l.add(new Martillo(77.7, 88.8));

        String esperado = "Maceta 11.1 descuento:22.2\n"
                + "Martillo 33.3 peso:44.4\n"
                + "Jarron 55.5 descuento:66.6\n"
                + "Martillo 77.7 peso:88.8\n";
        String producida = examen1.listarTodo();
        double dleve = levenshtein(producida, esperado);

        

        assertEquals("La cadena producida es completamente distinta a lo esperado",
                dleve >= 100, false);
        

        assertEquals("La cadena producida es más de un 50% distinta a lo esperado",
                dleve > 50, false);
        

        assertEquals("La cadena producida es más de un 25% distinta a lo esperado",
                dleve > 25, false);
        
        // Puntua a partir de que el resultado falla en menos de un 25%
        notaLocal = 2.5 * ((100.0 - dleve)/ 100);
        System.out.format("Ejercicio 1: %.2f%n", notaLocal);
        nota += notaLocal;
        
        assertEquals("La cadena producida es más de un 10% distinta a lo esperado",
                dleve > 10, false);
        assertEquals("La cadena producida es más de un 5% distinta a lo esperado",
                dleve > 5, false);
        assertEquals("La cadena producida es casi casi casi lo esperado, pero no es correcta",
                dleve > 1, false);

    }

    @Test
    public void Ejercicio2() {
        double notaLocal = 0;
        Examen examen1 = new Examen();
        List<IAlquilable> l = new ArrayList<>();
        examen1.miLista = l;
        l.add(new Maceta(100, 100));
        l.add(new Martillo(200, 200));
        l.add(new Jarron(300, 300));
        assertEquals("Si no hay producto de más de 10000 en la lista se espera que el metodo devuelva false",
                false, examen1.hayProductoDeMasDe10000());
        l.add(new Maceta(10000, 300));
        assertEquals("Si no hay producto de más de 10000 en la lista se espera que el metodo devuelva false",
                false, examen1.hayProductoDeMasDe10000());
        l.add(new Jarron(10000.1, 3));
        l.add(new Maceta(10, 300));
        assertEquals("Hay  un producto de más de 10000 en la lista se espera que el metodo devuelva true", true, examen1.hayProductoDeMasDe10000());
        notaLocal = 1.0;
        System.out.format("Ejercicio 2: %.2f%n", notaLocal);
        nota += notaLocal;
    }

    @Test
    public void Ejercicio3a() {
        double notaLocal = 0;

        assertEquals("El descuento esperado no coincide ", 10.0, f1.getDescuento(), 0.001);
        assertEquals("El descuento esperado no coincide ", 10.0, f2.getDescuento(), 0.001);
        notaLocal = 0.5;
        System.out.format("Ejercicio 3 (getDescuento): %.2f%n", notaLocal);
        nota += notaLocal;
  
    }
    
     @Test
    public void Ejercicio3b() {
        double notaLocal = 0;

        assertEquals("El precio esperado no coincide ", 100 * 0.15 * 0.9, f1.getPrecioMes(), 0.001);
        assertEquals("El precio esperado no coincide ", 200 * 0.15 * 0.9, f2.getPrecioMes(), 0.001);
       notaLocal = 0.5;
        System.out.format("Ejercicio 3 (getPrecioMes): %.2f%n", notaLocal);
        nota += notaLocal;
    }
    
     @Test
    public void Ejercicio3c() {
        double notaLocal = 0;

        assertEquals("Una maceta y un ficus no pueden ser iguales", false, f1.equals(m1));
        assertEquals("Dos ficus con distinta altura no pueden ser iguales", false, f1.equals(f2));
        assertEquals("Un ficus debe ser igual a sí mismo", true, f1.equals(f1));
        assertEquals("Un ficus debe ser igual a otro ficus de la misma altura", true, f1.equals(new Ficus(100)));
        notaLocal = 0.5;
        System.out.format("Ejercicio 3 (equals): %.2f%n", notaLocal);
        nota += notaLocal;
    }

    public static double levenshtein(String s1, String s2) {
        if (s1 == null) {
            return 100;
        }
        int coste = 0;
        int n1 = s1.length();
        int n2 = s2.length();
        int[][] m = new int[n1 + 1][n2 + 1];
        for (int i = 0; i <= n1; i++) {
            m[i][0] = i;
        }
        for (int i = 1; i <= n2; i++) {
            m[0][i] = i;
        }
        for (int i1 = 1; i1 <= n1; i1++) {
            for (int i2 = 1; i2 <= n2; i2++) {
                coste = (s1.charAt(i1 - 1) == s2.charAt(i2 - 1)) ? 0 : 1;
                m[i1][i2] = Math.min(
                        Math.min(
                                m[i1 - 1][i2] + 1,
                                m[i1][i2 - 1] + 1
                        ),
                        m[i1 - 1][i2 - 1] + coste
                );
            }
        }
        return (m[n1][n2] * 100.0) / s1.length();
    }
}
