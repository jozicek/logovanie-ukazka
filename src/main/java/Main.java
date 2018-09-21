import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {

    //Logovanie znamena ze zapisujes spravy pocas behu programu o programe samotnom do logu.
    //a uchovavas tak historiu toho co sa v programe dialo, vacsinou pre pripad, ked nastane nejaka chybova udalost
    //Log moze byt konzola, subor, pripadne sa logovacie spravy mozu posielat na nejaky server ktory ich potom zbiera a uklada do databazy atd atd...
    //Najcastejsie budes ale logovat do konzoly a suboru.

    //Logovanie ma viacero urovni. Od najnizsej po najzavaznejsiu: TRACE, DEBUG, INFO, WARN, ERROR
    //Uroven nastavis v subore logback.xml : <root level="DEBUG">
    //teraz je nastaveny DEBUG level co znamena ze sa zaloguju vsetky zpravy s urovnou DEBUG a vyssie, teda: DEBUG, INFO, WARN, ERROR

    //takze toto volanie sa zaloguje:
    LOGGER.info("Nazdar Longi");

    //ale toto nie:
    LOGGER.trace("Toto sa nezaloguje lebo je to nizzsi level ako DEBUG");
    //takto jednoducho vlozis do logu premennu:
    String den = "Nedela";
    LOGGER.debug("Dneska je {}", den);

    //alebo viacero premennych:
    String mesiac = "Oktober";
    LOGGER.debug("Den: {}, Mesiac: {}", den, mesiac);

    //Format zpravy v logu je definovany v logback.xml
    //<pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
    //vidis ze za datumom sa zobrazi aj vlakno v ktorom kod bezi pre tvoje multi vlaknove aplikacie :)

    //logback.xml je nakonfigurovany tak aby zapisoval nie len do konzoly ale aj do suboru logovanie-ukazka.log.
    //nastavenie je take ze ak subor dosiahne 10MB tak sa vytvori novy a stary sa ulozi do zlozky old

    //nasleduje ilustrativny priklad ako zalogovat exceptionu:

    int a = 10;
    int b = 0;

    try{
      int vysledok = a / b;
      LOGGER.info("{} / {} = {}", a, b, vysledok);
    }catch (ArithmeticException e){
      LOGGER.warn("Neda sa delit {} / {}", a, b, e); // do LOGGER.warn a error mozem dat ako posledny argument aj exception ktora nastala (to e) a on mi ju zaloguje.
    }

    //Skus napriklad prepnut level na <root level="WARN"> a jedine co sa bude logovat je to delenie nulou.
    //Skus zmenit b na nieco rozne od 0 a uvidis info spravu v logu.

    //A malicke vysvetlenie kedy ktory level kde pouzit v kode.
    //ERROR - chyba v programe ktoru nezapricinil uzivatel, napriklad ked sa nepodari pripojit k databaze.
    //WARN - napriklad chybny vstup od uzivatela alebo nejake ine varovanie ohladom behu programu ze neni to este chybovy stav ale treba davat pozor
    //INFO - normalny beh programu, nejake klucove udalosti
    //DEBUG - podrobnejsie logovanie ktore obvykle pomaha pri vyvoji a odladovani programu
    //TRACE - velmi podrobne logovanie typu ked uz fakt clovek nevie co do pekla sa deje :)
  }

}
