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
