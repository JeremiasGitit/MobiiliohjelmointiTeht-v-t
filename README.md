VIIKKO 3:

Compose tilanhallinta:

Tila on arvo, joka voi muuttua sovellusta käyttäessä. Composessa se mitä käyttöliittymä näyttää määrittyy tilan mukaan. Tilan muuttuessa Compose automaattisesti päivittää
käyttöliittymän vastamaan uutta tilaa (rekompositio)

Miksi ViewModel on parempi kuin pelkkä remember?

ViewModel on parempi, koska se säilyttää tilan konfiguraatiomuutosten yli, kuten esimerkiksi ruudun käännöksen, toisin kuin remember-funktio. Tällöin toimintalogiikka on
erotettu käyttöliittymästä.

MVVM on suunnittelumalli, joka jakaa sovelluksen kolmeen vastuualueeseen:
View: UI ja käyttäjän vuorovaikutus
ViewModel: UI-logiikka ja tilan hallinta
Model: 	Liiketoimintalogiikka ja data
Tämä helpottaa testattavuutta ja koodin luettavuutta

Stateflow on tapa hallita tilaa, joka pitää aina viimeisimmän tilan arvon ja sen muuttuessa automaattisesti päivittää sen kuuntelijoille.
Sillä on monimutkaisempi logiikka kuin MutableStatella, mutta enemmän ominaisuuksia.

VIIKKO 5:

Retrofit:
Hoitaa HTTP-pyyntöjen lähetyksen ja vastauksen vastaanoton. Sillä on automaattinen JSON-jäsennys, sekä tyyppiturvallisuus eli virheet havaitaan käännösaikana.

Miten JSON muutetaan dataluokiksi:
Gson muuntaa JSON-vastaukset automaattisesti Kotlin-dataluokiksi. JSON jäsenny tapahtuu data-class rakenteiden avulla

Coroutines: 
API-kutsut tehdään taustasäikeessä, UI päivittyy datan saapuessa.

UI-tila: 
ViewModel hallitsee WeatherUiState-olion. Compose reagoi tilamuutoksiin automaattisesti.

Miten API-key on tallennettu:
local.properties tiedosto sisältää API-keyn, josta se tuodaan koodiin build.gradle.kts tiedoston luoman BuildConfig-vakion kautta. Näin avain ei päädy GitHubiin

VIIKKO 6:

Mitä room tekee?

Tyypillisesti androidilla dataa tallennetaan pysyvästi laitteelle SQLite-tietokanna avulla. Room on Googlen kehittämä abstraktiokerros SQLiten päälle, joka koostuu:

Entity: malliluokka, joka luo SQLite-taulun. Jokainen Entity-luokka edustaa yhtä taulua.

DAO (Data Access Object): rajapinta, joka määrittelee tietokantaoperaatiot (insert, update, delete, query). Room automaattisesti generoi toteutuksen käännösaikana. Maahdollistaa sen, että SQL:ää ei tarvitse kirjoittaa käsin, paitsi @Query-annotaatiossa.

Database: Luo tietokantayhteyden. Kertoo Room:ille mitä tauluja (entities) tietokanta sisältää. Database-luokka on abstrakti luokka, joka tarjoaa pääsyn DAO:ihin. Toteutetaan käytännössä singleton-patternilla.

Repository: välikerros ViewModel:in ja tietokannan (DAO:n) välillä, joka tarjoaa ViewModelille puhtaan API:n tietokantatoimintoihin. Mahdollistaa sen, kun tietokanta muuttuu, ei tarvitse erikseen hakea dataa uudelleen

ViewModel: Muuttaa Flow:n StateFlowiksi. Luo Task-olion ja kutsuu repository.insert(task) korutiinissa. ViewModel yhdistää UI:n ja Repository:n ja säilyttää datan näkymän vaihtojen yli (esim. näytön kääntö)

UI: Compose-näkymät (TaskListScreen) lukevat ViewModelin datan ja näyttävät sen. Päivityy automaattisesti datan muuttuessa.

Projektin rakenne:

Projektissa käytetään MVVM-kansiorakennetta. 
Local:issa on entity, DAO ja app_database.kt.
View:issa compose-näkymät ja niiden komponentit
ViewModelissa TaskViewModel
Repositorylle on oma kansio

Datan kulku:
1	Käyttäjä painaa "Lisää" nappia, jolloin UI kutsuu viewModel.addTask(title, description)
2	ViewModel	Luo Task-olion ja kutsuu repository.insert(task) korutiinissa
3	Repository  Välittää kutsun DAO:lle taskDao.insert(task)
4	DAO 	Room generoi SQL:n INSERT INTO tasks VALUES(...) ja suorittaa sen
5	SQLite	Data tallentuu .db-tiedostoon laitteen muistiin
6	Flow  DAO:n Flow huomaa muutoksen ja lähettää päivitetyn listan automaattisesti
7	UI  collectAsState() vastaanottaa uuden listan → Compose piirtää näkymän uudelleen
