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
