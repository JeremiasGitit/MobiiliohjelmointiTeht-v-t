Compose tilanhallinta:
Tila on arvo, joka voi muuttua sovellusta käyttäessä. Composessa se mitä käyttöliittymä näyttää määrittyy tilan mukaan. Tilan muuttuessa Compose automaattisesti päivittää
käyttöliittymän vastamaan uutta tilaa (rekompositio)

Miksi ViewModel on parempi kuin pelkkä remember?
ViewModel on parempi, koska se säilyttää tilan konfiguraatiomuutosten yli, kuten esimerkiksi ruudun käännöksen, toisin kuin remember-funktio. Tällöin toimintalogiikka on
erotettu käyttöliittymästä.
